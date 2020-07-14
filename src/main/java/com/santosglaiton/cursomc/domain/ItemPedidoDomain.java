package com.santosglaiton.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ItemPedidoDomain implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();

    private Double desconto;
    private Integer quantidade;
    private Double preco;

    public ItemPedidoDomain (){
    }

    public ItemPedidoDomain(PedidoDomain pedido, ProdutoDomain produto, Double desconto, Integer quantidade, Double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Double getSubtotal(){
        return ( preco - desconto) * quantidade;
    }

    @JsonIgnore
    public PedidoDomain getPedido(){
        return id.getPedido();
    }

    public ProdutoDomain getProduto(){
        return id.getProduto();
    }

    public void setPedido(PedidoDomain pedido){
        id.setPedido(pedido);
    }

    public void setProduto(ProdutoDomain produto){
        id.setProduto(produto);
    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoDomain that = (ItemPedidoDomain) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
