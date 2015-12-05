
public class FrameAcceso implements IFrameAcceso {
	
	@Override
	public void acceder(Compania datosEmpresa) {
		FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
		fInterfaz.setVisible(true);
		
	}
}
