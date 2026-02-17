package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SocioRequest {

	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
	
}
