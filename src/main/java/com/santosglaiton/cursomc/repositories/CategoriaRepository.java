package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.CategoriaDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaDomain, Integer> {
}
