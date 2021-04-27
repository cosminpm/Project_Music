package umu.tds.persistencia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;

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

	public void registrarListaCanciones(ListaCanciones lista, Usuario usuarioActual) {
		Entidad eLista = new Entidad();
		boolean existe = true;

		// Si la entidad está registrada no la registra de nuevo
		eLista = servPersistencia.recuperarEntidad(lista.getCodigo());
		if (eLista == null)
			existe = false;

		if (existe)
			return;
		// registrar primero los atributos que son objetos
		// crear entidad Lista
		eLista = new Entidad();

		System.err.println("ESTOY EN Registrar Lista Canciones y el nombre de la lista es: " + lista.getNombre());

		eLista.setNombre("listaCanciones");
		eLista.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombre", lista.getNombre()),
				new Propiedad("lCanciones", obtenerCodigosCanciones(lista.getCanciones())))));

		// registrar entidad cancion
		eLista = servPersistencia.registrarEntidad(eLista);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		lista.setCodigo(eLista.getId());

		System.out.println("LOGIN: " + usuarioActual.getLogin());

		LinkedList<ListaCanciones> l = (LinkedList<ListaCanciones>) usuarioActual.getListaPlayList();
		l.addLast(lista);

		System.out.println("SET: " + l.size());

		usuarioActual.setListaCanciones(l);

		System.out.println("SET1: " + l.size());
		System.out.println("REGISTRAR LISTA0: " + usuarioActual.getListaPlayList().size());

		AdaptadorUsuarioTDS.getUnicaInstancia().modificarUsuario(usuarioActual);

		System.out.println("REGISTRAR LISTA1: " + usuarioActual.getListaPlayList().size());
		System.err.println("Print: registrarListaCancion FINAL");
		List<ListaCanciones> auxl = AppMusicControlador.getInstancia().getUsuarioActual().getListaPlayList();

		System.err.println(l.size());
		for (ListaCanciones listaCanciones : l) {
			System.err.println(listaCanciones.getNombre());
		}
	}

	public void borrarListaCanciones(ListaCanciones lista) {
		// No se comprueban restricciones de integridad con Venta
		Entidad eLista = servPersistencia.recuperarEntidad(lista.getCodigo());

		servPersistencia.borrarEntidad(eLista);
	}

	public void modificarListaCanciones(ListaCanciones lista) {

		Entidad eLista = servPersistencia.recuperarEntidad(lista.getCodigo());
		String canciones = obtenerCodigosCanciones(lista.getCanciones());

		for (Propiedad prop : eLista.getPropiedades()) {
			if (prop.getNombre().equals("lCanciones")) {
				prop.setValor(String.valueOf(canciones));
			}

			else if (prop.getNombre().equals("nombre")) {
				prop.setValor(String.valueOf(lista.getNombre()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public ListaCanciones recuperarListaCanciones(int codigo) {

		// Si la entidad está en el pool la devuelve directamente
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
		// recuperar propiedades que son objetos llamando a adaptadores
		// canciones
		canciones = obtenerCancionesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eLista, "lCanciones"));

		for (Cancion c : canciones)
			lista.addCancion(c);

		return lista;
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

	public void registrarPlayListConVariasCanciones(String nombre, List<Cancion> lista, Usuario usuarioActual) {
		ListaCanciones l = new ListaCanciones(nombre);
		for (Cancion c : lista) {
			l.addCancion(c);
		}
		this.registrarListaCanciones(l, usuarioActual);
	}
}
