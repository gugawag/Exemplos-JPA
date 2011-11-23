package com.gugawag.cloudingprojects.modelo;

import java.io.Serializable;
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String matricula;
	private String nome;
	private String login;
	private String senha;


	public UsuarioVO() {
		this(null, null, null, null);
	}

	public UsuarioVO(String matricula, String nome, String login, String senha) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String toString() {
		return "Matr’cula: [" + matricula + "] Nome: [" + nome + "] Login: ["
				+ login + "]";
	}

}
