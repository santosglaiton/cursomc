package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.ProdutoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Integer> {
}
