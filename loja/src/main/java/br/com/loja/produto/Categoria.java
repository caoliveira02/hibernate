package br.com.loja.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {

	/*@EmbeddedId
	private CategoriaId id;

	public Categoria() {
	}
	
	public Categoria(String nome, String tipo) {
		this.id = new CategoriaId(nome, tipo);
	}

	public String getNome() {
		return this.id.getNome();
	}*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	public Categoria() {
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
