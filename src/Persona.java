import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Persona {
	private String rut;
	private String idCompania;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private int fonoCel;
	private int fonoFijo;
	private String email;
	private int tipo = 0; // 0 = Admin, 1 = Cliente ... n = Algo más

	public Persona(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, int tipo) {
		this.rut = rut;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fonoCel = fonoCel;
		this.fonoFijo = fonoFijo;
		this.email = email;
		this.idCompania = idCompania;
		this.tipo = tipo;
	}

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public int getFonoCel() {
		return fonoCel;
	}

	public void setFonoCel(int fonoCel) {
		this.fonoCel = fonoCel;
	}

	public int getFonoFijo() {
		return fonoFijo;
	}

	public void setFonoFijo(int fonoFijo) {
		this.fonoFijo = fonoFijo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * Imprime un Reporte de todas las personas en la empresa.
	 * 
	 * @param datosEmpresa
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void reporte(Compania datosEmpresa) throws FileNotFoundException, DocumentException {
		ArrayList<Cliente> listaClientes = datosEmpresa.mostrarClientes();
		ArrayList<Administrador> listaAdmins = datosEmpresa.mostrarAdmins();
		String fonoFijo, fonoCel, email, direccion1, direccion2, nombre2, apellido2, deuda;
		Document documento = new Document();
		int idPlan, idEquipo; // Compararan ids de cada contrato de x cliente
								// con las ids almacenadas en Compania
		PdfWriter.getInstance(documento, new FileOutputStream("ReportePersonas.pdf"));
		documento.open(); // ABRE DOCUMENTO

		documento.add(new Paragraph(
				"Documento emitido por compañia " + datosEmpresa.getNombre() + ", RUT: " + datosEmpresa.getRut()));
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

			documento.add(new Paragraph("\n\n- Administrador :                        " + admin.getNombre1() + " "
					+ nombre2 + " " + admin.getApellido1() + " " + apellido2));
			documento.add(new Paragraph("\nRut: " + admin.getRut() + ", Email: " + email));
			documento.add(new Paragraph("\nTeléfono: " + fonoFijo + ", Celular: " + fonoCel));
		}

		documento.add(new Paragraph("\nLista de clientes:"));

		// RECORRE CADA CLIENTE E IMPRIME EN PDF SUS DATOS PERSONALES
		for (int i = 0; i < listaClientes.size(); i++) {
			Cliente c = listaClientes.get(i);
			if (c.getFonoFijo() == 0)
				fonoFijo = "Sin datos";
			else
				fonoFijo = Integer.toString(c.getFonoFijo());
			if (c.getFonoCel() == 0)
				fonoCel = "Sin datos";
			else
				fonoCel = Integer.toString(c.getFonoCel());
			if (c.getEmail() == null || c.getEmail() == "0" || c.getEmail() == "")
				email = "Sin datos";
			else
				email = c.getEmail();
			if (c.getDireccion1() == null || c.getDireccion1() == "0" || c.getDireccion1() == "")
				direccion1 = "Sin datos";
			else
				direccion1 = c.getDireccion1();
			if (c.getDireccion2() == null || c.getDireccion2() == "0" || c.getDireccion2() == "")
				direccion2 = "Sin datos";
			else
				direccion2 = c.getDireccion2();
			if (c.getNombre2() == null || c.getNombre2() == "0" || c.getNombre2() == "")
				nombre2 = "Sin datos";
			else
				nombre2 = c.getNombre2();
			if (c.getApellido2() == null || c.getApellido2() == "0" || c.getApellido2() == "")
				apellido2 = "Sin datos";
			else
				apellido2 = c.getApellido2();
			if (c.getDeuda() == 0)
				deuda = "Sin deuda";
			else
				deuda = Integer.toString(c.getDeuda());

			documento.add(new Paragraph("\n\n- Cliente :                        " + c.getNombre1() + " " + nombre2 + " "
					+ c.getApellido1() + " " + apellido2));
			documento.add(new Paragraph("\nRut: " + c.getRut() + ", Email: " + email));
			documento.add(new Paragraph("\nDirección: " + direccion1 + ", " + direccion2 + ", Teléfono: " + fonoFijo
					+ ", Celular: " + fonoCel));
			documento.add(new Paragraph("\nDeuda: " + deuda));

		}
		documento.close(); // SE CIERRA EL DOCUMENTO
	}
}
