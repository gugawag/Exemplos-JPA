package com.gugawag.cloudingprojects.bd;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gugawag.cloudingprojects.modelo.Usuario;
import com.gugawag.cloudingprojects.modelo.UsuarioInexistenteException;
import com.gugawag.cloudingprojects.modelo.UsuarioJahExistenteException;

@Stateless
public class UsuarioManager implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void acrescentaAtualizaUsuario(Usuario usuario) throws UsuarioJahExistenteException{
		if (usuario.getCodigo() != null){
			em.merge(usuario);
		} else{
			em.persist(usuario);
		}
	}
	
	public Usuario getUsuarioPorCodigo(int codigo){
		return em.find(Usuario.class, codigo);
	}
	
	public List<Usuario> getUsuarios(){
		return (List<Usuario>)em.createQuery("from Usuario").getResultList();
	}
	
	public Usuario getUsuarioPorMatricula(String matricula){
		List<Usuario> usuarios = (List<Usuario>)em.createQuery("from Usuario u where u.matricula=:matricula").setParameter("matricula", matricula).getResultList();
		if ((usuarios != null) && (usuarios.size()>0)){
			return usuarios.get(0);
		}
		return null;
	}
	
	public void removerUsuarioPorMatricula(Usuario usuario) throws UsuarioInexistenteException{
		Usuario usuarioARemover = getUsuarioPorMatricula(usuario.getMatricula());
		
		em.remove(usuarioARemover);
	}

	public Usuario getUsuarioPorNome(String nome) {
		List<Usuario> usuarios = (List<Usuario>)em.createQuery("from Usuario u where u.nome=:nome").setParameter("nome", nome).getResultList();
		if ((usuarios != null) && (usuarios.size()>0)){
		}
		return null;

	}
}
