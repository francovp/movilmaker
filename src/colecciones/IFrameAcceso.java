package colecciones;

public interface IFrameAcceso {

		/**
		 * Concede sobrescritura del metodo, con distintas formas de acceso definidos por el de persona
		 * @param datos Objeto de tipo Compania con sus datos
		 * @param tipo Entero que determina los privilegios de acceso de una persona
		 */
		public void acceder(Compania datos, int tipo);
	
}
