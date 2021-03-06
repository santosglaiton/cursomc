package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.Estado;
import com.santosglaiton.cursomc.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> findAll(){
        return estadoRepository.findAllByOrderByNome();
    }

}
