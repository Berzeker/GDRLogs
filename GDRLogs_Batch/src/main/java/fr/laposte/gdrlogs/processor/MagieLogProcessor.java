package fr.laposte.gdrlogs.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import fr.laposte.gdrlogs.domain.LogFile;
import fr.laposte.gdrlogs.persistance.dao.MagieLogDao;
import fr.laposte.gdrlogs.persistance.model.MagieLog;


public class MagieLogProcessor implements ItemProcessor<LogFile, MagieLog> {
	
	/**
	 * Accès DAO Log
	 */
	@Inject
	private MagieLogDao logRepository;
	
	@Value("${log.fullscan}")
	private boolean logFullScan;
	
	/**
	 * Indicateur de lecture de la log
	 */
	private boolean isLectureAutorised;

	/**
	 * Les logs déjà lues pendant la journée
	 */
	private Map<Date, MagieLog> mapLogsDuJour;

	
	/**
	 * Methode d'initialisation après construction du bean
	 */
	public void postConstruct() {
		
		Calendar calendrier2 = Calendar.getInstance();
		calendrier2.set(Calendar.HOUR, 0);
		calendrier2.set(Calendar.MINUTE, 0);
		calendrier2.set(Calendar.SECOND, 0);
		calendrier2.set(Calendar.MILLISECOND, 0);
		
		isLectureAutorised = true;
		
		Collection<MagieLog> listLogsDuJour = logRepository.findByDateApparitionAfter(calendrier2.getTime());
		
		mapLogsDuJour = new HashMap<Date, MagieLog>();
		for (MagieLog log : listLogsDuJour) {
			mapLogsDuJour.put(log.getDateApparition(), log);
		}
	}
	
	
	/**
	 * Methode de processing
	 */
	public MagieLog process(LogFile logFile) throws Exception {
		
		
		//Vérification si la log n'a pas déjà été lue
		//Lors d'un full scan des fichiers, cette vérification n'est pas réalisée
		if (!logFullScan) { verifierLectureAutorisee(logFile); }
		
		//Si lecture non autorisée, la log n'est pas persistée
		if (!isLectureAutorised) { return null; }
		
		//Traitement de la log
		MagieLog log = new MagieLog();
		
		//Récupération de la date de la log lue dans le fichier des traces
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
		Date dateApparition = null;
		try {
			dateApparition = format.parse(logFile.getDateApparition());
		} catch (ParseException e) {	
			//Récupération de la date du jour
			Calendar calendrier = Calendar.getInstance();
			calendrier.set(Calendar.HOUR, 0);
			calendrier.set(Calendar.MINUTE, 0);
			calendrier.set(Calendar.SECOND, 0);
			calendrier.set(Calendar.MILLISECOND, 0);
			dateApparition = calendrier.getTime();
		}
		
		log.setType(logFile.getType());
		log.setDateApparition(dateApparition);
		log.setLog(logFile.serialiser());
		if (!StringUtils.isBlank(logFile.getMethode())) {
			log.setMethode(logFile.getMethode());
		} else {
			log.setMethode(log.getLog());
		}
		
		return log;
	}
	
	
	/**
	 * 
	 * @param logFile
	 */
	private void verifierLectureAutorisee (LogFile logFile) {
		
		//Récupération de la date du jour
		Calendar calendrier = Calendar.getInstance();
		calendrier.set(Calendar.HOUR, 0);
		calendrier.set(Calendar.MINUTE, 0);
		calendrier.set(Calendar.SECOND, 0);
		calendrier.set(Calendar.MILLISECOND, 0);
		
		if (logFile.getDateApparition() != null) {
			
			//Récupération de la date de la log lue dans le fichier des traces
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
			Date dateApparition = null;
			try {
				dateApparition = format.parse(logFile.getDateApparition());
				
				
				if (dateApparition.compareTo(calendrier.getTime()) > 0 && !mapLogsDuJour.containsKey(dateApparition)) {
					isLectureAutorised = true;
				} else {
					isLectureAutorised = false;
				}
			} catch (ParseException e) {}
		}
	}
	

}
