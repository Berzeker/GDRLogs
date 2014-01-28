package fr.laposte.gdrlogs.persistance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MAGIELOG", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqMagieLog", sequenceName="SEQMAGIELOG", schema="FM3MAGIE_LOG")
public class MagieLog {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="DATEAPPARITION")
	private Date dateApparition;
	
	@Lob
	@Column(name="METHODE")
	private String methode;
	
	@Lob
	@Column(name="LOG")
	private String log;

	/**
	 * Constructor
	 */
	public MagieLog() {
	}

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the dateApparition
	 */
	public Date getDateApparition() {
		return dateApparition;
	}

	/**
	 * @param dateApparition the dateApparition to set
	 */
	public void setDateApparition(Date dateApparition) {
		this.dateApparition = dateApparition;
	}

	/**
	 * @return the methode
	 */
	public String getMethode() {
		return methode;
	}

	/**
	 * @param methode the methode to set
	 */
	public void setMethode(String methode) {
		this.methode = methode;
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
	
}
