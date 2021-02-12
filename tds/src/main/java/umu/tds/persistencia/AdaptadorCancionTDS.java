package umu.tds.persistencia;


import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Cancion;


public class AdaptadorCancionTDS implements IAdaptadorCancionDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancionTDS unicaInstancia = null;

	public static AdaptadorCancionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorCancionTDS();
		} else
			return unicaInstancia;
	}

	private AdaptadorCancionTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	
	public void registrarCancion(Cancion cancion) {
		Entidad eCancion = null;
		boolean existe = true; 
		
		// Si la entidad está registrada no la registra de nuevo
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		
		if (eCancion == null) {
			existe = false;
		}
	
		
		if (existe) {
			System.out.println("ESTA REGISTRADA");
			return;
		}

	
		// crear entidad Cancion
		
		
		//Pasar la lista de interpretes a un solo string para meterlo en el servidor de persistencia"
		String listaInterpretes = "";
		for (String s : cancion.getListaInterpretes())
		{
			s.replace(" ", "_");
		    listaInterpretes += s + ",";
		}
		
		
		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		eCancion.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", cancion.getTitulo()), new Propiedad("rutafichero", cancion.getRutaFichero()),
						new Propiedad("numreproducciones", String.valueOf(cancion.getNumReproducciones())), new Propiedad("estiloMusical", cancion.getEstiloMusical()), 
				        new Propiedad("listaInterpretes", listaInterpretes))));
		
	
		// registrar entidad cancion
		eCancion = servPersistencia.registrarEntidad(eCancion);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		System.err.println("Idetnficicador de eCancion:"+eCancion.getId());
		cancion.setCodigo(eCancion.getId()); 
		System.err.println("Identificador de Cancion:"+cancion.getCodigo());
	}
	
	
	public void borrarCancion(Cancion cancion) {
		
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		
		servPersistencia.borrarEntidad(eCancion);
	}
	
	
	public void modificarCancion(Cancion cancion) {

		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());

		//Pasar la lista de interpretes a un solo string para meterlo en el servidor de persistencia
				String listaInterpretes = "";
				for (String s : cancion.getListaInterpretes())
				{
					s.replace(" ", "_");
				    listaInterpretes += s + ",";
				}
				
		servPersistencia.eliminarPropiedadEntidad(eCancion, "titulo");
		servPersistencia.anadirPropiedadEntidad(eCancion, "titulo", cancion.getTitulo());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "rutafichero");
		servPersistencia.anadirPropiedadEntidad(eCancion, "rutafichero", cancion.getRutaFichero());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "numreproducciones");
		servPersistencia.anadirPropiedadEntidad(eCancion, "numreproducciones", String.valueOf(cancion.getNumReproducciones()));
		servPersistencia.eliminarPropiedadEntidad(eCancion, "estiloMusical");
		servPersistencia.anadirPropiedadEntidad(eCancion, "estiloMusical", cancion.getEstiloMusical());
		servPersistencia.eliminarPropiedadEntidad(eCancion, "listaInterpretes");
		servPersistencia.anadirPropiedadEntidad(eCancion, "listaInterpretes", listaInterpretes);
		
		
	}
	
	
	public Cancion recuperarCancion(int codigo) {

		// Si la entidad está en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Cancion) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		Entidad eCancion;
	
		String titulo;
		String rutafichero;
		String numre;
		
		
		//TODO VER QUE HACEMOS CON EL NUM DE REPRODUCCIONES, COMO LO AUMENTAMOS
		int numreproducciones;
		String estiloMusical;
		String listaInterpretes1;
		String[] listaInterpretes2;
		List <String> listaInterpretes;

		// recuperar entidad
		eCancion = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		
		titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");
		rutafichero = servPersistencia.recuperarPropiedadEntidad(eCancion, "rutafichero");
		numre = servPersistencia.recuperarPropiedadEntidad(eCancion, "numreproducciones");
		//Hemos guardado el num de reproducciones como string y lo pasamos ahora a int
		numreproducciones = Integer.parseInt(numre);
		estiloMusical = servPersistencia.recuperarPropiedadEntidad(eCancion, "estiloMusical");
		
		//Sacamos los interpretes como un string, lo pasamos a un array y luego a una lista de strings
		listaInterpretes1 = servPersistencia.recuperarPropiedadEntidad(eCancion,"listaInterpretes");
		listaInterpretes2 = listaInterpretes1.split(",");
		for (String aux : listaInterpretes2) {
			aux.replace("_", " ");
		}
		listaInterpretes = Arrays.asList(listaInterpretes2);
		

		Cancion cancion = new Cancion(titulo, rutafichero, estiloMusical , listaInterpretes);
		cancion.setCodigo(codigo);

		// IMPORTANTE:añadir la cancion al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, cancion);

		return cancion;
	}
	
	public List<Cancion> recuperarTodasCanciones() {

		List<Entidad> eCanciones = servPersistencia.recuperarEntidades("cancion");
		List<Cancion> canciones = new LinkedList<Cancion>();

		for (Entidad eCancion : eCanciones) {
			canciones.add(recuperarCancion(eCancion.getId()));
		}
		return canciones;
	}
	
	public boolean comprobarAutorTitulo(String Titulo, List<String> Autores) {	
		List<Entidad> eCanciones = servPersistencia.recuperarEntidades("cancion");
		String aux;
		Set<String> auxAutores = null;
		String stringDeAutores = null;
		Set<String> autoresToSet = new HashSet<String>(Autores);
		
		for (Entidad eCancion : eCanciones) {
					
			stringDeAutores = servPersistencia.recuperarPropiedadEntidad(eCancion, "listaInterpretes");	
			// Variables que contienen las propiedades de la base de datos
			auxAutores = genSetFromString(stringDeAutores);
			aux = servPersistencia.recuperarPropiedadEntidad(eCancion, "titulo");	
			if (auxAutores.equals(autoresToSet) && aux.equals(Titulo))
				return true;
		}
		return false;
	}
	
	public Set<String> genSetFromString(String conjuntoAutores){
		return new HashSet<String>(Arrays.asList(conjuntoAutores.split(",")));
	}
	
	
	public List<Cancion> filtrarCanciones(String interprete, String titulo, String estilo){
		
		
		System.err.println("INTERPRETE: "+interprete);
		System.err.println("TITULO: "+titulo);
		System.err.println("estilo: " +estilo);
		
		List<Cancion> todasCanciones = (LinkedList<Cancion>) this.recuperarTodasCanciones();
		List<Cancion> resultado = new LinkedList<Cancion>();
		
		if(titulo != null)
			todasCanciones = todasCanciones.stream().filter(c -> c.getTitulo().contains(titulo)).collect(Collectors.toList());
		
		if(estilo != null)
			todasCanciones.stream().filter(c -> c.getEstiloMusical().contains(estilo));
		
		if(interprete != null) {
			Set<String> autores = this.genSetFromString(interprete);
			for (Cancion c : todasCanciones) {
				if(c.getListaInterpretes().stream().collect(Collectors.toSet()).containsAll(autores))
					resultado.add(c);
			}
			return resultado;
		}
			
		resultado = todasCanciones;
		return resultado;
	}
}
