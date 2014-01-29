package org.gdrlogs.web.entity;


public class MagieLogEP {
	
	private Long id;
	
	private String type;
	
	private String nbreApparition;
	
	private String dateApparition;
	
	private String methode;

	private String log;
	
	private boolean alert;

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
	 * @return the nbreApparition
	 */
	public String getNbreApparition() {
		return nbreApparition;
	}

	/**
	 * @param nbreApparition the nbreApparition to set
	 */
	public void setNbreApparition(String nbreApparition) {
		this.nbreApparition = nbreApparition;
	}

	/**
	 * @return the dateApparition
	 */
	public String getDateApparition() {
		return dateApparition;
	}

	/**
	 * @param dateApparition the dateApparition to set
	 */
	public void setDateApparition(String dateApparition) {
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

	/**
	 * @return the alert
	 */
	public boolean isAlert() {
		return alert;
	}

	/**
	 * @param alert the alert to set
	 */
	public void setAlert(boolean alert) {
		this.alert = alert;
	}

}
