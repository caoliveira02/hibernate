package br.com.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.produto.Categoria;
import br.com.loja.produto.Produto;
import br.com.loja.util.JPAUtil;

public class CadastroDePoduto {
	
	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");
		Produto produto = new Produto("Iphone", "Iphone 11", new BigDecimal(1000), celulares);
			
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(produto);
		em.getTransaction().commit();
		em.close();
	}
}
