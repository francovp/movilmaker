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
public class Database {

	Connection c = null; // Objeto de tipo coneccion donde se guardarán los
							// datos de coneccion
	Statement stmt = null; // Objeto de tipo sentencia SQL
	ResultSet rs = null; // Objeto de tipo resultado Query SQL

	public Database() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vomistar", "postgres", "12345");
			c.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println("No se pudo establecer la conexión con la base de datos!\n"
					+ "Verifique que el servicio del servidor PostgreSQL esté iniciado, o que la base de datos de PostgreSQL esté instalada y configurada correctamente.\n"
					+ "El nombre de la base de datos debería ser 'vomistar', el usuario 'postgres' y la contraseña '12345'"
					+ "\n\nDetalles de la excepción:");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			c.close();
		}
	}

	public boolean ingresarEmpresaBD(Compania empresa) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO compania (nombre,id_compania) " + "VALUES ('" + empresa.getNombre() + "','"
					+ empresa.getRut() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			// Se cierra conexión a la BD
			c.close();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexión a la BD se retorna null;
	}

	public boolean ingresarClienteBD(Cliente datosCliente) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO persona (nombre1,apellido1,id_compania,"
					+ "nombre2,fono_celular,fono_fijo,email,direccion1,direccion2,apellido2,rut,tipo) " + "VALUES ('"
					+ datosCliente.getNombre1() + "','" + datosCliente.getApellido1() + "','"
					+ datosCliente.getIdCompania() + "','" + datosCliente.getNombre2() + "',"
					+ datosCliente.getFonoCel() + "," + datosCliente.getFonoFijo() + ",'" + datosCliente.getEmail()
					+ "','" + datosCliente.getDireccion1() + "','" + datosCliente.getDireccion2() + "','"
					+ datosCliente.getApellido2() + "','" + datosCliente.getRut() + "'," + datosCliente.getTipo()
					+ ");";
			stmt.executeUpdate(sql);
			stmt.close();
			// Se cierra conexión a la BD
			c.close();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexión a la BD se retorna null
	}

	public boolean ingresarAdminBD(Administrador datosAdmin) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO persona (nombre1,apellido1,id_compania,"
					+ "nombre2,fono_celular,fono_fijo,email,apellido2,rut,tipo,password) " + "VALUES ('"
					+ datosAdmin.getNombre1() + "','" + datosAdmin.getApellido1() + "','" + datosAdmin.getIdCompania()
					+ "','" + datosAdmin.getNombre2() + "'," + datosAdmin.getFonoCel() + "," + datosAdmin.getFonoFijo()
					+ ",'" + datosAdmin.getEmail() + "','" + datosAdmin.getApellido2() + "','" + datosAdmin.getRut()
					+ "'," + datosAdmin.getTipo() + ",'" + datosAdmin.getPassword() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			// Se cierra conexión a la BD
			c.close();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexión a la BD se retorna null;
	}

	// Para eliminar un cliente de la BD y todos sus contratos
	public boolean eliminarClienteBD(String rut) throws SQLException {
		String sql;
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			sql = "DELETE FROM clientes WHERE (rut = '" + rut + "');";
			stmt.executeUpdate(sql);
			stmt.close();

			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			sql = "DELETE FROM contratos WHERE (rut_cliente = '" + rut + "');";
			stmt.executeUpdate(sql);
			stmt.close();

			// Se cierra conexión a la BD
			c.close();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexión a la BD se retorna null;

	}

	// Para eliminar un contrato de la BD
	public boolean eliminarContratoBD(String rut) throws SQLException {
		String sql;
		if (c != null) {
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			sql = "DELETE FROM contratos WHERE (rut_cliente = '" + rut + "');";
			stmt.executeUpdate(sql);
			stmt.close();

			// Se cierra conexión a la BD
			c.close();
			return true;
		} else
			return false;
		// Si no se pudo establecer la conexión a la BD se retorna null;

	}

	public boolean ingresarContratoBD(Contrato contratoCliente) throws SQLException {
		if (c != null) {
			// Si se cre� la conexi�n a la BD exitosamente se contin�a
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO contratos(id_contrato,id_equipo,id_plan,fecha_inicio,fecha_termino,"
					+ "rut_cliente,valor_total,cuotas,valor_cuota)" + "VALUES('" + contratoCliente.getIdContrato()
					+ "'," + contratoCliente.getEquipoContratado().getIdEquipo() + ","
					+ contratoCliente.getPlanContratado().getIdPlan() + ",'" + contratoCliente.getFechaInicio() + "','"
					+ contratoCliente.getFechaTermino() + "','" + contratoCliente.getRutCliente() + "',"
					+ contratoCliente.getValorTotal() + "," + contratoCliente.getCuotas() + ","
					+ contratoCliente.getValorCuota() + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			return true;
		}
		return false;
	}

	public boolean ingresarRegistroBD(RegistroDePagos registro) throws SQLException {
		if (c != null) {
			// Si se cre� la conexi�n a la BD exitosamente se contin�a
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO boletas(id_contrato, rut_cliente,cuotas_rest,id_boleta,monto_pagadop)"
					+ "VALUES('" + registro.getIdContrato() + "'," + registro.getContratoAPagar().getRutCliente() + "',"
					+ registro.getCuotasRestantes() + "," + registro.getIdRegistro() + "," + registro.getMontoPagado()
					+ ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			return true;
		}
		return false;
	}

	// Establece todos los mótodos de lecturas desde la Base de datos
	public Compania leerEmpresaBD(Compania empresa) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM compania;");
			if (rs == null)
				// Si no hay ninguna empresa creada retorna null
				return null;
			else {
				while (rs.next())
					// Se obtienen datos de las tablas
					// Se crearó un objeto compaóia con los datos obtenidos de
					// la DB
					empresa = new Compania(rs.getString("nombre"), rs.getString("id_compania"));
				rs.close();
				stmt.close();
				// Se cierra conexión a la BD
				c.close();
				// Se retorna toda la colección empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexión a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	public Compania leerPlanesBD(Compania empresa) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se leen datos de Planes desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM planes;");
			if (rs == null)
				// Si no hay ningun plan creado retorna null
				return null;
			else {
				while (rs.next()) {
					// Se obtienen datos de la plan
					Plan p = new Plan(rs.getInt("id_plan"), rs.getString("nombrePlan"), rs.getInt("precio"),
							rs.getInt("minutos"), rs.getInt("gigas"), rs.getString("id_compania"));

					empresa.getPlanes().add(p);
				}
				rs.close();
				stmt.close();
				// Se cierra conexión a la BD
				c.close();
				// Se retorna toda la colección empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexión a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	public Compania leerEquiposBD(Compania empresa) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se leen datos de Equipos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM equipos;");
			if (rs == null)
				// Si no hay ningun Equipo creado retorna null
				return null;
			else {
				while (rs.next()) {
					// Se obtienen datos de la equipos
					Equipo e = new Equipo(rs.getInt("id_equipo"), rs.getString("nombreEquipo"),
							rs.getInt("valor_con_plan"), rs.getInt("valor_sin_plan"), rs.getString("capacidad"),
							rs.getString("id_compania"));

					empresa.getMoviles().add(e);
				}
				rs.close();
				stmt.close();
				// Se cierra conexión a la BD
				c.close();
				// Se retorna toda la colección empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexión a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	public Compania leerPersonasBD(Compania empresa) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se leen datos de Personas desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM persona;");
			if (rs == null)
				// Si no hay ninguna Persona creada retorna null
				return null;
			else {
				while (rs.next()) {
					// Se obtienen datos de personas y se verifica qué tipo de
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
						empresa.getListaAdmins().add(admin);
					}
					if (tipo == 1) {
						// Cliente
						Cliente cli = new Cliente(rs.getString("rut"), rs.getString("id_compania"),
								rs.getString("nombre1"), rs.getString("nombre2"), rs.getString("apellido1"),
								rs.getString("apellido2"), rs.getInt("fono_celular"), rs.getInt("fono_fijo"),
								rs.getString("email"), rs.getInt("tipo"), rs.getString("direccion1"),
								rs.getString("direccion2"), rs.getInt("deuda"), rs.getString("password"));
						empresa.getListaClientes().add(cli);
					}
				}
				rs.close();
				stmt.close();
				// Se cierra conexión a la BD
				c.close();
				// Se retorna toda la colección empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexión a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	public Compania leerContratosBD(Compania empresa) throws SQLException {
		if (c != null) {
			// Si se creó la conexión a la BD exitosamente se continua
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se leen datos de Contratos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM contratos;");
			if (rs == null)
				// Si no hay ninguna Persona creada retorna null
				return null;
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

					// Busco el cliente al cual se le asignará el contrato
					Cliente cliente = empresa.buscarCliente(c.getRutCliente());
					// Luego agrego el contrato a ESE cliente
					cliente.getContratos().add(c);
				}
				// Se retorna toda la colección empresa
				return empresa;
			}
		} else
			// Si no se pudo establecer la conexión a la BD se retorna null;
			// Si hubo cualquier especie de error al conectar a la BD o al crear
			// los datos.
			return null;
	}

	public void cerrarDatabase() throws SQLException {
		rs.close();
		stmt.close();
		// Se cierra conexión a la BD
		c.close();
	}

	// public Compania leerBoletasBD(Compania empresa) throws SQLException {
	// if (c != null){
	// // Si se creó la conexión a la BD exitosamente se continua
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
	// // Se cierra conexión a la BD
	// c.close();
	// // Se retorna toda la colección empresa
	// return empresa;
	// }
	// }else{
	// // Si no se pudo establecer la conexión a la BD se retorna null;
	// // Si hubo cualquier especie de error al conectar a la BD o al crear los
	// datos.
	// return null;
	// }
	// }
}
