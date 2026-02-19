package com.betacom.jpa.services.implementations;


import static com.betacom.jpa.utils.Utils.stringToDate;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.CertificatoRequest;
import com.betacom.jpa.dto.outputs.CertificatoDTO;
import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.exceptions.AcademyException;
import com.betacom.jpa.models.Certificato;
import com.betacom.jpa.models.Socio;
import com.betacom.jpa.repository.ICertificatoRepository;
import com.betacom.jpa.repository.ISocioRepository;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CertificatoImpl implements ICertificatoServices{

	private final ICertificatoRepository certR;
	private final ISocioRepository socioR;
	
	public CertificatoImpl(ICertificatoRepository certR, ISocioRepository socioR) {
		this.certR = certR;
		this.socioR = socioR;
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void create(CertificatoRequest req) throws Exception {
		log.debug("create {}", req);
		Socio soc = socioR.findById(req.getSocioId())
				.orElseThrow(() -> new AcademyException("Socio non trovato: " + req.getSocioId()));
		
		Certificato cert = new Certificato();
		cert.setTipo((req.getTipo() == null) ? false : true);
		cert.setDataCertificato(stringToDate(req.getDataCertificato()));
		cert.setSocio(soc);
		
		certR.save(cert);
	}

	@Override
	public List<SocioDTO> listSocio() throws Exception {
		log.debug("listSocio");
		List<Certificato> lC = certR.findAll();
		return lC.stream()
				.map(c -> SocioDTO.builder()
						.id(c.getSocio().getId())
						.nome(c.getSocio().getNome())
						.cognome(c.getSocio().getCognome())
						.codiceFiscale(c.getSocio().getCodiceFiscale())
						.email(c.getSocio().getEmail())
						.certificato(CertificatoDTO.builder()
								.id(c.getId())
								.tipo(c.getTipo())
								.dataCertificato(c.getDataCertificato())
								.build())
						.build())
				.toList();
	}

}
