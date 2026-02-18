package com.betacom.jpa.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "socio_palestra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socio {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (length = 100, nullable = false)
	private String cognome;
	
	@Column (length = 100, nullable = false)
	private String nome;

	@Column (name = "codice_fiscale" , length = 16, nullable = false, unique = true)
	private String codiceFiscale;
	
	private String email;
	
	@OneToOne(
			mappedBy = "socio",
			cascade = CascadeType.REMOVE
			)
	private Certificato certificato;
	
	@OneToMany (
			mappedBy = "socio",
			fetch = FetchType.EAGER
			)
	private List<Abbonamento> abbonamento;
}
