package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.CategoriaDomain;
import com.santosglaiton.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public CategoriaDomain buscar(Integer id){

        Optional<CategoriaDomain> cat = repo.findById(id);
        return cat.orElse(null);
    }

}
