package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.DTO.CategoriaDTO;
import com.santosglaiton.cursomc.domain.CategoriaDomain;
import com.santosglaiton.cursomc.repositories.CategoriaRepository;
import com.santosglaiton.cursomc.service.exceptions.DataIntegrityException;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public CategoriaDomain find(Integer id){

        Optional<CategoriaDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + CategoriaDomain.class.getName()));
    }

    public CategoriaDomain insert(CategoriaDomain obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public CategoriaDomain update(CategoriaDomain obj){
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete (Integer id) {
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<CategoriaDomain> findAll(){
        return repo.findAll();
    }

    public Page<CategoriaDomain> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public CategoriaDomain fromDto(CategoriaDTO objDto){
        return new CategoriaDomain(objDto.getId(),objDto.getNome());
    }

}
