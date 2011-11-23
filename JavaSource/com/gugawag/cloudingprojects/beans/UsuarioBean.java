package com.gugawag.cloudingprojects.beans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.gugawag.cloudingprojects.bd.UsuarioManager;
import com.gugawag.cloudingprojects.modelo.Mensagem;
import com.gugawag.cloudingprojects.modelo.Usuario;
import com.gugawag.cloudingprojects.modelo.UsuarioInexistenteException;
import com.gugawag.cloudingprojects.modelo.UsuarioJahExistenteException;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	@EJB
	private UsuarioManager usuarioManager;
	
	private Usuario usuario, usuarioPesquisado;
	private DataModel dmUsuarios;
	

	public UsuarioBean() {
		usuario = new Usuario();
		usuarioPesquisado = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DataModel getUsuarios() {
		return (dmUsuarios = new ListDataModel(usuarioManager.getUsuarios())); 
	}

	public Usuario getUsuarioPesquisado() {
		return usuarioPesquisado;
	}

	public void setUsuarioPesquisado(Usuario usuarioPesquisado) {
		this.usuarioPesquisado = usuarioPesquisado;
	}

	public String cadastrar() {

		try {
			
			Mensagem mensagem = new Mensagem("hello world");
			usuario.enviarMensagem(mensagem);
			usuarioManager.acrescentaAtualizaUsuario(usuario);
			
			usuario.setNome(usuario.getNome() + " - Alterado");
			for(Mensagem m: usuario.getMensagens()){
				m.setTexto(m.getTexto() + " - Alterada");
			}
			usuarioManager.acrescentaAtualizaUsuario(usuario);
			
//			usuario.setNome(usuario.getNome() + " - Alterado");
//			for (Mensagem m: usuario.getMensagens()){
//				m.setTexto(m.getTexto() + " - Alterado");
//			}
//			usuarioManager.acrescentaAtualizaUsuario(usuario);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario cadastrado"));
		} catch (UsuarioJahExistenteException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String pesquisarPorNome(){
		this.usuarioPesquisado = usuarioManager.getUsuarioPorNome(this.usuarioPesquisado.getNome());
		usuarioPesquisado.setNome(usuarioPesquisado.getNome() + " ALTERADO BB ");
		if (usuarioPesquisado == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Nenhum usuario foi encontrado!"));
		}
		return null;
	}
	
	public String removerUsuario(){
		Usuario usuarioARemover = (Usuario) dmUsuarios.getRowData();
		try {
			usuarioManager.removerUsuarioPorMatricula(usuarioARemover);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario removido com sucesso!"));
		} catch (UsuarioInexistenteException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("N‹o existe usuario com esta matr’cula!"));
		}
		return null;
	}
	
}
