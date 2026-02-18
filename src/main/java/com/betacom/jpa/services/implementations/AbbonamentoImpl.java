package com.betacom.jpa.services.implementations;

import org.springframework.stereotype.Service;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repository.IAbbonamentoRepository;
import com.betacom.jpa.repository.ISocioRepository;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;
import static com.betacom.jpa.utils.Utils.stringToDate;

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

}
