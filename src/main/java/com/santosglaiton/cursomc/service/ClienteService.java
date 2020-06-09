package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.ClienteDomain;
import com.santosglaiton.cursomc.repositories.ClienteRepository;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public ClienteDomain find(Integer id){

        Optional<ClienteDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + ClienteDomain.class.getName()));
    }

}
