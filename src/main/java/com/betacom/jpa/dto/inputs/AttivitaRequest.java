package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttivitaRequest {

	private Integer id;
	private String description;
	private Integer abbonamentoID;
}
