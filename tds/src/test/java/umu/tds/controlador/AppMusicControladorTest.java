package umu.tds.controlador;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

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

}
