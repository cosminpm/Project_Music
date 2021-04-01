package umu.tds.persistencia;

import java.util.List;

import beans.Entidad;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario usuario);
	public boolean borrarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(Entidad eUsuario);
	public List<Usuario> recuperarTodosUsuarios();
	public boolean comprobarListaYaExiste(String nombre, Usuario usuario);
	public void setPremium (Usuario usuario, boolean opcion);
}
