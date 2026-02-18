package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Abbonamento;

@Repository
public interface IAbbonamentoRepository extends JpaRepository<Abbonamento, Integer>{

}
