import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	public Plan[] plan = new Plan[3]; // plan S,M,L
	
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
	
	public void mostrarClientes()//MUESTRA LOS CLIENTES 	
	{
		for(int i=0; i<listaClientes.size();i++)
			System.out.println((i+1)+"- "+listaClientes.get(i).getNombre1()+" "+listaClientes.get(i).getApellido1()+".");
	}
	
	public Cliente buscarCliente(String rut) // BUSCA A UN CLIENTE Y SI LO ENCUENTRA LO RETORNA.
	{
		for (int i = 0 ; i< listaClientes.size();i++)
			if(listaClientes.get(i).getRut().equals(rut)) // si el rut ingresado se encuenta 
				return listaClientes.get(i);	// se retorna al cliente
		return null;
				
	}
	
	public void crearClienteNuevo() throws IOException //CREA UN NUEVO CLIENTE Y SU CONTRATO RESPECTIVO
	{	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
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

	public boolean eliminarCliente(String rut)
	{	Cliente c;
		if(buscarCliente(rut)!= null)
		{
			c=buscarCliente(rut);
			listaClientes.remove(c);
			return true;
		}
		return false;
		
	}
	
	public Contrato crearContrato() throws IOException{
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
	
	public void agregarOtroContrato() throws IOException
	{	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
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
	
	public boolean eliminarContrato(String rut)throws IOException 
	{
		if(buscarCliente(rut)!=null)
		{	Cliente c=buscarCliente(rut);
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
	
	public Contrato buscarContrato(Cliente c,int id) // BUSCA UN CONTRATO DE UN CLIENTE MEDIANTE EL ID INGRESADO
	{	
		for(int i=0;i<c.contratos.size();i++)
			if(c.contratos.get(i).getIdContrato()== id)
				return c.contratos.get(i);
		return null;
		
	}
	
	public void listarContratos(Cliente c) // MUESTRA TODOS LOS CONTRATOS DE 1 CLIENTE
	{
		System.out.println("Contratos de "+c.getNombre1()+" "+c.getApellido1()+".");
		for(int i =0; i< c.contratos.size() ; i++)
			System.out.println((i+1)+"- ID Contrato: "+c.contratos.get(i).getIdContrato()+". Movil: "+c.contratos.get(i).getMovilCliente()+". Plan: "+c.contratos.get(i).getTipoDePlan());
		
	}
///////////////////////// METODOS DE PLAN Y EQUIPOS /////////////////////////////////////////////
	
	public void mostrarPlanes()
	{
		for(int i=0;i<plan.length;i++)
		{
			System.out.println("Plan "+plan[i].getTipoPlan());
			System.out.println("	A solo $"+plan[i].getPrecio()+".");
			System.out.println("	Con "+plan[i].getNet()+"GB de navegacion y "+plan[i].getMin()+" minutos a todo destino!!\n");
		}
	}
	
	public Equipo elegirMovil() throws IOException // seleciona un movil, de los que tiene la compania disponible
	{	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int i;
		System.out.println("Moviles Disponibles");
		for(i=0;i<moviles.size();i++)
			System.out.println((i+1)+"- "+ moviles.get(i).getModelo());
		
		System.out.println("Igrese el numero de la opcion:");
		i=Integer.parseInt(bf.readLine());
		return moviles.get(i);
	}
	
	public Plan elegirPlan() throws IOException  // Se seleciona el Plan que desea asignar al contrato
	{	int i;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Nuestros Plan para ud son:");
		for( i=0;i< plan.length; i++)
			System.out.println((i+1)+"- Plan "+ plan[i].getTipoPlan()+", coste: "+plan[i].getPrecio());
		
		System.out.println("Igrese el numero de la opcion:");
		i=Integer.parseInt(bf.readLine());
		return plan[i];
	}
	
////////////////////////////**TXT**////////////////////////////////////////////////////////////////	
	
	public void escribirEnTxt(String archivo,String cadena) // ESCRIBRE EN TXT LA INFORMACION ENVIADA EN "CADENA"
	{
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


	public String leerDeTxt(String archivo)throws FileNotFoundException, IOException //lee desde un txt y lo traspasa a un String
	{	String cadena,aux="";
	FileReader f = new FileReader(archivo);
	BufferedReader b = new BufferedReader(f);
	while((cadena = b.readLine())!=null) {
		aux=aux+"\n"+cadena;
	}
	b.close();
	aux=aux+"\n";
	return aux;
	}
}

