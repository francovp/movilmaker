package extras;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import colecciones.Administrador;
import colecciones.Cliente;
import colecciones.Compania;
import colecciones.Contrato;
import colecciones.Equipo;
import colecciones.Plan;
import colecciones.RegistroDePagos;

/**
 * @author Franco
 *
 */
public class Database {
	
	private int tipodb = 0; // 0 = remota (openshift) ; 1 = localhost
	private static Database db;
	private static boolean databaseEstaDisponible = true;
  
	private static Connection dbConnection = null; // Objeto de tipo coneccion donde se guardaran los datos de coneccion
	private static Statement stmt = null; // Objeto de tipo sentencia SQL
	private static ResultSet rs = null; // Objeto de tipo resultado Query SQL
	
	// CONSTRUCTOR
	private Database() {
		if(tipodb == 0){
			try {
				Class.forName("org.postgresql.Driver");
				dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/movilmaker", "adminvkgxatl", "YxjXw3He8hzu");
				dbConnection.setAutoCommit(true);
			} catch (SQLException | ClassNotFoundException e) {
				System.err.println("No se pudo establecer la conexion con la base de datos!\n"
						+ "Verifique que el proceso db_connection.exe estÃ© iniciado y que el puerto local sea 5433\n"
						+ "Verifique que la base de datos PostgreSQL este configurada correctamente.\n"
						+ "\n\nDetalles de la excepcion:");
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				try {
					dbConnection.close();
				} catch (SQLException e1) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
			}
		}
		if(tipodb == 1){
			try {
				Class.forName("org.postgresql.Driver");
				dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/movilmaker", "postgres", "12345");
				dbConnection.setAutoCommit(true);
			} catch (SQLException | ClassNotFoundException e) {
				System.err.println("No se pudo establecer la conexion con la base de datos!\n"
						+ "Verifique que el servicio del servidor PostgreSQL este iniciado, o que la base de datos de PostgreSQL este instalada y configurada correctamente.\n"
						+ "\n\nDetalles de la excepcion:");
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				try {
					dbConnection.close();
				} catch (SQLException e1) {
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
				}
			}
		}
	}

	public static Database obtenerDB() {
		if (databaseEstaDisponible) {
			if (db == null) {
				db = new Database();
			}
			databaseEstaDisponible = false;
			return db;
		} else {
			return null;
			//DB no está¡ disponible, 
			//  puede retornar un error o retornal null
		}
	}

	/**
	 * Cierra la coneccion con la BD
	 */
	public static void cerrarDatabase() {
		try {
			db.cleanDatabase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		databaseEstaDisponible = true;
	}

	public void cleanDatabase() throws SQLException {
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		// Se cierra conexion a la BD
		dbConnection.close();
	}  

	public Connection getDatabaseConnection() {
		return this.dbConnection;
	}
	
	public void setDatabaseConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////

	/**
	 * Ingresa los datos de la empresa en la Tabla COMPANIA de la BD
	 * @param empresa - una referencia a la Compania
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean ingresarEmpresaBD(Compania empresa) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO compania (nombre,id_compania) " + "VALUES ('" + empresa.getNombre() + "','"
					+ empresa.getRut() + "');";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexion a la BD se retorna null;
	}
	
	/**
	 * Ingresa los datos de un cliente en la Tabla PERSONA de la BD
	 * @param datosCliente - una referencia al Cliente
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean ingresarClienteBD(Cliente datosCliente) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO persona (nombre1,apellido1,id_compania,"
					+ "nombre2,fono_celular,fono_fijo,email,direccion1,direccion2,apellido2,rut,tipo) " + "VALUES ('"
					+ datosCliente.getNombre1() + "','" + datosCliente.getApellido1() + "','"
					+ datosCliente.getIdCompania() + "','" + datosCliente.getNombre2() + "',"
					+ datosCliente.getFonoCel() + "," + datosCliente.getFonoFijo() + ",'" + datosCliente.getEmail()
					+ "','" + datosCliente.getDireccion1() + "','" + datosCliente.getDireccion2() + "','"
					+ datosCliente.getApellido2() + "','" + datosCliente.getRut() + "'," + datosCliente.getTipo()
					+ ");";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexion a la BD se retorna null
	}

	/**
	 * Ingresa los datos de un Administrador en la Tabla PERSONA de la BD
	 * @param datosAdmin - una referencia al Administrador
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean ingresarAdminBD(Administrador datosAdmin) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO persona (nombre1,apellido1,id_compania,"
					+ "nombre2,fono_celular,fono_fijo,email,apellido2,rut,tipo,password) " + "VALUES ('"
					+ datosAdmin.getNombre1() + "','" + datosAdmin.getApellido1() + "','" + datosAdmin.getIdCompania()
					+ "','" + datosAdmin.getNombre2() + "'," + datosAdmin.getFonoCel() + "," + datosAdmin.getFonoFijo()
					+ ",'" + datosAdmin.getEmail() + "','" + datosAdmin.getApellido2() + "','" + datosAdmin.getRut()
					+ "'," + datosAdmin.getTipo() + ",'" + datosAdmin.getPassword() + "');";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexion a la BD se retorna null;
	}
	
	/**
	 * Ingresa un Contrato de un Cliente en la Tabla CONTRATOS de la BD
	 * @param contratoCliente - una referencia al Contrato del cliente a agregar
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean ingresarContratoBD(Contrato contratoCliente) throws SQLException {
		if (dbConnection != null) {
			// Si se creï¿½ la conexiï¿½n a la BD exitosamente se continï¿½a
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO contratos(id_contrato,id_equipo,id_plan,fecha_inicio,fecha_termino,"
					+ "rut_cliente,valor_total,cuotas,valor_cuota)" + "VALUES('" + contratoCliente.getIdContrato()
					+ "'," + contratoCliente.getEquipoContratado().getIdEquipo() + ","
					+ contratoCliente.getPlanContratado().getIdPlan() + ",'" + contratoCliente.getFechaInicio() + "','"
					+ contratoCliente.getFechaTermino() + "','" + contratoCliente.getRutCliente() + "',"
					+ contratoCliente.getValorTotal() + "," + contratoCliente.getCuotas() + ","
					+ contratoCliente.getValorCuota() + ");";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		}
		return false;
	}
	
	/**
	 * Ingresa un Plan en la Tabla PLANES de la BD
	 * @param p - una referencia al Contrato del cliente a agregar
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean ingresarPlanBD(Plan p) throws SQLException {
		if (dbConnection != null) {
			// Si se creï¿½ la conexiï¿½n a la BD exitosamente se continï¿½a
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO planes(id_plan,nombre_plan,minutos,gigas,precio,sms,valor_min,id_compania)"
					+ "VALUES('" + p.getIdPlan() + "','" + p.getNombrePlan()+ "'," + p.getMinutos() + "," + p.getGigas() + ","
					+ p.getPrecio() + "," + p.getSms() + "," + p.getValorMin() + "," + p.getIdCompania() + ");";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		}
		return false;
	}

	public static boolean ingresarRegistroBD(RegistroDePagos registro) throws SQLException {
		if (dbConnection != null) {
			// Si se creï¿½ la conexiï¿½n a la BD exitosamente se continï¿½a
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			String sql = "INSERT INTO boletas(id_contrato, rut_cliente,cuotas_rest,id_boleta,monto_pagadop)"
					+ "VALUES('" + registro.getIdContrato() + "'," + registro.getContratoAPagar().getRutCliente() + "',"
					+ registro.getCuotasRestantes() + "," + registro.getIdRegistro() + "," + registro.getMontoPagado()
					+ ");";
			stmt.executeUpdate(sql);
			stmt.close();
			dbConnection.close();
			return true;
		}
		return false;
	}

	/**
	 * Elimina un Cliente desde la Tabla PERSONA de la BD
	 * @param rut - el RUT de la persona a eliminar
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean eliminarClienteBD(String rut) throws SQLException {
		String sql;
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			sql = "DELETE FROM persona WHERE (rut = '" + rut + "');";
			stmt.executeUpdate(sql);
			stmt.close();

			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			sql = "DELETE FROM contratos WHERE (rut_cliente = '" + rut + "');";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexion a la BD se retorna null;

	}

	/**
	 * Elimina un Contrato desde la Tabla CONTRATOS de la BD
	 * @param rut - el RUT de la persona a la que pertenece el Contrato
	 * @return Un boolean si se ingresaron los datos correctamente o no
	 */
	public static boolean eliminarContratoBD(String rut) throws SQLException {
		String sql;
		if (dbConnection != null) {
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			sql = "DELETE FROM contratos WHERE (rut_cliente = '" + rut + "');";
			stmt.executeUpdate(sql);
			cerrarDatabase();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexion a la BD se retorna null;

	}
	
	/**
	 * Lee los datos de la Empresa desde la Tabla COMPANIA de la BD
	 * @param empresa - una referencia a un objeto de tipo Empresa para guardar los datos 
	 * @return Un objeto de tipo Compania con los datos ya ingresados en el
	 */
	public static Compania leerEmpresaBD(Compania empresa) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM compania;");
			if (rs == null){
				// Si no hay ninguna empresa creada retorna null
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next())
					// Se obtienen datos de las tablas
					// Se crearo un objeto compaoia con los datos obtenidos de
					// la DB
					empresa = new Compania(rs.getString("nombre"), rs.getString("id_compania"));
				cerrarDatabase();
				// Se retorna toda la coleccion empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexion a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	/**
	 * Lee los datos de los planes desde la Tabla PLANES de la BD
	 * @param empresa - una referencia a un objeto de tipo Empresa para guardar los datos 
	 * @return Un objeto de tipo Compania con los datos ya ingresados en el
	 */
	public static Compania leerPlanesBD(Compania empresa) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			// Se leen datos de Planes desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM planes;");
			if (rs == null){
				// Si no hay ningun plan creado retorna null
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next()) {
					// Se obtienen datos de la plan
					Plan p = new Plan(rs.getInt("id_plan"), rs.getString("nombre_plan"), rs.getInt("precio"),
							rs.getInt("minutos"), rs.getInt("gigas"), rs.getInt("sms"), rs.getInt("valor_min"), rs.getString("id_compania"));

					empresa.getPlanes().agregarPlan(p);
				}
				cerrarDatabase();
				// Se retorna toda la coleccion empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexion a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	/**
	 * Lee los datos de los Equipos desde la Tabla EQUIPOS de la BD
	 * @param empresa - una referencia a un objeto de tipo Empresa para guardar los datos 
	 * @return Un objeto de tipo Compania con los datos ya ingresados en el
	 */
	public static Compania leerEquiposBD(Compania empresa) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			// Se leen datos de Equipos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM equipos;");
			if (rs == null){
				// Si no hay ningun Equipo creado retorna null
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next()) {
					// Se obtienen datos de la equipos
					Equipo e = new Equipo(rs.getInt("id_equipo"), rs.getString("nombre_equipo"),
							rs.getInt("valor_con_plan"), rs.getInt("valor_sin_plan"), rs.getString("capacidad"),
							rs.getString("id_compania"));

					empresa.getEquipos().agregarEquipo(e);
				}
				cerrarDatabase();
				// Se retorna toda la coleccion empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexion a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	/**
	 * Lee los datos de las Personas desde la Tabla PERSONAS de la BD
	 * @param empresa - una referencia a un objeto de tipo Empresa para guardar los datos 
	 * @return Un objeto de tipo Compania con los datos ya ingresados en el
	 */
	public static Compania leerPersonasBD(Compania empresa) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			// Se leen datos de Personas desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM persona;");
			if (rs == null){
				// Si no hay ninguna Persona creada retorna null
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next()) {
					// Se obtienen datos de personas y se verifica que tipo de
					// persona es la fila actual
					int tipo = rs.getInt("tipo");
					// y se asigna dependiendo del tipo
					if (tipo == 0) {
						// Administrador
						Administrador admin = new Administrador(rs.getString("rut"), rs.getString("id_compania"),
								rs.getString("nombre1"), rs.getString("nombre2"), rs.getString("apellido1"),
								rs.getString("apellido2"), rs.getInt("fono_celular"), rs.getInt("fono_fijo"),
								rs.getString("email"), rs.getInt("tipo"), rs.getString("direccion1"),
								rs.getString("direccion2"), rs.getInt("deuda"), rs.getString("password"));
						empresa.getAdministradores().agregarAdministrador(admin);
					}
					if (tipo == 1) {
						// Cliente
						Cliente cli = new Cliente(rs.getString("rut"), rs.getString("id_compania"),
								rs.getString("nombre1"), rs.getString("nombre2"), rs.getString("apellido1"),
								rs.getString("apellido2"), rs.getInt("fono_celular"), rs.getInt("fono_fijo"),
								rs.getString("email"), rs.getInt("tipo"), rs.getString("direccion1"),
								rs.getString("direccion2"), rs.getInt("deuda"), rs.getString("password"));
						empresa.getClientes().agregarCliente(cli);
					}
				}
				cerrarDatabase();
				// Se retorna toda la coleccion empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexion a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	/**
	 * Lee los datos de los contratos desde la Tabla CONTRATOS de la BD
	 * @param empresa - una referencia a un objeto de tipo Empresa para guardar los datos 
	 * @return Un objeto de tipo Compania con los datos ya ingresados en el
	 */
	public static Compania leerContratosBD(Compania empresa) throws SQLException {
		if (dbConnection != null) {
			// Si se creo la conexion a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = dbConnection.createStatement();
			// Se leen datos de Contratos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM contratos;");
			if (rs == null){
				// Si no hay ninguna Persona creada retorna null
				cerrarDatabase();
				return null;
			}
			else {
				while (rs.next()) {
					// Se obtienen datos de la equipos
					int idPlan = rs.getInt("id_plan");
					int idEquipo = rs.getInt("id_equipo");
					Plan p = empresa.buscarPlan(idPlan);
					Equipo e = empresa.buscarEquipo(idEquipo);

					Contrato c = new Contrato(rs.getInt("id_contrato"), rs.getString("fecha_inicio"),
							rs.getString("fecha_termino"), idEquipo, idPlan, e, p, rs.getInt("valor_total"),
							rs.getInt("cuotas"), rs.getInt("valor_cuota"), rs.getString("rut_cliente"));

					// Busco el cliente al cual se le asignara el contrato
					Cliente cliente = empresa.buscarCliente(c.getRutCliente());
					// Luego agrego el contrato a ESE cliente
					cliente.getContratos().agregarContrato(c);
				}
				cerrarDatabase();
				// Se retorna toda la coleccion empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexion a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	// public Compania leerBoletasBD(Compania empresa) throws SQLException {
	// if (c != null){
	// // Si se creo la conexion a la BD exitosamente se continua
	// // Se crea una nueva sentencia SQL
	// stmt = c.createStatement();
	// // Se leen datos de boletas desde la BD
	// int idContratoBoleta;
	// String rutClienteBoleta;
	// Contrato contratoBoleta = null;
	// Cliente clienteBoleta = null;
	// // Se ejecuta la sentencia SQL y se guarda
	// rs = stmt.executeQuery("SELECT * FROM boletas;");
	// if(rs == null){
	// // Si no hay ninguna Persona creada retorna null
	// return null;
	// }else{
	// while (rs.next()) {
	// idContratoBoleta = rs.getInt("id_contrato");
	// rutClienteBoleta = rs.getString("rut_cliente");
	// contratoBoleta =
	// empresa.buscarCliente(rutClienteBoleta).buscarContrato(idContratoBoleta);
	// // Se obtienen datos de la equipos
	//
	// RegistroDePagos c = new RegistroDePagos (idContratoBoleta,
	// contratoBoleta.getIdEquipo(),
	// contratoBoleta.getIdPlan(), contratoBoleta.getEquipoContratado(),
	// contratoBoleta.getPlanContratado(),
	// contratoBoleta.getFechaInicio(), contratoBoleta.getFechaTermino(),
	// contratoBoleta.getRutCliente(),contratoBoleta.getValorTotal(),contratoBoleta.getValorCuota(),
	// contratoBoleta.getCuotas()
	// , rs.getInt("id_boleta"), rs.getInt("monto_pagado"),
	// rs.getInt("montoAdeudado"), rs.getInt("cuotas_rest"));
	//
	// empresa.buscarCliente(rutClienteBoleta).getContratos().add(c);
	// }
	// rs.close();
	// stmt.close();
	// // Se cierra conexion a la BD
	// c.close();
	// // Se retorna toda la coleccion empresa
	// return empresa;
	// }
	// }else{
	// // Si no se pudo establecer la conexion a la BD se retorna null;
	// // Si hubo cualquier especie de error al conectar a la BD o al crear los
	// datos.
	// return null;
	// }
	// }
}
