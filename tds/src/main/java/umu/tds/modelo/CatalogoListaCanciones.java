/*
package umu.tds.modelo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class CatalogoListaCanciones {

	private static CatalogoListaCanciones unicaInstancia = null;
	private FactoriaDAO factoria;
	
	
	public static CatalogoListaCanciones getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoListaCanciones();
		return unicaInstancia;
	}
	
	private HashMap<Integer, ListaCanciones> ListaCancionesPorID;
	
	
	private CatalogoListaCanciones (){
		
		ListaCancionesPorID = new HashMap<Integer, ListaCanciones>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<ListaCanciones> ListalistaCanciones = factoria.getListaCancionesDAO().recuperarTodasListasCanciones();
			for (ListaCanciones lista : ListalistaCanciones) {
				ListaCancionesPorID.put(lista.getCodigo(),lista);
				
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	
	public List<ListaCanciones> getListaCanciones() throws DAOException {
		return new LinkedList<ListaCanciones>(ListaCancionesPorID.values());
	}

	public ListaCanciones getListaCanciones(int id) {
		return ListaCancionesPorID.get(id);
	}
	
	public void addListaCanciones(ListaCanciones lista) {
		ListaCancionesPorID.put(lista.getCodigo(), lista);
		
	}
	
	public void removeListaCanciones(ListaCanciones lista) {
		ListaCancionesPorID.remove(lista.getCodigo());
		
	}
	
	
	public ListaCanciones eliminarPrimera(ListaCanciones lCanciones){
		System.err.println("ANTEES: "+lCanciones.getCanciones().size());
		List<Cancion> aux = lCanciones.getCanciones();
		aux.remove(0);
		lCanciones.setCanciones(aux);
		System.err.println("DESPUES: "+lCanciones.getCanciones().size());
		return lCanciones;
	}
	
}*/
