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

		Compania datosEmpresa = null; // Aquí se guardarán los datos de la clase empresa
	
		//Interfaz
//		Interfaz i = new Interfaz();
//		i.setVisible(true);
		
		//Conexión a la base de datos de Postgres
		datosEmpresa = leerDatosBD();
		if(datosEmpresa == null){
			System.out.println("ERROR FATAL: No conectó a base de datos");
			System.exit(0);
		}
		else 
			System.out.println("Datos leidos desde la base de datos");
			menuPrincipal(datosEmpresa);
		//Llama al menú principal 
	}
	public static void menuPrincipal(Compania empresa) throws IOException {
		int res;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("¡ BIENVENIDO A "+empresa.getNombre()+"!");
		System.out.println("Eliga el numero de opcion que desee.");
		System.out.println("1- Ingresar un nuevo cliente y su contrato.");
		System.out.println("2- Ingresar un nuevo contrato a un cliente existente.");
		System.out.println("3- Terminar un contrato.");
		System.out.println("4- Eliminar un cliente de nuestra empresa.");
		System.out.println("5- Ver nuestros planes.");
		System.out.println("6- Ver Clientes actuales.");
		res=Integer.parseInt(bf.readLine());
		if(res==1)
			empresa.crearClienteNuevo();
		if(res==2)
			empresa.agregarOtroContrato();
		if(res==3)
		{
			System.out.println("Ingrese rut del cliente.");
			String rut=bf.readLine();
			empresa.eliminarContrato(rut);
		}
		if(res==4)
		{
			System.out.println("Ingrese rut del cliente.");
			String rut=bf.readLine();
			empresa.eliminarCliente(rut);
		}
		if(res==5)
			empresa.mostrarPlanes();
		if(res==6)
			empresa.mostrarClientes();
	}
	
////////////////////////////** BASE DE DATOS **////////////////////////////////////////////////////////////////	
	
	private static Compania leerDatosBD() throws SQLException //
	{
		Compania empresa = null;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vomistar","postgres", "12345");
			c.setAutoCommit(false);
			//Se crea la empresa
			empresa = leerDatosDBEmpresa(stmt,c);
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			c.close();
			return null;
		}
		c.close();

		return empresa;
	}
	
	private static Compania leerDatosDBEmpresa(Statement stmt, Connection c) throws SQLException
	{
		Compania empresa = null;
		//Se crea un objeto de tipo sentencia
		stmt = c.createStatement();
		//Se ejecuta la sentencia SQL
		ResultSet rs = stmt.executeQuery( "SELECT * FROM compania;" );
		while ( rs.next() ) {
			//Se obtienen datos de las tablas
			String  rutCompania = rs.getString("id_compania");
			String  nombreCompania = rs.getString("nombre");
			//Se creará un objeto compañia con los datos obtenidos de la DB
			empresa = new Compania (nombreCompania,rutCompania);
		}
		rs.close();
		stmt.close();
		return empresa;
	}

}
