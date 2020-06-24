package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.DTO.CategoriaDTO;
import com.santosglaiton.cursomc.DTO.ClienteDTO;
import com.santosglaiton.cursomc.domain.CategoriaDomain;
import com.santosglaiton.cursomc.domain.ClienteDomain;
import com.santosglaiton.cursomc.repositories.ClienteRepository;
import com.santosglaiton.cursomc.service.exceptions.DataIntegrityException;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public ClienteDomain insert(ClienteDomain obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public ClienteDomain update(ClienteDomain obj){
        ClienteDomain newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete (Integer id) {
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível porque há entidades relacionadas");
        }
    }

    public List<ClienteDomain> findAll(){
        return repo.findAll();
    }

    public Page<ClienteDomain> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public ClienteDomain fromDto(ClienteDTO objDto){
        return new ClienteDomain(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(ClienteDomain newObj, ClienteDomain obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
