package umu.tds.controlador;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.time.LocalDate;
import java.util.*;

import umu.tds.persistencia.AdaptadorCancionTDS;
import umu.tds.persistencia.AdaptadorListaCancionesTDS;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.CatalogoUsuarios;
import umu.tds.modelo.DescuentoFijo;
import umu.tds.modelo.DescuentoJovenes;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.IAdaptadorCancionDAO;
import umu.tds.persistencia.IAdaptadorListaCancionesDAO;
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.VentanaMisListas;
import umu.tds.componente.*;

public class AppMusicControlador implements CancionesListener {

	private MediaPlayer mediaPlayer;
	private String binPath;
	private String tempPath;

	private static AppMusicControlador unicaInstancia = null;
	private Usuario usuarioActual;
	private String tipoDescuento = "";

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;
	private IAdaptadorListaCancionesDAO adaptadorListaCanciones;
	
	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	private CargadorCanciones cargadorCanciones = new CargadorCanciones();
	
	private AppMusicControlador() {
		inicializarAdaptadores(); // debe ser la primera linea para evitar error
		// de sincronización
		try {
			inicializarCatalogos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AppMusicControlador getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new AppMusicControlador();
		}

		return unicaInstancia;
	}

	public void registrarComoOyente() {

		cargadorCanciones.addOyente(unicaInstancia);

	}

	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password,
			LocalDate fechaNacimiento) {

		if (catalogoUsuarios.comprobarUsuarioRegistrado(login)) {
			return false;
		}
		// Comprobar correos
		if (catalogoUsuarios.comprobarCorreosRegistrados(email)) {
			return false;
		}
		Usuario usuario = new Usuario(nombre, apellidos, email, login, password, fechaNacimiento);
		adaptadorUsuario.registrarUsuario(usuario);
		catalogoUsuarios.addUsuario(usuario);
		return true;
	}

	public boolean login(String login, String clave) {

		// Buscar el string usuario en el catálogo
		// Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(login);

		// Buscar el string usuario en base de datos
		Usuario usuario = catalogoUsuarios.comprobarLoginPassword(login, clave);
		if (usuario != null) {
			this.setUsuarioActual(usuario);
			return true;
		}
		return false;

		/*
		 * //Si existe, entonces comprobar clave: usuario.login(clave) if( usuario !=
		 * null && usuario.getPassword().equals(clave)) {
		 * 
		 * this.usuarioActual = usuario; // Si clave correcta, return true return true;
		 * }
		 * 
		 * //Si no, return false return false;
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

	// TODO
	private void inicializarCatalogos() throws DAOException {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	// Este metodo carga las canciones el la base de datos
	public void cargarCanciones(String fichero) {
		cargadorCanciones.setFichero(fichero);
		cargadorCanciones.setArchivoCanciones();
		// Ahora las canciones estan en el atributo de clase archivoCanciones
		List<umu.tds.modelo.Cancion> listaCancionesModelo = ListaCanciones
				.listaCancionesComponenteToListaCancionesModelo(cargadorCanciones.getArchivoCanciones());
		for (umu.tds.modelo.Cancion cancion2 : listaCancionesModelo) {
			if (!catalogoCanciones.comprobarAutorTitulo(cancion2.getTitulo(), cancion2.getListaInterpretes())) {
				adaptadorCancion.registrarCancion(cancion2);
				catalogoCanciones.addCancion(cancion2);
			}
				
		}
		// Hay que llamar otra vez al catalogo para rellenarlo puesto que hemos
		// modificado la base de datos al cargar las canciones
		catalogoCanciones = CatalogoCanciones.getUnicaInstancia();
	}

	// TODO Borrar
	/*
	 * public void getCanciones(){ List<Cancion> lista =
	 * adaptadorCancion.recuperarTodasCanciones(); for (Cancion cancion : lista) {
	 * System.out.println("Cancion con ruta "+ cancion.getRutaFichero() + "Nombre"+
	 * cancion.getTitulo()); System.out.println(cancion.getListaInterpretes()); } }
	 */

	// TODO Ver que sucede con los eventos
	@Override
	public void enteradoCambioCanciones(EventObject ev) {
		if (ev instanceof CancionesEvent) {
			CancionesEvent event = (CancionesEvent) ev;
			System.out.println("Nuevo archivo" + event.getFicheroNuevo());
			if (event.getFicheroAntiguo() != null) {
				System.out.println("Viejo Archivo " + event.getFicheroAntiguo());
			}
			// TODO Actualizar catalogo de canciones...etc
		}
	}

	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo) {
		List<Cancion> resultado = new LinkedList<Cancion>();
		// Filtrar segun parametros
		resultado = (List<Cancion>) catalogoCanciones.filtrarCanciones(interprete, titulo, estilo);
		return resultado;
	}

	public String printAutoresNice(List<String> autores) {
		String resultado = "";
		for (String s : autores) {
			resultado = resultado + ", " + s;
		}
		resultado = resultado.substring(2);
		return resultado;
	}

	// TODO Ver como hacemos esto
	public LinkedHashSet<String> recuperarEstilos() {
		return catalogoCanciones.recuperarTodosEstilos();
	}

	public String comprobarCorrecionTitulo(String titulo) {
		return catalogoCanciones.comprobarCorrecionTitulo(titulo);
	}

	public String comprobarCorrecionInterprete(String interprete) {
		return catalogoCanciones.comprobarCorrecionInterprete(interprete);
	}

	public Set<Cancion> listToSet(List<Cancion> l) {
		return catalogoCanciones.listToSet(l);
	}

	public List<Cancion> setToList(Set<Cancion> s) {
		return catalogoCanciones.setToList(s);
	}

	public List<Cancion> rmRepetidas(List<Cancion> l) {
		return catalogoCanciones.rmRepetidas(l);
	}

	//TODO Modificar
	// Metodos PlayList
	public void registrarListaCanciones(ListaCanciones lista) {
		usuarioActual.registrarListaCanciones(lista);
	}
	// TODO MODIFICAR ESTO SOLUCIONAR SALEN DOS VECES EL NOMBRE DE LAS LISTAS
	public void registrarPlayListConVariasCanciones(String nombre, List<Cancion> lista) {
		
		//usuarioActual.registrarPlayListConVariasCanciones(nombre, lista);
		usuarioActual.registrarPlayListConVariasCanciones(nombre, lista);
	}

	public boolean comprobarNombreExiste(String nombre) {
		return usuarioActual.comprobarNombreExiste(nombre);
	}

	public List<ListaCanciones> recuperarTodasListasCanciones() {
		return usuarioActual.getListaPlayList();
	}

	/*
	public boolean comprobarListaYaExiste(String nombre, Usuario usuario) {
		return adaptadorUsuario.comprobarListaYaExiste(nombre, usuario);
	}*/
	
	public boolean comprobarListaYaExiste(String nombre) {
		return usuarioActual.comprobarListaYaExiste(nombre);
	}
	

	public void modificarPlayList(ListaCanciones lista) {
		usuarioActual.modificarListaCanciones(lista);
	}

	public void play(Cancion cancion) {
		// Cada vez que se reproduce, añadir a Recientes
		List<ListaCanciones> lista = usuarioActual.getListaPlayList();
		System.err.println("Printeando todas las listas en PLAY");
		for (ListaCanciones listaCanciones : lista) {
			System.out.println(listaCanciones.getNombre());
		}
		System.err.println("FIN todas las listas en PLAY");

		// TODO TRATAR BOOLEANO
		mediaPlayer = null;
		binPath = AppMusicControlador.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// System.out.println(binPath);
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");
		tempPath = tempPath.replace("%20", " ");
		System.out.println(tempPath);
		// tempPath = "C:/Users/crist/OneDrive/Escritorio/Proyecto Actual
		// TDS/Project_Music/tds/target/classes/";
		System.out.println(tempPath);
		URL uri = null;
		try {
			com.sun.javafx.application.PlatformImpl.startup(() -> {
			});

			uri = new URL(cancion.getRutaFichero());

			System.setProperty("java.io.tmpdir", tempPath);
			Path mp3 = Files.createTempFile("now-playing", ".mp3");

			System.out.println(mp3.getFileName());
			try (InputStream stream = uri.openStream()) {
				Files.copy(stream, mp3, StandardCopyOption.REPLACE_EXISTING);
			}
			System.out.println("finished-copy: " + mp3.getFileName());

			Media media = new Media(mp3.toFile().toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			mediaPlayer.play();
			cancion.aumentarNumReproducciones();
			
			AdaptadorCancionTDS.getUnicaInstancia().modificarCancion(cancion);
			
			Cancion cancion2 = AdaptadorCancionTDS.getUnicaInstancia().recuperarCancion(cancion.getCodigo());
			
			System.err.println("PERSIS: NUM REPRO "+cancion2.getNumReproducciones() + " DE CANCION: "+ cancion2.getTitulo());
			
			System.err.println("MODELO: NUM REPRO "+cancion.getNumReproducciones() + " DE CANCION: "+ cancion.getTitulo());
			
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void stop(Cancion cancion) {
		if (mediaPlayer != null)
			mediaPlayer.stop();
		File directorio = new File(tempPath);
		String[] files = directorio.list();
		for (String archivo : files) {
			File fichero = new File(tempPath + File.separator + archivo);
			fichero.delete();
		}
	}

	public void crearRecientes() {
		if (usuarioActual.getListaPlayList().size() == 0) {
			ListaCanciones playList = new ListaCanciones("Recientes");
			this.registrarListaCanciones(playList);
		}
	}

	public void aniadirRecientes(Cancion cancion) {
		usuarioActual.aniadirRecientes(cancion);

	}

	// Metodo que modifica si el usuario es premium tanto en el catalogo como en la
	// persistencia
	public void setPremium(boolean opcion) {
		usuarioActual.setEsPremium(opcion);
	}

	public int calcularDescuentoFijo() {
		DescuentoFijo descuentoFijo = new DescuentoFijo();
		descuentoFijo.calcDescuento();
		return descuentoFijo.getDescuento();
	}

	public int calcularDescuentoJoven() {
		DescuentoJovenes descuentoJoven = new DescuentoJovenes();
		descuentoJoven.calcDescuento();
		return descuentoJoven.getDescuento();
	}

	public String getTipoDescuento() {
		return tipoDescuento;
	}

	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public boolean compararDescuento(String descuento) {
		if (this.tipoDescuento.equals(descuento)) {
			System.out.println("");
			return true;
		}
		return false;
	}

}
