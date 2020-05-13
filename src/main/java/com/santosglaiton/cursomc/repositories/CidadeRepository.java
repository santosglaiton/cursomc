package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.CidadeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeDomain, Integer> {
}
