package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.PedidoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoDomain, Integer> {
}
