package fr.laposte.gdrlogs.persistance.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.laposte.gdrlogs.persistance.model.Parametre;

public interface ParametreDao extends JpaRepository<Parametre, Long> {

}
