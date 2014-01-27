package fr.laposte.gdrlogs.persistance.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.laposte.gdrlogs.persistance.model.SysOutLog;

@Named
public interface SysLogDao extends JpaRepository<SysOutLog, Long>{
	
	/**
	 * Cette méthode permet de rechercher une Log à partir de la date d'apparition
	 * 
	 * @param date la date d'apparition
	 * @return la log système
	 */
	SysOutLog findByDateApparition(Date date);
	
	
	/**
	 * Cette méthode permet de rechercher les logs apparues après la date passée en paramètre
	 * @param date la date comptable
	 * @return les logs apparues après la date passée en paramètre
	 */
	List<SysOutLog> findByDateApparitionAfter(Date dateComptable);
	
	/**
	 * Cette méthode permet de rechercher les logs apparues entre deux dates
	 * @param date la date comptable
	 * @return les logs apparues après la date passée en paramètre
	 */
	@Query("select count(sl.complementLog) as nbreLogs, sl from SysOutLog as sl "
			+ "where sl.dateApparition >= :dateDebut and sl.dateApparition < :dateFin "
			+ "group by sl.complementLog order by nbreLogs desc, sl.dateApparition desc")
	Iterable<Object[]> findByDateApparitionAfterGroupByLog(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);

}
