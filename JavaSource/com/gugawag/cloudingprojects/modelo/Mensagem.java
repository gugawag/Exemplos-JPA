package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mensagem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer codigo;
	private String texto;
	
	@ManyToOne
	private Usuario enviador;
	
	public Mensagem() {
		this(null, null);
	}
	
	public Mensagem(String texto) {
		this(texto, null);
	}

	
	public Mensagem(String texto, Usuario enviador) {
		super();
		this.texto = texto;
		this.enviador = enviador;
	}


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getEnviador() {
		return enviador;
	}

	public void setEnviador(Usuario enviador) {
		this.enviador = enviador;
	}

}
