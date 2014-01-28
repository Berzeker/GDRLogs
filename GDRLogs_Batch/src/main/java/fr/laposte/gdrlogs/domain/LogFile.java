package fr.laposte.gdrlogs.domain;


public class LogFile {

	private String type;
	
	private String dateApparition;
	
	private String categorie;
	
	private String uri;
	
	private String session;
	
	private String methode;
	
	private String etape;
	
	private String messageLog;
	

	/**
	 * Constructor
	 */
	public LogFile() {
	}


	/**
	 * Cette méthode permet de serialisation une instance de la classe LogFile
	 * @return les données en une seule ligne
	 */
	public String serialiser() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(categorie).append(";");
		stringBuffer.append(uri).append(";");
		stringBuffer.append(session).append(";");
		stringBuffer.append(methode).append(";");
		stringBuffer.append(etape).append(";");
		stringBuffer.append(messageLog);
		return stringBuffer.toString();
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
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}


	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}


	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}


	/**
	 * @return the session
	 */
	public String getSession() {
		return session;
	}


	/**
	 * @param session the session to set
	 */
	public void setSession(String session) {
		this.session = session;
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
	 * @return the etape
	 */
	public String getEtape() {
		return etape;
	}


	/**
	 * @param etape the etape to set
	 */
	public void setEtape(String etape) {
		this.etape = etape;
	}


	/**
	 * @return the messageLog
	 */
	public String getMessageLog() {
		return messageLog;
	}


	/**
	 * @param messageLog the messageLog to set
	 */
	public void setMessageLog(String messageLog) {
		this.messageLog = messageLog;
	}

}
