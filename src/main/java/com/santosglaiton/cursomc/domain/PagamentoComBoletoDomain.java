package com.santosglaiton.cursomc.domain;

import com.santosglaiton.cursomc.domain.enums.EstadoPagamento;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PagamentoComBoletoDomain extends PagamentoDomain {

    private Date dataVencimento;
    private Date dataPagamento;

    public PagamentoComBoletoDomain(){
    }

    public PagamentoComBoletoDomain(Integer id, EstadoPagamento estado, PedidoDomain pedido, Date dataVencimento, Date dataPagamento){
        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
