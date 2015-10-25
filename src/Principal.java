import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */

/**
 * @author Franco
 *
 */
public class Principal {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		obtenerDatos();
	}

	public static void obtenerDatos() throws SQLException {
		Database bd = null;
		Compania datos = null; // Aquí se guardará toda la información de la
								// empresa

		// Valor para saber qué datos faltan en los datos guardados
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
			System.out.println("Datos de empresa obtenidos");
			// Ahora se obtienen datos de Personas
			bd = new Database();
			datos = bd.leerPersonasBD(datos);
			if (datos != null) {
				// Se verificará si en el ArrayList hay algun admin
				if (datos.getListaAdmins().size() == 0) {
					System.err.println("Datos de personas obtenidos, pero NO HAY ADMIN");
					// Para saber que hay personas guardadas, pero ninguna es un
					// admin.
					falta = 1;
					// Se llamará a una Interfaz AgregarAdmin para registrar al
					// primer administrador
					FrameAgregarAdmin fAdmin = new FrameAgregarAdmin(datos, falta);
					fAdmin.setVisible(true);
				} else {
					// Hay admins en la lista de Personas
					System.out.println("Datos de personas obtenidos");
					// Para saber que hay admin
					falta = 2;
					// Ahora se obtienen datos de Contratos
					bd = new Database();
					datos = bd.leerContratosBD(datos);
					if (datos != null) {
						System.out.println("Datos de contratos obtenidos");
						// Ahora se obtienen datos de Planes
						bd = new Database();
						datos = bd.leerPlanesBD(datos);
						if (datos != null) {
							System.out.println("Datos de planes obtenidos");
							// Ahora se obtienen datos de Equipos
							bd = new Database();
							datos = bd.leerEquiposBD(datos);
							if (datos != null) {
								System.out.println("Datos de equipos obtenidos");
								falta = -1;
								// Se llamará a Interfaz principal
								FrameInterfaz fInterfaz = new FrameInterfaz(datos, falta);
								fInterfaz.setVisible(true);
							} else
								System.err.println("No hay datos de equipos");
							// Por ahora aquí no se hace nada
							// Mientras no se hace nada mientras no se agregue
							// una interfaz para crear Equipos
						} else
							System.err.println("No hay datos de planes");
						// Por ahora aquí no se hace nada
						// Mientras no se hace nada mientras no se agregue una
						// interfaz para crear Planes
					} else
						System.err.println("No hay datos de contratos");
					// Por ahora aquí no se hace nada
					// Mientras no se hace nada mientras no se agregue una
					// interfaz para crear Equipos
				}
			} else {
				System.err.println("No hay datos de personas");
				// Para saber que no hay personas guardadas. Se creará un admin
				falta = 0;
				// Se llamará a una Interfaz AgregarAdmin para registrar al
				// primer administrador
				FrameAgregarAdmin fAdmin = new FrameAgregarAdmin(datos, falta);
				fAdmin.setVisible(true);
			}
		} else {
			System.err.println("No hay empresa registrada en el programa.\n");
			System.out.println("Se procesederá a registrar una.");
			// Llama a Interfaz para Registrar Empresa
			FrameRegistrarEmpresa iRegistrarEmpresa = new FrameRegistrarEmpresa(datos);
			iRegistrarEmpresa.setVisible(true);
		}
	}

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
}
