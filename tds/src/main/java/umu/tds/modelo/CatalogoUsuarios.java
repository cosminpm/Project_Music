package umu.tds.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class CatalogoUsuarios {
	private static CatalogoUsuarios unicaInstancia;
	private FactoriaDAO factoria;

	private HashMap<Integer, Usuario> asistentesPorID;
	private HashMap<String, Usuario> asistentesPorLogin;

	public static CatalogoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoUsuarios();
		return unicaInstancia;
	}
	 

	private CatalogoUsuarios (){
		asistentesPorID = new HashMap<Integer, Usuario>();
		asistentesPorLogin = new HashMap<String, Usuario>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Usuario> listaAsistentes = factoria.getUsuarioDAO().recuperarTodosUsuarios();
			for (Usuario usuario : listaAsistentes) {
				asistentesPorID.put(usuario.getCodigo(), usuario);
				asistentesPorLogin.put(usuario.getLogin(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Usuario> getUsuarios() throws DAOException {
		return new LinkedList<Usuario>(asistentesPorLogin.values());
	}
	
	public Usuario getUsuario(String login) {
		return asistentesPorLogin.get(login);
	}

	public Usuario getUsuario(int id) {
		return asistentesPorID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		asistentesPorID.put(usuario.getCodigo(), usuario);
		asistentesPorLogin.put(usuario.getLogin(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		asistentesPorID.remove(usuario.getCodigo());
		asistentesPorLogin.remove(usuario.getLogin());
	}

}