package br.com.loja.dao;

import javax.persistence.EntityManager;

import br.com.loja.produto.Pedido;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
}
