package com.betacom.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.inputs.CertificatoRequest;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.ICertificatoServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/certificato/")
public class CertificatoCxontroller {
	
	private final ICertificatoServices certificatoServices;

	@PostMapping("create")
	public ResponseEntity<Resp> create(@RequestBody (required = true) CertificatoRequest request) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			certificatoServices.create(request);
			r.setMsg("Certificato creato");
		} catch (Exception e) {
			r.setMsg("Errore nella creazione" + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@PutMapping("update")
	public ResponseEntity<Resp> update(@RequestBody (required = true) CertificatoRequest request) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			certificatoServices.update(request);
			r.setMsg("Certificato creato");
		} catch (Exception e) {
			r.setMsg("Errore nella creazione" + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
	
	@GetMapping("list")
	public ResponseEntity<Object> findById() {

		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = certificatoServices.listSocio();
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
}
