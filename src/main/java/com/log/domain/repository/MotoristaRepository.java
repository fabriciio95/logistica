package com.log.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.domain.model.Motorista;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

}
