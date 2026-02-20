package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Attivita;

@Repository
public interface IAttivitaRepository extends JpaRepository<Attivita, Integer>{
	Boolean existsByDescription(String description);
}
