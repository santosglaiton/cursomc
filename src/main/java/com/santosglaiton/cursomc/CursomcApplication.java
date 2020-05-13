package com.santosglaiton.cursomc;

import com.santosglaiton.cursomc.domain.CategoriaDomain;
import com.santosglaiton.cursomc.domain.CidadeDomain;
import com.santosglaiton.cursomc.domain.EstadoDomain;
import com.santosglaiton.cursomc.domain.ProdutoDomain;
import com.santosglaiton.cursomc.repositories.CategoriaRepository;
import com.santosglaiton.cursomc.repositories.CidadeRepository;
import com.santosglaiton.cursomc.repositories.EstadoRepository;
import com.santosglaiton.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		CategoriaDomain cat1 = new CategoriaDomain(null,"Informática");
		CategoriaDomain cat2 = new CategoriaDomain(null,"Escritório");

		ProdutoDomain p1 = new ProdutoDomain(null,"Computador", 2000.00);
		ProdutoDomain p2 = new ProdutoDomain(null, "Impressora", 800.00);
		ProdutoDomain p3 = new ProdutoDomain(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		EstadoDomain est1 = new EstadoDomain(null, "Minas Gerais");
		EstadoDomain est2 = new EstadoDomain(null, "São Paulo");

		CidadeDomain c1 = new CidadeDomain(null, "Uberlandia", est1);
		CidadeDomain c2 = new CidadeDomain(null, "São Paulo", est2);
		CidadeDomain c3 = new CidadeDomain(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

	}
}
