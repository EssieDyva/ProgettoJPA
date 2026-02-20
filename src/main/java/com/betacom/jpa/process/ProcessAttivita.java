package com.betacom.jpa.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AttivitaRequest;
import com.betacom.jpa.dto.outputs.AttivitaDTO;
import com.betacom.jpa.services.implementations.AbbonamentoImpl;
import com.betacom.jpa.services.interfaces.IAttivitaServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessAttivita {
	private final IAttivitaServices attS;

	public ProcessAttivita(IAttivitaServices attS) {
		this.attS = attS;
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void createAttivita() throws Exception {
		List<AttivitaRequest> lreq = new ArrayList<AttivitaRequest>();
		AttivitaRequest req = new AttivitaRequest();
		req.setDescription("Yoga");
		lreq.add(req);
		req = new AttivitaRequest();
		req.setDescription("Karate");
		lreq.add(req);
		req = new AttivitaRequest();
		req.setDescription("Ritmica");
		lreq.add(req);
		for(AttivitaRequest r: lreq)
			attS.create(r);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void createAttivitaAbbonamento(AttivitaRequest req) throws Exception {
		attS.createAttivitaAbbonamento(req);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void deleteAttivitaAbbonamento(Integer idAbbonamento, Integer idAttivita) throws Exception {
		attS.deleteAttivitaAbbonamento(idAbbonamento, idAttivita);
	}
	
	@Transactional (rollbackFor = Exception.class)
	public void deleteAttivita(Integer id) throws Exception {
		attS.delete(id);
	}
	
	public void list() {
		List<AttivitaDTO> lA = attS.listAttivita();
		lA.forEach(a -> log.debug(a.toString()));
	}
	
	@Transactional (rollbackFor = Exception.class)
	public List<AttivitaDTO> getByIdAbbonamento(Integer id) throws Exception {
	    List<AttivitaDTO> lAtt = attS.getByIdAbbonamento(id);
	    for (AttivitaDTO att : lAtt) {
	        log.debug("Attivita: {}", att.getDescription());
	    }
	    return lAtt;
	}

}
