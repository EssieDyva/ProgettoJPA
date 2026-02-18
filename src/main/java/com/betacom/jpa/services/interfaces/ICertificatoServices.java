package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.CertificatoRequest;
import com.betacom.jpa.dto.outputs.SocioDTO;

public interface ICertificatoServices {
	void create(CertificatoRequest req) throws Exception;
	List<SocioDTO> listSocio() throws Exception;
}
