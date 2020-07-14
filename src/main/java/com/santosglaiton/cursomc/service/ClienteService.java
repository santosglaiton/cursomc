package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.dto.ClienteDTO;
import com.santosglaiton.cursomc.dto.ClienteNewDTO;
import com.santosglaiton.cursomc.domain.CidadeDomain;
import com.santosglaiton.cursomc.domain.ClienteDomain;
import com.santosglaiton.cursomc.domain.EnderecoDomain;
import com.santosglaiton.cursomc.domain.enums.TipoCliente;
import com.santosglaiton.cursomc.repositories.CidadeRepository;
import com.santosglaiton.cursomc.repositories.ClienteRepository;
import com.santosglaiton.cursomc.repositories.EnderecoRepository;
import com.santosglaiton.cursomc.service.exceptions.DataIntegrityException;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public ClienteDomain find(Integer id){

        Optional<ClienteDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + ClienteDomain.class.getName()));
    }

    @Transactional
    public ClienteDomain insert(ClienteDomain obj){
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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

    public ClienteDomain fromDto(ClienteNewDTO objDto){
        ClienteDomain cli = new ClienteDomain(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        CidadeDomain cid = cidadeRepository.getOne(objDto.getCidadeId());
        EnderecoDomain end = new EnderecoDomain(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if(objDto.getTelefone2()!= null){
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if(objDto.getTelefone3()!= null){
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }

    private void updateData(ClienteDomain newObj, ClienteDomain obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

}
