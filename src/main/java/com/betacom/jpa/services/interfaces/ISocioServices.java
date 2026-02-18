package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.SocioRequest;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;

public interface ISocioServices {

	Integer create(SocioRequest req) throws AcademyException;
	List<SocioDTO> findAll() throws AcademyException;
	void update(SocioRequest req) throws AcademyException;
	void delete(Integer id) throws AcademyException;
	SocioDTO findById(Integer id) throws Exception; 
}
