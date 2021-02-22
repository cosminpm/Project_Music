package umu.tds.componenteTest;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import umu.tds.componente.Canciones;
import umu.tds.componente.MapperCancionesXMLtoJava;


public class CargadorCanciones implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Canciones archivoCanciones;
	//private Vector<CancionesListener> oyentes;
	private PropertyChangeSupport oyentesArchivoCanciones = new
			PropertyChangeSupport(this); 

	/*public synchronized boolean addOyente(CancionesListener oyente) {

		if (oyentes.add(oyente)) {
			return true;
		}
		return false;
	}

	public synchronized boolean removeOyente(CancionesListener oyente) {

		if (oyentes.remove(oyente)) {
			return true;

		}
		return false;

	}
	*/
	
	public void addArchivoCancionesChangeListener(PropertyChangeListener pcl) {
		 oyentesArchivoCanciones.addPropertyChangeListener(pcl);
		 }
		public void removeArchivoCancionesChangeListener(PropertyChangeListener pcl) {
		 oyentesArchivoCanciones.removePropertyChangeListener(pcl);
		 } 

	

	public Canciones getArchivoCanciones() {
		return archivoCanciones;
	}

	/*public void setArchivoCanciones(Canciones nuevoArchivoCanciones) {
		Canciones antesArchivoCanciones = archivoCanciones;
		this.archivoCanciones = nuevoArchivoCanciones;
		if (!antesArchivoCanciones.equals(nuevoArchivoCanciones)) {

			CancionesEvent evento = new CancionesEvent(this, antesArchivoCanciones, nuevoArchivoCanciones);
			notificarCambio(evento);
		}
	}*/
	
	public void setArchivoCanciones(String fichero) {
		
		Canciones nuevoArchivoCanciones = MapperCancionesXMLtoJava.cargarCanciones(fichero);
		Canciones antesArchivoCanciones = archivoCanciones;
		this.archivoCanciones = nuevoArchivoCanciones;
		oyentesArchivoCanciones.firePropertyChange("archivoCanciones",antesArchivoCanciones, nuevoArchivoCanciones);
		}
	
	/*
	// TODO PREGUNTAR PROFE
	@SuppressWarnings("unchecked")
	private void notificarCambio(CancionesEvent event) {
		Vector<CancionesListener> lista;
		synchronized (this) {
			lista = (Vector<CancionesListener>) oyentes.clone();
		}
		for (CancionesListener cl : lista) {
			cl.enteradoCambioCanciones(event);
		}

	}*/
}
