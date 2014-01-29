package org.gdrlogs.web.entity;

import java.util.ArrayList;
import java.util.List;

public class SysLogEP {
	
	private Long id;
	
	private String dateApparition;
	
	private String nbreApparition;
	
	private String messageLog;

	private String complementLog;

	private List<StackTraceLogEP> Stacktrace = new ArrayList<StackTraceLogEP>();

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

	/**
	 * @return the complementLog
	 */
	public String getComplementLog() {
		return complementLog;
	}

	/**
	 * @param complementLog the complementLog to set
	 */
	public void setComplementLog(String complementLog) {
		this.complementLog = complementLog;
	}

	/**
	 * @return the stacktrace
	 */
	public List<StackTraceLogEP> getStacktrace() {
		return Stacktrace;
	}

	/**
	 * @param stacktrace the stacktrace to set
	 */
	public void setStacktrace(List<StackTraceLogEP> stacktrace) {
		Stacktrace = stacktrace;
	}

}
