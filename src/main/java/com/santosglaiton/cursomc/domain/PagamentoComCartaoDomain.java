package com.santosglaiton.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.santosglaiton.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartaoDomain extends PagamentoDomain{

    private Integer numeroDeParcelas;

    public PagamentoComCartaoDomain(){
    }

    public PagamentoComCartaoDomain(Integer id, EstadoPagamento estado, PedidoDomain pedido, Integer numeroDeParcelas){
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
