package com.santosglaiton.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.santosglaiton.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PagamentoDomain implements Serializable {

    @Id
    private Integer id;
    private Integer estado;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private PedidoDomain pedido;

    public PagamentoDomain(){

    }

    public PagamentoDomain(Integer id, EstadoPagamento estado, PedidoDomain pedido) {
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
    }

    public PedidoDomain getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDomain pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoDomain that = (PagamentoDomain) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
