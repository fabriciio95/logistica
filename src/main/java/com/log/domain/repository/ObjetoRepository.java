package com.log.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.log.domain.model.Objeto;

@Repository
public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

}
