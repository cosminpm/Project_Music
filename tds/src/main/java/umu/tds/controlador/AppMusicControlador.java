package umu.tds.controlador;

import java.time.LocalDate;
import java.util.*;

import umu.tds.persistencia.AdaptadorCancionTDS;
import umu.tds.persistencia.AdaptadorListaCancionesTDS;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.CatalogoListaCanciones;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorListaCancionesDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.componente.*;


public class AppMusicControlador implements CancionesListener {
	
	private static AppMusicControlador unicaInstancia = null;
	private Usuario usuarioActual;
	
	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorListaCancionesDAO adaptadorListaCanciones;
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	private CatalogoListaCanciones catalogoListaCanciones;
	private CargadorCanciones cargadorCanciones = new CargadorCanciones();

	private AppMusicControlador() {
		inicializarAdaptadores(); // debe ser la primera linea para evitar error
		  // de sincronización
		inicializarCatalogos();
	}
	
	
	public static AppMusicControlador getInstancia() {
		if(unicaInstancia == null) {
			unicaInstancia = new AppMusicControlador();
		}
		
		return unicaInstancia;
	}
	
	public void registrarComoOyente() {
		
		cargadorCanciones.addOyente(unicaInstancia);
		
	}
	
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			LocalDate fechaNacimiento) {
		
		if(adaptadorUsuario.comprobarUsuarioRegistrado(login)) {
			return false;
		}
		// Comprobar correos
		
		if(adaptadorUsuario.comprobarCorreosRegistrados(email)) {
			return false;
		}
	
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);
		return true;
	}
	
	
	
	
	public boolean login(String login, String clave) {
		
		//Buscar el string usuario en el catálogo
		//Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(login);
		
		//Buscar el string usuario en base de datos
		Usuario usuario = adaptadorUsuario.comprobarLoginPassword(login, clave);
		if (usuario != null) {
			this.setUsuarioActual(usuario);
			return true;
		}
		return false;
		
		/*
		//Si existe, entonces comprobar clave: usuario.login(clave)
		if( usuario != null && usuario.getPassword().equals(clave)) {
			
			this.usuarioActual = usuario;
			// Si clave correcta, return true
			return true;
		}
		
		//Si no, return false 
		return false;
		*/
	}
	
	public Usuario recuperarUsuario(String login, String password) {
		
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(login);
		return usuario;
		
	}
	
	

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}


	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
		adaptadorListaCanciones = factoria.getListaCancionesDAO();
	}

	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
		catalogoListaCanciones = CatalogoListaCanciones.getUnicaInstancia();
	}
	
	
	
	public void cargarCanciones(String fichero) {

		// componente.setArchivoCanciones(fichero);
		cargadorCanciones.setFichero(fichero);
		cargadorCanciones.setArchivoCanciones();
	
		//Ahora las canciones estan en el atributo de clase archivoCanciones
		//System.out.println("El path es: "+ fichero);

		List<umu.tds.modelo.Cancion> listaCancionesModelo = ListaCanciones.listaCancionesComponenteToListaCancionesModelo(cargadorCanciones.getArchivoCanciones());
		
		for (umu.tds.modelo.Cancion cancion2 : listaCancionesModelo) {
			if (!adaptadorCancion.comprobarAutorTitulo(cancion2.getTitulo(), cancion2.getListaInterpretes()))
				adaptadorCancion.registrarCancion(cancion2);
		}
	}
	
	public void getCanciones(){
		
		
		List<Cancion> lista = adaptadorCancion.recuperarTodasCanciones();
		for (Cancion cancion : lista) {
			System.out.println("Cancion con ruta "+ cancion.getRutaFichero() + "Nombre"+ cancion.getTitulo());
			System.out.println(cancion.getListaInterpretes());
			
		}
	}


	@Override
	public void enteradoCambioCanciones(EventObject ev) {
		if(ev instanceof CancionesEvent){
			 CancionesEvent event=(CancionesEvent)ev;
			 System.out.println("Nuevo archivo"+event.getFicheroNuevo());
			 
			 if(event.getFicheroAntiguo() != null) {
				 
				 System.out.println("Viejo Archivo "+event.getFicheroAntiguo());
			 }
		
	
	         //TODO Actualizar catalogo de canciones...etc	
		}
	}
	
	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo){
		List<Cancion> resultado = new LinkedList<Cancion>();
		// Filtrar segun parametros
		resultado =(List<Cancion>) adaptadorCancion.filtrarCanciones(interprete, titulo, estilo);
		return resultado;
	}
	
	public String printAutoresNice(List<String> autores) {
		String resultado =  "";
		for (String s : autores) {
			resultado = resultado + ", " + s;
		}
		resultado = resultado.substring(2);
		return resultado;
	}

	public LinkedHashSet<String> recuperarEstilos() {
		return adaptadorCancion.recuperarTodosEstilos();
	}
	
	public String comprobarCorrecionTitulo(String titulo) {
		return adaptadorCancion.comprobarCorrecionTitulo(titulo);
	}
	
	public String comprobarCorrecionInterprete(String interprete) {
		return adaptadorCancion.comprobarCorrecionInterprete(interprete);
	}
	
	public Set<Cancion> listToSet(List<Cancion> l){
		return adaptadorCancion.listToSet(l);
	}
	
	public List<Cancion> setToList(Set<Cancion> s){
		return adaptadorCancion.setToList(s);
	}
	
	public List<Cancion> rmRepetidas(List<Cancion> l){
		return adaptadorCancion.rmRepetidas(l);
	}
	
	// Metodos PlayList
	public void registrarListaCanciones(ListaCanciones lista) {
		adaptadorListaCanciones.registrarListaCanciones(lista);
	}
	public void registrarPlayListConVariasCanciones(String nombre, List<Cancion> lista) {
		adaptadorListaCanciones.registrarPlayListConVariasCanciones(nombre, lista);
	}
	
	public boolean comprobarNombreExiste(String nombre) {
		return adaptadorListaCanciones.comprobarNombreExiste(nombre);
	}
	
	public List<ListaCanciones> recuperarTodasListasCanciones(){
		return adaptadorListaCanciones.recuperarTodasListasCanciones();
	}
	public boolean comprobarListaYaExiste(String nombre, Usuario usuario) {
		return adaptadorUsuario.comprobarListaYaExiste(nombre, usuario);
	}
	
	
	
}
