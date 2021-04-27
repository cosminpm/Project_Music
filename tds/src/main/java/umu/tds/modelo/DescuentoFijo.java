package umu.tds.modelo;

public class DescuentoFijo extends Descuento{

	@Override
	public
	void calcDescuento() {
		int precioInicial = 20;
		int descuento = 15;
		this.descuento = (precioInicial*descuento)/100;
	}
	

}
