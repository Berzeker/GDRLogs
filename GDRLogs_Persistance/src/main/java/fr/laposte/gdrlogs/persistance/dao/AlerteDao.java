package fr.laposte.gdrlogs.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.laposte.gdrlogs.persistance.model.Alerte;

public interface AlerteDao extends JpaRepository<Alerte, Long> {

}
