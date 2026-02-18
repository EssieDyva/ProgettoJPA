package com.betacom.jpa.dto.outputs;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CertificatoDTO {

	private Integer id;
	private Boolean tipo; //false normale, true agonistico
	private LocalDate dataCertificato;
}
