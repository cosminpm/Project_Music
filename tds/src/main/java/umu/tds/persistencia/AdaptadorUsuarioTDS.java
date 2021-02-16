package umu.tds.persistencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.Constantes;
import umu.tds.modelo.Usuario;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia(); 
	}
	
	
	public void registrarUsuario(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		boolean existe = true; 
		
		// Si la entidad está registrada no la registra de nuevo
		
		eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		if (eUsuario == null) {
			existe = false;
		}
			
		if (existe) {
			return;
		}

		// crear entidad Usuario
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		
		//Pasar LocalDate a String con el formato que deseamos almacenar
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.fecha_format);
		String fechaCodificada = usuario.getFechaNacimiento().format(formatter);
		
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", usuario.getNombre()), new Propiedad("apellidos", usuario.getApellidos()),
						new Propiedad("email", usuario.getEmail()), new Propiedad("login", usuario.getLogin()), 
				        new Propiedad("password", usuario.getPassword()), new Propiedad("fechanacimiento", fechaCodificada))));
		
		// registrar entidad usuario
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		usuario.setCodigo(eUsuario.getId()); 
	}
	
	
	
	public boolean borrarUsuario(Usuario usuario) {
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		
		return servPersistencia.borrarEntidad(eUsuario);
	}
	
	
	public void modificarUsuario(Usuario usuario) {

		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		
		//Pasar LocalDate a String con el formato que deseamos almacenar
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.fecha_format);
		String fechaCodificada = usuario.getFechaNacimiento().format(formatter);
		
		
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos", usuario.getApellidos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "login");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "login", usuario.getLogin());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechanacimiento");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechanacimiento", fechaCodificada);
		
	}
	
	public Usuario recuperarUsuario(Entidad eUsuario) {

		
		String nombre;
		String apellidos;
		String email;
		String login;
		String password;
		LocalDate fecha;
		String fechanacimiento;


		// recuperar propiedades que no son objetos
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		login = servPersistencia.recuperarPropiedadEntidad(eUsuario, "login");
		password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		fechanacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechanacimiento");
		
		// Pasar la fecha con nuestro formato a LocalDate
		//Pasar string a LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.fecha_format);
		fecha = LocalDate.parse(fechanacimiento,formatter);
		

		Usuario usuario = new Usuario(nombre,apellidos,email,login,password,fecha);
		usuario.setCodigo(eUsuario.getId());
	
		return usuario;
	}
	
	public List<Usuario> recuperarTodosUsuarios() {

		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		List<Usuario> usuarios = new LinkedList<Usuario>();

		for (Entidad eUsuario : eUsuarios) {
			usuarios.add(recuperarUsuario(eUsuario));
		}
		return usuarios;
	}

	
	
	public boolean comprobarUsuarioRegistrado(String login) {	
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		String aux;
		for (Entidad eUsuario : eUsuarios) {
			aux = servPersistencia.recuperarPropiedadEntidad(eUsuario, "login");	
			if(aux.equals(login))
				return true;
		}
		return false;
	}
	
	public boolean comprobarCorreosRegistrados(String email) {	
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		String aux;
		for (Entidad eUsuario : eUsuarios) {
			aux = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");	
			if(aux.equals(email))
				return true;
		}
		return false;
	}
	
	public Usuario comprobarLoginPassword(String login, String clave) {
		
		if (login == null || clave == null) {
			return null;
		}
		
		List<Usuario> listaUsuarios = recuperarTodosUsuarios();
		for (Usuario usuario1 : listaUsuarios) {
			System.out.println(usuario1.toString());
			if(usuario1.getLogin().equals(login) && usuario1.getPassword().equals(clave)) {
				return usuario1;
			}
		}
		return null;	
	}
}
