package com.log.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.domain.model.Destinatario;

@Repository
public interface DestinatarioRepository extends JpaRepository<Destinatario, Long> {

	Destinatario findByEntregaId(Long id);
}
