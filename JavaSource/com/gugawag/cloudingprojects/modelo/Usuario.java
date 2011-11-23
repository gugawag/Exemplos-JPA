package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer codigo;
	@Column(name="mt")
	private String matricula;
	private String nome;
	private String login;
	private String senha;

	@OneToMany(mappedBy="enviador", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Mensagem> mensagens;
	
	public Usuario() {
		this(null, null, null, null);
	}

	public Usuario(String matricula, String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.matricula = matricula;
		mensagens = new ArrayList<Mensagem>();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	public void enviarMensagem(Mensagem mensagem){
		mensagem.setEnviador(this);
		this.mensagens.add(mensagem);
	}

	public String toString() {
		return "Matr’cula: [" + matricula + "] Nome: [" + nome + "] Login: ["
				+ login + "]";
	}

}
