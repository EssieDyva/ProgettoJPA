package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.AttivitaRequest;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.AttivitaDTO;

public interface IAttivitaServices {
	void create(AttivitaRequest req) throws Exception;
	void update(AttivitaRequest req) throws Exception;
	void delete(Integer id) throws Exception;
	
	void createAttivitaAbbonamento(AttivitaRequest req) throws Exception;
	void deleteAttivitaAbbonamento(Integer idAbbonamento, Integer idAttivita) throws Exception;
	
	List<AttivitaDTO> listAttivita();
	
	List<AttivitaDTO> getByIdAbbonamento(Integer id) throws Exception;
}
