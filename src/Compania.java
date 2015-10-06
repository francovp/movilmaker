import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;
import java.text.*;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Compania{

	private String nombre;
	private String rut;
	public ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	public ArrayList<Equipo> moviles = new ArrayList<Equipo>();
	public ArrayList<Plan> planes = new ArrayList<Plan>();
	
	public Compania(String nombre, String rut) {
		super();
		this.nombre = nombre;
		this.rut = rut;
	}
	
	public String getNombre() {
		return nombre;
	}
	public String getRut() {
		return rut;
	}
	
	
	/////////////////////METODOS DE CLIENTES Y CONTRATOS////////////////////////////
	
	//MUESTRA LOS CLIENTES 	
	public void mostrarClientes(){
		for(int i=0; i<listaClientes.size();i++)
			System.out.println((i+1)+"- "+listaClientes.get(i).getNombre1()+" "+listaClientes.get(i).getApellido1()+".");
	}
	
	// BUSCA A UN CLIENTE Y SI LO ENCUENTRA LO RETORNA.
	public Cliente buscarCliente(String rut) {
		for (int i = 0 ; i< listaClientes.size();i++)
			if(listaClientes.get(i).getRut().equals(rut)) // si el rut ingresado se encuenta 
				return listaClientes.get(i);	// se retorna al cliente
		return null;
				
	}
	
	// MODIFICA INFORMACION DEL CLIENTE
	public boolean modificarCliente(String rut)throws IOException {
		if(buscarCliente(rut)!=null){	
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			Cliente c=buscarCliente(rut);
			System.out.println("Ingrese el numero de lo que desee modificar");
			System.out.println("1- Celular");
			System.out.println("2- Telefono fijo");
			System.out.println("3- Direccion");
			System.out.println("4- E-mail");
			int res=Integer.parseInt(bf.readLine()); // RESPUESTA DE OPCION
			
			
			System.out.println("Ingrese la nueva informacion");
			String info=bf.readLine(); // INFORMACION NUEVA A MODIFICAR
			if(res==1)//CAMBIA EL CELULAR
			{
				c.setFonoCel(Integer.parseInt(info));
				return true;
			}
			if(res==2)//CAMBIA EL TFNO FIJO
			{
				c.setFonoFijo(Integer.parseInt(info));
				return true;
			}
			if(res==3)//CAMBIA LA DIRECCION
			{
				c.setDireccion2(info);
				return true;
			}
			if(res==4)//CAMBIA EL E.MAIL
			{
				c.setEmail(info);
				return true;
			}
			
		}
		return false;
	}
	
	//CREA UN NUEVO CLIENTE Y SU CONTRATO RESPECTIVO
	public void crearClienteNuevo() throws IOException {	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String nombre1;
		String nombre2;
		String apellido1;
		String apellido2;
		String rut;
		int fonoCel;
		int fonoFijo;
		String email;
		String direccion1;
		String direccion2;


		// peticion de datos del cliente
		System.out.println("Datos necesarios del cliente");
		System.out.print("Primer Nombre:");
		nombre1 = bf.readLine();
		System.out.print("Segundo Nombre:");
		nombre2 = bf.readLine();
		System.out.print("Apellido Paterno:");
		apellido1 = bf.readLine();
		System.out.print("Apellido Materno:");
		apellido2 = bf.readLine();
		System.out.print("Rut(123456789):");
		rut = bf.readLine();
		System.out.print("Celular:");
		fonoCel = Integer.parseInt(bf.readLine());
		System.out.print("Telefono fijo:");
		fonoFijo= Integer.parseInt(bf.readLine());
		System.out.print("E-mail:");
		email = bf.readLine();
		System.out.print("Direccion:");
		direccion1 = bf.readLine();
		System.out.print("Direccion 2:");
		direccion2 = bf.readLine();

		if(buscarCliente(rut)!= null) //Si el rut existe, le informo que ya existe.
			System.out.println("Cliente ya existe");
		else // si no existe
		{
			// se crea el obj cliente y se guarda en el arraylist

			Cliente c = new Cliente(nombre1,nombre2,apellido1,apellido2,rut,fonoCel,fonoFijo,email,direccion1,direccion2);
			c.contratos.add(crearContrato()); // creo el contrato del cliente ingresado y lo agrego al ArrayList del Cliente
			listaClientes.add(c);
		}
	}

	public boolean eliminarCliente(String rut) {	
		Cliente c;
		if(buscarCliente(rut)!= null)
		{
			c=buscarCliente(rut);
			listaClientes.remove(c);
			return true;
		}
		return false;
		
	}
	
	public Contrato crearContrato() throws IOException {
		Random rnd= new Random();
		int idRandom;
		Contrato contrato;
		
		// datos para usar fecha real
		Calendar fechaF = new GregorianCalendar();
		Calendar fechaI = new GregorianCalendar();
		DateFormat dfi = DateFormat.getDateInstance();
		DateFormat dff = DateFormat.getDateInstance();
		Date di = fechaI.getTime();
		fechaF.add(GregorianCalendar.MONTH,5); // 5 meses como minimo con el plan
		Date d = fechaF.getTime();
		String fi = dfi.format(di);
		String ff = dff.format(d);

		System.out.println("Fecha de inicio del contrato: "+fi+". El dia de esta fecha se estipulara como fecha de pago. ");
		System.out.println("El cliente debera estar 5 meses como minimo con el plan contratado.");
		System.out.println("Fecha de termino: "+ff+". Despues de esta fecha el cliente seguira con el plan por el tiempo que el estime conveniente.");
		idRandom=rnd.nextInt(); // genera un numero random entre 2^-32 y 2 ^32 que sera el id con contrato
		contrato = new Contrato(idRandom,fi,ff,elegirMovil(),elegirPlan());// se crea el obj contrato y  se retorna
		return contrato;
	}
	
	public void agregarOtroContrato() throws IOException {	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese el rut del cliente:");
		String rut=bf.readLine();
		if(buscarCliente(rut)!= null) // si el cliente existe
		{ int pos;
			Cliente c = buscarCliente(rut);
			c.contratos.add(crearContrato()); // se le agrega el contrato
			pos=listaClientes.indexOf(c); // obtengo su posicion en el Arraylist
			listaClientes.add(pos,c); // lo agrego en su misma posicion (sobrescribir)
		}
		else
			System.out.println("Cliente no existe.");
	}
	
	public boolean eliminarContrato(String rut)throws IOException {
		if(buscarCliente(rut)!=null){	
			Cliente c=buscarCliente(rut);
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int resp,pos;
			listarContratos(c); // listo todos los contratos del cliente
			System.out.print("\nIngrese el ID del contrato: ");
			resp=Integer.parseInt(bf.readLine());// solicito el contrato a eliminar
			 
			c.contratos.remove(buscarContrato(c,resp)); // elimino el contrato
			pos=listaClientes.indexOf(c); // obtengo su posicion en el Arraylist
			listaClientes.add(pos,c); // lo agrego en su misma posicion (sobrescribir)
			
			return true;
		}
		return false;
	}
	
	// BUSCA UN CONTRATO DE UN CLIENTE MEDIANTE EL ID INGRESADO
	public Contrato buscarContrato(Cliente c,int id) {	
		for(int i=0;i<c.contratos.size();i++)
			if(c.contratos.get(i).getIdContrato()== id)
				return c.contratos.get(i);
		return null;
		
	}
	
	// MUESTRA TODOS LOS CONTRATOS DE 1 CLIENTE
	public void listarContratos(Cliente c) {
		System.out.println("Contratos de "+c.getNombre1()+" "+c.getApellido1()+".");
		for(int i =0; i< c.contratos.size() ; i++)
			System.out.println((i+1)+"- ID Contrato: "+c.contratos.get(i).getIdContrato()+". Movil: "+c.contratos.get(i).getMovilCliente()+". Plan: "+c.contratos.get(i).getTipoDePlan());
		
	}
	
///////////////////////// METODOS DE PLAN Y EQUIPOS /////////////////////////////////////////////
	
	public void mostrarPlanes(){
		for(int i=0;i<planes.size();i++)
		{
				System.out.println("Plan "+planes.get(i).getTipoPlan());
				System.out.println("	A solo $"+planes.get(i).getPrecio()+".");
				System.out.println("	Con "+planes.get(i).getNet()+"GB de navegacion y "+planes.get(i).getMin()+" minutos a todo destino!!\n");
		}			
	}
	
	// seleciona un movil, de los que tiene la compania disponible
	public Equipo elegirMovil() throws IOException {	
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int i;
		System.out.println("Moviles Disponibles");
		for(i=0;i<moviles.size();i++)
			System.out.println((i+1)+"- "+ moviles.get(i).getModelo());
		
		System.out.println("Igrese el numero de la opcion:");
		i=Integer.parseInt(bf.readLine());
		i--;
		return moviles.get(i);
	}
	
	// Se seleciona el Plan que desea asignar al contrato
	public Plan elegirPlan() throws IOException {	
		int i;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Nuestros Plan para ud son:");
		for( i=0;i< planes.size(); i++)
			System.out.println((i+1)+"- Plan "+ planes.get(i).getTipoPlan()+", coste: "+planes.get(i).getPrecio());
		
		System.out.println("Igrese el numero de la opcion:");
		i=Integer.parseInt(bf.readLine());
		i--;
		return planes.get(i);
	}
////////////////////////////**TXT**////////////////////////////////////////////////////////////////	
	
	// ESCRIBRE EN TXT LA INFORMACION ENVIADA EN "CADENA"
	public void escribirEnTxt(String archivo,String cadena) {
		File f;
		f= new File(archivo);
			try
			{
				FileWriter w = new FileWriter(f);
				BufferedWriter bw = new BufferedWriter(w);
				PrintWriter wr = new PrintWriter(bw); 
				wr.write(cadena);
				wr.close();
				bw.close();
			}catch(IOException e){};
	}

}

