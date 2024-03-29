package umu.tds.modelo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class CatalogoCanciones {

	private static CatalogoCanciones unicaInstancia = null;
	private FactoriaDAO factoria;

	public static CatalogoCanciones getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new CatalogoCanciones();
		return unicaInstancia;
	}

	private HashMap<Integer, Cancion> cancionesPorID;

	private CatalogoCanciones() {
		cancionesPorID = new HashMap<Integer, Cancion>();
		try {
			factoria = FactoriaDAO.getInstancia();
			List<Cancion> listaCanciones = factoria.getCancionDAO().recuperarTodasCanciones();
			for (Cancion cancion : listaCanciones) {
				cancionesPorID.put(cancion.getCodigo(), cancion);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}

	public List<Cancion> getCanciones() {
		return new LinkedList<Cancion>(cancionesPorID.values());
	}

	// Cancion recuperar cancion
	public Cancion getCancion(int id) {
		return cancionesPorID.get(id);
	}

	// Aniadir una cancion
	public void addCancion(Cancion cancion) {
		cancionesPorID.put(cancion.getCodigo(), cancion);

	}

	// Eliminar una cancion del catalogo
	public void removeCancion(Cancion cancion) {
		cancionesPorID.remove(cancion.getCodigo());

	}

	// Comprobar si la variable es igual a interprete
	public String comprobarCorrecionInterprete(String interprete) {
		if (interprete.equals("INTERPRETE")) {
			return null;
		}
		return interprete;
	}

	// Comprobar si la variable de texto es igual a titulo
	public String comprobarCorrecionTitulo(String titulo) {
		if (titulo.equals("TITULO")) {
			return null;
		}
		return titulo;
	}

	// Metodos auxiliares que nos sirven para eliminar las canciones repetidas
	public Set<Cancion> listToSet(List<Cancion> l) {
		return new LinkedHashSet<Cancion>(l);
	}
	public List<Cancion> setToList(Set<Cancion> s) {
		return new LinkedList<Cancion>(s);
	}

	// Eliminar las canciones repetidas
	public List<Cancion> rmRepetidas(List<Cancion> l) {
		return setToList(listToSet(l));
	}

	public Set<String> genSetFromString(String conjuntoAutores) {

		return new HashSet<String>(Arrays.asList(conjuntoAutores.split(",")));
	}

	public LinkedHashSet<String> recuperarTodosEstilos() {
		LinkedHashSet<String> estilos = new LinkedHashSet<String>();
		List<Cancion> canciones = this.getCanciones();
		estilos.add("Cualquiera");
		for (Cancion cancion : canciones) {
			estilos.add(cancion.getEstiloMusical());
		}
		return estilos;
	}

	public boolean comprobarAutorTitulo(String Titulo, List<String> Autores) {
		List<Cancion> canciones = this.getCanciones();
		String aux;
		Set<String> auxAutores = null;
		String stringDeAutores = null;
		Set<String> autoresToSet = new HashSet<String>(Autores);

		for (Cancion c : canciones) {

			stringDeAutores = c.getListaInterpretes().get(0);

			// Variables que contienen las propiedades de la base de datos
			auxAutores = genSetFromString(stringDeAutores);

			aux = c.getTitulo();

			if (auxAutores.equals(autoresToSet) && aux.equals(Titulo))
				return true;
		}
		return false;
	}
	
	
	// Filtrado de canciones
	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo) {
		List<Cancion> todasCanciones = this.getCanciones();
		List<Cancion> resultado = new LinkedList<Cancion>();
		// Filtramos por titulo de la cancion
		if (titulo != null)
			todasCanciones = todasCanciones.stream()
					.filter(c -> c.getTitulo().contains(titulo))
					.collect(Collectors.toList());
		// Filtramos por estilo de la cancion
		if (estilo != null)
			todasCanciones = todasCanciones.stream()
					.filter(c -> c.getEstiloMusical().contains(estilo))
					.collect(Collectors.toList());
		// Se filtra por interprete de la cancion
		if (interprete != null) {
			Set<String> autores = this.genSetFromString(interprete);
			for (Cancion c : todasCanciones) {
				if (c.getListaInterpretes().stream()
						.collect(Collectors.toSet()).containsAll(autores)
						|| c.getListaInterpretes().stream().filter(a -> a.contains(interprete))
								.collect(Collectors.toList()).size() > 0)
					resultado.add(c);
			}
			return resultado;
		}
		resultado = todasCanciones;
		return resultado;
	}
	// Obtencion de las canciones mas reproducidas para la ventana de las mas reproducidas
	public List<Cancion> obtenerMasReproducidas() {
		List<Cancion> r = new LinkedList<Cancion>();
		r = this.getCanciones().stream().sorted(Comparator.comparingInt(Cancion::getNumReproducciones).reversed())
				.collect(Collectors.toList());
		return r;
	}

}
