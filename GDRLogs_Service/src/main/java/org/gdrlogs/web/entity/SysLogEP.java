package org.gdrlogs.web.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SysLogEP {
	
	private Long id;
	
	private String dateApparition;
	
	private String nbreApparition;
	
	private String messageLog;

	private String complementLog;

	private List<StackTraceLogEP> Stacktrace = new ArrayList<StackTraceLogEP>();

}
