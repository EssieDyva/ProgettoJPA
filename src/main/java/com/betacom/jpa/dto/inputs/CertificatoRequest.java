package com.betacom.jpa.dto.inputs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CertificatoRequest {

	private Integer id;
	private Boolean tipo; //false normale, true agonistico
	private String dataCertificato;
	private Integer socioId;
}
