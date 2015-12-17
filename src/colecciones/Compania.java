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
		private ListaAdministradores admins;
		private ListaEjecutivos ejecutivos;
		private ListaClientes clientes;
		private ListaEquipos equipos;
		private ListaPlanes planes;
		private ListaRegistroDePagos pagos;
		
		/**
		 * @param nombre
		 * @param rut
		 */
		public Compania(String nombre, String rut) {
			super();
			this.nombre = nombre;
			this.rut = rut;
			this.admins = new ListaAdministradores();
			this.ejecutivos = new ListaEjecutivos();
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
			return admins;
		}

		public void setAdministradores(ListaAdministradores administradores) {
			this.admins = administradores;
		}

		public ListaEjecutivos getEjecutivos() {
			return ejecutivos;
		}

		public void setEjecutivos(ListaEjecutivos ejecutivos) {
			this.ejecutivos = ejecutivos;
		}

		public ListaRegistroDePagos getPagos() {
			return pagos;
		}

		public void setPagos(ListaRegistroDePagos pagos) {
			this.pagos = pagos;
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

		/**
		 * Imprime un Reporte completo de todos los datos de la empresa.
		 *
		 * @throws FileNotFoundException
		 * @throws DocumentException
		 */
		public void reporte() throws FileNotFoundException, DocumentException {
			
			Document documento = new Document();
			PdfWriter.getInstance(documento, new FileOutputStream("Reporte" + getNombre() + ".pdf"));
			documento.open(); // ABRE DOCUMENTO

			documento.add(new Paragraph("Documento emitido por compañia " + getNombre() + ", RUT: " + getRut()));
			documento.add(new Paragraph("\n**Lista de administradores:"));
			// RECORRE CADA ADMINISTRADOR E IMPRIME SUS DATOS PERSONALES
			admins.reportarAdmin(documento);

			// RECORRE CADA ADMINISTRADOR E IMPRIME SUS DATOS PERSONALES
			ejecutivos.reportarEjecutivo(documento);
			
			//IMPRIME TODOS LOS PLANES DE LA EMPRESA 
			planes.reportarPlanesCompania(documento);
			
			//IMPRIME TODOS LOS EQUIPOS MOVILES DE LA EMPRESA
			equipos.reportarEquiposCompania(documento);
					
			documento.add(new Paragraph("\n** Lista de Clientes:"));
			// RECORRE CADA cliente E IMPRIME SUS DATOS PERSONALES Y SUS PLANES ASOCIADOS
			clientes.reportarClientes(documento);

			documento.close(); // SE CIERRA EL DOCUMENTO
		}

}


