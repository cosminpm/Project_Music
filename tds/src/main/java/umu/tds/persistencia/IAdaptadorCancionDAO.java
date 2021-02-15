package umu.tds.persistencia;

import java.util.LinkedHashSet;
import java.util.List;

import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {

	
	public void registrarCancion(Cancion cancion);
	public void borrarCancion(Cancion cancion);
	public void modificarCancion(Cancion cancion);
	public Cancion recuperarCancion(int codigo);
	public List<Cancion> recuperarTodasCanciones();
	public boolean comprobarAutorTitulo(String Titulo, List<String> Autores);
	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo);
	public LinkedHashSet<String> recuperarTodosEstilos();
	public String comprobarCorrecionTitulo(String titulo);
	public String comprobarCorrecionInterprete(String interprete);

	
}
