package fr.laposte.gdrlogs.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ALERTE", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqAlerte", sequenceName="SEQALERTE", schema="FM3MAGIE_LOG")
public class Alerte {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="LOG")
	private String log;
	
	@Column(name="ALERTE")
	private boolean alerte;
	
	
	/**
	 * Constructor
	 */
	public Alerte() {}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * @return the alerte
	 */
	public boolean isAlerte() {
		return alerte;
	}

	/**
	 * @param alerte the alerte to set
	 */
	public void setAlerte(boolean alerte) {
		this.alerte = alerte;
	}
	
	
	
}
