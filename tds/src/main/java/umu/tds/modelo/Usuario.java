package umu.tds.modelo;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Usuario {

	private int codigo;
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private LocalDate fecha;
	private List<ListaCanciones> listaPlayList;

	private boolean esPremium;

	public Usuario(String nombre, String apellidos, String email, String login, String password,
			LocalDate fechaNacimiento) {

		this.codigo = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fecha = fechaNacimiento;
		this.listaPlayList = new LinkedList<ListaCanciones>();
		this.esPremium = false;
	}

	public Usuario(String nombre, String apellidos, String email, String login, String password,
			LocalDate fechaNacimiento, List<ListaCanciones> listaPlayList) {
		this(nombre, apellidos, email, login, password, fechaNacimiento);
		this.listaPlayList = new LinkedList<ListaCanciones>(listaPlayList);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fecha;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fecha = fechaNacimiento;
	}

	public List<ListaCanciones> getListaPlayList() {
		List<ListaCanciones> lista = new LinkedList<ListaCanciones>(this.listaPlayList);
		return lista;
	}

	public void setListaCanciones(List<ListaCanciones> l) {
		this.listaPlayList = l;
	}

	public String getNombreListaDePlaylist() {

		String aux = "";
		aux = this.login;
		aux += "PLAYLIST"; // loginPLAYLIST

		return aux;
	}

	public ListaCanciones obtenerRecientes() {
		return this.listaPlayList.get(0);
	}

	public void addListaCanciones(ListaCanciones c) {
		listaPlayList.add(c);
	}

	public void setEsPremium(boolean p) {
		this.esPremium = p;
	}

	public boolean getEsPremium() {
		return esPremium;
	}

	public boolean login(String clave) {
		return this.password.equals(clave);

	}

	public ListaCanciones getListaEnConcreto(String nombre) {
		for (ListaCanciones playlist : this.getListaPlayList()) {
			if (playlist.getNombre().equals(nombre)) {
				return playlist;
			}
		}
		// Esto no deberia pasar nunca puesto que el nombre siempre deberia existir
		return null;
	}

	public List<Cancion> getListEnConcreto(String nombre) {
		return this.getListaEnConcreto(nombre).getCanciones();
	}

	public boolean comprobarListaYaExiste(String nombre) {
		for (ListaCanciones playlist : this.getListaPlayList()) {
			if (playlist.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	public void aniadirRecientes(Cancion cancion) {
		ListaCanciones recientes = this.obtenerRecientes();
		recientes.addCancionSet(cancion);
		if (recientes.getCanciones().size() > 10) {
			recientes = this.eliminarPrimera(recientes);
		}
	}

	public ListaCanciones eliminarPrimera(ListaCanciones lCanciones) {
		List<Cancion> aux = lCanciones.getCanciones();
		aux.remove(0);
		lCanciones.setCanciones(aux);
		return lCanciones;
	}

	public boolean comprobarNombreExiste(String nombre) {
		List<ListaCanciones> l = this.getListaPlayList();
		for (ListaCanciones listaCanciones : l) {
			if (listaCanciones.getNombre().equals(nombre))
				return true;
		}
		return false;
	}

	// Modificar la lsita de canciones
	public void editarPlayList(String nombre, List<Cancion> l) {
		ListaCanciones lista = this.listaPlayList.stream().filter(aux -> aux.getNombre().equals(nombre)).findFirst()
				.get();
		lista.aniadirCancionesSinRepetir(l);
	}

	public void eliminarPlayList(String nombre) {
		ListaCanciones l = this.getListaEnConcreto(nombre);
		listaPlayList.remove(l);
	}
}
