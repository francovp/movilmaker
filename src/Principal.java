import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		//Conexion a la base de datos de Postgres
		Database bd = new Database();

		Compania datosEmpresa = null; // Aqui­ se guardaran los datos de la clase empresa

		// Conexión la base de datos de Postgres
		datosEmpresa = bd.leerDatosBD();
		if (datosEmpresa != null)
			// Si se crearon los datos de la empresa
			System.out.println("Datos leidos desde la base de datos");
		else {
			// Si hubo cualquier especie de error al conectar a la BD o al crear los datos.
			System.err.println("ERROR FATAL: No se obtubieron datos desde la base de datos. ");
			System.exit(0);
		}

//================================Llama a FrameInterfaz==========================================
		FrameInterfaz iPrincipal = new FrameInterfaz(datosEmpresa);
		iPrincipal.setVisible(true);
	}
}
