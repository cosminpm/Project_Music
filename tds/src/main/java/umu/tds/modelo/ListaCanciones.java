package umu.tds.modelo;


import java.util.List;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import umu.tds.componente.*;

public class ListaCanciones {
	
	
	private int codigo;
	private String nombre;
	private List<Cancion> lCanciones;
	
	public ListaCanciones(String nombre) {
		this.nombre = nombre;
		this.codigo = 0;
		lCanciones = new LinkedList<Cancion>();
	}
	
	
	public ListaCanciones(String nombre, List<Cancion> l) {
		this.nombre = nombre;
		this.codigo = 0;
		lCanciones = l;
	}	
	
	
	// TODO Metodos que hemos aniadido nosotros
	public String getNombre() {
		return nombre;
	}
	
	public List<Cancion> getCanciones() {
		return new LinkedList<Cancion>(lCanciones);
	}	
	
	
	public void setCanciones(List<Cancion> lCanciones) {
		this.lCanciones = lCanciones;
	}
	
	public void addCancion(Cancion c) {
		lCanciones.add(c);
	}
    public boolean addCancionSet(Cancion c) {
    	//Return false, la cancion pertenece a recientes por primera vez
    	if(!lCanciones.contains(c)) {
    		lCanciones.add(c);
    		return false;
    	}
    	//Return true, la cancion ya pertenece a recientes
    	return true;
    }
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void aniadirCancionesSinRepetir(List<Cancion> l) {
		List<Cancion> aux = this.getCanciones();
		aux = l;
		// Eliminar en el caso de que hubiera alguna repetida
		LinkedHashSet<Cancion> aux2 = new LinkedHashSet<Cancion>(aux);
		LinkedList<Cancion> aux3 = new LinkedList<Cancion>(aux2);
		this.setCanciones(aux3);
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
