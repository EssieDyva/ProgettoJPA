package com.betacom.jpa.process;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;
import com.betacom.jpa.dto.inputs.CertificatoRequest;
import com.betacom.jpa.dto.inputs.SocioRequest;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import com.betacom.jpa.services.interfaces.ICertificatoServices;
import com.betacom.jpa.services.interfaces.ISocioServices;

@Component
public class ProcessTransaction {

	private final ISocioServices socioS;
	private final ICertificatoServices certS;
	private final IAbbonamentoServices abbS;
	
	public ProcessTransaction(ISocioServices socioS, ICertificatoServices certS, IAbbonamentoServices abbS) {
		this.socioS = socioS;
		this.certS = certS;
		this.abbS = abbS;
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	public int aggiornamenti (SocioRequest req) throws Exception {
		int id = insertSocio(req);
		
//		req = new SocioRequest();
//		req.setCodiceFiscale("SMI4EUWH78");
//		req.setId(id);
//		updateSocio(req);
		
		return id;
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	public void deleteSocio(Integer id) throws Exception {
		socioS.delete(id);
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	public void insertAbbonamento(AbbonamentoRequest req) throws Exception {
		abbS.create(req);
	}
	
	private Integer insertSocio(SocioRequest req) throws Exception {
		int id = 0;
		id = socioS.create(req);

		return id;
	}
	
	private void updateSocio(SocioRequest req) throws Exception {
		socioS.update(req);
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	public void insertCertificato(CertificatoRequest req) throws Exception{
		certS.create(req);
	}
}
