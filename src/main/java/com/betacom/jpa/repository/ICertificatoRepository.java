package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Certificato;

@Repository
public interface ICertificatoRepository extends JpaRepository<Certificato, Integer>{

}
