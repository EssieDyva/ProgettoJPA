package com.betacom.jpa.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.betacom.jpa.dto.inputs.SocioRequest;
import com.betacom.jpa.response.Resp;
import com.betacom.jpa.services.interfaces.ISocioServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/socio/")
public class SocioController {

	private final ISocioServices socioServices;

	@GetMapping("list")
	public ResponseEntity<Object> findByFilter(@RequestParam (required = false) Integer id,
												@RequestParam (required = false) String nome,
												@RequestParam (required = false) String cognome,
												@RequestParam (required = false) Integer attivita) {

		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = socioServices.findById(id);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

	@GetMapping("findById")
	public ResponseEntity<Object> findById(@RequestParam (required = true) Integer id) {

		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = socioServices.findById(id);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

	@GetMapping("findByAttivita")
	public ResponseEntity<Object> findById(@RequestParam (required = true) String attivita) {

		Object r = new Object();
		HttpStatus status = HttpStatus.OK;
		try {
			r = socioServices.findByAttivita(attivita);
		} catch (Exception e) {
			r = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

	@PostMapping("create")
	public ResponseEntity<Resp> create(@RequestBody (required = true) SocioRequest request) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			socioServices.create(request);
			r.setMsg("Socio creato");
		} catch (Exception e) {
			r.setMsg("Errore nella creazione" + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

	@PutMapping("update")
	public ResponseEntity<Resp> update(@RequestBody (required = true) SocioRequest request) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			socioServices.update(request);
			r.setMsg("Socio aggiornato");
		} catch (Exception e) {
			r.setMsg("Errore nell'aggiornamento" + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Resp> delete(@PathVariable Integer id) {
		Resp r = new Resp();
		HttpStatus status = HttpStatus.OK;
		try {
			socioServices.delete(id);
			r.setMsg("Socio cancellato");
		} catch (Exception e) {
			r.setMsg("Errore nella cancellazione" + e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return ResponseEntity.status(status).body(r);
	}
}
