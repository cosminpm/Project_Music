package umu.tds.modelo;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;


public class Cancion {
	
	private int codigo;
	private String titulo;
	private String rutaFichero;
	private int numReproducciones;
	private String estiloMusical;
	private List<String> listaInterpretes;
	
	public Cancion(String titulo, String rutaFichero,String estiloMusical, List<String> listaInterpretes) {
		this.titulo = titulo;
		this.rutaFichero = rutaFichero;
		this.numReproducciones = 0;
		this.estiloMusical = estiloMusical;
		this.listaInterpretes = new LinkedList<String>(listaInterpretes);
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getEstiloMusical() {
		return estiloMusical;
	}
	public int getNumReproducciones() {
		return numReproducciones;
	}
	public String getRutaFichero() {
		return rutaFichero;
	}
	
	public List<String> getListaInterpretes() {
		return Collections.unmodifiableList(listaInterpretes);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	// Metodo que aumenta en uno el numero de reproducciones
	public void aumentarReproduccion() {
		numReproducciones = numReproducciones + 1;
	}

	public static Cancion componenteCanciontoCancion (umu.tds.componente.Cancion cancion) {
		

		String rutaFichero = cancion.getURL();
		String estilo = cancion.getEstilo();
		String interprete = cancion.getInterprete();
		String titulo = cancion.getTitulo();
		List<String> listaInterpretes = new LinkedList<String>();
		listaInterpretes.add(interprete);
		
		Cancion aux = new Cancion(titulo,rutaFichero, estilo,listaInterpretes);
	
		return aux;
	}
	
}