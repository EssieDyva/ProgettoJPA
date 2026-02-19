package com.betacom.jpa.process;

import java.util.List;

import org.springframework.stereotype.Component;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;
import com.betacom.jpa.dto.inputs.AttivitaRequest;
import com.betacom.jpa.dto.inputs.SocioRequest;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.services.interfaces.ICertificatoServices;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MainProcess {

	private final ISocioServices socioS;
	private final ICertificatoServices certS;
	
	private final ProcessTransaction pT;
	private final ProcessAttivita pA;

	public MainProcess(ISocioServices socioS, ProcessTransaction pT, ICertificatoServices certS, ProcessAttivita pA) {
		this.socioS = socioS;
		this.pT = pT;
		this.certS = certS;
		this.pA = pA;
	}

	public void executeSocio() throws Exception {
		log.debug("Begin executeSocio");
		SocioRequest req = new SocioRequest();
		req.setNome("Aldo");
		req.setCognome("Baglio");
		req.setCodiceFiscale("ALDJ83DYCB");
		req.setEmail("a.baglio@tim.com");

		int id = 0;
		
		try {
//			id = pT.aggiornamenti(req);
//			CertificatoRequest reqC = new CertificatoRequest();
//			reqC.setDataCertificato("04/05/2026");
//			reqC.setSocioId(id);
//			pT.insertCertificato(reqC);
//			
//			pT.deleteSocio(8);
//			ListSocioViaCertificato();
//			insertAbbonamento(8);
//			listSocio();
//			listSocioById(8);
//			pA.createAttivita();
//			pA.deleteAttivita(1);
//			createAbbonamentoAttivita(1, 1);
		} catch (Exception e) {
			log.error("Errore found in process {}", e.getMessage());
		}
	}

	private void insertAbbonamento(Integer socioId) throws Exception {
		AbbonamentoRequest req = new AbbonamentoRequest();
		req.setDataIscrizione("01/02/2026");
		req.setSocioId(socioId);
		
		pT.insertAbbonamento(req);
	}
	
	private void listSocio() {
		try {
			List<SocioDTO> lS = socioS.findAll();
			lS.forEach(s -> log.debug(s.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void ListSocioViaCertificato() {
		List<SocioDTO> lS;
		try {
			lS = certS.listSocio();
			lS.forEach(c -> log.debug(c.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void listSocioById(Integer id) {
		try {
			SocioDTO s = socioS.findById(id);
			log.debug(s.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private void createAbbonamentoAttivita(int abbonamentoID, int attivitaID) throws Exception {
		AttivitaRequest req = new AttivitaRequest();
		req.setId(attivitaID);
		req.setAbbonamentoID(abbonamentoID);
		
		pA.createAttivitaAbbonamento(req);
	}
	
	
}
