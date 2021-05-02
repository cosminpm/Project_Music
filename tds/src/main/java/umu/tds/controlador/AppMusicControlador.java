package umu.tds.controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import umu.tds.persistencia.AdaptadorUsuarioTDS;
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
import umu.tds.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.componente.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AppMusicControlador implements CancionesListener {

	private MediaPlayer mediaPlayer;
	private String binPath;
	private String tempPath;

	private static AppMusicControlador unicaInstancia = null;
	private Usuario usuarioActual;
	private String tipoDescuento = "";

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorCancionDAO adaptadorCancion;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoCanciones catalogoCanciones;
	private CargadorCanciones cargadorCanciones = new CargadorCanciones();

	private AppMusicControlador() {
		inicializarAdaptadores(); // debe ser la primera linea para evitar error de sincronización
		try {
			inicializarCatalogos();
		} catch (DAOException e) {
			// Auto-generated catch block
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
	}

	// Recuperar el usuario que nos pasen como login
	public Usuario recuperarUsuario(String login) {
		Usuario usuario = CatalogoUsuarios.getUnicaInstancia().getUsuario(login);
		return usuario;
	}
	// Recuperamos el usuario actual
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	// Para cambiar el usuario actual
	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	// Incializacion de Adaptadores
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorCancion = factoria.getCancionDAO();
	}
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

	@Override
	public void enteradoCambioCanciones(EventObject ev) {
		if (ev instanceof CancionesEvent) {
			CancionesEvent event = (CancionesEvent) ev;
			System.out.println("Nuevo archivo" + event.getFicheroNuevo());
			if (event.getFicheroAntiguo() != null) {
				System.out.println("Viejo Archivo " + event.getFicheroAntiguo());
			}
		}
	}
	// Para filtrar las canciones
	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo) {
		List<Cancion> resultado = new LinkedList<Cancion>();
		// Filtrar segun parametros
		resultado = (List<Cancion>) catalogoCanciones.filtrarCanciones(interprete, titulo, estilo);
		return resultado;
	}
	// Para imprimir de forma correcta los autores
	public String printAutoresNice(List<String> autores) {
		String resultado = "";
		for (String s : autores) {
			resultado = resultado + ", " + s;
		}
		resultado = resultado.substring(2);
		return resultado;
	}
	// Para recuperar los estilos de la base de datos
	public LinkedHashSet<String> recuperarEstilos() {
		return catalogoCanciones.recuperarTodosEstilos();
	}
	// Para comprobar si es TITULO que es lo que hay por defecto o es otro
	public String comprobarCorrecionTitulo(String titulo) {
		return catalogoCanciones.comprobarCorrecionTitulo(titulo);
	}
	// Para comprobar si lo que hay escrito es INTERPRETE o otro, esto nos servira para si es INTERPRETE se busquen todos
	public String comprobarCorrecionInterprete(String interprete) {
		return catalogoCanciones.comprobarCorrecionInterprete(interprete);
	}
	// Para pasar a un set la lista
	public Set<Cancion> listToSet(List<Cancion> l) {
		return catalogoCanciones.listToSet(l);
	}
	// Para pasar el set a una lista
	public List<Cancion> setToList(Set<Cancion> s) {
		return catalogoCanciones.setToList(s);
	}
	// Esto llama a los dos metedos anteriores para pasar las listas a listas pero sin repetir, llamando al metodo setToList y otra vez luego al list toset del catalogo
	public List<Cancion> rmRepetidas(List<Cancion> l) {
		return catalogoCanciones.rmRepetidas(l);
	}

	// Metodos PlayList
	public void registrarListaCanciones(ListaCanciones lista) {
		AdaptadorListaCancionesTDS.getUnicaInstancia().registrarListaCanciones(lista, usuarioActual);
	}
	/// Para registrar la playlist con varias canciones
	public void registrarPlayListConVariasCanciones(String nombre, List<Cancion> lista) {
		AdaptadorListaCancionesTDS.getUnicaInstancia().registrarPlayListConVariasCanciones(nombre, lista, usuarioActual);
	}
	// Para comprobar si el nombre de las playlist ya existe
	public boolean comprobarNombreExiste(String nombre) {
		return usuarioActual.comprobarNombreExiste(nombre);
	}
	// Para recuperar la lista de playlist del usuario
	public List<ListaCanciones> recuperarTodasListasCanciones() {
		return usuarioActual.getListaPlayList();
	}
	// Para comprobar si el usuario ya tiene una lista con ese nombre
	public boolean comprobarListaYaExiste(String nombre) {
		return usuarioActual.comprobarListaYaExiste(nombre);
	}
	// Metodo de reproduccion de canciones
	public void play(Cancion cancion) {
		mediaPlayer = null;
		binPath = AppMusicControlador.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");
		tempPath = tempPath.replace("%20", " ");
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

			// Para modificar el numero de reproducciones de la cancion
			AdaptadorCancionTDS.getUnicaInstancia().modificarCancion(cancion);
			// Para aniadir a recientes
			this.aniadirRecientes(cancion);

		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	// Metodo de parar una cancion
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

	// Para crear la playlist de recientes del usuario
	public void crearRecientes() {
		if (usuarioActual.getListaPlayList().size() == 0) {
			ListaCanciones playList = new ListaCanciones("Recientes");
			this.registrarListaCanciones(playList);
		}
	}

	// Para aniadir una cancion a recientes
	public void aniadirRecientes(Cancion cancion) {
		usuarioActual.aniadirRecientes(cancion);
		ListaCanciones recientes = usuarioActual.obtenerRecientes();
		AdaptadorListaCancionesTDS.getUnicaInstancia().modificarListaCanciones(recientes);
	}

	// Metodo que modifica si el usuario es premium tanto en el catalogo como en la persistencia
	public void setPremium(boolean opcion) {
		usuarioActual.setEsPremium(opcion);
		AdaptadorUsuarioTDS.getUnicaInstancia().setPremium(usuarioActual, opcion);
	}

	// Para calcular el descuento Fijo
	public int calcularDescuentoFijo() {
		DescuentoFijo descuentoFijo = new DescuentoFijo();
		descuentoFijo.calcDescuento();
		return descuentoFijo.getDescuento();
	}
	// Para calcular el descuento joven
	public int calcularDescuentoJoven() {
		DescuentoJovenes descuentoJoven = new DescuentoJovenes();
		descuentoJoven.calcDescuento();
		return descuentoJoven.getDescuento();
	}
	// Para ver que tipo de descuento se esta utilizando
	public String getTipoDescuento() {
		return tipoDescuento;
	}
	
	// Para cambiar de un tipo de descuento a otro
	public void setTipoDescuento(String tipoDescuento) {
		this.tipoDescuento = tipoDescuento;
	}

	public List<Cancion> obtenerMasReproducidas() {
		return catalogoCanciones.obtenerMasReproducidas();
	}

	public void generarPDF() throws FileNotFoundException, DocumentException {
		String usuario = this.usuarioActual.getLogin() + "DocumentoPDF";
		binPath = AppMusicControlador.class.getClassLoader().getResource(".").getPath();
		binPath = binPath.replaceFirst("/", "");
		// System.out.println(binPath);
		// quitar "/" añadida al inicio del path en plataforma Windows
		tempPath = binPath.replace("/bin", "/temp");
		tempPath = tempPath.replace("%20", " ");
		System.out.println(tempPath);
		FileOutputStream archivo = new FileOutputStream(tempPath + usuario + ".pdf");
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		for (ListaCanciones lista : this.usuarioActual.getListaPlayList()) {
			String nombreLista = lista.getNombre();
			documento.add(new Paragraph("Nombre Playlist: " + nombreLista));
			List<Cancion> l = lista.getCanciones();
			documento.add(new Paragraph("Canciones: "));
			for (Cancion ca : l) {
				documento.add(new Paragraph("-" + ca.getTitulo() + ", "
						+ AppMusicControlador.getInstancia().printAutoresNice(ca.getListaInterpretes()) + ", "
						+ ca.getEstiloMusical()));

			}
			documento.add(new Paragraph("-------------------------------------------------"));
		}
		documento.close();
	}

	// Para editar una playlist en concreto, pasandole un nombre y una lista de canciones, esto se utiliza practicamente para editar las canciones en nuevaLista
	public void editarPlayList(String nombre, List<Cancion> l) {
		usuarioActual.editarPlayList(nombre, l);
		ListaCanciones lista = usuarioActual.getListaEnConcreto(nombre);
		AdaptadorListaCancionesTDS.getUnicaInstancia().modificarListaCanciones(lista);
	}

	// Para obtener una lista del usuario que nos interese
	public ListaCanciones getListaEnConcreto(String nombre){
		return usuarioActual.getListaEnConcreto(nombre);
	}
	public List<Cancion> getListEnConcreto(String nombre){
		return usuarioActual.getListEnConcreto(nombre);
	}
	
	
	public void eliminarPlayList(String nombre) {
		
		ListaCanciones l = usuarioActual.getListaEnConcreto(nombre);
		AdaptadorListaCancionesTDS.getUnicaInstancia().borrarListaCanciones(l);
		usuarioActual.eliminarPlayList(nombre);
		AdaptadorUsuarioTDS.getUnicaInstancia().modificarUsuario(usuarioActual);
		
	}
	
}
