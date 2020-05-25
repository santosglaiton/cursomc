package com.santosglaiton.cursomc.domain;

import com.santosglaiton.cursomc.repositories.ProdutoRepository;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemPedidoPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoDomain pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoDomain produto;

    public PedidoDomain getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDomain pedido) {
        this.pedido = pedido;
    }

    public ProdutoDomain getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDomain produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoPk that = (ItemPedidoPk) o;
        return pedido.equals(that.pedido) &&
                produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido, produto);
    }
}
