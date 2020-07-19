package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.*;
import com.santosglaiton.cursomc.domain.enums.EstadoPagamento;
import com.santosglaiton.cursomc.domain.enums.TipoCliente;
import com.santosglaiton.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {


    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public void instantiateTestDatabase() throws ParseException {
        CategoriaDomain cat1 = new CategoriaDomain(null,"Informática");
        CategoriaDomain cat2 = new CategoriaDomain(null,"Escritório");
        CategoriaDomain cat3 = new CategoriaDomain(null,"Cama mesa e banho");
        CategoriaDomain cat4 = new CategoriaDomain(null,"Eletronicos");
        CategoriaDomain cat5 = new CategoriaDomain(null,"Jardinagem");
        CategoriaDomain cat6 = new CategoriaDomain(null,"Decoração");
        CategoriaDomain cat7 = new CategoriaDomain(null,"Perfumaria");

        ProdutoDomain p1 = new ProdutoDomain(null,"Computador", 2000.00);
        ProdutoDomain p2 = new ProdutoDomain(null, "Impressora", 800.00);
        ProdutoDomain p3 = new ProdutoDomain(null, "Mouse", 80.00);
        ProdutoDomain p4 = new ProdutoDomain(null, "Mesa de escritório", 300.00);
        ProdutoDomain p5 = new ProdutoDomain(null, "Toalha", 50.00);
        ProdutoDomain p6 = new ProdutoDomain(null, "Colcha", 200.00);
        ProdutoDomain p7 = new ProdutoDomain(null, "Tv true color", 1200.00);
        ProdutoDomain p8 = new ProdutoDomain(null, "Roçadeira", 800.00);
        ProdutoDomain p9 = new ProdutoDomain(null, "Abajour", 100.00);
        ProdutoDomain p10 = new ProdutoDomain(null, "Pendente", 100.00);
        ProdutoDomain p11 = new ProdutoDomain(null, "Shampoo", 90.00);


        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));


        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        EstadoDomain est1 = new EstadoDomain(null, "Minas Gerais");
        EstadoDomain est2 = new EstadoDomain(null, "São Paulo");

        CidadeDomain c1 = new CidadeDomain(null, "Uberlandia", est1);
        CidadeDomain c2 = new CidadeDomain(null, "São Paulo", est2);
        CidadeDomain c3 = new CidadeDomain(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        ClienteDomain cli1 = new ClienteDomain(null,"Maria Silva", "maria@gmail.com", "123456789", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("12345467", "122345567"));

        EnderecoDomain e1 = new EnderecoDomain(null, "Rua Flores", "300", "Apto 303", "Jardim", "3821547", cli1, c1);

        EnderecoDomain e2 = new EnderecoDomain(null, "Avenida Matos", "101", "Apto 100", "Centro", "789456123", cli1, c2 );

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 11:23"), cli1, e2);

        PagamentoDomain pagto1 = new PagamentoComCartaoDomain(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        PagamentoDomain pagto2 = new PagamentoComBoletoDomain(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("12/12/2015 13:46"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedidoDomain ip1 = new ItemPedidoDomain(ped1, p1, 0.00, 1, 2000.00);
        ItemPedidoDomain ip2 = new ItemPedidoDomain(ped1, p3, 0.00, 2, 80.00);
        ItemPedidoDomain ip3 = new ItemPedidoDomain(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }

}
