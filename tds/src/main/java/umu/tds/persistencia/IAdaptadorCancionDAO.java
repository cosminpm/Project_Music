package umu.tds.persistencia;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import umu.tds.modelo.Cancion;

public interface IAdaptadorCancionDAO {
	public void registrarCancion(Cancion cancion);
	public void borrarCancion(Cancion cancion);
	public void modificarCancion(Cancion cancion);
	public Cancion recuperarCancion(int codigo);
	public List<Cancion> recuperarTodasCanciones();
}
