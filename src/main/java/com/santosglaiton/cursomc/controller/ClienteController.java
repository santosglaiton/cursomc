package com.santosglaiton.cursomc.controller;

import com.santosglaiton.cursomc.domain.ClienteDomain;
import com.santosglaiton.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id ){

        ClienteDomain obj = service.buscar(id);

        return ResponseEntity.ok().body(obj);
    }

}
