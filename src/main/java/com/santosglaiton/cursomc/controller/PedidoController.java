package com.santosglaiton.cursomc.controller;

import com.santosglaiton.cursomc.domain.PedidoDomain;
import com.santosglaiton.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDomain> find(@PathVariable Integer id ){

        PedidoDomain obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }


}
