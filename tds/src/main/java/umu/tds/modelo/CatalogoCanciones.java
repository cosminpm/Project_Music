package umu.tds.modelo;


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import umu.tds.persistencia.DAOException;
import umu.tds.persistencia.FactoriaDAO;

public class CatalogoCanciones {

	private static CatalogoCanciones unicaInstancia = null;
	private FactoriaDAO factoria;
	
	
	public static CatalogoCanciones getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new CatalogoCanciones();
		return unicaInstancia;
	}
	
	private HashMap<Integer, Cancion> cancionesPorID;
	
	
	private CatalogoCanciones (){
		
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
	
	
	public List<Cancion> getCanciones() throws DAOException {
		return new LinkedList<Cancion>(cancionesPorID.values());
	}
	

	public Cancion getCancion(int id) {
		return cancionesPorID.get(id);
	}
	
	public void addCancion(Cancion cancion) {
		cancionesPorID.put(cancion.getCodigo(), cancion);
		
	}
	
	public void removeCancion(Cancion cancion) {
		cancionesPorID.remove(cancion.getCodigo());
		
	}
	
	public String comprobarCorrecionInterprete(String interprete) {
		if (interprete.equals("INTERPRETE")) {
			return null;
		}
		return interprete;
	}
	
	public String comprobarCorrecionTitulo(String titulo) {
		if (titulo.equals("TITULO")) {
			return null;
		}
		return titulo;
	}
	
	public Set<Cancion> listToSet(List<Cancion> l){
		return new LinkedHashSet<Cancion>(l);
	}
	
	public List<Cancion> setToList(Set<Cancion> s){
		return new LinkedList<Cancion>(s); 
	}
	
	
	public List<Cancion> rmRepetidas(List<Cancion> l){
		return setToList(listToSet(l));
	}
	
}
