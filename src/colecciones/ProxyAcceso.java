package colecciones;

import interfaz.FrameInterfaz;

/**
 * Elemento que compone patron de diseño Proxy
 * Implementa el verdadero acceso al menu FrameInterfaz 
 */
public class ProxyAcceso implements IFrameAcceso {
	
	/**
	 * Accede al Menu principal
	 * Crea el objeto FrameInterfaz y lo vuelve visible
	 */
	@Override
	public void acceder(Compania datos, int tipo) {
		FrameInterfaz fInterfaz = new FrameInterfaz(datos, tipo);
		fInterfaz.setVisible(true);
	}
}
