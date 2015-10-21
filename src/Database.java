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

	Connection c = null; // Objeto de tipo coneccion donde se guardarán los datos de coneccion
	Statement stmt = null; // Objeto de tipo sentencia SQL
	Compania empresa = null; // Objeto de tipo empresa donde se guardarón los datos leidos desde la BD
	ResultSet rs = null; // Objeto de tipo resultado Query SQL

	public Database() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vomistar", "postgres", "12345");
			c.setAutoCommit(true);
		} catch (Exception e) {
			System.err.println("No se pudo establecer la conexión con la base de datos!\n"
					+ "Verifique que el servicio del servidor PostgreSQL esté iniciado.\n"
					+ "\nDetalles de la excepción:");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			c.close();
		}
	}

	public boolean ingresarClienteBD(Cliente datosCliente) throws SQLException {
		if(c !=null )
		{
			// Si se creó la conexión a la BD exitosamente se continóa
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO clientes (nombre1,apellido1,id_compania,"
					+ "nombre2,fono_celular,fono_fijo,email,direccion1,direccion2,apellido2,rut) "
					+ "VALUES ('"+datosCliente.getNombre1()+"','"+datosCliente.getApellido1()
					+ "','"+datosCliente.getIdCompania()+"','"+datosCliente.getNombre2()
					+ "',"+datosCliente.getFonoCel()+","+datosCliente.getFonoFijo()+",'"+datosCliente.getEmail()
					+ "','"+datosCliente.getDireccion1()+"','"+datosCliente.getDireccion2()+"','"+datosCliente.getApellido2()
					+  "','"+datosCliente.getRut()+"');";
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
		if(c !=null )
		{
			// Si se creó la conexión a la BD exitosamente se continóa
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			sql = "DELETE FROM clientes WHERE (rut = '"+rut+"');";
			stmt.executeUpdate(sql);
			stmt.close();
			
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			sql = "DELETE FROM contratos WHERE (rut_cliente = '"+rut+"');";
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
	public boolean eliminarConstratoBD(String rut) throws SQLException {
		String sql;
		if(c !=null )
		{
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			sql = "DELETE FROM contratos WHERE (rut_cliente = '"+rut+"');";
			stmt.executeUpdate(sql);
			stmt.close();
			
			// Se cierra conexión a la BD
			c.close();
			return true;
		} else
			return false;
			// Si no se pudo establecer la conexión a la BD se retorna null;

	}
	
	public boolean ingresarContratoBD(Contrato contratoCliente) throws SQLException{
		if(c !=null )
		{
			// Si se cre� la conexi�n a la BD exitosamente se contin�a				
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO contratos(id_contrato,id_equipo,id_plan,fecha_inicio,fecha_termino,"
					+ "rut_cliente,valor_total,cuotas,valor_cuota)"
					+"VALUES('"+contratoCliente.getIdContrato()+"',"+ contratoCliente.getEquipoContratado().getIdEquipo()
					+","+contratoCliente.getPlanContratado().getIdPlan()
					+",'"+contratoCliente.getFechaInicio()+"','"+contratoCliente.getFechaTermino()
					+"','"+contratoCliente.getRutCliente()+"',"+contratoCliente.getValorTotal()+","+contratoCliente.getCuotas()
					+","+contratoCliente.getValorCuota()+");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			return true;
		}
		return false;
	}
	
	public boolean ingresarRegistroBD(RegistroDePagos registro) throws SQLException{
		if(c !=null )
		{
			// Si se cre� la conexi�n a la BD exitosamente se contin�a				
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			String sql = "INSERT INTO boletas(id_contrato, rut_cliente,cuotas_rest,id_boleta,monto_pagadop)"
					+"VALUES('"+registro.getIdContrato()+"',"+registro.getRutCliente()
					+"',"+registro.getCuotasRestantes()+","+registro.getIdRegistro()
					+","+registro.getMontoPagado()+");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			return true;
		}
		return false;
	}
	
	// Establece todos los mótodos de lecturas desde la Base de datos
	public Compania leerDatosBD() throws SQLException {
		if (c != null){
			// Si se creó la conexión a la BD exitosamente se continóa
			// Se crea una nueva sentencia SQL
			stmt = c.createStatement();
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM compania;");
			while (rs.next())
				// Se obtienen datos de las tablas
				// Se crearó un objeto compaóia con los datos obtenidos de la DB
				empresa = new Compania(rs.getString("nombre"), rs.getString("id_compania"));

			// Se leen datos de Planes desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM planes;");
			while (rs.next()) {
				// Se obtienen datos de la plan
				Plan p = new Plan(rs.getInt("id_plan"), rs.getString("nombrePlan"), rs.getInt("precio")
						, rs.getInt("minutos"), rs.getInt("gigas"), rs.getString("id_compania"));

				empresa.getPlanes().add(p);
			}

			// Se leen datos de Equipos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM equipos;");
			while (rs.next()) {
				// Se obtienen datos de la equipos
				Equipo e = new Equipo(rs.getInt("id_equipo"), rs.getString("nombreEquipo")
						, rs.getInt("valor_con_plan"), rs.getInt("valor_sin_plan")
						, rs.getString("capacidad"), rs.getString("id_compania"));

				empresa.getMoviles().add(e);
			}

			// Se leen datos de Clientes desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM clientes;");
			while (rs.next()) {
				// Se obtienen datos de cliente y se asignan
				Cliente cli = new Cliente(rs.getString("rut"),rs.getString("id_compania"),rs.getString("nombre1"),rs.getString("nombre2")
						,rs.getString("apellido1"),rs.getString("apellido2"),rs.getInt("fono_celular"),rs.getInt("fono_fijo"),rs.getString("email")
						,rs.getString("direccion1"),rs.getString("direccion2"), rs.getInt("deuda"));
				empresa.getListaClientes().add(cli);
			}
			
			// Se leen datos de Contratos desde la BD
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM contratos;");
			while (rs.next()) {
				// Se obtienen datos de la equipos
				Contrato c = new Contrato(rs.getInt("id_contrato"), rs.getInt("id_equipo"), rs.getInt("id_plan")
						, rs.getString("fecha_inicio"), rs.getString("fecha_termino"), rs.getString("rut_cliente")
						, rs.getInt("valor_total"), rs.getInt("cuotas"), rs.getInt("valor_cuota"));
				
				empresa.buscarCliente(c.getRutCliente()).getContratos().add(c);
			}
			
			// Se leen datos de Contratos desde la BD
			int idContratoBoleta;
			String rutClienteBoleta;
			Contrato contratoBoleta = null;
			Cliente clienteBoleta = null;
			// Se ejecuta la sentencia SQL y se guarda
			rs = stmt.executeQuery("SELECT * FROM boletas;");
			while (rs.next()) {
				idContratoBoleta = rs.getInt("id_contrato");
				rutClienteBoleta = rs.getString("rut_cliente");
				contratoBoleta = empresa.buscarCliente(rutClienteBoleta).buscarContrato(idContratoBoleta);
				// Se obtienen datos de la equipos
				RegistroDePagos c = new RegistroDePagos (idContratoBoleta, contratoBoleta.getIdEquipo(),contratoBoleta.getIdPlan()
						, contratoBoleta.getFechaInicio(), contratoBoleta.getFechaTermino(), rutClienteBoleta
						, contratoBoleta.getValorTotal(), contratoBoleta.getValorCuota(), contratoBoleta.getCuotas()
						, rs.getInt("id_boleta"), rs.getInt("monto_pagado"), rs.getInt("cuotas_rest"));
				
				empresa.buscarCliente(rutClienteBoleta).getContratos().add(c);
			}

			rs.close();
			stmt.close();

			// Se cierra conexión a la BD
			c.close();

			// Se retorna toda la colección empresa
			return empresa;
		}else
			// Si no se pudo establecer la conexión a la BD se retorna null;
			return null;
	}
}
