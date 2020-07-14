package com.santosglaiton.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class PedidoDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instante;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private PagamentoDomain pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteDomain cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private EnderecoDomain enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedidoDomain> itens = new HashSet<>();

    public PedidoDomain() {
    }

    public PedidoDomain(Integer id, Date instante, ClienteDomain cliente, EnderecoDomain enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public double getValorTotal(){
        Double soma = 0.0;
        for(ItemPedidoDomain ip : itens){
            soma = soma + ip.getSubtotal();
        }
        return soma;
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
        PedidoDomain that = (PedidoDomain) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
