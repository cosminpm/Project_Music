package umu.tds.controlador;
 
import static org.junit.Assert.*;
 
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
 
import org.junit.Test;
 
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;
 
public class AppMusicControladorTest {
 
	@Test
	public void testRegistrarUsuario1() {
 
		String nombre = "carlos";
		String apellidos = "ortiz";
		String email = "carlos.ortizr@um.es";
		String login = "carlos";
		String password = "1";
		LocalDate fechaNacimiento = LocalDate.now();
		boolean resultado = false;
		assertEquals(resultado, AppMusicControlador.getInstancia().registrarUsuario(nombre, apellidos, email, login, password, fechaNacimiento));
	}
	@Test
	public void testRegistrarUsuario2() {
 
		String nombre = "Carlos";
		String apellidos = "Ortiz";
		String email = "carlos.ortizr2@um.es";
		String login = "carlosPrueba";
		String password = "1";
		LocalDate fechaNacimiento = LocalDate.now();
		boolean resultado = false;
		assertEquals(resultado, AppMusicControlador.getInstancia().registrarUsuario(nombre, apellidos, email, login, password, fechaNacimiento));
	}
 
	@Test
	public void testRegistrarUsuario3() {
 
		String nombre = "Pepe";
		String apellidos = "Ortiz22";
		String email = "carlos.ortizr2322@um.es";
		String login = "carlosPrueba23232";
		String password = "1";
		LocalDate fechaNacimiento = LocalDate.now();
 
 
		AppMusicControlador.getInstancia().registrarUsuario(nombre, apellidos, email, login, password, fechaNacimiento);
 
		Usuario registrado = AppMusicControlador.getInstancia().recuperarUsuario(login);
 
		assertEquals(nombre, registrado.getNombre());
		assertEquals(apellidos, registrado.getApellidos());
		assertEquals(email, registrado.getEmail());
		assertEquals(login, registrado.getLogin());
		assertEquals(password, registrado.getPassword());
		assertEquals(fechaNacimiento, registrado.getFechaNacimiento());
	}
 
	@Test
	public void testRegistrarPlayList() {
 
		AppMusicControlador.getInstancia().login("carlos", "1");
		List<String> listaInterpretes = new LinkedList<String>();
		listaInterpretes.add("Cosmin");
 
		Cancion cancion = new Cancion("Cosmin", "C//Cosmin", "Cosminstyle", listaInterpretes);
 
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		listaCanciones.add(cancion);
 
		String nombre = "JUnitPlaylist";
		ListaCanciones playlist = new ListaCanciones(nombre, listaCanciones);
		
		
		if(AppMusicControlador.getInstancia().comprobarListaYaExiste(nombre)) {
					
		}
		
		else {
			
			AppMusicControlador.getInstancia().registrarListaCanciones(playlist);
		}
 
		List<ListaCanciones> a = AppMusicControlador.getInstancia().recuperarTodasListasCanciones();
 
		ListaCanciones listaRegistrada = null;
 
		for(ListaCanciones lis: a) {
 
			if(lis.getNombre().equals(nombre)) {
 
				listaRegistrada = lis;
 
 
			}
 
		}
		assertEquals(nombre, listaRegistrada.getNombre());
		assertEquals(listaCanciones, listaRegistrada.getCanciones());
		AppMusicControlador.getInstancia().eliminarPlayList(nombre);
	}
 
 
	@Test
	public void testLoginOK() {
		String login = "carlos";
		String clave = "1";
 
		boolean resultado = true;
		assertEquals(resultado, AppMusicControlador.getInstancia().login(login, clave));
	}
 
	@Test
	public void testLoginNoOK() {
		String login = "carlos";
		String clave = "2";
 
		boolean resultado = false;
		assertEquals(resultado, AppMusicControlador.getInstancia().login(login, clave));
	}
	
	@Test
	public void filtrarCanciones() {
		String interprete = null;
		String interprete1 = "King Crimson";
		String titulo = null;
		String titulo1 = "I'll take a melody";
		String estilo = null;
		
		String estilo1 = "Tango";
		
		List<Cancion> filtrado1 = CatalogoCanciones.getUnicaInstancia().filtrarCanciones(interprete, titulo, estilo1);
		List<Cancion> filtrado2 = CatalogoCanciones.getUnicaInstancia().filtrarCanciones(interprete, titulo1, estilo);
		List<Cancion> filtrado3 = CatalogoCanciones.getUnicaInstancia().filtrarCanciones(interprete1, titulo, estilo);
		Cancion cFiltrada1 = filtrado1.get(0);
		Cancion cFiltrada2 = filtrado2.get(0);
		Cancion cFiltrada3 = filtrado3.get(0);
		
		
		int longitud = 1;
		String resultado1 = "La Cumparsita";
		String resultado2 = "Todd Sheaffer";
		String resultado3 = "Rock-sinfonico";
		
		assertEquals(longitud, filtrado1.size());
		assertEquals(longitud, filtrado2.size());
		assertEquals(longitud, filtrado3.size());
		assertEquals(resultado1, cFiltrada1.getTitulo());
		assertEquals(resultado2, cFiltrada2.getListaInterpretes().get(0));
		assertEquals(resultado3, cFiltrada3.getEstiloMusical());
		
	}
 
	@Test
	public void eliminarPlaylist() {
		
		List<String> listaInterpretes = new LinkedList<String>();
		listaInterpretes.add("Cosmin");
 
		Cancion cancion = new Cancion("Cosmin", "C//Cosmin", "Cosminstyle", listaInterpretes);
 
		List<Cancion> listaCanciones = new LinkedList<Cancion>();
		listaCanciones.add(cancion);
 
		String nombre = "JUnitEliminarPlaylist";
		ListaCanciones playlist = new ListaCanciones(nombre, listaCanciones);
 
		AppMusicControlador.getInstancia().registrarListaCanciones(playlist);
		
		AppMusicControlador.getInstancia().eliminarPlayList(nombre);
		
		List<ListaCanciones> a = AppMusicControlador.getInstancia().recuperarTodasListasCanciones();
		 
		boolean resultado = false;
 
		for(ListaCanciones lis: a) {
 
			if(lis.getNombre().equals(nombre)) {
 
				resultado = true;
 
 
			}
		
		
		
	}
	assertEquals(resultado, false);
 
	}
}