package com.santosglaiton.cursomc.repositories;

import com.santosglaiton.cursomc.domain.ClienteDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDomain, Integer> {
}
