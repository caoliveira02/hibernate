package br.com.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ClienteDao;
import br.com.loja.dao.PedidoDao;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.produto.Categoria;
import br.com.loja.produto.Cliente;
import br.com.loja.produto.ItemPedido;
import br.com.loja.produto.Pedido;
import br.com.loja.produto.Produto;
import br.com.loja.util.JPAUtil;
import br.com.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		
		ClienteDao clienteDao = new ClienteDao(em);
		Cliente cliente = clienteDao.buscarPorId(1l);
		Cliente cliente2 = clienteDao.buscarPorId(2l);
		
		em.getTransaction().begin();
		Pedido pedido = new Pedido(cliente);
		pedido.adcionarItem(new ItemPedido(10, pedido, produto));

		Pedido pedido2 = new Pedido(cliente2);
		pedido.adcionarItem(new ItemPedido(5, pedido2, produto2));
		pedido.adcionarItem(new ItemPedido(7, pedido2, produto3));

		
		PedidoDao pedidoDao = new PedidoDao(em);
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		em.getTransaction().commit();
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		System.out.println("VALOR TOTAL VENDIDO: " + totalVendido);
		
		List<Object[]> relatorio = pedidoDao.relatorioDeVendas();
		for (Object[] obj : relatorio) {
			System.out.println("Produto:");
			System.out.println(obj[0]);
			System.out.println(obj[1]);
			System.out.println(obj[2]);
		}
		
		
		List<RelatorioDeVendasVo> relatorio2 = pedidoDao.relatorioDeVendasVO();
		relatorio2.forEach(System.out::println);
		
		Pedido pedido1A = pedidoDao.buscarPedidoComCliente(1l);
		em.close();
		System.out.println(pedido1A.getCliente().getNome());
		
	}
	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria computadores = new Categoria("COMPUTADOR");
		Categoria eletronicos = new Categoria("ELETRONICOS");
		
		Produto produto = new Produto("Iphone", "Iphone 11", new BigDecimal(1000), celulares);
		Produto produto2 = new Produto("NOTEBOOK", "DELL", new BigDecimal(7000), computadores);
		Produto produto3 = new Produto("Barbeador", "LG", new BigDecimal(500), eletronicos);
		
		Cliente cliente = new Cliente("Carlos","123.560.980-11");
		Cliente cliente2 = new Cliente("Antonio","493.560.980-11");
		

		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(computadores);
		categoriaDao.cadastrar(eletronicos);
		
		produtoDao.cadastrar(produto);
		produtoDao.cadastrar(produto2);
		produtoDao.cadastrar(produto3);
		
		clienteDao.cadastrar(cliente);
		clienteDao.cadastrar(cliente2);
		
		em.getTransaction().commit();
		
		//em.find(Categoria.class , new CategoriaId("CELULARES", "TESTE"));
		
		em.close();
	}
}


