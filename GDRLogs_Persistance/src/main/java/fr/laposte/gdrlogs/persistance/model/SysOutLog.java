package fr.laposte.gdrlogs.persistance.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="SYSOUTLOG", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqSysOutLog", sequenceName="SEQSYSOUTLOG", schema="FM3MAGIE_LOG")
public class SysOutLog {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DATEAPPARITION")
	private Date dateApparition;
	
	@Lob
	@Column(name="MESSAGELOG")
	private String messageLog;
	
	@Lob
	@Column(name="COMPLEMENTLOG")
	private String complementLog;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="sysOutLog_id", referencedColumnName="id")
	private List<StackTraceLog> Stacktrace = new ArrayList<StackTraceLog>();

	/**
	 * Constructor
	 */
	public SysOutLog() {
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
	public List<StackTraceLog> getStacktrace() {
		return Stacktrace;
	}

	/**
	 * @param stacktrace the stacktrace to set
	 */
	public void setStacktrace(List<StackTraceLog> stacktrace) {
		Stacktrace = stacktrace;
	}
	
}
