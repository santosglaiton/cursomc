package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.PagamentoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<PagamentoDomain, Integer> {
}
