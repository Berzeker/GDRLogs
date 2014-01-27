package org.gdrlogs.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MagieLogEP {
	
	private Long id;
	
	private String type;
	
	private String nbreApparition;
	
	private String dateApparition;
	
	private String methode;

	private String log;
	
	private boolean alert;
	
}
