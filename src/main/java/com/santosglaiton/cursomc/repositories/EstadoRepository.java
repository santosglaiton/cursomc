package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.EstadoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoDomain, Integer> {
}
