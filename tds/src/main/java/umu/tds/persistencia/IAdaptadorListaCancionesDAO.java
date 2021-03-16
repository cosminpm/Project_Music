package umu.tds.persistencia;

import java.util.List;

import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;

public interface IAdaptadorListaCancionesDAO {

	public void registrarListaCanciones(ListaCanciones lista, Usuario usuarioActual);
	public void borrarListaCanciones(ListaCanciones lista);
	public void modificarListaCanciones(ListaCanciones lista);
	public ListaCanciones recuperarListaCanciones(int codigo);
	public List<ListaCanciones> recuperarTodasListasCanciones();
	public void registrarPlayListConVariasCanciones(String nombre, List<Cancion> lista, Usuario usuarioActual);
	public boolean comprobarNombreExiste(String nombre);
	public ListaCanciones eliminarPrimera(ListaCanciones lCanciones);
}
