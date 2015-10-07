import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

		Compania datosEmpresa = null; // Aqu� se guardar�n los datos de la clase empresa

		// Conexi�n a la base de datos de Postgres
		datosEmpresa = leerDatosBD();
		if (datosEmpresa != null)
			// Si se crearon los datos de la empresa
			System.out.println("Datos leidos desde la base de datos");
		else {
			// Si hubo cualquier especie de error al conectar a la BD o al crear los datos. 
			System.out.println("ERROR FATAL: No se obtubieron datos desde la base de datos. "
					+ "No se pudo establecer la conexi�n al servidor");
			System.exit(0);
		}
		
		// Llama al men� principal
		menuPrincipal(datosEmpresa);
	}

	public static void menuPrincipal(Compania empresa) throws IOException, SQLException {
		int res, resFinal = 1;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while (resFinal == 1) {
			System.out.println("� BIENVENIDO A " + empresa.getNombre() + "!");
			System.out.println("Eliga el numero de opcion que desee.");
			System.out.println("1- Ingresar un nuevo cliente y su contrato.");
			System.out.println("2- Ingresar un nuevo contrato a un cliente existente.");
			System.out.println("3- Terminar un contrato.");
			System.out.println("4- Eliminar un cliente de nuestra empresa.");
			System.out.println("5- Ver nuestros planes.");
			System.out.println("6- Ver Clientes actuales.");
			res = Integer.parseInt(bf.readLine());
			if (res == 1){
				// Se crear� un cliente nuevo y se asignar� a un objeto de tipo Cliente
				Cliente datosCliente = empresa.crearClienteNuevo(empresa.getRut()); 
				if(datosCliente != null)
					// Si se cre� el cliente nuevo se escribir� en la BD
					//if(escribirDatosBDCliente(datosCliente))
						System.out.println("Cliente creado...");
					//else 
					//	System.out.println("Cliente creado... pero no se pudo escribir en la Base de Datos. "
					//			+ "No se pudo establecer la conexi�n al servidor");
				else 
					//Sino, se informa que el cliente ya existe y se vuelve al men�
					System.out.println("Cliente ya existe...");
			}
			if (res == 2)
				empresa.agregarOtroContrato();
			if (res == 3) {
				System.out.println("Ingrese rut del cliente.");
				String rut = bf.readLine();
				empresa.eliminarContrato(rut);
			}
			if (res == 4) {
				System.out.println("Ingrese rut del cliente.");
				String rut = bf.readLine();
				empresa.eliminarCliente(rut);
			}
			if (res == 5)
				empresa.mostrarPlanes();
			if (res == 6)
				empresa.mostrarClientes();
			System.out.println("\nIngrese 1 para volver al men� principal"
					+ "Ingrese 0 para salir del programa: \n");
			resFinal = Integer.parseInt(bf.readLine());
			if (resFinal == 0) System.exit(0);
		}
	}

	//////////////////////////// ** BASE DE DATOS //////////////////////////// 

	// Crea una conexi�n a la BD 
	private static Connection conectarBD() throws SQLException {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vomistar", "postgres", "12345");
			c.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			c.close();
			return null;
		}
		return c;
	}
	
	// Establece todos los m�todos de lecturas desde la Base de datos
	private static Compania leerDatosBD() throws SQLException {
		// Se crea un objeto de tipo conexi�n SQL con los datos de conecci�n a la DB
		Connection c = conectarBD();
		if (c != null){
			// Si se cre� la conexi�n a la BD exitosamente se contin�a
			
			// Se crea un objeto de tipo sentencia SQL
			Statement stmt = c.createStatement();
			// Se leen datos de la empresa desde la BD
			Compania empresa = leerDatosDBEmpresa(stmt, c);
			// Se leen datos de Planes desde la BD
			leerDatosDBPlanes(stmt, c, empresa);
			// Se leen datos de Equipos desde la BD
			leerDatosDBEquipos(stmt, c, empresa);
			
			// Se cierra conexi�n a la BD
			c.close();
			
			// Se retorna toda la colecci�n empresa
			return empresa;
		}else 
			// Si no se pudo establecer la conexi�n a la BD se retorna null;
			return null;
	}

	// Metodo para leer datos de la empresa desde la Base de datos
	private static Compania leerDatosDBEmpresa (Statement stmt, Connection c) throws SQLException {
		Compania empresa = null;
		// Se crea un objeto de tipo sentencia
		stmt = c.createStatement();
		// Se crea un objeto de tipo resultado Query SQL y ejecuta la sentencia SQL
		ResultSet rs = stmt.executeQuery("SELECT * FROM compania;");
		while (rs.next()) {
			// Se obtienen datos de las tablas
			String rutCompania = rs.getString("id_compania");
			String nombreCompania = rs.getString("nombre");
			// Se crear� un objeto compa�ia con los datos obtenidos de la DB
			empresa = new Compania(nombreCompania, rutCompania);
		}
		rs.close();
		stmt.close();
		return empresa;
	}

	// Metodo para leer datos de los Planes desde la Base de datos
	private static void leerDatosDBPlanes (Statement stmt, Connection c, Compania empresa) throws SQLException {
		// // Se crea un objeto de tipo resultado Query SQL y ejecuta la sentencia SQL
		ResultSet rs = stmt.executeQuery("SELECT * FROM planes;");
		while (rs.next()) {
			// Se obtienen datos de la plan
			int idPlan = rs.getInt("id_plan");
			String nombrePlan = rs.getString("nombrePlan");
			int minutosPlan = rs.getInt("minutos");
			int gigasPlan = rs.getInt("gigas");
			int precioPlan = rs.getInt("precio");
			String idCompaniaPlan = rs.getString("id_compania");

			Plan p = new Plan(idPlan, nombrePlan, precioPlan, minutosPlan, gigasPlan, idCompaniaPlan);
			empresa.planes.add(p);
		}
		rs.close();
		stmt.close();
	}
	
	// Metodo para leer datos de los Equipos desde la Base de datos
	private static void leerDatosDBEquipos (Statement stmt, Connection c, Compania empresa) throws SQLException {
		// Se crea un objeto de tipo sentencia
		stmt = c.createStatement();
		// Se ejecuta la sentencia SQL
		ResultSet rs = stmt.executeQuery("SELECT * FROM equipos;");
		while (rs.next()) {
			// Se obtienen datos de la equipos
			int idEquipo = rs.getInt("id_equipo");
			String nombreEquipo = rs.getString("nombreEquipo");
			String capacidadEquipo = rs.getString("capacidad");
			int valorPlanEquipo = rs.getInt("valor_con_plan");
			int valorSinPlanEquipo = rs.getInt("valor_sin_plan");
			String idCompaniaEquipo = rs.getString("id_compania");
			Equipo e = new Equipo(idEquipo, nombreEquipo, valorPlanEquipo, valorSinPlanEquipo, capacidadEquipo, idCompaniaEquipo);
			empresa.moviles.add(e);
		}
		rs.close();
		stmt.close();
	}
}
