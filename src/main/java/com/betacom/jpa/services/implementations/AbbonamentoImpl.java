package com.betacom.jpa.services.implementations;

import static com.betacom.jpa.utils.Utils.stringToDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repository.IAbbonamentoRepository;
import com.betacom.jpa.repository.ISocioRepository;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AbbonamentoImpl implements IAbbonamentoServices{

	private final IAbbonamentoRepository abbR;
	private final ISocioRepository socioR;
	
	public AbbonamentoImpl(IAbbonamentoRepository abbR, ISocioRepository socioR) {
		this.abbR = abbR;
		this.socioR = socioR;
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void create(AbbonamentoRequest req) throws Exception {
		log.debug("create {}", req);
		Socio soc = socioR.findById(req.getSocioId())
				.orElseThrow(() -> new AcademyException("Socio nontrovato in DB: " + req.getSocioId()));
		
		Abbonamento abb = new Abbonamento();
		abb.setDataIscrizione(stringToDate(req.getDataIscrizione()));
		abb.setSocio(soc);
		
		abbR.save(abb);
	}

	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		
		Abbonamento abb = abbR.findById(id)
				.orElseThrow(() -> new AcademyException("Abbonamento non trovato"));
		
		if(!abb.getAttivitas().isEmpty())
			throw new AcademyException("Ci sono attività per questo abbonamento");
		
		abbR.delete(abb);
	}

}
