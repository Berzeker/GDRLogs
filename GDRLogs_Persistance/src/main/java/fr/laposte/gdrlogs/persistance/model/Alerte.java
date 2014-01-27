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
@Table(name="ALERTE", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqAlerte", sequenceName="SEQALERTE", schema="FM3MAGIE_LOG")
@Data
@NoArgsConstructor
public class Alerte {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqAlerte")
	private Long id;
	
	@Lob
	@Column(name="LOG")
	private String log;
	
	@Column(name="ALERTE")
	private boolean alerte;
	
}
