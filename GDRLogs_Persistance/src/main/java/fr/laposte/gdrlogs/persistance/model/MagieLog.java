package fr.laposte.gdrlogs.persistance.model;

import java.util.Date;

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
@Table(name="MAGIELOG", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqMagieLog", sequenceName="SEQMAGIELOG")
@Data
@NoArgsConstructor
public class MagieLog {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="DATEAPPARITION")
	private Date dateApparition;
	
	@Lob
	@Column(name="METHODE")
	private String methode;
	
	@Lob
	@Column(name="LOG")
	private String log;

}
