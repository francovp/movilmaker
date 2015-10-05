import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

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
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		conectarDB();
		menuPrincipal();
	}
	
	public static void menuPrincipal() throws IOException {
		int res;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Compania empresa = new Compania("Vomistar","78.659.325-k");
		
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
	
	public static void conectarDB(){
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vomistar","postgres", "12345");
		}
		catch (Exception e) {
			e.printStackTrace();
		    System.err.println(e.getClass().getName()+": "+e.getMessage());
		    System.exit(0);
		}
		System.out.println("Conectó a base de datos");
	}

}
