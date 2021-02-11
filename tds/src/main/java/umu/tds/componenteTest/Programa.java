package umu.tds.componenteTest;

import umu.tds.componente.Cancion;
import umu.tds.componente.Canciones;
import umu.tds.componente.MapperCancionesXMLtoJava;

public class Programa {

	public static void main(String[] args) {

		Canciones canciones = MapperCancionesXMLtoJava
				.cargarCanciones("xml/canciones.xml"); //Obtener fichero a cargar mediante JFileChooser en Swing
	
		for (Cancion cancion : canciones.getCancion()) {
			
			System.out.println("Titulo: " + cancion.getTitulo());
			System.out.println(" Interprete : " + cancion.getInterprete());
			System.out.println(" Estilo : " + cancion.getEstilo());
			System.out.println(" URL : " + cancion.getURL());
			System.out.println("***** ***** *****");
			
			
		}
	
	}

}