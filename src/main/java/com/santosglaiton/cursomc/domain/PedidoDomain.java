package com.santosglaiton.cursomc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class PedidoDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private PagamentoDomain pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteDomain cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private EnderecoDomain enderecoDeEntrega;

    public PedidoDomain() {
    }

    public PedidoDomain(Integer id, Date instante, ClienteDomain cliente, EnderecoDomain enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public PagamentoDomain getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDomain pagamento) {
        this.pagamento = pagamento;
    }

    public ClienteDomain getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDomain cliente) {
        this.cliente = cliente;
    }

    public EnderecoDomain getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(EnderecoDomain enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDomain that = (PedidoDomain) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
