package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.PedidoDomain;
import com.santosglaiton.cursomc.repositories.PedidoRepository;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    public PedidoDomain buscar(Integer id){

        Optional<PedidoDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + PedidoDomain.class.getName()));
    }

}
