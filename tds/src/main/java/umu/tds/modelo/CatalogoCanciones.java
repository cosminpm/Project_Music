package umu.tds.modelo;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
	
	
	
	
	
}
