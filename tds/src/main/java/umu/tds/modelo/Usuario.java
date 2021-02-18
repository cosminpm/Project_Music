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
	//TODO Lista que contiene listas de canciones
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
		this(nombre, apellidos,email, login, password, fechaNacimiento);
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
	
	public List<ListaCanciones> getListaCanciones(){
		List<ListaCanciones> lista = new LinkedList<ListaCanciones>(this.listaPlayList);
		return lista;
		
	}
	
	
	public String getNombreListaDePlaylist() {
		
		String aux = "";
		aux = this.login;
		aux+="PLAYLIST";  //loginPLAYLIST
		
		return aux;
	}
	
	
	// TODO De aqui adelante es nuestro codigo
	
	// TODO Â¿Es correcto esto?
	public void addListaCanciones(ListaCanciones c) {
		listaPlayList.add(c);
	}	
	
	public void setEsPremium(boolean p) {
		this.esPremium = p;
	}
	
	public boolean getEsPremium() {
		return esPremium;
	}

	
	//MÃ©todo que llama el controlador para comprobar la clave 
	
	public boolean login(String clave) {
		return this.password.equals(clave);
		
	}
	

}
