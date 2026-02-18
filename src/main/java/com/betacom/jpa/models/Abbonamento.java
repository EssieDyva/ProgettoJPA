package com.betacom.jpa.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name = "abbonamento_socio")
@Data
public class Abbonamento {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column (
			name = "data_iscrizione",
			nullable = false
			)
	private LocalDate dataIscrizione;
	
	@ManyToOne
	@JoinColumn (name = "id_socio")
	private Socio socio;
}
