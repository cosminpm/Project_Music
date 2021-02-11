package umu.tds.persistencia;

import java.util.List;

import beans.Entidad;
import umu.tds.modelo.Usuario;

public interface IAdaptadorUsuarioDAO {

	public void registrarUsuario(Usuario usuario);
	public boolean borrarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	public Usuario recuperarUsuario(Entidad eUsuario);
	public List<Usuario> recuperarTodosUsuarios();
	public boolean comprobarUsuarioRegistrado(String login);
	public boolean comprobarCorreosRegistrados(String email);
	public Usuario comprobarLoginPassword(String login, String clave);
}
