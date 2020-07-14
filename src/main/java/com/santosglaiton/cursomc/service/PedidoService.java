package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.ItemPedidoDomain;
import com.santosglaiton.cursomc.domain.PagamentoComBoletoDomain;
import com.santosglaiton.cursomc.domain.PagamentoDomain;
import com.santosglaiton.cursomc.domain.PedidoDomain;
import com.santosglaiton.cursomc.domain.enums.EstadoPagamento;
import com.santosglaiton.cursomc.repositories.ItemPedidoRepository;
import com.santosglaiton.cursomc.repositories.PagamentoRepository;
import com.santosglaiton.cursomc.repositories.PedidoRepository;
import com.santosglaiton.cursomc.repositories.ProdutoRepository;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repo;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoDomain find(Integer id){

        Optional<PedidoDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + PedidoDomain.class.getName()));
    }

    @Transactional
    public PedidoDomain insert(PedidoDomain obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoletoDomain) {
            PagamentoComBoletoDomain pagto = (PagamentoComBoletoDomain) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedidoDomain ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getIdProduto()));
            ip.setPreco(ip.getProduto().getPrecoProduto());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }

}
