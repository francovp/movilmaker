package colecciones;
import java.sql.SQLException;

import extras.Database;
import interfaz.FrameAccesoProxy;

public class Principal {
	/**
	 * @author FValerio, DMayorga, MSilva, LMondaca
	 * @throws SQLException 
	 *
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Database bd = null;
		Compania datos = null;
		bd = new Database();
		datos = bd.leerEmpresaBD(datos);
		bd = new Database();
		datos = bd.leerPersonasBD(datos);
		bd = new Database();
		datos = bd.leerPlanesBD(datos);
		bd = new Database();
		datos = bd.leerEquiposBD(datos);
		bd = new Database();
		datos = bd.leerContratosBD(datos);
		
		FrameAccesoProxy accessproxy = new FrameAccesoProxy(datos);
		accessproxy.setVisible(true);
	}
	
	/**
	 * Verifica si un RUT es válido o no
	 * @param rut - El RUT a verificar
	 * @return un boolean si el RUT es válido o no
	 **/
	public static boolean validarRut(String rut) {
		 
		boolean validacion = false;
		try {
			rut =  rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}

		} catch (java.lang.NumberFormatException e) {
		} catch (Exception e) {
		
		}
		return validacion;
	}

}
