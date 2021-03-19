package umu.tds.modelo;

public abstract class Descuento {

	// Metodo que heredaran los hijos
	int descuento;
	
	abstract void calcDescuento();
	
	public int getDescuento() {
		return descuento;
	}
}
