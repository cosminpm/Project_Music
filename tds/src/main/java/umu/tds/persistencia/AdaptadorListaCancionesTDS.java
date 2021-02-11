package umu.tds.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;

public class AdaptadorListaCancionesTDS implements IAdaptadorListaCancionesDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaCancionesTDS unicaInstancia = null;

	public static AdaptadorListaCancionesTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorListaCancionesTDS();
		} else
			return unicaInstancia;
	}

	private AdaptadorListaCancionesTDS() { 
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	public void registrarListaCanciones(ListaCanciones lista) {
		
		Entidad eLista;
		boolean existe = true; 
		
		// Si la entidad está registrada no la registra de nuevo
		try {
			eLista = servPersistencia.recuperarEntidad(lista.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}
		if (existe) return;

	
		// crear entidad Lista
		
		
		// registrar primero los atributos que son objetos
				AdaptadorCancionTDS adaptadorCancion = AdaptadorCancionTDS.getUnicaInstancia();
				for (Cancion c: lista.getCanciones())
					adaptadorCancion.registrarCancion(c);
		
				
				
		eLista = new Entidad();
		eLista.setNombre("listaCanciones");
		eLista.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", lista.getNombre()), new Propiedad("lCanciones",obtenerCodigosCanciones(lista.getCanciones())))));
		
	
		// registrar entidad cancion
		eLista = servPersistencia.registrarEntidad(eLista);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		lista.setCodigo(eLista.getId()); 
	}
	
	
	public void borrarListaCanciones(ListaCanciones lista) {
		// No se comprueban restricciones de integridad con Venta
		Entidad eLista = servPersistencia.recuperarEntidad(lista.getCodigo());
		
		servPersistencia.borrarEntidad(eLista);
	}
	
	
	public void modificarListaCanciones(ListaCanciones lista) {

		Entidad eLista = servPersistencia.recuperarEntidad(lista.getCodigo());

		servPersistencia.eliminarPropiedadEntidad(eLista, "nombre");
		servPersistencia.anadirPropiedadEntidad(eLista, "nombre", lista.getNombre());
		
		String canciones = obtenerCodigosCanciones(lista.getCanciones());
		
		servPersistencia.eliminarPropiedadEntidad(eLista, "lCanciones");
		servPersistencia.anadirPropiedadEntidad(eLista, "nombre", canciones);

	}
	
	
	public ListaCanciones recuperarListaCanciones(int codigo) {

		// Si la entidad está en el pool la devuelve directamente
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ListaCanciones) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		Entidad eLista;
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		String nombre;

		// recuperar entidad
		eLista = servPersistencia.recuperarEntidad(codigo);

		// recuperar propiedades que no son objetos
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eLista, "nombre");

		ListaCanciones lista = new ListaCanciones(nombre);
		lista.setCodigo(codigo);

		// IMPORTANTE:añadir el cliente al pool antes de llamar a otros
		// adaptadores
		PoolDAO.getUnicaInstancia().addObjeto(codigo, lista);

		// recuperar propiedades que son objetos llamando a adaptadores
		// canciones
		canciones = obtenerCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eLista, "lCanciones"));

		for (Cancion c : canciones)
			lista.addCancion(c);

		return lista;
	}

	public List<ListaCanciones> recuperarTodasListasCanciones() {

		List<Entidad> eListas = servPersistencia.recuperarEntidades("nombre");
		List<ListaCanciones> listas = new LinkedList<ListaCanciones>();

		for (Entidad eLista : eListas) {
			listas.add(recuperarListaCanciones(eLista.getId()));
		}
		return listas;
	}
	
	
	
	
	// -------------------Funciones auxiliares-----------------------------
		private String obtenerCodigosCanciones(List<Cancion> listaCanciones) {
			String aux = "";
			for (Cancion c : listaCanciones) {
				aux += c.getCodigo() + " ";
			}
			return aux.trim();
		}
		
		
		private List<Cancion> obtenerCancionesDesdeCodigos(String canciones) {

			List<Cancion> listaCanciones = new LinkedList<Cancion>();
			StringTokenizer strTok = new StringTokenizer(canciones, " ");
			AdaptadorCancionTDS adaptadorC = AdaptadorCancionTDS.getUnicaInstancia();
			while (strTok.hasMoreTokens()) {
				listaCanciones.add(adaptadorC.recuperarCancion(Integer.valueOf((String) strTok.nextElement())));
			}
			return listaCanciones;
		}
}
