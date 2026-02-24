package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;

public interface IAbbonamentoServices {

	void create(AbbonamentoRequest req) throws Exception;
	void update(AbbonamentoRequest req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<AbbonamentoDTO> getBySocio(SocioDTO s) throws Exception;
	List<AbbonamentoDTO> list() throws Exception;
	AbbonamentoDTO aggiungiAttivita(Integer abbonamentoId, Integer attivitaId) throws Exception;
}
