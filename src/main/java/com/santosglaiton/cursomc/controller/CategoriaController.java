package com.santosglaiton.cursomc.controller;

import com.santosglaiton.cursomc.domain.CategoriaDomain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    public List<CategoriaDomain> listar(){

        CategoriaDomain categoriaDomain1 = new CategoriaDomain(1,"Informática");
        CategoriaDomain categoriaDomain2 = new CategoriaDomain(2,"Escritório");

        List<CategoriaDomain> lista = new ArrayList<>();

        lista.add(categoriaDomain1);
        lista.add(categoriaDomain2);

        return lista;
    }

}
