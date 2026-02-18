package com.betacom.jpa.dto.outputs;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class SocioDTO {

	private Integer id;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String email;
	private CertificatoDTO certificato;
	private List<AbbonamentoDTO> abbonamento;
}
