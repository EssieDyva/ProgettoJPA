package com.betacom.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Socio;
import java.util.List;



@Repository
public interface ISocioRepository extends JpaRepository<Socio, Integer>{
	Optional<Socio> findByCodiceFiscale(String codiceFiscale);

	List<Socio> findByCognomeContaining(String pattern); // like
	List<Socio> findByCognomeAndNome(String cognome, String nome);
}
