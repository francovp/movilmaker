import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Cliente extends Persona {
	private String direccion1;
	private String direccion2;
	private int deuda;
	public ArrayList<Contrato> contratos = new ArrayList<Contrato>();

	// CONSTRUCTOR
	public Cliente(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, int tipo, String direccion1, String direccion2, int deuda,
			String password) {

		super(rut, idCompania, nombre1, nombre2, apellido1, apellido2, fonoCel, fonoFijo, email, tipo);

		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

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
		deuda = deudaTotal;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	/**
	 * Crea un contrato nuevo con datos obtenidos desde la interfaz
	 * @param numPlan - una ID del Plan elejido en la Interfaz para el nuevo contrato
	 * @param numEquipo - una ID del Equipo elejido en la interfaz para el nuevo contrato
	 * @param numCuotas - La cantidad de cuotas elejidas para pagar el nuevo contrato
	 * @param e - Una referencia a la empresa para obtener algunas variables
	 * @return Un objeto Contrato del contrato nuevo
	 */
	public Contrato crearContrato(int numPlan, int numEquipo, int numCuotas, Compania e) {
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

		// Obtiene el Equipo y el plan del contrato
		movil = e.elegirMovil(numEquipo);
		plan = e.elegirPlan(numPlan);

		// Calcula el monto total de la deuda del contraro
		monto = movil.getValorConPlan() + plan.getPrecio();
		// Actualiza la deuda del cliente antes de crear el contrato
		setDeuda(getDeuda() + monto);

		// Calcula el valor de cada cuota (sin interes)
		valorCuota = movil.getValorConPlan() / numCuotas + plan.getPrecio();

		// Se crea el obj contrato y se retorna
		contrato = new Contrato(idRandom, fi, ff, movil.getIdEquipo(), plan.getIdPlan(), movil, plan, monto, valorCuota,
				numCuotas, getRut());
		// SE LE OTORGA NUEVO CONTRATO AL CLIENTE
		contratos.add(contrato);

		System.out.println("\nINFORMACION DEL CONTRATO\n" + "Fecha de inicio del contrato: " + fi
				+ ". El dia de esta fecha se estipulara como fecha de pago. ");
		System.out.println("\nEl cliente debera estar 5 meses como minimo con el plan contratado de lo contrario"
				+ " debera cancelar los meses restantes.");
		System.out.println("\nFecha de termino: " + ff
				+ ". Despues de esta fecha el cliente seguira con el plan por el tiempo que el estime conveniente.");
		System.out.println("\nMonto total de la deuda a pagar: " + monto);
		System.out.println("\nCantidad de cuotas: " + numCuotas);
		System.out.println("\nValor de cada cuota: " + valorCuota);

		return contrato;
	}
	
	/**
	 * Busca un contrato en especifico y lo retorna
	 * @param id - un entero con la ID del Contrato a seleccionar
	 * @return Un objeto Contrato con el contrato elejido
	 */
	public Contrato buscarContrato(int id) {
		for (int i = 0; i < contratos.size(); i++)
			if (contratos.get(i).getIdContrato() == id)
				// si la id ingresada se encuentra
				return contratos.get(i); // se retorna al cliente
			else {
				System.err.println("No se encontro contrato del cliente");
				return null;
			}
		System.err.println("Cliente no tiene ningun contrato");
		return null;
	}
	
	// ELIMINA UN CONTRATO , VERIFICCANDO ANTES DE QUE CUMPLA QUE LAS CUOTAS
	// ESTEN PAGADAS
	public boolean eliminarContrato(Compania e) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		RegistroDePagos boletaMasNueva;
		int cuotasRestantes, montoAdeudado;
		listarContratos(); // listo todos los contratos del cliente
		System.out.print("\nIngrese el ID del contrato: ");
		int res = Integer.parseInt(bf.readLine()); // LEO EL ID DEL CONTRATO

		if (buscarContrato(res) != null) {
			Contrato contr = buscarContrato(res);
			System.out.println("Fecha de termino del contratro estipulada: " + contr.getFechaTermino());
			System.out.println("¿Se cumplio el pazo minimo con el plan ?\n1-Si\n2-No");
			
			// Se verificarÃ¡ si en el contrato existe alguna boleta para obtener el
			// valor de cuotasRestantes
			boletaMasNueva = contr.buscarPago(contr.getRutCliente());
			if (boletaMasNueva != null) {
				// Obtiene el ultimo valor de CuotasRestantes conocido
				cuotasRestantes = boletaMasNueva.getCuotasRestantes();
			} else {
				// Como aun no ha producido ningun pago, se Obtienen los valores totales
				cuotasRestantes = contr.getCuotas();
			}

			// SI SE CUMPLE LOS MESES OLBIGATORIOS pagados (HIPOTETICAMENTE) SE
			// PROCEDE A ELMINAR EL CONTRATO
			if (Integer.parseInt(bf.readLine()) == 1 && cuotasRestantes <= 0) {
				contratos.remove(contr); // elimino el contrato

				if (contratos.size() == 0) {// si quedo sin contratos , se elimina el cliente
					System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
					e.eliminarCliente(getRut());
				}
				return true;

			} else// SI NO CUMPLIO EL PLAZO MINIMO , DEBE CANCELARLO Y PAGAR UN
					// INTERES
			{
				System.out.println("No se a cumplido el plazo");
				if (e.pagarUnPlan(contr)) // si calcela lo debido se elimina
				{
					contratos.remove(contr); // elimino el contrato
					if (contratos.size() == 0) {// si quedo sin contratos , se
												// elimina el cliente
						System.out.println("Cliente se encuentra sin contratos, se procede a eliminar su registro");
						e.eliminarCliente(getRut());
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Muestra todos los contratos del cliente
	 * (Version consola)
	 */
	public void listarContratos() {
		System.out.println("Contratos de " + getNombre1() + " " + getApellido1() + ".");
		for (int i = 0; i < contratos.size(); i++)
			System.out.println(i + 1 + "- ID Contrato: " + contratos.get(i).getIdContrato() + ". Movil: "
					+ contratos.get(i).getEquipoContratado().getModelo() + ". Plan: "
					+ contratos.get(i).getPlanContratado().getNombrePlan() + ".");

	}

	/**
	 * Imprime un Reporte en pdf del cliente y sus contratos (Sobreescritura de
	 * Persona)
	 * 
	 * @param datosEmpresa - Una referencia a la Compañia con toda su estructura de datos
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	@Override
	public void reporte(Compania datosEmpresa) throws FileNotFoundException, DocumentException {
		Document documento = new Document();
		String fonoFijo, fonoCel, email, direccion1, direccion2, nombre2, apellido2, deuda;
		int idPlan, idEquipo; // Compararan ids de cada contrato de x cliente
								// con las ids almacenadas en Compania
		PdfWriter.getInstance(documento, new FileOutputStream("reportes\\Reporte_cliente_" + getRut() + ".pdf"));
		documento.open(); // ABRE DOCUMENTO

		documento.add(new Paragraph(
				"Documento emitido por compañia " + datosEmpresa.getNombre() + ", RUT: " + datosEmpresa.getRut()));

		if (Integer.toString(getFonoCel()) == null || getFonoFijo() == 0)
			fonoFijo = "Sin datos";
		else
			fonoFijo = Integer.toString(getFonoFijo());
		if (Integer.toString(getFonoCel()) == null || getFonoCel() == 0)
			fonoCel = "Sin datos";
		else
			fonoCel = Integer.toString(getFonoCel());
		if (getEmail() == null || getEmail().length() == 0)
			email = "Sin datos";
		else
			email = getEmail();
		if (getDireccion1() == null || getDireccion1().length() == 0)
			direccion1 = "Sin datos";
		else
			direccion1 = getDireccion1();
		if (getDireccion2() == null || getDireccion2().length() == 0)
			direccion2 = "Sin datos";
		else
			direccion2 = getDireccion2();
		if (getNombre2() == null || getNombre2().length() == 0)
			nombre2 = "Sin datos";
		else
			nombre2 = getNombre2();
		if (getApellido2() == null || getApellido2().length() == 0)
			apellido2 = "Sin datos";
		else
			apellido2 = getApellido2();
		if (Integer.toString(getDeuda()) == null || getDeuda() == 0)
			deuda = "Sin deuda";
		else
			deuda = Integer.toString(getDeuda());

		documento.add(new Paragraph("\nDatos del cliente :                        " + getNombre1() + " " + nombre2 + " "
				+ getApellido1() + " " + apellido2));
		documento.add(new Paragraph("\nRut: " + getRut() + ", Email: " + email));
		documento.add(new Paragraph("\nDireccion: " + direccion1 + ", " + direccion2 + ", Telefono: " + fonoFijo
				+ ", Celular: " + fonoCel));
		documento.add(new Paragraph("\nDeuda: " + deuda));

		documento.add(new Paragraph("\nContratos : "));
		// RECORRE CONTRATOS DE CLIENTE Y OBTIENE VALOR DE idPLAN Y idEQUIPO DE
		// CADA CONTRATO
		for (int j = 0; j < contratos.size(); j++) {
			idPlan = contratos.get(j).getIdPlan();
			idEquipo = contratos.get(j).getIdEquipo();
			// imprime en pdf id contrato y valor total a pagar de cada cliente
			documento.add(new Paragraph("- ID Contrato :                 " + contratos.get(j).getIdContrato()));
			documento.add(new Paragraph("- Valor total :                   $" + contratos.get(j).getValorTotal()));

			// RECORRE PLANES EN COMPANIA E IMPRIME EL PLAN EN PDF
			for (int k = 0; k < datosEmpresa.getPlanes().size(); k++)
				if (datosEmpresa.getPlanes().get(k).getIdPlan() == idPlan)
					documento.add(new Paragraph(
							"- Plan contratado :          " + datosEmpresa.getPlanes().get(k).getNombrePlan()));

			// RECORRE EQUIPOS EN COMPANIA E IMPRIME EL EQUIPO EN PDF
			for (int k = 0; k < datosEmpresa.getMoviles().size(); k++)
				if (datosEmpresa.getMoviles().get(k).getIdEquipo() == idEquipo)
					documento.add(new Paragraph(
							"- Equipo contratado :      " + datosEmpresa.getMoviles().get(k).getNombreEquipo()));
		}
		documento.close(); // SE CIERRA EL DOCUMENTO
	}
}
