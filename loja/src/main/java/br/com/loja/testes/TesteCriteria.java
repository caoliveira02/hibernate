package br.com.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ClienteDao;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.produto.Categoria;
import br.com.loja.produto.Cliente;
import br.com.loja.produto.Produto;
import br.com.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		/*produtoDao.buscarPorParametrosComCriteria("NOTEBOOK", null, null);*/
		produtoDao.buscarPorParametrosComCriteria("NOTEBOOK", null, LocalDate.now());
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria computadores = new Categoria("COMPUTADOR");
		Categoria eletronicos = new Categoria("ELETRONICOS");
		
		Produto produto = new Produto("Iphone", "Iphone 11", new BigDecimal(1000), celulares);
		Produto produto2 = new Produto("NOTEBOOK", "DELL", new BigDecimal(7000), computadores);
		Produto produto3 = new Produto("Barbeador", "LG", new BigDecimal(500), eletronicos);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(computadores);
		categoriaDao.cadastrar(eletronicos);
		
		produtoDao.cadastrar(produto);
		produtoDao.cadastrar(produto2);
		produtoDao.cadastrar(produto3);
		
		em.getTransaction().commit();
		em.close();
	}
	
}
