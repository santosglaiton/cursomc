package com.santosglaiton.cursomc.DTO;

import com.santosglaiton.cursomc.domain.ProdutoDomain;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO(){
    }

    public ProdutoDTO(ProdutoDomain obj){
        id = obj.getIdProduto();
        nome = obj.getNomeProduto();
        preco = obj.getPrecoProduto();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
