package fr.laposte.gdrlogs.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PARAMETRE", schema="FM3MAGIE_LOG")
@SequenceGenerator(name="seqParametre", sequenceName="SEQPARAMETRE", schema="FM3MAGIE_LOG")
@Data
@NoArgsConstructor
public class Parametre {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqParametre")
	private Long id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="VALEUR")
	private String valeur;

}
