package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.CategoriaDomain;
import com.santosglaiton.cursomc.domain.ProdutoDomain;
import com.santosglaiton.cursomc.repositories.CategoriaRepository;
import com.santosglaiton.cursomc.repositories.ProdutoRepository;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoDomain find(Integer id){

        Optional<ProdutoDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + ProdutoDomain.class.getName()));
    }

    public Page<ProdutoDomain> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<CategoriaDomain> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }

}
