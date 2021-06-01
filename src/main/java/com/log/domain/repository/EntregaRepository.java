package com.log.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.log.domain.model.Entrega;
import com.log.domain.model.StatusEntrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

	@Query("SELECT e FROM Entrega e WHERE e.cliente.id = ?1")
	Page<Entrega> obterEntregasCliente(Long clienteId, Pageable pageable);
	
	@Query("SELECT e FROM Entrega e WHERE e.motorista.id = ?1")
	Page<Entrega> obterEntregasMotorista(Long motoristaId, Pageable pageable);
	
	@Query("SELECT e FROM Entrega e WHERE e.status = ?1")
	Page<Entrega> obterEntregasPorStatus(StatusEntrega statusEntrega, Pageable pageable);
	
}
