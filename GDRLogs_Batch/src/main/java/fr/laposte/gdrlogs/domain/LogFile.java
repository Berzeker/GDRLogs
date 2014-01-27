package fr.laposte.gdrlogs.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}
