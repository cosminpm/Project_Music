package umu.tds.componenteTest;

import java.util.EventObject;

import umu.tds.componente.Canciones;

@SuppressWarnings("serial")
public class CancionesEvent extends EventObject {

	protected Canciones cancionesAntiguas;
	protected Canciones cancionesNuevas;

	public CancionesEvent(Object fuente, Canciones cancionesAntiguas, Canciones cancionesNuevas) {
		super(fuente);
		this.cancionesAntiguas = cancionesAntiguas;
		this.cancionesNuevas = cancionesNuevas;
	}

	public Canciones getCancionesAntiguas() {

		return cancionesAntiguas;

	}

	public Canciones getCancionesNuevas() {

		return cancionesNuevas;

	}

}
