package colecciones;

import java.io.IOException;
import java.sql.SQLException;

import extras.Database;
import interfaz.FrameInterfaz;
import interfaz.FrameRegistrarEmpresa;
import interfaz.agregar.FrameAgregarAdmin;

/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Principal {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		// Se obtendran los datos desde la BD y se cargarán las interfaces respectivas
		obtenerDatos();
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	/**
	 * Obtiene todos la estructura de datos del programa desde la Base de datos
	 * Además se encarga de decidir qué interfaces cargar dependiendo de qué datos están
	 * presentes en la BD
	 * @throws ClassNotFoundException 
	 **/
	public static boolean obtenerDatos() throws SQLException{
		Database bd = null;
		Compania datos = null; // Aquí se guarda toda la informacion de la empresa

		// Valor para saber que datos faltan en los datos guardados
		int falta = 0;
		// 0 = No hay personas, no hay admin ; 1 = Hay personas, NO hay admin ;
		// 2 = Hay personas y hay admin
		// -1 = hay todo
		// ... continuará..

		// Conexion a la base de datos de Postgres
		bd = new Database();

		// Se obtienen datos de la Empresa
		datos = bd.leerEmpresaBD(datos);
		if (datos != null) {
			// Ahora se obtienen datos de Personas
			bd = new Database();
			datos = bd.leerPersonasBD(datos);
			if (datos != null) {
				// Se verifica si en el ArrayList hay algun admin
				if (datos.mostrarAdmins().size() == 0) {
					System.err.println("NO HAY ADMINISTRADOR! Se procesedará a registrar uno");
					// Para saber que hay personas guardadas, pero ninguna es un admin.
					falta = 1;
					// Se llama a una Interfaz AgregarAdmin para registrar al
					// primer administrador
					FrameAgregarAdmin fAdmin = new FrameAgregarAdmin(datos, falta);
					fAdmin.setVisible(true);
				}
				// Hay admins en la lista de Personas
				// Para saber que hay admin
				falta = 2;
				// Ahora se obtienen datos de Contratos
				bd = new Database();
				datos = bd.leerContratosBD(datos);
				if (datos != null) {
					// Ahora se obtienen datos de Planes
					bd = new Database();
					datos = bd.leerPlanesBD(datos);
					if (datos != null) {
						// Ahora se obtienen datos de Equipos
						bd = new Database();
						datos = bd.leerEquiposBD(datos);
						if (datos != null) {
							falta = -1;
							// Se llama a Interfaz principal
							FrameInterfaz fInterfaz = new FrameInterfaz(datos, falta);
							fInterfaz.setVisible(true);
						} else System.err.println("No hay datos de equipos");
					} else System.err.println("No hay datos de planes");
				} else System.err.println("No hay datos de contratos");
			} else {
				System.err.println("No hay datos de personas");
				// Para saber que no hay personas guardadas. Se crea un admin
				falta = 0;
				// Se llama a una Interfaz AgregarAdmin para registrar al
				// primer administrador
				FrameAgregarAdmin fAdmin = new FrameAgregarAdmin(datos, falta);
				fAdmin.setVisible(true);
			}
		} else {
			System.err.println("No hay empresa registrada en el programa.\n");
			System.out.println("Se procesede a registrar una.");
			// Llama a Interfaz para Registrar Empresa
			FrameRegistrarEmpresa iRegistrarEmpresa = new FrameRegistrarEmpresa(datos);
			iRegistrarEmpresa.setVisible(true);
		}
		return true;
	}
	
	/**
	 * Limpia la consola
	 * (No funciona)
	 **/
	public static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows"))
				Runtime.getRuntime().exec("cls");
			else
				Runtime.getRuntime().exec("clear");
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}
	
	/**
	 * Verifica si una cadena es de tipo númerico
	 * @param cadena - Un string con los datos a verificar
	 * @return un boolean si la cadena es númerica o no
	 **/
	public static boolean esNumerico(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
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
