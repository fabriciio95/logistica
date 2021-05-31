package com.log.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.domain.model.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

	Optional<Motorista> findByEmail(String email);
	
	Optional<Motorista> findByCpf(String cpf);
	
	List<Motorista> findByCpfContaining(String cpf);
}
