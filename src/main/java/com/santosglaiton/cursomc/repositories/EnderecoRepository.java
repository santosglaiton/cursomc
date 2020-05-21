package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.EnderecoDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoDomain, Integer> {
}
