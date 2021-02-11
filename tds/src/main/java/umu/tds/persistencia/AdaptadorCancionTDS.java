package umu.tds.persistencia;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
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
		Entidad eCancion;
		boolean existe = true; 
		
		// Si la entidad está registrada no la registra de nuevo
		try {
			eCancion = servPersistencia.recuperarEntidad(cancion.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;

	
		// crear entidad Cancion
		
		
		//Pasar la lista de interpretes a un solo string para meterlo en el servidor de persistencia
		String listString = "";
		for (String s : cancion.getListaInterpretes())
		{
		    listString += s + "\t";
		}
		
		eCancion = new Entidad();
		eCancion.setNombre("cancion");
		eCancion.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", cancion.getTitulo()), new Propiedad("rutafichero", cancion.getRutaFichero()),
						new Propiedad("numreproducciones", String.valueOf(cancion.getNumReproducciones())), new Propiedad("estiloMusical", cancion.getEstiloMusical()), 
				        new Propiedad("listaInterpretes", listString))));
		
	
		// registrar entidad cancion
		eCancion = servPersistencia.registrarEntidad(eCancion);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		cancion.setCodigo(eCancion.getId()); 
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
				    listaInterpretes += s + "\t";
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
		listaInterpretes2 = listaInterpretes1.split(" ");
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
	
	
	
	
	
}
