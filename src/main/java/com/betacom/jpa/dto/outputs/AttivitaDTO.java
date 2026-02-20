package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class AttivitaDTO {

	private Integer id;
	private String description;
}
