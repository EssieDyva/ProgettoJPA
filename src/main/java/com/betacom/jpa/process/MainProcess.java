package com.betacom.jpa.process;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.SocioRequest;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MainProcess {

	private final ISocioServices socioS;

	public MainProcess(ISocioServices socioS) {
		this.socioS = socioS;
	}

	public void executeSocio() throws Exception {
		log.debug("Begin executeSocio");
		SocioRequest req = new SocioRequest();
		req.setNome("Paolo");
		req.setCognome("Rossi");
		req.setCodiceFiscale("RSPLIVJEI29");
		req.setEmail("p.rossi@tim.com");

		try {
			aggiornamenti(req);
			listSocio();
		} catch (Exception e) {
			log.error("Errore found in process {}", e.getMessage());
		}
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	private void aggiornamenti(SocioRequest req) throws Exception {
		int id = insertSocio(req);
		id = insertSocio(req);
		
		req = new SocioRequest();
		req.setCodiceFiscale("update");
		req.setId(id);
		updateSocio(req);
		
	}

	private Integer insertSocio(SocioRequest req) throws Exception {
		int id = 0;
		id = socioS.create(req);

		return id;
	}

	private void listSocio() {
		try {
			List<SocioDTO> lS = socioS.findAll();
			lS.forEach(s -> log.debug(s.toString()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private void updateSocio(SocioRequest req) throws Exception {
		socioS.update(req);
	}  

	private void deleteSocio(Integer id) throws Exception {
		socioS.delete(id);
	}
}
