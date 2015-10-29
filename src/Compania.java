import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Compania {

	private String nombre;
	private String rut;
	private ArrayList<Persona> listaPersona = new ArrayList<Persona>();
	private ArrayList<Equipo> moviles = new ArrayList<Equipo>();
	private ArrayList<Plan> planes = new ArrayList<Plan>();

	/**
	 * @param nombre
	 * @param rut
	 */
	public Compania(String nombre, String rut) {
		super();
		this.nombre = nombre;
		this.rut = rut;
	}

	/////////////////////////// * GETTERS & SETTERS
	/////////////////////////// *////////////////////////////////////

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public ArrayList<Persona> getListaPersona() {
		return listaPersona;
	}

	public void getListaPersona(ArrayList<Persona> ListaPersona) {
		this.listaPersona = listaPersona;
	}

	public ArrayList<Equipo> getMoviles() {
		return moviles;
	}

	public void setMoviles(ArrayList<Equipo> moviles) {
		this.moviles = moviles;
	}

	public ArrayList<Plan> getPlanes() {
		return planes;
	}

	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}

	///////////////// METODOS DE ADMINISTRADOR ////////////////////
	
	/**
	 * Crea un nuevo Administrador y lo agrega al Arralist listaAdmins de
	 * Compania
	 *
	 * @param adminNuevo
	 * @return Un objeto de tipo Administrado con el administrador nuevo creado
	 */
	public Administrador crearAdminNuevo(Administrador adminNuevo) {

		if (buscarAdmin(adminNuevo.getRut()) != null)
			// Si el rut existe, le informo que ya existe.
			return null;
		else {
			// Si no existe se guarda en el arraylist
			listaPersona.add(adminNuevo);
			return adminNuevo;
		}
	}
	
	public Administrador buscarAdmin(String rut) {
		for (int i = 0; i < listaPersona.size(); i++)
			if (listaPersona.get(i).getRut().equals(rut) && listaPersona.get(i).getTipo() == 0)
				return (Administrador) listaPersona.get(i);

		return null;
	}
	
	public ArrayList<Administrador> mostrarAdmins() {
		ArrayList<Administrador> admins = new ArrayList<Administrador>();
		for (int i = 0; i < listaPersona.size(); i++){
			if(listaPersona.get(i) instanceof Administrador){
				admins.add((Administrador)listaPersona.get(i));
			}
		}
		return admins;
	}

	///////////////////// METODOS DE CLIENTES ///////////////////// 
	
	/**
	 * Crea un nuevo Cliente y lo agrega al Arralist listaClientes de Compania
	 *
	 * @param clienteNuevo
	 * @return Un objeto de tipo Cliente con el cliente nuevo creado
	 */
	public Cliente crearClienteNuevo(Cliente clienteNuevo) {

		if (buscarCliente(clienteNuevo.getRut()) != null)
			// Si el rut existe, le informo que ya existe.
			return null;
		else {
			// Si no existe se guarda en el arraylist
			listaPersona.add(clienteNuevo);
			return clienteNuevo;
		}
	}

	// MUESTRA LOS CLIENTES
	public ArrayList<Cliente> mostrarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for (int i = 0; i < listaPersona.size(); i++){
			if(listaPersona.get(i) instanceof Cliente){
				clientes.add((Cliente)listaPersona.get(i));
			}
		}
		return clientes;
	}

	// BUSCA A UN CLIENTE Y SI LO ENCUENTRA LO RETORNA.
	public Cliente buscarCliente(String rut) {
		for (int i = 0; i < listaPersona.size(); i++)
			// si el rut ingresado se encuenta
			if (listaPersona.get(i).getRut().equals(rut) && listaPersona.get(i).getTipo() == 1) 
				// se retorna al cliente
				return (Cliente) listaPersona.get(i); 
		return null;

	}

	// MODIFICA INFORMACION DEL CLIENTE
	public boolean modificarCliente(String rut) throws IOException {
		if (buscarCliente(rut) != null) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			Cliente c = buscarCliente(rut);
			System.out.println("Informacion de: " + c.getNombre1() + " " + c.getApellido1() + "\nEmail: " + c.getEmail()
					+ "\nCelular: " + c.getFonoCel() + "\nTelefono: " + c.getFonoFijo() + "\nDireccion: "
					+ c.getDireccion1());
			System.out.println("Informacion modificable: ");
			System.out.println("1- Celular");
			System.out.println("2- Telefono fijo");
			System.out.println("3- Direccion");
			System.out.println("4- E-mail");
			System.out.println("Ingrese el numero de lo que desee modificar:");
			int res = Integer.parseInt(bf.readLine()); // RESPUESTA DE OPCION

			System.out.println("Ingrese la nueva informacion");
			String info = bf.readLine(); // INFORMACION NUEVA A MODIFICAR
			if (res == 1) // CAMBIA EL CELULAR
			{
				c.setFonoCel(Integer.parseInt(info));
				System.out.println("Informacion actualizada");
				return true;
			}
			if (res == 2) // CAMBIA EL TFNO FIJO
			{
				c.setFonoFijo(Integer.parseInt(info));
				System.out.println("Informacion actualizada");
				return true;
			}
			if (res == 3) // CAMBIA LA DIRECCION
			{
				c.setDireccion2(info);
				System.out.println("Informacion actualizada");
				return true;
			}
			if (res == 4) // CAMBIA EL E.MAIL
			{
				c.setEmail(info);
				System.out.println("Informacion actualizada");
				return true;
			}

		}
		return false;
	}

	public boolean eliminarCliente(String rut) {
		Cliente c;
		if (buscarCliente(rut) != null) {
			c = buscarCliente(rut);
			listaPersona.remove(c);
			return true;
		}
		return false;
	}

	///////////////////////// METODOS DE PLAN Y EQUIPOS /////////////////////////

	public void mostrarPlanes() {
		for (int i = 0; i < planes.size(); i++) {
			System.out.println(planes.get(i).getNombrePlan());
			System.out.println("	A solo $" + planes.get(i).getPrecio() + ".");
			System.out.println("	Con " + planes.get(i).getGigas() + "GB de navegacion y "
					+ planes.get(i).getMinutos() + " minutos a todo destino!!\n");
		}
	}

	// Consola
	
		// seleciona un movil, de los que tiene la compania disponible
		public Equipo elegirMovil() throws IOException {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int i;
			System.out.println("Moviles Disponibles");
			for (i = 0; i < moviles.size(); i++)
				System.out.println(i + 1 + "- " + moviles.get(i).getModelo());
	
			System.out.println("Ingrese el numero de la opcion:");
			i = Integer.parseInt(bf.readLine());
			i--;
			return moviles.get(i);
		}
	
		// Se seleciona el Plan que desea asignar al contrato
		public Plan elegirPlan() throws IOException {
			int i;
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Nuestros Plan para ud son:");
			for (i = 0; i < planes.size(); i++)
				System.out.println(i + 1 + "- " + planes.get(i).getNombrePlan() + ", coste: " + planes.get(i).getPrecio());
	
			System.out.println("Igrese el numero de la opcion:");
			i = Integer.parseInt(bf.readLine());
			i--;
			return planes.get(i);
		}
	
	// Interfaz
		// seleciona un movil, escogido desde FrameContrato
		public Equipo elegirMovil(int movil) {
			int i;
			i = movil;
			return moviles.get(i);
		}
	
		// Se seleciona el Plan, escogido desde FrameContrato
		public Plan elegirPlan(int plan) {
			int i;
			i = plan;
			return planes.get(i);
		}

	//////////////////////////// **TXT**////////////////////////////////////////////////////////////////

	// ESCRIBRE EN TXT LA INFORMACION ENVIADA EN "CADENA"
	public void escribirEnTxt(String archivo, String cadena) {
		File f;
		f = new File(archivo);
		try {
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			wr.write(cadena);
			wr.close();
			bw.close();
		} catch (IOException e) {
		}
		;
	}

	/////////////////// FUNCIONES EXTRAS ///////////////////////

	public void buscarClientesConMasPlanes()
	{
		int cont=0,j=1;
		Cliente c=null;
		for(int i=0;i<listaPersona.size();i++)
		{
			if(listaPersona.get(i) instanceof Cliente)
				//CALCULA AL FIN DEL CICLO QUE CLIENTE TIENE MAS PLANES
				if(((Cliente)listaPersona.get(i)).contratos.size() > cont)
				{
					cont=((Cliente)listaPersona.get(i)).contratos.size();
					c = ((Cliente)listaPersona.get(i));
				}
				//SE MUESTRAN LOS CLIENTES CON MAS DE 3 CONTRATOS
				if(((Cliente)listaPersona.get(i)).contratos.size() > 3){
	
					while (j == 1) {// ciclo solo para imprimir 1 sola vez
						System.out.println("Clientes con mas de 3 planes:");
						j = 0;
					}
	
					System.out.println(" Rut: " + c.getRut() + " " + c.getNombre1() + " " + c.getApellido1());
				}

		}
		// CLIENTE ESTRELLA CON MAS CONTRATOS
		if (c != null)
			System.out.println("Cliente estrella, con un total de: " + cont + " planes contratados.\n*** "
					+ c.getNombre1() + " " + c.getApellido1() + ". Rut: " + c.getRut() + " ***");
	}

	// METODO PARA BUSCAR UN PLAN Y RETORNARLO
	public Plan buscarPlan(int id) {
		for (int i = 0; i < planes.size(); i++)
			if (planes.get(i).getIdPlan() == id)
				// si la id ingresada se encuentra
				return planes.get(i); // se retorna al cliente
			else {
				System.err.println("No se encontró plan");
				return null;
			}
		System.err.println("No existe plan en el contrato");
		return null;
	}

	// METODO PARA BUSCAR UN PLAN Y RETORNARLO
	public Equipo buscarEquipo(int id) {
		for (int i = 0; i < moviles.size(); i++)
			if (moviles.get(i).getIdEquipo() == id)
				// si la id ingresada se encuentra
				return moviles.get(i); // se retorna al cliente
			else {
				System.err.println("No se encontró equipo");
				return null;
			}
		System.err.println("No existe equipo en el contrato");
		return null;
	}

	/**
	 * Imprime un Reporte completo de todos los datos de la empresa.
	 *
	 * @param datosEmpresa
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void reporte() throws FileNotFoundException, DocumentException {
		ArrayList<Cliente> listaClientes = mostrarClientes();
		ArrayList<Administrador> listaAdmins = mostrarAdmins();
		String fonoFijo, fonoCel, email, direccion1, direccion2, nombre2, apellido2, deuda;
		Document documento = new Document();
		int idPlan, idEquipo; // Compararan ids de cada contrato de x cliente
								// con las ids almacenadas en Compania
		PdfWriter.getInstance(documento, new FileOutputStream("Reporte" + getNombre() + ".pdf"));
		documento.open(); // ABRE DOCUMENTO

		documento.add(new Paragraph("Documento emitido por compañia " + getNombre() + ", RUT: " + getRut()));
		documento.add(new Paragraph("\nLista de administradores:"));

		// RECORRE CADA ADMINISTRADOR E IMPRIME SUS DATOS PERSONALES
		for (int i = 0; i < listaAdmins.size(); i++) {
			Administrador admin = listaAdmins.get(i);
			if (admin.getFonoFijo() == 0)
				fonoFijo = "Sin datos";
			else
				fonoFijo = Integer.toString(admin.getFonoFijo());
			if (admin.getFonoCel() == 0)
				fonoCel = "Sin datos";
			else
				fonoCel = Integer.toString(admin.getFonoCel());
			if (admin.getEmail() == null || admin.getEmail() == "0" || admin.getEmail() == "")
				email = "Sin datos";
			else
				email = admin.getEmail();
			if (admin.getNombre2() == null || admin.getNombre2() == "0" || admin.getNombre2() == "")
				nombre2 = "Sin datos";
			else
				nombre2 = admin.getNombre2();
			if (admin.getApellido2() == null || admin.getApellido2() == "0" || admin.getApellido2() == "")
				apellido2 = "Sin datos";
			else
				apellido2 = admin.getApellido2();

			documento.add(new Paragraph("- " + admin.getNombre1() + " "
					+ nombre2 + " " + admin.getApellido1() + " " + apellido2));
			documento.add(new Paragraph("-- Rut: " + admin.getRut() + ", Email: " + email));
			documento.add(new Paragraph("-- Teléfono: " + fonoFijo + ", Celular: " + fonoCel));
		}

		documento.add(new Paragraph("\nLista de clientes y sus contratos:"));

		// RECORRE CADA CLIENTE E IMPRIME EN PDF SUS DATOS PERSONALES
		for (int i = 0; i < listaClientes.size(); i++) {
			Cliente c = listaClientes.get(i);
			if (Integer.toString(c.getFonoCel()) == null || c.getFonoFijo() == 0)
				fonoFijo = "Sin datos";
			else
				fonoFijo = Integer.toString(c.getFonoFijo());
			if (Integer.toString(c.getFonoCel()) == null || c.getFonoCel() == 0)
				fonoCel = "Sin datos";
			else
				fonoCel = Integer.toString(c.getFonoCel());
			if (c.getEmail() == null || c.getEmail().length() == 0)
				email = "Sin datos";
			else
				email = c.getEmail();
			if (c.getDireccion1() == null || c.getDireccion1().length() == 0)
				direccion1 = "Sin datos";
			else
				direccion1 = c.getDireccion1();
			if (c.getDireccion2() == null || c.getDireccion2().length() == 0)
				direccion2 = "Sin datos";
			else
				direccion2 = c.getDireccion2();
			if (c.getNombre2() == null || c.getNombre2().length() == 0)
				nombre2 = "Sin datos";
			else
				nombre2 = c.getNombre2();
			if (c.getApellido2() == null || c.getApellido2().length() == 0)
				apellido2 = "Sin datos";
			else
				apellido2 = c.getApellido2();
			if (Integer.toString(c.getDeuda()) == null || c.getDeuda() == 0)
				deuda = "Sin deuda";
			else
				deuda = Integer.toString(c.getDeuda());

			documento.add(new Paragraph("- " + c.getNombre1() + " " + nombre2 + " "
					+ c.getApellido1() + " " + apellido2));
			documento.add(new Paragraph("-- Rut: " + c.getRut() + ", Email: " + email));
			documento.add(new Paragraph("-- Dirección: " + direccion1 + ", " + direccion2 + ", Teléfono: " + fonoFijo
					+ ", Celular: " + fonoCel));
			documento.add(new Paragraph("-- Deuda: " + deuda));
			
			documento.add(new Paragraph("--- Lista de contratos del cliente:"));

			// RECORRE CONTRATOS DE CLIENTE Y OBTIENE VALOR DE idPLAN Y idEQUIPO
			// DE CADA CONTRATO
			for (int j = 0; j < c.getContratos().size(); j++) {
				idPlan = c.getContratos().get(j).getIdPlan();
				idEquipo = c.getContratos().get(j).getIdEquipo();
				// imprime en pdf id contrato y valor total a pagar de cada
				// cliente
				documento.add(
						new Paragraph("---- ID Contrato :                 " + c.getContratos().get(j).getIdContrato()));
				documento.add(
						new Paragraph("---- Valor total :                   $" + c.getContratos().get(j).getValorTotal()));

				// RECORRE PLANES EN COMPANIA E IMPRIME EL PLAN EN PDF
				for (int k = 0; k < getPlanes().size(); k++)
					if (getPlanes().get(k).getIdPlan() == idPlan)
						documento.add(
								new Paragraph("----- Plan contratado :          " + getPlanes().get(k).getNombrePlan()));

				// RECORRE EQUIPOS EN COMPANIA E IMPRIME EL EQUIPO EN PDF
				for (int k = 0; k < getMoviles().size(); k++)
					if (getMoviles().get(k).getIdEquipo() == idEquipo)
						documento.add(
								new Paragraph("----- Equipo contratado :      " + getMoviles().get(k).getNombreEquipo()));
			}
		}
		documento.close(); // SE CIERRA EL DOCUMENTO
	}
}
