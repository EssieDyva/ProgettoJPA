package com.betacom.jpa.services.implementations;

import static com.betacom.jpa.utils.Utils.stringToDate;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AbbonamentoRequest;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repository.IAbbonamentoRepository;
import com.betacom.jpa.repository.ISocioRepository;
import com.betacom.jpa.services.interfaces.IAbbonamentoServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AbbonamentoImpl implements IAbbonamentoServices{

	private final IAbbonamentoRepository abbR;
	private final ISocioRepository socioR;

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void create(AbbonamentoRequest req) throws Exception {
		log.debug("create {}", req);
		Socio soc = socioR.findById(req.getSocioId())
				.orElseThrow(() -> new AcademyException("Socio non trovato in DB: " + req.getSocioId()));
		
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
		
		if(!abb.getAttivitas().isEmpty()) {
			log.debug("cancel all attivita from this abbonamento");
			abb.getAttivitas().removeAll(abb.getAttivitas());
			abbR.save(abb);
		}
			
			
		abbR.delete(abb);
	}

	@Override
	public List<AbbonamentoDTO> getBySocio(SocioDTO soc) throws Exception {
	    log.debug("getBySocio {}", soc);
	    
	    Socio socio = socioR.findById(soc.getId())
	            .orElseThrow(() -> new AcademyException("Socio non trovato: " + soc.getId()));
	    
	    List<Abbonamento> lA = abbR.getAllBySocio(socio);
	    
	    return lA.stream()
	            .map(a -> AbbonamentoDTO.builder()
	                    .id(a.getId())
	                    .dataIscrizione(a.getDataIscrizione())
	                    .build()
	                    ).collect(Collectors.toList());
	}

	@Override
	public void update(AbbonamentoRequest req) throws Exception {
		log.debug("create {}", req);
		Socio soc = socioR.findById(req.getSocioId())
				.orElseThrow(() -> new AcademyException("Socio nontrovato in DB: " + req.getSocioId()));
		
		Abbonamento abb = abbR.getBySocio(soc);
		abb.setDataIscrizione(stringToDate(req.getDataIscrizione()));
		abb.setSocio(soc);
		
		abbR.save(abb);
	}

	@Override
	public List<AbbonamentoDTO> list() throws Exception {
		log.debug("list {}");
		List<Abbonamento> lA = abbR.findAll();
		return lA.stream()
				.map(a -> AbbonamentoDTO.builder()
						.id(a.getId())
						.dataIscrizione(a.getDataIscrizione())
						.build())
				.collect(Collectors.toList());
	}

}
