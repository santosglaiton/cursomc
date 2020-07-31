package com.santosglaiton.cursomc.controller;

import com.santosglaiton.cursomc.dto.CategoriaDTO;
import com.santosglaiton.cursomc.domain.Categoria;
import com.santosglaiton.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable Integer id ){

        Categoria obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")@PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto){
        Categoria obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")@PutMapping("/{id}")
    public ResponseEntity<Void> update (@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id ){
        Categoria obj = service.fromDto(objDto);
        obj.setId(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete ( @PathVariable Integer id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        List<Categoria> list = service.findAll();
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage( @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                        @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction ){
        Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
