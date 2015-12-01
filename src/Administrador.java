
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Administrador extends Persona {
	private String password;

	// CONSTRUCTOR
	public Administrador(String rut, String idCompania, String nombre1, String nombre2, String apellido1,
			String apellido2, int fonoCel, int fonoFijo, String email, int tipo, String direccion1, String Direccion2,
			int deuda, String password) {

		super(rut, idCompania, nombre1, nombre2, apellido1, apellido2, fonoCel, fonoFijo, email, tipo);

		this.password = password;
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	/**
	 * Imprime un Reporte en pdf del Administrador (Sobreescritura de Persona
	 * 
	 * @param datosEmpresa
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	@Override
	public void reporte(Compania datosEmpresa) throws FileNotFoundException, DocumentException {
		Document documento = new Document();
		String fonoFijo, fonoCel, email, nombre2, apellido2;
		PdfWriter.getInstance(documento, new FileOutputStream("reportes\\Reporte_admin_" + getRut() + ".pdf"));
		documento.open(); // ABRE DOCUMENTO

		documento.add(new Paragraph(
				"Documento emitido por compañia " + datosEmpresa.getNombre() + ", RUT: " + datosEmpresa.getRut()));

		if (getFonoFijo() == 0)
			fonoFijo = "Sin datos";
		else
			fonoFijo = Integer.toString(getFonoFijo());
		if (getFonoCel() == 0)
			fonoCel = "Sin datos";
		else
			fonoCel = Integer.toString(getFonoCel());
		if (getEmail() == null || getEmail() == "0" || getEmail() == "")
			email = "Sin datos";
		else
			email = getEmail();
		if (getNombre2() == null || getNombre2() == "0" || getNombre2() == "")
			nombre2 = "Sin datos";
		else
			nombre2 = getNombre2();
		if (getApellido2() == null || getApellido2() == "0" || getApellido2() == "")
			apellido2 = "Sin datos";
		else
			apellido2 = getApellido2();

		documento.add(new Paragraph("\nDatos del administrador:                        " + getNombre1() + " " + nombre2
				+ " " + getApellido1() + " " + apellido2));
		documento.add(new Paragraph("\nRut: " + getRut() + ", Email: " + email));
		documento.add(new Paragraph("\nTeléfono: " + fonoFijo + ", Celular: " + fonoCel));
		documento.close(); // SE CIERRA EL DOCUMENTO
	}

}
