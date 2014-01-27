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

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="SYSOUTLOG", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqSysOutLog", sequenceName="SEQSYSOUTLOG")
@Data
@NoArgsConstructor
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
	
}
