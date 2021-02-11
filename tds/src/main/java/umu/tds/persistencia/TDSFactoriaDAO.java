package umu.tds.persistencia;


public class TDSFactoriaDAO extends FactoriaDAO {

	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancionTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorListaCancionesDAO getListaCancionesDAO() {
		return AdaptadorListaCancionesTDS.getUnicaInstancia();
	}

	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuarioTDS.getUnicaInstancia();
	}

}
