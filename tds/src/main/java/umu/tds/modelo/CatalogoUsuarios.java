package umu.tds.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
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
	
	public List<Usuario> getUsuarios() {
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
	
	public boolean comprobarListaYaExiste(String nombre, Usuario usuario) {
		List<ListaCanciones> lista = usuario.getListaCanciones();
		System.err.println("Imprimiendo numero de playlsit de usuario: "+lista.size());
		for (ListaCanciones playlist : lista) {
			if(playlist.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	public String obtenerCodigosPlayList(List<ListaCanciones> listaPlaylist) {
		String aux = "";
		for (ListaCanciones playlist : listaPlaylist) {
			aux += playlist.getCodigo() + " ";
		}
		return aux.trim();
	}
	
	public void setPremium (Usuario usuario, boolean opcion) {
		if(usuario.getEsPremium() == opcion) {
			return;
		}
		usuario.setEsPremium(opcion);
	}
	
	public boolean comprobarCorreosRegistrados(String email) {	
		List<Usuario> usuarios = this.getUsuarios();
		String aux;
		for (Usuario u : usuarios) {
			aux = u.getEmail();
			if(aux.equals(email))
				return true;
		}
		return false;
	}	
	
	public boolean comprobarUsuarioRegistrado(String login) {	
		List<Usuario> usuarios = this.getUsuarios();
		String aux;
		for (Usuario u : usuarios) {
			aux = u.getLogin();
			if(aux.equals(login))
				return true;
		}
		return false;
	}	
	
	
	public Usuario comprobarLoginPassword(String login, String clave) {
		
		if (login == null || clave == null) {
			return null;
		}
		
		List<Usuario> listaUsuarios = this.getUsuarios();
		for (Usuario usuario1 : listaUsuarios) {
			if(usuario1.getLogin().equals(login) && usuario1.getPassword().equals(clave)) {
				return usuario1;
			}
		}
		return null;	
	}
	
}
