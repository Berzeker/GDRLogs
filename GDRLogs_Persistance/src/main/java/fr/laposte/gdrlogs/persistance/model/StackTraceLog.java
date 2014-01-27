package fr.laposte.gdrlogs.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="STACKTRACELOG", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqStackTraceLog", sequenceName="SEQSTACKTRACELOG")
@Data
@NoArgsConstructor
public class StackTraceLog {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="MESSAGELOG")
	private String messageLog;
	
}
