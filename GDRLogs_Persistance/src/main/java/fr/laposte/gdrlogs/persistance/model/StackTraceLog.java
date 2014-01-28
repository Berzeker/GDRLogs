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
@Table(name="STACKTRACELOG", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqStackTraceLog", sequenceName="SEQSTACKTRACELOG", schema="FM3MAGIE_LOG")
public class StackTraceLog {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="MESSAGELOG")
	private String messageLog;

	/**
	 * Constructor
	 */
	public StackTraceLog() {
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
