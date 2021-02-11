package umu.tds.modelo;


import java.util.List;
import java.util.LinkedList;
import umu.tds.componente.*;

public class ListaCanciones {
	
	
	private int codigo;
	private String nombre;
	// TODO Lista de canciones
	private List<Cancion> lCanciones;
	
	public ListaCanciones(String nombre) {
		this.nombre = nombre;
		lCanciones = new LinkedList<Cancion>();
	}
	
	// TODO Metodos que hemos aniadido nosotros
	public String getNombre() {
		return nombre;
	}
	
	public List<Cancion> getCanciones() {
		return new LinkedList<Cancion>(lCanciones);
	}	
	
	public void addCancion(Cancion c) {
		lCanciones.add(c);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
	public static List<Cancion> listaCancionesComponenteToListaCancionesModelo (Canciones lista){
		
		List<umu.tds.componente.Cancion> listaCancionesComponente = lista.getCancion();
		List<Cancion> listaCancionesModelo = new LinkedList<Cancion>();
		
		
		for (umu.tds.componente.Cancion cancionEnLista : listaCancionesComponente) {
			
			listaCancionesModelo.add(Cancion.componenteCanciontoCancion(cancionEnLista));
					
		}

		return listaCancionesModelo;
	
	}
	
}