package colecciones;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
	public ListaContratos contratos;

	// CONSTRUCTOR
	public Cliente(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, int tipo, String direccion1, String direccion2, int deuda,
			String password) {

		super(rut, idCompania, nombre1, nombre2, apellido1, apellido2, fonoCel, fonoFijo, email, tipo);
		
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
		this.contratos=new ListaContratos();
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
	
	public ListaContratos getContratos() {
		return contratos;
	}

	public void setContratos(ListaContratos contratos) {
		this.contratos = contratos;
	}
	
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////

	public Contrato crearContrato(Cliente cliente, Plan plan, Equipo movil, int numCuotas, Compania e) {
		return contratos.crearContrato(cliente, plan, movil, numCuotas, e);
	}

	/**
	 * Imprime un Reporte en pdf del cliente y sus contratos (Sobreescritura de
	 * Persona)
	 * 
	 * @param datosEmpresa - Una referencia a la Compa�ia con toda su estructura de datos
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	@Override
	public void reporte (Compania datosEmpresa) throws FileNotFoundException, DocumentException{
		Document documento = new Document();
		String fonoFijo, fonoCel, email, direccion1, direccion2, nombre2, apellido2, deuda;
		int idPlan, idEquipo; // Compararan ids de cada contrato de x cliente
								// con las ids almacenadas en Compania
		PdfWriter.getInstance(documento, new FileOutputStream("reportes\\Reporte_cliente_" + getRut() + ".pdf"));
		documento.open(); // ABRE DOCUMENTO

		documento.add(new Paragraph(
				"Documento emitido por compa�ia " + datosEmpresa.getNombre() + ", RUT: " + datosEmpresa.getRut()));

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
		for (int j = 0; j < contratos.getContratos().size(); j++) {
			idPlan = contratos.getContratos().get(j).getIdPlan();
			idEquipo = contratos.getContratos().get(j).getIdEquipo();
			// imprime en pdf id contrato y valor total a pagar de cada cliente
			documento.add(new Paragraph("- ID Contrato :                 " + contratos.getContratos().get(j).getIdContrato()));
			documento.add(new Paragraph("- Valor total :                   $" + contratos.getContratos().get(j).getValorTotal()));

			// RECORRE PLANES EN COMPANIA E IMPRIME EL PLAN EN PDF
			for (int k = 0; k < datosEmpresa.getPlanes().getPlanes().size(); k++)
				if (datosEmpresa.getPlanes().getPlanes().get(k).getIdPlan() == idPlan)
					documento.add(new Paragraph(
							"- Plan contratado :          " + datosEmpresa.getPlanes().getPlanes().get(k).getNombrePlan()));

			// RECORRE EQUIPOS EN COMPANIA E IMPRIME EL EQUIPO EN PDF
			for (int k = 0; k < datosEmpresa.getEquipos().getEquipos().size(); k++)
				if (datosEmpresa.getEquipos().getEquipos().get(k).getIdEquipo() == idEquipo)
					documento.add(new Paragraph(
							"- Equipo contratado :      " + datosEmpresa.getEquipos().getEquipos().get(k).getNombreEquipo()));
		}
		documento.close(); // SE CIERRA EL DOCUMENTO
	}
}
