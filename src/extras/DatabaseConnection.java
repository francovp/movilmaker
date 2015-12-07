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
public class DatabaseConnection {
	
	private static int tipodb = 1; // 0 = remota (openshift) ; 1 = localhost
	//private static DatabaseConnection db;
	private static boolean databaseEstaDisponible = true;
	private static Connection dbConnection = null; // Objeto de tipo coneccion donde se guardaran los datos de coneccion
	
	// CONSTRUCTOR
	private DatabaseConnection() {
	}

	public static Connection conectarDB() {
		if (databaseEstaDisponible) {
			//if (db == null) {
				if(tipodb == 0){
					try {
						Class.forName("org.postgresql.Driver");
						dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/movilmaker", "adminvkgxatl", "YxjXw3He8hzu");
						dbConnection.setAutoCommit(true);
					} catch (SQLException | ClassNotFoundException e) {
						System.err.println("No se pudo establecer la conexion con la base de datos!\n"
								+ "Verifique que el proceso db_connection.exe est√© iniciado y que el puerto local sea 5433\n"
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
			//}
			databaseEstaDisponible = false;
			return dbConnection;
		} else {
			return null;
			//DB no est·° disponible, 
			//  puede retornar un error o retornal null
		}
	}

	/**
	 * Cierra la coneccion con la BD
	 */
	public static void cerrarConnection() {
		try {
			dbConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		databaseEstaDisponible = true;
	}

	public Connection getDatabaseConnection() {
		return this.dbConnection;
	}
	
	public void setDatabaseConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public static int getTipodb() {
		return tipodb;
	}

	public static void setTipodb(int tipodb) {
		DatabaseConnection.tipodb = tipodb;
	}

	public static boolean isDatabaseEstaDisponible() {
		return databaseEstaDisponible;
	}

	public static void setDatabaseEstaDisponible(boolean databaseEstaDisponible) {
		DatabaseConnection.databaseEstaDisponible = databaseEstaDisponible;
	}

	public static Connection getDbConnection() {
		return dbConnection;
	}

	public static void setDbConnection(Connection dbConnection) {
		DatabaseConnection.dbConnection = dbConnection;
	}
}
