package fr.laposte.gdrlogs.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.cache.Cache;

import fr.laposte.gdrlogs.cache.CacheManager;
import fr.laposte.gdrlogs.domain.LogFile;
import fr.laposte.gdrlogs.persistance.dao.SysLogDao;
import fr.laposte.gdrlogs.persistance.model.StackTraceLog;
import fr.laposte.gdrlogs.persistance.model.SysOutLog;

public class SystemLogProcessor implements ItemProcessor<LogFile, SysOutLog> {

	/**
	 * Cache Manager
	 */
	@Inject
	CacheManager cacheManager;
	
	/**
	 * Repository des logs systèmes
	 */
	@Inject
	SysLogDao sysLogDao;
	
	/**
	 * La dernière date d'apparition traitée
	 */
	private Date lastDateApparition = null;
	
	/**
	 * Indicateur si le traitement doit réaliser un full scan
	 */
	@Value("${log.fullscan}")
	private boolean logFullScan;
	
	/**
	 * Indicateur de lecture des logs
	 */
	private boolean isLectureAutorised;
	
	/**
	 * Liste des dates d'apparition déjà traitées
	 */
	List<Date> listDateApparitionDuJour = new ArrayList<Date>();
	
	
	/**
	 * Méthode d'initialisation du bean après injection des dépendances
	 */
	public void postConstruct() {
		
		//initialisation de l'indicateur de lecture des logs
		isLectureAutorised = true;
		
		//Option Full scan désactivée, on récupère les logs du jours déjà traitées
		if (!logFullScan) {
			//Calendrier pour récupérer la date du jour
			Calendar calendrier = Calendar.getInstance();
			calendrier.set(Calendar.HOUR, 0);
			calendrier.set(Calendar.MINUTE, 0);
			calendrier.set(Calendar.SECOND, 0);
			calendrier.set(Calendar.MILLISECOND, 0);

			//Récupération des logs du jours déjà traités
			Collection<SysOutLog> listLogsDuJour = sysLogDao.findByDateApparitionAfter(calendrier.getTime());
			
			for (SysOutLog sysOutLog : listLogsDuJour) {
				listDateApparitionDuJour.add(sysOutLog.getDateApparition());
			}
		}
	}
	
	
	/**
	 * Méthode qui permet de traiter les données lues
	 */
	public SysOutLog process(LogFile item) throws Exception {
		
		//Récupération du message de la log et de la date d'apparition
		String messageLog = item.getMessageLog();
		Date dateApparition = recupererDateApparition(messageLog);
		
		//Si l'option full scan est activée, on parcours tout le fichier
		if (!logFullScan) { verifierLectureAutorisee(dateApparition); }
		
		//Si la lecture est autorisée, on traite la ligne 
		if (!isLectureAutorised) { return null; }
		
		//Si la ligne n'est pas une trace d'erreur, alors elle n'est pas traitée
		if (!verifierLigneErreurSys(messageLog)) { return null; }
		
		//Résultat, la log transformée
		SysOutLog sysOutLog = traitementSysLog(dateApparition, messageLog);
		
		return sysOutLog;
	}
	
	
	/**
	 * Cette méthode permet de récupérer la date d'apparition de la log
	 * 
	 * @param messageLog La log
	 * @return la date d'apparition
	 */
	private Date recupererDateApparition (String messageLog) {
		Date dateApparition = null;
		
		if (messageLog.startsWith("[")) {
			
			//Récupération de type de l'erreur
			String[] splitMessageLog = messageLog.split(" ");
			
			String dateStr = null;
			if (splitMessageLog.length >= 2) {
				dateStr = splitMessageLog[0].substring(1) + " " + splitMessageLog[1];
			} else {
				return null;
			}
			
			SimpleDateFormat formatDate = new SimpleDateFormat("MM/dd/yy HH:mm:ss:SSS");
			
			try {
				dateApparition = formatDate.parse(dateStr);
			} catch (ParseException e) {
				dateApparition = null;
			}
		}
		
		return dateApparition;
	}

	
	/**
	 * Cette méthode permet de vérifier si la lecture des logs est autorisée
	 * 
	 * @param dateApparition date apparition de la log en cours de traitement
	 */
	private void verifierLectureAutorisee (Date dateApparition) {
		
		if (dateApparition != null) {
			
			//Récupération de la date du jour
			Calendar calendrier = Calendar.getInstance();
			calendrier.set(Calendar.HOUR, 0);
			calendrier.set(Calendar.MINUTE, 0);
			calendrier.set(Calendar.SECOND, 0);
			calendrier.set(Calendar.MILLISECOND, 0);

			if (dateApparition.compareTo(calendrier.getTime()) > 0 && !listDateApparitionDuJour.contains(dateApparition)) {
				isLectureAutorised = true;
			} else {
				isLectureAutorised = false;
			}
		}
	}
	
	
	/**
	 * Cette méthode permet de vérifier si la ligne correspond à une erreur tracée
	 * 
	 * @param messageLog la log
	 * @return indicateur true si la log doit être prise en compte
	 */
	private boolean verifierLigneErreurSys (String messageLog) {
		
		//Indicateur permet de savoir si la log fait partie d'une exception
		boolean indException = false;
		
		//Split de la ligne
		String[] splitMessageLog = messageLog.split(" ");
		
		//La ligne traitée ne fait pas partie d'une stacktrace
		if (!messageLog.startsWith("at")) {
			for (String splitMesg : splitMessageLog) {
				if ("E".equals(splitMesg)) {
					indException = true;
					break;
				}
			}
		} else {
			indException = true;
		}
		return indException;
	}

	
	/**
	 * Cette methode permet de transformer la log lu dans le fichier en une log à persister en Base de données
	 * 
	 * @param dateApparition date apparition de la log
	 * @param messageLog le message de la log
	 * @return la log transformée
	 */
	private SysOutLog traitementSysLog(Date dateApparition, String messageLog) {
		
		//Résultat, la log transformée
		SysOutLog sysOutLog = null;
		
		//Récupération du cache
		Cache<Date, SysOutLog> cacheSysOutLog = cacheManager.getCacheSysOutLog();
		
		//Date apparition non null alors début d'une stack trace
		if (dateApparition != null) {
			
			//Création du bean SysOutLog
			sysOutLog = new SysOutLog();
			sysOutLog.setDateApparition(dateApparition);
			sysOutLog.setMessageLog(messageLog);
			lastDateApparition = dateApparition;
			cacheSysOutLog.put(dateApparition, sysOutLog);
			
		} else {
			
			//Date apparition null alors ligne d'une stack trace commencant par at
			//Récupération du début de la log
			sysOutLog = cacheSysOutLog.getIfPresent(lastDateApparition);
			
			//Log non présente dans le cache, alors chargement de la base de données
			if (sysOutLog == null) {
				sysOutLog = sysLogDao.findByDateApparition(lastDateApparition);
			}
			
			
			//Log trouvée, ajout de la suite de la stack trace
			if (sysOutLog != null) {
				StackTraceLog stackTrace = new StackTraceLog();
				stackTrace.setMessageLog(messageLog);
				sysOutLog.getStacktrace().add(stackTrace);
				if (sysOutLog.getComplementLog() == null) {
					sysOutLog.setComplementLog(messageLog);
				}
				cacheSysOutLog.put(lastDateApparition, sysOutLog);
			}
		}
		
		return sysOutLog;
	}

}
