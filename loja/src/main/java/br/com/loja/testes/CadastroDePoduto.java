package br.com.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.produto.Categoria;
import br.com.loja.produto.Produto;
import br.com.loja.util.JPAUtil;

public class CadastroDePoduto {
	
	public static void main(String[] args) {
		
		cadastrarProduto();
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());

		List<Produto> todos = produtoDao.buscarTodos();
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		List<Produto> porNome = produtoDao.buscarPorNome("Iphone");
		porNome.forEach(p3 -> System.out.println(p.getDescricao()));

		List<Produto> porCategoria = produtoDao.buscarPorCategoria("CELULARES");
		porCategoria.forEach(p4 -> System.out.println(p.getDataCadastro()));
		
		BigDecimal precoProduto = produtoDao.buscarPrecoDoProdutoComNome("Iphone");
		System.out.println("Preço do produto: " + precoProduto);

		/*List<Produto> produtoPorCategoria = produtoDao.buscarProdutoPorCategoria("CELULARES");
		produtoPorCategoria.forEach(p4 -> System.out.println("NamedQuey:" + p.getDataCadastro()));*/
	}

	private static void cadastrarProduto() {
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
