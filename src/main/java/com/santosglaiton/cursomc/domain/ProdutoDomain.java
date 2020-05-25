package com.santosglaiton.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class ProdutoDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nomeProduto;
    private Double precoProduto;

    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaDomain> categorias = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")
    private Set<ItemPedidoDomain> itens = new HashSet<>();

    public ProdutoDomain() {
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public ProdutoDomain(Integer idProduto, String nomeProduto, Double precoProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }

    @JsonIgnore
    public List<PedidoDomain> getPedidos(){
        List<PedidoDomain> lista = new ArrayList<>();
        for (ItemPedidoDomain x : itens){
            lista.add(x.getPedido());
        }
        return lista;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public List<CategoriaDomain> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDomain> categorias) {
        this.categorias = categorias;
    }

    public Set<ItemPedidoDomain> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedidoDomain> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDomain that = (ProdutoDomain) o;
        return idProduto.equals(that.idProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto);
    }
}
