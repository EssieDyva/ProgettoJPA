package com.betacom.jpa.services.interfaces;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;

public interface IAbbonamentoServices {

	void create(AbbonamentoRequest req) throws Exception;
	void delete(Integer id) throws Exception;
}
