package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class ListaEjecutivos implements Validador {

	private ArrayList <Ejecutivo> lista;
	
	//	CONSTRUCTOR
	public ListaEjecutivos (){
		this.lista = new ArrayList<Ejecutivo>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Ejecutivo> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Ejecutivo> ejecutivos) {
		this.lista = ejecutivos;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	@Override
	public boolean validarAgregar(Object o) {
		if(validarEjecutivo(((Ejecutivo)o).getRut()) == false){
			agregarEjecutivo((Ejecutivo)o);
			return true;
		}
		else return false;
	}

	/**
	 * Agrega un ejecutivo a la lista
	 * @param ejecutivo
	 */
	public void agregarEjecutivo(Ejecutivo ejecutivo) {
		lista.add(ejecutivo);
		}

	/**
	 * Verifica si el ejecutivo ya se encuentra en la lista
	 * @param rut Identificador de la persona
	 * @return boolean
	 */
	public boolean validarEjecutivo (String rut){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return true;	//	Ejecutivo Existe
			}
		}
		return false;	//	Ejecutivo no existe
	}
	
	/**
	 * Elimina de la lista al ejecutivo
	 * @param rut identificador de la persona
	 * @return boolean
	 */
	public boolean eliminarEjecutivo (String rut){
		for (int i = 0 ; i<lista.size();i++){
			if ((lista.get(i).getRut()).equalsIgnoreCase(rut)){
				lista.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca al ejecutivo en la lista y lo retorna
	 * @param rut Identificador de la persona
	 * @return Ejecutivo
	 */
	public Ejecutivo buscarEjecutivo (String rut){
		for(int i=0;i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return lista.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retorna Lista completa de ejecutivos
	 * @return
	 */
	public ArrayList <Ejecutivo> obtenerLista (){
		return lista;
	}
	
	/**
	 * Imprime reporte con datos del Ejecutivo
	 * @param doc
	 * @throws DocumentException
	 */
	public void reportarEjecutivo(Document doc) throws DocumentException
	{
		String fonoFijo, fonoCel, email, nombre2, apellido2;
		for (int i = 0; i < lista.size(); i++) {
			Ejecutivo ejecutivo = lista.get(i);
			if (ejecutivo.getFonoFijo() == 0)
				fonoFijo = "Sin datos";
			else
				fonoFijo = Integer.toString(ejecutivo.getFonoFijo());
			if (ejecutivo.getFonoCel() == 0)
				fonoCel = "Sin datos";
			else
				fonoCel = Integer.toString(ejecutivo.getFonoCel());
			if (ejecutivo.getEmail() == null || ejecutivo.getEmail() == "0" || ejecutivo.getEmail() == "")
				email = "Sin datos";
			else
				email = ejecutivo.getEmail();
			if (ejecutivo.getNombre2() == null || ejecutivo.getNombre2() == "0" || ejecutivo.getNombre2() == "")
				nombre2 = "Sin datos";
			else
				nombre2 = ejecutivo.getNombre2();
			if (ejecutivo.getApellido2() == null || ejecutivo.getApellido2() == "0" || ejecutivo.getApellido2() == "")
				apellido2 = "Sin datos";
			else
				apellido2 = ejecutivo.getApellido2();

			doc.add(new Paragraph("- " + ejecutivo.getNombre1() + " "
					+ nombre2 + " " + ejecutivo.getApellido1() + " " + apellido2));
			doc.add(new Paragraph("-- Rut: " + ejecutivo.getRut() + ", Email: " + email));
			doc.add(new Paragraph("-- Telefono: " + fonoFijo + ", Celular: " + fonoCel));
		}
	}
	
}
