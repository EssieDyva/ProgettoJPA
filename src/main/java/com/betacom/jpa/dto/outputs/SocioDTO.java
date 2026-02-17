package com.betacom.jpa.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SocioDTO {

	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
}
