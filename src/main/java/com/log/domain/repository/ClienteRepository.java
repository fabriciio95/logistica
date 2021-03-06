package com.log.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findByNome(String name);
	
	List<Cliente> findByNomeContaining(String nome);
	
	Optional<Cliente> findByEmail(String email);
	
	Optional<Cliente> findByCpf(String cpf);
	
	Page<Cliente> findByCpfContaining(String cpf, Pageable pageable);
}
