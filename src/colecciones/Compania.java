package colecciones;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Compania {

		private String nombre;
		private String rut;
		private ListaAdministradores administradores;
		private ListaClientes clientes;
		private ListaEquipos equipos;
		private ListaPlanes planes;

		/**
		 * @param nombre
		 * @param rut
		 */
		public Compania(String nombre, String rut) {
			super();
			this.nombre = nombre;
			this.rut = rut;
			this.administradores = new ListaAdministradores();
			this.clientes = new ListaClientes();
			this.equipos = new ListaEquipos();
			this.planes = new ListaPlanes();
			}

		/////////////////////////// * GETTERS & SETTERS ////////////////////////////////////
		
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

		public ListaClientes getClientes() {
			return clientes;
		}

		public void setClientes(ListaClientes clientes) {
			this.clientes = clientes;
		}

		public ListaAdministradores getAdministradores() {
			return administradores;
		}

		public void setAdministradores(ListaAdministradores administradores) {
			this.administradores = administradores;
		}

		public ListaEquipos getEquipos() {
			return equipos;
		}

		public void setEquipos(ListaEquipos equipos) {
			this.equipos = equipos;
		}

		public ListaPlanes getPlanes() {
			return planes;
		}

		public void setPlanes(ListaPlanes planes) {
			this.planes = planes;
		}
		
		/////////////////////////// * METODOS * /////////////////////////////////////////////
		
		public void agregarCliente (Cliente c) {
			clientes.agregarCliente(c);
		}
		
		public void agregarAdministrador (Administrador admin) {
			administradores.agregarAdministrador(admin);
		}
		
		public Cliente buscarCliente (String rut) {
			return clientes.buscarCliente(rut);
		}
		
		public boolean validarCliente (String rut){
			return clientes.validarCliente(rut);
		}
		
		public boolean validarAdmin (String rut){
			return administradores.validarAdmin(rut);
		}
		
		public ArrayList <Cliente> obtenerClientes(){
			return clientes.obtenerClientes();
		}
		
		public boolean eliminarCliente (String rut){
			clientes.eliminarCliente(rut);
			return true;
		}
		
		public ArrayList <Administrador> obtenerAdministradores(){
			return administradores.obtenerAdministradores(); 
		}
		
		public void agregarEquipo (Equipo equipo){
			equipos.agregarElemento(equipo);
		}
		
		public Equipo buscarEquipo (int id){
			return equipos.buscarEquipo(id);
		}
		
		public Equipo buscarEquipo (String nombre){
			System.out.println("bbb: "+nombre);
			return equipos.buscarEquipo(nombre);
		}
		
		public void agregarPlan (Plan plan){
			planes.agregarPlan(plan);
		}
		
		/**
		 * Crea un nuevo Plan y lo agrega al Arralist listaPlanes de Compania
		 *
		 * @param planNuevo
		 * @return Un objeto de tipo Cliente con el cliente nuevo creado
		 */
		public Plan crearPlan (Plan plan) {
			if (buscarPlan(plan.getIdPlan()) != null)
				// Si la id ya existe, le informo que ya existe.
				return null;
			else {
				// Si no existe se guarda en el arraylist
				agregarPlan(plan);
				return plan;
			}
		}
		
		public Plan buscarPlan (int id){
			return planes.buscarPlan(id);
		}
	
		public Plan buscarPlan (String nombre){
			System.out.println("aaaa: "+nombre);
			return planes.buscarPlan(nombre);
		}
		

		/**
		 * Imprime un Reporte completo de todos los datos de la empresa.
		 *
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 */
		public void reporte() throws FileNotFoundException, DocumentException {
			ArrayList<Cliente> listaClientes = obtenerClientes();
			ArrayList<Administrador> listaAdmins = obtenerAdministradores();
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
				documento.add(new Paragraph("-- Telefono: " + fonoFijo + ", Celular: " + fonoCel));
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
				documento.add(new Paragraph("-- Direccion: " + direccion1 + ", " + direccion2 + ", Telefono: " + fonoFijo
						+ ", Celular: " + fonoCel));
				documento.add(new Paragraph("-- Deuda: " + deuda));
				
				documento.add(new Paragraph("--- Lista de contratos del cliente:"));

				// RECORRE CONTRATOS DE CLIENTE Y OBTIENE VALOR DE idPLAN Y idEQUIPO
				// DE CADA CONTRATO
				for (int j = 0; j < c.getContratos().getContratos().size(); j++) {
					idPlan = c.getContratos().getContratos().get(j).getIdPlan();
					idEquipo = c.getContratos().getContratos().get(j).getIdEquipo();
					// imprime en pdf id contrato y valor total a pagar de cada
					// cliente
					documento.add(
							new Paragraph("---- ID Contrato :                 " + c.getContratos().getContratos().get(j).getIdContrato()));
					documento.add(
							new Paragraph("---- Valor total :                   $" + c.getContratos().getContratos().get(j).getValorTotal()));

					// RECORRE PLANES EN COMPANIA E IMPRIME EL PLAN EN PDF
					for (int k = 0; k < planes.getPlanes().size(); k++)
						if (planes.getPlanes().get(k).getIdPlan() == idPlan)
							documento.add(
									new Paragraph("----- Plan contratado :          " + planes.getPlanes().get(k).getNombrePlan()));

					// RECORRE EQUIPOS EN COMPANIA E IMPRIME EL EQUIPO EN PDF
					for (int k = 0; k < equipos.getEquipos().size(); k++)
						if (equipos.getEquipos().get(k).getIdEquipo() == idEquipo)
							documento.add(
									new Paragraph("----- Equipo contratado :      " + equipos.getEquipos().get(k).getNombreEquipo()));
				}
			}
			documento.close(); // SE CIERRA EL DOCUMENTO
		}

}


