import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class Cliente extends Persona {
	private String direccion1;
	private String direccion2;
	private int deuda;
	public ArrayList<Contrato> contratos = new ArrayList<Contrato>();

	//CONSTRUCTOR 
	public Cliente(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, String direccion1, String direccion2, int deuda) {

		super(rut,idCompania,nombre1,nombre2,apellido1,apellido2,fonoCel,fonoFijo,email);
		
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
	}
	///////////////////////////* GETTERS & SETTERS *////////////////////////////////////

	public String getDireccion1() {
		return direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	public int getDeuda() {
		return deuda;
	}

	public void setDeuda(int deudaTotal) {
		this.deuda = deudaTotal;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	// CREA NUEVO CONTRATO DESDE LA INTERFAZ FrameContrato
	public Contrato crearContrato(int numPlan, int numEquipo, int numCuotas, Compania e)  {
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
		movil = e.elegirMovil(numEquipo);
		plan = e.elegirPlan(numPlan);
		
		//Calcula el monto total de la deuda del contraro 
		monto = movil.getValorConPlan() + plan.getPrecio(); 
		// Actualiza la deuda del cliente antes de crear el contrato
		setDeuda(getDeuda()+monto);
		
		// Calcula el valor de cada cuota (sin interes)
		valorCuota = (movil.getValorConPlan() / numCuotas) + plan.getPrecio();
		
		// Se crea el obj contrato y se retorna
		contrato = new Contrato(idRandom, fi, ff, movil.getIdEquipo(), plan.getIdPlan(), movil, plan, monto, valorCuota, numCuotas, getRut()); 
		// SE LE OTORGA NUEVO CONTRATO AL CLIENTE
		contratos.add(contrato);

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

	// METODO PARA BUSCAR UN CONTRATO Y RETORNARLO
	public Contrato buscarContrato(int id) {
		for (int i = 0; i < contratos.size(); i++){
			if (contratos.get(i).getIdContrato() == id)
				// si la id ingresada se encuentra
				return contratos.get(i); // se retorna al cliente
			else{ 
				System.err.println("No se encontró contrato del cliente");
				return null ;
			}
		}
		System.err.println("Cliente no tiene ningún contrato");
		return null;
	}
	
	//ELIMINA UN CONTRATO , VERIFICCANDO ANTES DE QUE CUMPLA QUE LAS CUOTAS ESTEN PAGADAS
	public boolean eliminarContrato(Compania e) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		listarContratos(); // listo todos los contratos del cliente
		System.out.print("\nIngrese el ID del contrato: ");
		int res=Integer.parseInt(bf.readLine()); // LEO EL ID DEL CONTRATO
		
		if(buscarContrato(res)!=null)
		{
			Contrato contr=buscarContrato(res);
			System.out.println("Fecha de termino del contratro estipulada: "+contr.getFechaTermino());
			System.out.println("¿Se cumplio el pazo minimo con el plan ?\n1-Si\n2-No");
			
			 // SI SE CUMPLE LOS MESES OLBIGATORIOS  pagados (HIPOTETICAMENTE) SE PROCEDE A ELMINAR EL CONTRATO
			if((Integer.parseInt(bf.readLine())==1) && ((RegistroDePagos)contr).getCuotasRestantes()<=0)
			{
					contratos.remove(contr); // elimino el contrato
	
					if(contratos.size()==0){//si quedo sin contratos , se  elimina el cliente
						System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
						e.eliminarCliente(getRut());						
					}
					return true;
					
			}
			else// SI NO CUMPLIO EL PLAZO MINIMO , DEBE CANCELARLO Y PAGAR UN INTERES
			{	System.out.println("No se a cumplido el plazo");	
				if(e.pagarUnPlan(contr)) // si calcela lo debido se elimina
				{
					contratos.remove(contr); // elimino el contrato
					if(contratos.size()==0){//si quedo sin contratos , se elimina el cliente
						System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
						e.eliminarCliente(getRut());
						return true;
					}		
				}
			}
		}
		return false;
	}
	
	// ELIMINA UN CONTRATO DIRECTAMENTE
	// SOBRECARGA
	public boolean eliminarContrato (Contrato c) {
		//listarContratos(); // listo todos los contratos del cliente
		//System.out.println("Fecha de termino del contratro estipulada: "+contr.getFechaTermino());
		//System.out.println("¿Se cumplio el pazo minimo con el plan ?\n1-Si\n2-No");
		// elimino el contrato
		if(contratos.remove(c)){
			//si quedo sin contratos , se  elimina el cliente
			//if(contratos.size()==0)
				System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
			//e.eliminarCliente(getRut());		
			listarContratos();
			return true;
		}
		else return false;		
	}
		
	// MUESTRA TODOS LOS CONTRATOS DE 1 CLIENTE
	public void listarContratos() {
		System.out.println("Contratos de " + getNombre1() + " " + getApellido1() + ".");
		for (int i = 0; i < contratos.size(); i++)
			System.out.println(i + 1 + "- ID Contrato: " + contratos.get(i).getIdContrato() + ". Movil: "
								+ contratos.get(i).getEquipoContratado().getModelo() + ". Plan: "
								+ contratos.get(i).getPlanContratado().getNombrePlan()+".");

	}
}
