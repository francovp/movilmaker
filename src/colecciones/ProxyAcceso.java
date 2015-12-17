package colecciones;

import interfaz.FrameInterfaz;

public class ProxyAcceso implements IFrameAcceso {
	
	@Override
	public void acceder(Compania datos, int tipo) {
		FrameInterfaz fInterfaz = new FrameInterfaz(datos, tipo);
		fInterfaz.setVisible(true);
	}
}
