package colecciones;

import interfaz.FrameInterfaz;

public class ProxyAcceso implements IFrameAcceso {
	
	@Override
	public void acceder(Compania datosEmpresa) {
		FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
		fInterfaz.setVisible(true);
		
	}
}
