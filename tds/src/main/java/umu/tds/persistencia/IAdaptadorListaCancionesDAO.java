package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;

public interface IAdaptadorListaCancionesDAO {

	public void registrarListaCanciones(ListaCanciones lista);
	public void borrarListaCanciones(ListaCanciones lista);
	public void modificarListaCanciones(ListaCanciones lista);
	public ListaCanciones recuperarListaCanciones(int codigo);
	public List<ListaCanciones> recuperarTodasListasCanciones();
	public void registrarPlayListConVariasCanciones(String nombre, List<Cancion> lista);
	public boolean comprobarNombreExiste(String nombre);
}
