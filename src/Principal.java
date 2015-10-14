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

		Compania datosEmpresa = null; // Aqui se guardar�n los datos de la clase empresa

		// Conexion a la base de datos de Postgres
		datosEmpresa = leerDatosBD();
		if (datosEmpresa != null)
			// Si se crearon los datos de la empresa
			System.out.println("Datos leidos desde la base de datos");
		else {
			// Si hubo cualquier especie de error al conectar a la BD o al crear los datos. 
			System.out.println("ERROR FATAL: No se obtubieron datos desde la base de datos. "
					+ "No se pudo establecer la conexion al servidor");
			System.exit(0);
		}
		
		// Llama al menu principal
		menuPrincipal(datosEmpresa);
	}

	public static void menuPrincipal(Compania datosEmpresa) throws IOException, SQLException {
		int res, resFinal = 1;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		Cliente datosCliente = null;
		while (resFinal == 1) {
			System.out.println("BIENVENIDO A " + datosEmpresa.getNombre() + "!");
			System.out.println("Eliga el numero de opcion que desee.");
			System.out.println("1- Ingresar un nuevo cliente y su contrato.");
			System.out.println("2- Ingresar un nuevo contrato a un cliente existente.");
			System.out.println("3- Terminar un contrato.");
			System.out.println("4- Eliminar un cliente de nuestra empresa.");
			System.out.println("5- Ver nuestros planes.");
			System.out.println("6- Ver Clientes actuales.");
			System.out.println("7- Modificar Datos de un cliente.");
			res = Integer.parseInt(bf.readLine());
			if (res == 1){
				// Se creara un cliente nuevo y se asignara a un objeto de tipo Cliente
				datosCliente = datosEmpresa.crearClienteNuevo(datosEmpresa.getRut()); 
				
				if(datosCliente != null){
					// Si se crea el cliente nuevo se escribira en la BD
					if(ingresarDatosBD(datosCliente))
						System.out.println("Cliente creado...");
	
					else 
					System.out.println("Cliente creado... pero no se pudo escribir en la Base de Datos. ");
					//			+ "No se pudo establecer la conexi�n al servidor");
				}
				else 
					//Sino, se informa que el cliente ya existe y se vuelve al men�
					System.out.println("Cliente ya existe...");
			}
			if (res == 2){
				// Se creara un contrato nuevo y se asignara a un objeto de tipo Cliente
				datosCliente = datosEmpresa.agregarOtroContrato();
				// Si se crea el contrato nuevo se escribira en la BD
				if(ingresarContratosBD(datosCliente.contratos.get(datosCliente.contratos.size()-1),datosCliente.getRut()))
					System.out.println("Contrato creado...");

				else 
				System.out.println("Contrato creado... pero no se pudo escribir en la Base de Datos. ");
				//			+ "No se pudo establecer la conexi�n al servidor");
			}
			if (res == 3) {
				System.out.println("Ingrese rut del cliente.");
				String rut = bf.readLine();
				if(datosEmpresa.eliminarContrato(rut))
					System.out.println("Contrato Finalizado");
			}
			if (res == 4) {
				System.out.println("Ingrese rut del cliente.");
				String rut = bf.readLine();
				if(datosEmpresa.eliminarCliente(rut))
					System.out.println("Cliente Eliminado correctamente");
				else
					System.out.println("Rut no econtrado");
			}
			if (res == 5)
				datosEmpresa.mostrarPlanes();
			if (res == 6){
				datosEmpresa.mostrarClientes();
				datosEmpresa.buscarClientesConMasPlanes();
			}
		
			if(res == 7 ) {
				System.out.println("Ingrese rut del cliente.");
				String rut = bf.readLine();
				datosEmpresa.modificarCliente(rut);
			}
			System.out.println("\nIngrese 1 para volver al menu principal. \n"
					+ "Ingrese 0 para salir del programa: \n");
			resFinal = Integer.parseInt(bf.readLine());
			if (resFinal == 0) System.exit(0);
		}
	}

	//////////////////////////// ** BASE DE DATOS //////////////////////////// 

	
	// Crea una conexion a la BD 
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
	private static boolean ingresarContratosBD(Contrato contratoCliente,String rut) throws SQLException{
		// Se crea un objeto de tipo sentencia SQL
		Statement stmt = null;
		// Se crea un objeto de tipo conexi�n SQL con los datos de conecci�n a la DB
				Connection c = conectarBD();
		if(c !=null )
		{
			// Si se cre� la conexi�n a la BD exitosamente se contin�a				
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO contratos(id_contrato,id_ciente,id_equipo,id_plan,fecha_inicio,fecha_termino,rut_cliente)"
					+"VALUES("+contratoCliente.getIdContrato()+",0,"
					+ contratoCliente.getEquipoContratado().getIdEquipo()
					+","+contratoCliente.getPlanContratado().getIdPlan()
					+",'"+contratoCliente.getFechaInicio()+"','"+contratoCliente.getFechaTermino()+"','"+rut+"'); commit";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			return true;
		}
		return false;
	}
	private static boolean ingresarDatosBD(Cliente datosCliente) throws SQLException
	{
		// Se crea un objeto de tipo sentencia SQL
		Statement stmt = null;
		// Se crea un objeto de tipo conexion SQL con los datos de coneccion a la DB
		Connection c = conectarBD();
		if(c !=null )
		{
			// Si se crea la conexion la BD exitosamente y se continua
			
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO clientes (nombre1,apellido1,id_compania,"
					+ "nombre2,fono_celular,fono_fijo,email,direccion1,direccion2,apellido2,rut) "
					+ "VALUES ('"+datosCliente.getNombre1()+"','"+datosCliente.getApellido1()
					+ "','"+datosCliente.getIdCompania()+"','"+datosCliente.getNombre2()
					+ "',"+datosCliente.getFonoCel()+","+datosCliente.getFonoFijo()+",'"+datosCliente.getEmail()
					+ "','"+datosCliente.getDireccion1()+"','"+datosCliente.getDireccion2()+"','"+datosCliente.getApellido2()
					+  "','"+datosCliente.getRut()+"'); commit";
			stmt.executeUpdate(sql);
			stmt.close();
			ingresarContratosBD(datosCliente.contratos.get(datosCliente.contratos.size()-1),datosCliente.getRut());
			c.close();
			return true;
		}else
		{
			return false;
			// Si no se pudo establecer la conexión a la BD se retorna null;
			
		}
		
		
	}
	// Establece todos los metodos de lecturas desde la Base de datos
	private static Compania leerDatosBD() throws SQLException {
		// Se crea un objeto de tipo empresa donde se guardaran los datos leidos desde la BD
		Compania DatosEmpresa = null;
		// Se crea un objeto de tipo sentencia SQL
		Statement stmt = null;
		// Se crea un objeto de tipo resultado Query SQL
		ResultSet rs = null;
		// Se crea un objeto de tipo conexion SQL con los datos de coneccion a la DB
		Connection c = conectarBD();
		if (c != null){
			// Si se creo la conexion a la BD exitosamente y se continua
			
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM compania;");
			while (rs.next()) {
				// Se obtienen datos de las tablas
				// Se crea un objeto compania con los datos obtenidos de la DB
				DatosEmpresa = new Compania(rs.getString("nombre"), rs.getString("id_compania"));
			}
			
			// Se leen datos de Planes desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM planes;");
			while (rs.next()) {
				// Se obtienen datos de la plan
				Plan p = new Plan(rs.getInt("id_plan"), rs.getString("nombrePlan"), rs.getInt("precio")
								, rs.getInt("minutos"), rs.getInt("gigas"), rs.getString("id_compania"));
				
				DatosEmpresa.getPlanes().add(p);
			}
			
			// Se leen datos de Equipos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM equipos;");
			while (rs.next()) {
				// Se obtienen datos de la equipos
				Equipo e = new Equipo(rs.getInt("id_equipo"), rs.getString("nombreEquipo")
									, rs.getInt("valor_con_plan"), rs.getInt("valor_sin_plan")
									, rs.getString("capacidad"), rs.getString("id_compania"));
				
				DatosEmpresa.getMoviles().add(e);
			}
			
			rs = stmt.executeQuery("SELECT * FROM clientes;");
			while (rs.next()) {
				// Se obtienen datos de cliente y se asignan
				Cliente cli = new Cliente(rs.getString("nombre1"),rs.getString("nombre2"),rs.getString("apellido1"),rs.getString("apellido2")
										,rs.getString("rut"),rs.getInt("fono_celular"),rs.getInt("fono_fijo"),rs.getString("direccion1")
										,rs.getString("direccion2"),rs.getString("rut"),rs.getString("id_compania"));
				DatosEmpresa.getListaClientes().add(cli);
			}
			
			rs.close();
			stmt.close();
			
			// Se cierra conexion a la BD
			c.close();
			
			// Se retorna toda la coleccion empresa
			return DatosEmpresa;
		}else 
			// Si no se pudo establecer la conexion a la BD se retorna null;
			return null;
	}
}
