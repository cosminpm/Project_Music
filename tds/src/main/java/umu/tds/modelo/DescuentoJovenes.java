package umu.tds.modelo;

public class DescuentoJovenes extends Descuento{

	@Override
	public 
	void calcDescuento() {
		int precioInicial = 20;
		int descuento = 30;
		//int precio;
		//precio = (precioInicial*descuento)/100;
		this.descuento = (precioInicial*descuento)/100;
		//return precio;
	}

}
