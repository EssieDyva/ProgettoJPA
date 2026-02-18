package com.betacom.jpa.dto.outputs;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AbbonamentoDTO {

	private Integer id;
	private LocalDate dataIscrizione;
}
