package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.SocioRequest;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repository.ISocioRepository;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SocioImpl implements ISocioServices{

	private final ISocioRepository socioR;

	public SocioImpl(ISocioRepository socioR) {
		this.socioR = socioR;
	}
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public Integer create(SocioRequest req) throws AcademyException {
		log.debug("create {}", req);
		if(req.getCodiceFiscale() == null) 
			throw new AcademyException("Codice fiscale non caricato");
		if(req.getCognome() == null) 
			throw new AcademyException("Cognome non caricato");
		if(req.getEmail() == null) 
			throw new AcademyException("Email non caricato");
		if(req.getNome() == null) 
			throw new AcademyException("Nome non caricato");
		
		Optional<Socio> s = socioR.findByCodiceFiscale(req.getCodiceFiscale());
		if(s.isPresent())
			throw new AcademyException("Codice fiscale già presente nel DB" + req.getCodiceFiscale());

		Socio soc = new Socio();
		soc.setNome(req.getNome());
		soc.setCognome(req.getCognome());
		soc.setCodiceFiscale(req.getCodiceFiscale());
		soc.setEmail(req.getEmail());
		
		return socioR.save(soc).getId();
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public List<SocioDTO> findAll() throws AcademyException {
		log.debug("findAll");
		List<Socio> lS = socioR.findAll();
		return lS.stream()
				.map(s -> new SocioDTO(s.getId(), 
						s.getCognome(), s.getNome(), 
						s.getCodiceFiscale(), 
						s.getEmail())).toList();
	}

	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void update(SocioRequest req) throws AcademyException {
		log.debug("update {}", req);
		Optional<Socio> soc = socioR.findById(req.getId());
		if(soc.isEmpty())
			throw new AcademyException("Socio non trovato in DB");
		
		Socio s = soc.get();
		if(req.getCodiceFiscale() != null) 
			s.setCodiceFiscale(req.getCodiceFiscale());
		if(req.getCognome() != null) 
			s.setCognome(req.getCognome());
		if(req.getEmail() != null) 
			s.setEmail(req.getEmail());
		if(req.getNome() != null) 
			s.setNome(req.getNome());
		
		socioR.save(s);
	}

	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void delete(Integer id) throws AcademyException {
		log.debug("delete {}", id);
		Optional<Socio> soc = socioR.findById(id);
		if(soc.isEmpty())
			throw new AcademyException("Socio non trovato in DB");
		
		socioR.delete(soc.get());
	}
	
}
