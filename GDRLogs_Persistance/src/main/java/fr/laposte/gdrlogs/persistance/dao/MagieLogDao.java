package fr.laposte.gdrlogs.persistance.dao;

import java.util.Collection;
import java.util.Date;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.laposte.gdrlogs.persistance.model.MagieLog;

@Named
public interface MagieLogDao extends JpaRepository<MagieLog, Long> {
	
	Collection<MagieLog> findByDateApparitionAfter(Date date);
	
	@Query("select count(ml.methode) as nbreApparition, ml from MagieLog as ml where ml.dateApparition >= :dateDebut "
			+ "and ml.dateApparition < :dateFin group by ml.methode order by nbreApparition desc, ml.dateApparition desc")
	Iterable<Object[]> findByDateApparitionAfterGroupByMethode(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}
