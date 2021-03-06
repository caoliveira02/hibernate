package br.com.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.loja.produto.Pedido;
import br.com.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<Object[]> relatorioDeVendas() {
		
		String jpql = "SELECT produto.nome, "
				+ "SUM(item.quantidade), "
			    + "MAX(pedido.dataCadastro) "
				+ "FROM Pedido pedido "
			    + "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
			    + "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return em.createQuery(jpql, Object[].class)
				.getResultList();
	}
	
	public  List<RelatorioDeVendasVo> relatorioDeVendasVO() {
		String jpql = "SELECT new br.com.loja.vo.RelatorioDeVendasVo (" 
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
			    + "MAX(pedido.dataCadastro)) "
				+ "FROM Pedido pedido "
			    + "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
			    + "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		
		return em.createQuery(jpql, RelatorioDeVendasVo.class)
				.getResultList();
	}
	
	public Pedido buscarPedidoComCliente(long id) {
		/*JOIN FETCH ? para carregar o cliente relacionado*/
		return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
