package br.com.loja.dao;

import javax.persistence.EntityManager;

import br.com.loja.produto.Cliente;
import br.com.loja.produto.Produto;

public class ClienteDao {
	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		super();
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId(long id) {
		return em.find(Cliente.class, id);
	}

}
