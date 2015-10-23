import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Compania {

	private String nombre;
	private String rut;
	private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
	private ArrayList<Equipo> moviles = new ArrayList<Equipo>();
	private ArrayList<Plan> planes = new ArrayList<Plan>();
	private ArrayList<Administrador> administradores = new ArrayList<Administrador>();
	
	/**
	 * @param nombre
	 * @param rut
	 */
	public Compania(String nombre, String rut) {
		super();
		this.nombre = nombre;
		this.rut = rut;
	}

	///////////////////////////* GETTERS & SETTERS *////////////////////////////////////

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

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
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
	public ArrayList<Administrador> getTrabajadores() {
		return administradores;
	}

	public void setTrabajadores(ArrayList<Administrador> trabajadores) {
		this.administradores = trabajadores;
	}
	///////////////// METODOS DE TRABAJADOR ////////////////////////////
	
	public void agregarAdmin(Administrador a)
	{
		administradores.add(a);
		
	}
	public Administrador buscarAdmin(String rut)
	{
		for(int i=0 ; i < administradores.size() ; i++)
			if(administradores.get(i).getRut().equals(rut)){
				administradores.get(i).identificarPersona();
				return administradores.get(i);
			}
				
		return null;
	}

	///////////////////// METODOS DE CLIENTES Y
	///////////////////// CONTRATOS////////////////////////////


	// MUESTRA LOS CLIENTES
	public void mostrarClientes() {
		if(listaClientes.size()==0)
			System.out.println("No hay clientes.");
		else
			for (int i = 0; i < listaClientes.size(); i++)
			System.out.println(
					i + 1 + "- " + listaClientes.get(i).getNombre1() + " " + listaClientes.get(i).getApellido1() + ".");
	}

	// BUSCA A UN CLIENTE Y SI LO ENCUENTRA LO RETORNA.
	public Cliente buscarCliente(String rut) {
		for (int i = 0; i < listaClientes.size(); i++)
			if (listaClientes.get(i).getRut().equals(rut)) // si el rut
															// ingresado se
															// encuenta
				return listaClientes.get(i); // se retorna al cliente
		return null;

	}
	
	// CREA UN NUEVO CLIENTE Y SU CONTRATO RESPECTIVO
//	public Cliente crearClienteNuevo(String idCompania) throws IOException {
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		String nombre1;
//		String nombre2;
//		String apellido1;
//		String apellido2;
//		String rut;
//		int fonoCel;
//		int fonoFijo;
//		String email;
//		String direccion1;
//		String direccion2;
//
//		// peticion de datos del cliente
//		System.out.println("Datos necesarios del cliente");
//		System.out.print("Primer Nombre:");
//		nombre1 = bf.readLine();
//		System.out.print("Segundo Nombre:");
//		nombre2 = bf.readLine();
//		System.out.print("Apellido Paterno:");
//		apellido1 = bf.readLine();
//		System.out.print("Apellido Materno:");
//		apellido2 = bf.readLine();
//		System.out.print("RUT (123456780):");
//		rut = bf.readLine();
//		System.out.print("N� Celular:");
//		fonoCel = Integer.parseInt(bf.readLine());
//		System.out.print("N� Telefono fijo:");
//		fonoFijo = Integer.parseInt(bf.readLine());
//		System.out.print("Email:");
//		email = bf.readLine();
//		System.out.print("Direccion (Calle y n�):");
//		direccion1 = bf.readLine();
//		System.out.print("Ciudad:");
//		direccion2 = bf.readLine();
//
//		if (buscarCliente(rut) != null)
//			// Si el rut existe, le informo que ya existe.
//			return null;
//		else {
//			// Si no existe se crea el obj cliente y se guarda en el arraylist
//			Cliente clienteNuevo = new Cliente(nombre1, nombre2, apellido1, apellido2, rut, fonoCel, fonoFijo, email, direccion1,
//					direccion2, idCompania);
//			clienteNuevo.contratos.add(crearContrato()); // creo el contrato del cliente ingresado
//														// y lo agrego al ArrayList de contratos del Cliente
//			listaClientes.add(clienteNuevo);
//			return clienteNuevo;
//		}
//	}

	// MODIFICA INFORMACION DEL CLIENTE
	public boolean modificarCliente(String rut) throws IOException {
		if (buscarCliente(rut) != null) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			Cliente c = buscarCliente(rut);
			System.out.println("Informacion de: "+c.getNombre1()+" "+c.getApellido1()+"\nEmail: "+c.getEmail()
								+"\nCelular: "+c.getFonoCel()+"\nTelefono: "+c.getFonoFijo()+"\nDireccion: "
								+c.getDireccion1());
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
			listaClientes.remove(c);
			return true;
		}
		return false;
	}
	
	public Contrato crearContrato(Cliente clienteActual) throws IOException {
		Random rnd = new Random();
		int idRandom, monto, valorCuota;
		Contrato contrato = null;
		Equipo movil = null;
		Plan plan = null;

//		// Datos para usar fecha real
//		Calendar fechaF = new GregorianCalendar();
//		Calendar fechaI = new GregorianCalendar();
//		DateFormat dfi = DateFormat.getDateInstance();
//		DateFormat dff = DateFormat.getDateInstance();
//		Date di = fechaI.getTime();
//		fechaF.add(Calendar.MONTH, 5); // 5 meses como minimo con el plan
//		Date d = fechaF.getTime();
//		String fi = dfi.format(di);
//		String ff = dff.format(d);
//		// Genera un numero random entre 0 y 100000 que sera el id con contrato
//		idRandom = rnd.nextInt(100000); 
//		
//		//Obtiene el Equipo y el plan del contrato
//		movil = interfazElegirMovil(numEquipo);
//		plan = interfazElegirPlan(numPlan);
//		
//		//Calcula el monto total de la deuda del contraro 
//		monto = movil.getPrecio() + plan.getPrecio(); 
//		// Actualiza la deuda del cliente antes de crear el contrato
//		clienteActual.setDeuda(clienteActual.getDeuda()+monto);
//		
//		// Calcula el valor de cada cuota (sin interes)
//		valorCuota = (movil.getPrecio() / numCuotas) + plan.getPrecio();
//		
//		// Se crea el obj contrato y se retorna
//		contrato = new Contrato(idRandom, fi, ff, movil, plan, monto, valorCuota, numCuotas, clienteActual.getRut()); 
//		
		return contrato;
	}

	public Cliente agregarOtroContrato(Cliente clienteActual) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Ingrese el rut del cliente:");
		String rut = bf.readLine();
		if (buscarCliente(rut) != null) // Si el cliente existe
		{
			Cliente c = buscarCliente(rut);
			c.contratos.add(crearContrato(clienteActual)); // Se le agrega el contrato del cliente
			return c;
		} else
			System.out.println("Cliente no existe.");
			return null;
	}

	//ELIMINA UN CONTRATO , VERIFICCANDO ANTES DE QUE CUMPLA QUE LAS CUOTAS ESTEN PAGADAS
	public boolean eliminarContrato(String rut) throws IOException {
		if (buscarCliente(rut) != null) {
			Cliente c = buscarCliente(rut);
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			listarContratos(c); // listo todos los contratos del cliente
			System.out.print("\nIngrese el ID del contrato: ");
			int res=Integer.parseInt(bf.readLine()); // LEO EL ID DEL CONTRATO
			
			if(c.buscarContrato(res)!=null)
			{
				Contrato contr=c.buscarContrato(res);
				System.out.println("Fecha de termino del contratro estipulada: "+contr.getFechaTermino());
				System.out.println("¿Se cumplio el pazo minimo con el plan ?\n1-Si\n2-No");
				
				 // SI SE CUMPLE LOS MESES OLBIGATORIOS  pagados (HIPOTETICAMENTE) SE PROCEDE A ELMINAR EL CONTRATO
				if((Integer.parseInt(bf.readLine())==1) && ((RegistroDePagos)contr).getCuotasRestantes()<=0)
				{
						c.contratos.remove(contr); // elimino el contrato
		
						if(c.contratos.size()==0){//si quedo sin contratos , se  elimina el cliente
							System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
							eliminarCliente(c.getRut());						
						}
						return true;
						
				}
				else// SI NO CUMPLIO EL PLAZO MINIMO , DEBE CANCELARLO Y PAGAR UN INTERES
				{	System.out.println("No se a cumplido el plazo");	
					if(pagarUnPlan(contr)) // si calcela lo debido se elimina
					{
						c.contratos.remove(contr); // elimino el contrato
						if(c.contratos.size()==0){//si quedo sin contratos , se elimina el cliente
							System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
							eliminarCliente(c.getRut());
							return true;
						}		
					}
				}
			}
			return true;
		}
		return false;
	}

	// MUESTRA TODOS LOS CONTRATOS DE 1 CLIENTE
	public void listarContratos(Cliente c) {
		System.out.println("Contratos de " + c.getNombre1() + " " + c.getApellido1() + ".");
		for (int i = 0; i < c.contratos.size(); i++)
			System.out.println(i + 1 + "- ID Contrato: " + c.contratos.get(i).getIdContrato() + ". Movil: "
								+ c.contratos.get(i).getEquipoContratado().getModelo() + ". Plan: "
								+ c.contratos.get(i).getPlanContratado().getNombrePlan()+".");

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

	// seleciona un movil, de los que tiene la compania disponible
	public Equipo elegirMovil() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int i;
		System.out.println("Moviles Disponibles");
		for (i = 0; i < moviles.size(); i++)
			System.out.println(i + 1 + "- " + moviles.get(i).getModelo());

		System.out.println("Igrese el numero de la opcion:");
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
			System.out.println(
					i + 1 + "- " + planes.get(i).getNombrePlan() + ", coste: " + planes.get(i).getPrecio());

		System.out.println("Igrese el numero de la opcion:");
		i = Integer.parseInt(bf.readLine());
		i--;
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
	
	// 1 SOBRECARGA METODO PARA PAGAR UN PLAN
	
	//	METODO PARA PAGAR MENSUALIDAD MEDIANTE ELECCION DE PLAN
	public boolean pagarUnPlan(String rut) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Cliente c;
		Contrato contratoAPagar;
		RegistroDePagos boletaMasNueva;
		int cuotasRestantes = 0, montoAdeudado = 0;
		
		if(buscarCliente(rut)!=null) // BUSCO AL CLIENTE
		{
			c = buscarCliente(rut);
			if(c.contratos.size()>1)// SI EL CLIENTE TIENE MAS DE 1 PLAN CONTRATADO 
			{						// SE PIDE IDENTIFICAR CUAL VA A CANCELAR
				listarContratos(c);
				System.out.println("Ingrese el Id del contrato: ");
				int id=Integer.parseInt(bf.readLine()); //SE LEE EL ID DEL CONTRATO A PAGAR
				
				if(c.buscarContrato(id)!=null)//VALIDACION DEL CONTRATO A PAGAR
				{	// CUOTAS OBLIGATORIAS AL PLAN ( CORRESPONDIENTE A LOS MESES MINIMOS)
					contratoAPagar=c.buscarContrato(id);
					
					//Se verificará si en el contrato existe alguna boleta para obtener el valor de cuotasRestantes
					boletaMasNueva = contratoAPagar.buscarPago(c.getRut());
					
					if(boletaMasNueva != null){
						// Obtiene el ultimo valor de CuotasRestantes conocido
						cuotasRestantes = boletaMasNueva.getCuotasRestantes(); 
						// OBtiene el ultimo valor de montoAdeudado conocido
						montoAdeudado = boletaMasNueva.getMontoAdeudado(); 
					}
					else{
						// Como aun no ha producido ningun pago, se Obtienen los valores totales
						cuotasRestantes = contratoAPagar.getCuotas();
						montoAdeudado = contratoAPagar.getValorTotal();
					}
					
					// SE CREA EL REGISTRO DE PAGO
					RegistroDePagos registro = new RegistroDePagos (contratoAPagar.getIdContrato(),contratoAPagar.getIdEquipo(),
							contratoAPagar.getIdPlan(), contratoAPagar.getFechaInicio(), contratoAPagar.getFechaTermino(),
							contratoAPagar.getRutCliente(),contratoAPagar.getValorTotal(),contratoAPagar.getValorCuota(),
							contratoAPagar.getCuotas(),0,contratoAPagar.getValorCuota(), montoAdeudado, cuotasRestantes-1);
					
					if(registro.getCuotasRestantes() > 0){  // SE DEBE MENSUALIDAD
						System.out.println("Monto de Cuotas: "+ registro.getValorCuota());
						System.out.println("Cuotas por pagar: "+ registro.getCuotasRestantes());
						System.out.println("Ingrese el numero de cuotas a cancelar:");
						
						//SE PROCEDE A PAGAR MENSUALIDAD
						if(registro.pagar(Integer.parseInt(bf.readLine()),contratoAPagar.getValorCuota())){
							
							// GUARDADO DE REGISTROS EN LA BD
							// Preparar Conexión a BD
							Database bd = null;
							try {
								bd = new Database();
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							// Se escribira registro en la BD
							try {
								bd.ingresarRegistroBD(registro);
								System.out.println("Boleta agregado a la base de datos...");						
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								System.err.println("Boleta no se pudo escribir en la Base de Datos.\n"
										+ "\nDetalles de la excepción:");
								System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
							}
							
							return true; // cuotas canceladas correctamente
						}
					}
					else { // SI NO DEBE
						System.out.println("Cuotas Obligatorias canceladas");
						return false;
					}
						
					
				}
				else{ // ID DE CONTRATO NO ENCONTRADA O NO EXISTE
					System.out.println("ID ingresao no valido y/o no existe.");	
					return false;
				}
			}			
			else{// SI EL CLIENTE TIENE SOLO 1 PLAN CONTRATADO
				
				contratoAPagar=c.contratos.get(0);
				
				//Se verificará si en el contrato existe alguna boleta para obtener el valor de cuotasRestantes
				boletaMasNueva = contratoAPagar.buscarPago(c.getRut());
				if(boletaMasNueva != null){
					// Obtiene el ultimo valor de CuotasRestantes conocido
					cuotasRestantes = boletaMasNueva.getCuotasRestantes(); 
					// OBtiene el ultimo valor de montoAdeudado conocido
					montoAdeudado = boletaMasNueva.getMontoAdeudado(); 
				}
				else{
					// Como aun no ha producido ningun pago, se Obtienen los valores totales
					cuotasRestantes = contratoAPagar.getCuotas();
					montoAdeudado = contratoAPagar.getValorTotal();
				}
				
				// SE CREA EL REGISTRO DE PAGO
				RegistroDePagos registro = new RegistroDePagos (contratoAPagar.getIdContrato(),contratoAPagar.getIdEquipo(),
						contratoAPagar.getIdPlan(), contratoAPagar.getFechaInicio(), contratoAPagar.getFechaTermino(),
						contratoAPagar.getRutCliente(),contratoAPagar.getValorTotal(),contratoAPagar.getValorCuota(),
						contratoAPagar.getCuotas(),0,contratoAPagar.getValorCuota(), montoAdeudado, cuotasRestantes-1);
				
				if(registro.getCuotasRestantes() > 0){  // SE DEBE MENSUALIDAD
					System.out.println("Monto de Cuotas: "+ registro.getValorCuota());
					System.out.println("Cuotas por pagar: "+ registro.getCuotasRestantes());
					System.out.println("Ingrese el numero de cuotas a cancelar:");
					
					//SE PROCEDE A PAGAR MENSUALIDAD
					if(registro.pagar(Integer.parseInt(bf.readLine()),contratoAPagar.getValorCuota())){
						
						// GUARDADO DE REGISTROS EN LA BD
						// Preparar Conexión a BD
						Database bd = null;
						try {
							bd = new Database();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						// Se escribira registro en la BD
						try {
							bd.ingresarRegistroBD(registro);
							System.out.println("Boleta agregado a la base de datos...");						
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							System.err.println("Boleta no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepción:");
							System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
						}
						return true; // cuotas canceladas correctamente
					}
				}
			}
		}
		else{ // SI NO SE ENCUENTRA EL CLIENTE
			System.out.println("Cliente no existe");
			return false;
		}
		return false;
	}
	
	//METODO PARA PAGAR contrato Y FINALIZARLO
	public boolean pagarUnPlan (Contrato contratoAPagar) throws IOException {
		RegistroDePagos boletaMasNueva;
		int cuotasRestantes, montoAdeudado;
		
		//Se verificará si en el contrato existe alguna boleta para obtener el valor de cuotasRestantes
		boletaMasNueva = contratoAPagar.buscarPago(contratoAPagar.getRutCliente());
		if(boletaMasNueva != null){
			// Obtiene el ultimo valor de CuotasRestantes conocido
			cuotasRestantes = boletaMasNueva.getCuotasRestantes(); 
			// OBtiene el ultimo valor de montoAdeudado conocido
			montoAdeudado = boletaMasNueva.getMontoAdeudado(); 
		}
		else{
			// Como aun no ha producido ningun pago, se Obtienen los valores totales
			cuotasRestantes = contratoAPagar.getCuotas();
			montoAdeudado = contratoAPagar.getValorTotal();
		}
		
		// SE CREA EL REGISTRO DE PAGO
		RegistroDePagos registro = new RegistroDePagos (contratoAPagar.getIdContrato(),contratoAPagar.getIdEquipo(),
				contratoAPagar.getIdPlan(), contratoAPagar.getFechaInicio(), contratoAPagar.getFechaTermino(),
				contratoAPagar.getRutCliente(),contratoAPagar.getValorTotal(),contratoAPagar.getValorCuota(),
				contratoAPagar.getCuotas(),0,contratoAPagar.getValorCuota(), montoAdeudado, cuotasRestantes-1);
		
		System.out.println("\nMeses a cancelar restantes para terminar el plazo minimo estipulado: "+ registro.getCuotasRestantes());
		System.out.println("Al no terminar tener el plan contratado por los meses minimos estipulados.");

		if(contratoAPagar.pagar(registro, registro.getCuotasRestantes())){
			
			// GUARDADO DE REGISTROS EN LA BD
			// Preparar Conexión a BD
			Database bd = null;
			try {
				bd = new Database();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// Se escribira registro en la BD
			try {
				bd.ingresarRegistroBD(registro);
				System.out.println("Boleta agregado a la base de datos...");						
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				System.err.println("Boleta no se pudo escribir en la Base de Datos.\n"
						+ "\nDetalles de la excepción:");
				System.err.println(e2.getClass().getName() + ": " + e2.getMessage());
			}
			return true;
		}		
		return false;
	}

	public void buscarClientesConMasPlanes()
	{
		int cont=0,j=1;
		Cliente c=null;
		for(int i=0;i<listaClientes.size();i++)
		{
			//CALCULA AL FIN DEL CICLO QUE CLIENTE TIENE MAS PLANES
			if(listaClientes.get(i).contratos.size()-1>cont)
			{
				cont=listaClientes.get(i).contratos.size()-1;
				c = listaClientes.get(i);
			}
			//SE MUESTRAN LOS CLIENTES CON MAS DE 3 CONTRATOS
			if(listaClientes.get(i).contratos.size()-1>3){

				while(j==1){//ciclo solo para imprimir 1 sola vez
					System.out.println("Clientes con mas de 3 planes:");
					j=0;
				}

				System.out.println(" Rut: "+c.getRut()+" "+ c.getNombre1()+" "+c.getApellido1());
			}

		}
		//CLIENTE ESTRELLA CON MAS CONTRATOS
		if(c!=null)
			System.out.println("Cliente estrella, con un total de: "+cont+" planes contratados.\n*** "+ c.getNombre1()+" "+c.getApellido1()+". Rut: "+c.getRut()+" ***");
	}

	//=============================== METODOS DE LA INTERFAZ =================================

	// CREA UN NUEVO CLIENTE Y SU CONTRATO RESPECTIVO
		public Cliente crearClienteNuevo(Cliente clienteNuevo) {

			if (buscarCliente(clienteNuevo.getRut()) != null)
				// Si el rut existe, le informo que ya existe.
				return null;
			else {
				// Si no existe se crea el obj cliente y se guarda en el arraylist
				//clienteNuevo.contratos.add(interfazCrearContrato()); // creo el contrato del cliente ingresado
				// y lo agrego al ArrayList de contratos del Cliente
				listaClientes.add(clienteNuevo);
				System.out.println("Cliente agregado");
				return clienteNuevo;
			}
		}

	// CREA NUEVO CONTRATO DESDE LA INTERFAZ FrameContrato
	public Contrato crearContrato(int numPlan, int numEquipo, int numCuotas, Cliente clienteActual)  {
		Random rnd = new Random();
		int idRandom, monto, valorCuota;
		Contrato contrato = null;
		Equipo movil = null;
		Plan plan = null;

		// Datos para usar fecha real
		Calendar fechaF = new GregorianCalendar();
		Calendar fechaI = new GregorianCalendar();
		DateFormat dfi = DateFormat.getDateInstance();
		DateFormat dff = DateFormat.getDateInstance();
		Date di = fechaI.getTime();
		fechaF.add(Calendar.MONTH, 5); // 5 meses como minimo con el plan
		Date d = fechaF.getTime();
		String fi = dfi.format(di);
		String ff = dff.format(d);
		// Genera un numero random entre 0 y 100000 que sera el id con contrato
		idRandom = rnd.nextInt(100000); 
		
		//Obtiene el Equipo y el plan del contrato
		movil = elegirMovil(numEquipo);
		plan = elegirPlan(numPlan);
		
		//Calcula el monto total de la deuda del contraro 
		monto = movil.getValorConPlan() + plan.getPrecio(); 
		// Actualiza la deuda del cliente antes de crear el contrato
		clienteActual.setDeuda(clienteActual.getDeuda()+monto);
		
		// Calcula el valor de cada cuota (sin interes)
		valorCuota = (movil.getValorConPlan() / numCuotas) + plan.getPrecio();
		
		// Se crea el obj contrato y se retorna
		contrato = new Contrato(idRandom, fi, ff, movil, plan, monto, valorCuota, numCuotas, clienteActual.getRut()); 

		System.out.println("INFORMACION DEL CONTRATO\n"+
				"Fecha de inicio del contrato: " + fi + ". El dia de esta fecha se estipulara como fecha de pago. ");
		System.out.println("\nEl cliente debera estar 5 meses como minimo con el plan contratado de lo contrario"
				+ " debera cancelar los meses restantes.");
		System.out.println("\nFecha de termino: " + ff
				+ ". Despues de esta fecha el cliente seguira con el plan por el tiempo que el estime conveniente.");
		System.out.println("\nMonto total de la deuda a pagar: " + monto);
		System.out.println("\nCantidad de cuotas: " + numCuotas);
		System.out.println("\nValor de cada cuota: " + valorCuota);
		System.out.println("\n");
		
		return contrato;
	}

	public Cliente agregarOtroContrato2(Cliente cliente) {
		String rut = cliente.getRut();
		if (buscarCliente(rut) != null) // Si el cliente existe
		{
			Cliente c = buscarCliente(rut);
			//c.contratos.add(crearContrato()); // Se le agrega el contrato del cliente
			return c;
		} else
			System.out.println("Cliente no existe.");
			return null;
	}


	// seleciona un movil, escogido desde FrameContrato
	public Equipo elegirMovil(int movil) {
		int i;
		i = movil;
		return moviles.get(i);
	}

	// Se seleciona el Plan, escogido desde FrameContrato
	public Plan elegirPlan(int plan) {
		int i;
		i= plan;
		return planes.get(i);
	}

}

