package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class ListaClientes implements Validador {

	private ArrayList <Cliente> lista;

	// CONSTRUCTOR
	public ListaClientes(){
		this.lista = new ArrayList<Cliente>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public ArrayList<Cliente> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Cliente> clientes) {
		this.lista = clientes;
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	@Override
	public boolean validarAgregar(Object o) {
		if(validarCliente(((Cliente)o).getRut()) == false){
			agregarCliente((Cliente)o);
			return true;
		}
		else return false;
	}

	public void agregarCliente(Cliente c) {
		lista.add(c);								
	}

	public Cliente buscarCliente (String rut){
		for(int i=0;i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return lista.get(i);
			}
		}
		return null;
	}

	public boolean validarCliente (String rut){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return true;	//	Cliente Existe
			}
		}
		return false;	//	Cliente no existe
	}

	public boolean eliminarCliente (String rut){
		for (int i = 0 ; i<lista.size();i++){
			if ((lista.get(i).getRut()).equalsIgnoreCase(rut)){
				lista.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList <Cliente> obtenerLista(){
		return lista;
	}
	
	public void reportarClientes(Document documento) throws DocumentException
	{
		String fonoFijo, fonoCel, email, direccion1, direccion2, nombre2, apellido2, deuda;
	
		// RECORRE CADA CLIENTE E IMPRIME EN PDF SUS DATOS PERSONALES
					for (int i = 0; i < lista.size(); i++) {
						Cliente c = lista.get(i);
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
						c.contratos.reportarContratos(documento);
						
												
					}
}
}
