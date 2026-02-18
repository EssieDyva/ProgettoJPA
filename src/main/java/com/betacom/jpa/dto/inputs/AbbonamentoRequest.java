package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AbbonamentoRequest {

	private Integer id;
	private String dataIscrizione;
	private Integer socioId;
}
