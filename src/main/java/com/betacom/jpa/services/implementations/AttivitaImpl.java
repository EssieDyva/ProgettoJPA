package com.betacom.jpa.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.AttivitaRequest;
import com.betacom.jpa.dto.outputs.AbbonamentoDTO;
import com.betacom.jpa.dto.outputs.AttivitaDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Attivita;
import com.betacom.jpa.repository.IAbbonamentoRepository;
import com.betacom.jpa.repository.IAttivitaRepository;
import com.betacom.jpa.services.interfaces.IAttivitaServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttivitaImpl implements IAttivitaServices{
	
	private final IAttivitaRepository attR;
	private final IAbbonamentoRepository abbR;
	
	public AttivitaImpl(IAttivitaRepository attR, IAbbonamentoRepository abbR) {
		this.attR = attR;
		this.abbR = abbR;
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void create(AttivitaRequest req) throws Exception {
		log.debug("create {}", req);
		if(attR.existsByDescription(req.getDescription().trim().toUpperCase()))
			throw new AcademyException("attivita già esiste:" + req.getDescription());
		
		Attivita att = new Attivita();
		att.setDescription(req.getDescription());
		
		attR.save(att);
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void update(AttivitaRequest req) throws Exception {
		log.debug("update {}", req);
		Attivita att = attR.findById(req.getId())
				.orElseThrow(() -> new Exception("Attivita non trovata in db"));
		
		Attivita a = att;
		if(req.getDescription() != null)
			a.setDescription(req.getDescription());
		
		attR.save(a);
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Attivita att = attR.findById(id)
				.orElseThrow(() -> new AcademyException("Attivita non trovata in DB"));
		
		if(!att.getAbbonamentos().isEmpty())
			throw new AcademyException();
		
		attR.delete(att);
		
	}

	@Override
	public List<AttivitaDTO> listAttivita() {
		log.debug("listattivita");
		List<Attivita> lA = attR.findAll();
		return lA.stream()
				.map(a -> AttivitaDTO.builder()
						.id(a.getId())
						.description(a.getDescription())
						.build()
						).toList();
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void createAttivitaAbbonamento(AttivitaRequest req) throws Exception {
		log.debug("createAttivitaAbbonamento {}", req);
		Attivita at = attR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Attivita non trovata"));
		Abbonamento ab = abbR.findById(req.getAbbonamentoID())
				.orElseThrow(() -> new AcademyException("Abbonamento non trovato"));
		
		if(!ab.getAttivitas().contains(at)) {
			ab.getAttivitas().add(at); // update whith new attivita
			abbR.save(ab);
		} else {
			throw new AcademyException("Attivita presente nell'abbonamento");
		}
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void deleteAttivitaAbbonamento(Integer idAbbonamento, Integer idAttivita) throws Exception {
		log.debug("deleteAttivitaAbbonamento {} / {}", idAbbonamento, idAttivita);
		Abbonamento ab = abbR.findById(idAbbonamento)
				.orElseThrow(() -> new AcademyException("Abbonamento non trovato"));
		
		ab.getAttivitas().stream()
			.filter(a -> a.getId() == idAttivita)
			.findFirst()
			.ifPresent(ab.getAttivitas()::remove);
		
		abbR.save(ab);
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public List<AttivitaDTO> getByIdAbbonamento(Integer id) throws Exception {
		log.debug("getByIdAbbonamento {}", id);
		Abbonamento ab = abbR.findById(id)
				.orElseThrow(() -> new AcademyException("Abbonamento non trovato"));
		
		return ab.getAttivitas().stream()
				.map(a -> AttivitaDTO.builder()
						.id(a.getId())
						.description(a.getDescription())
						.build()).toList();
	}

	

}
