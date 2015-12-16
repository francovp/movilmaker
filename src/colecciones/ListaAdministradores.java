package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class ListaAdministradores implements Validador {

	private ArrayList <Administrador> lista;
	
	//	CONSTRUCTOR
	public ListaAdministradores (){
		this.lista = new ArrayList<Administrador>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Administrador> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Administrador> admins) {
		this.lista = admins;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	@Override
	public boolean validarAgregar(Object o) {
		if(validarAdmin(((Administrador)o).getRut()) == false){
			agregarAdmin((Administrador)o);
			return true;
		}
		else return false;
	}

	public void agregarAdmin(Administrador admin) {
		lista.add(admin);
		}

	public boolean validarAdmin (String rut){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return true;	//	Admin Existe
			}
		}
		return false;	//	Admin no existe
	}
	
	public ArrayList <Administrador> obtenerLista (){
		return lista;
	}
	
	public void reportarAdmin(Document doc) throws DocumentException
	{
		String fonoFijo, fonoCel, email, nombre2, apellido2;
		for (int i = 0; i < lista.size(); i++) {
			Administrador admin = lista.get(i);
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

			doc.add(new Paragraph("- " + admin.getNombre1() + " "
					+ nombre2 + " " + admin.getApellido1() + " " + apellido2));
			doc.add(new Paragraph("-- Rut: " + admin.getRut() + ", Email: " + email));
			doc.add(new Paragraph("-- Telefono: " + fonoFijo + ", Celular: " + fonoCel));
		}
	}
	
}
