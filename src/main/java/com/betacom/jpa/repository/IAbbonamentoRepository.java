package com.betacom.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.dto.outputs.SocioDTO;
import com.betacom.jpa.models.Abbonamento;
import com.betacom.jpa.models.Socio;


@Repository
public interface IAbbonamentoRepository extends JpaRepository<Abbonamento, Integer>{

	List<Abbonamento> getAllBySocio(Socio socio);
}
