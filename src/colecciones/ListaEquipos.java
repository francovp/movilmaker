package colecciones;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class ListaEquipos implements Validador {
	private ArrayList <Equipo> lista;
		
	// CONSTRUCTOR
	
	public ListaEquipos(){
		lista = new ArrayList <Equipo> ();
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Equipo> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Equipo> equipos) {
		this.lista = equipos;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	@Override
	public boolean validarAgregar(Object o) {
		if(validarEquipo(((Equipo)o).getNombre()) == false){
			agregarEquipo((Equipo)o);
			return true;
		}
		else return false;
	}

	/**
	 * Agrega un equipo a la lista
	 * @param m objeto de tipo Equipo que se insertara en la lista
	 */
	public void agregarEquipo(Equipo m) { 
		lista.add(m);
	}

	/**
	 * Verifica si el equipo se encuentra en la lista
	 * @param id un entero con la ID del Equipo a seleccionar
	 * @return boolean
	 */
	public boolean validarEquipo (String id){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getNombre() == id){
				return true;	//	 Existe
			}
		}
		return false;	//	 no existe
	}
	
	/**
	 * Elimina de la lista el equipo seleccionado por medio de su id
	 * @param id un entero con la ID del Equipo a seleccionar
	 * @return boolean
	 */
	public boolean eliminar (String id){
		for (int i = 0 ; i<lista.size();i++){
			if (((Equipo)lista.get(i)).getNombre().equalsIgnoreCase(id)){
				lista.remove(i);
				return true;	//	Se elimino
			}
		}
		return false;
	}
		
	/**
	 * Busca un Equipo especificado mediante una ID y lo retorna
	 * @param id - un entero con la ID del Equipo a seleccionar
	 * @return Un objeto Equipo con el Equipo elejido
	 */
	public Equipo buscarEquipo(int id) {
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getIdEquipo() == id)
				// si la id ingresada se encuentra
				return lista.get(i);
			else {
				//"No se encontro equipo"
			}
		//"No existe equipo en el contrato
		return null;
	}
	
	/**
	 * Busca y retorna un objeto de tipo Equipo por medio de su nombre
	 * @param id String con nombre del Equipo
	 * @return Un objeto de tipo Equipo
	 */
	public Equipo buscarEquipo (String id){
		for (int i = 0; i < lista.size(); i++){
			if ((lista.get(i).getNombre()).equalsIgnoreCase(id))
				// si la id ingresada se encuentra
				return lista.get(i);
			else{
			//No se encontro equipo
			}
		}
		//No existe equipo en el contrato
		return null;
	}
	
	/**
	 * Imprime un reporte en pdf de los equipos en la lista y su disponibilidad
	 * @param doc
	 * @throws DocumentException
	 */
	public void reportarEquiposCompania(Document doc) throws DocumentException
	{
		doc.add(
				new Paragraph("***** EQUIPOS MOVILES DISPONIBLES ****** :          "));
		
		for (int i = 0; i < lista.size(); i++)
				doc.add(
						new Paragraph("----- Equipo  :      " + lista.get(i).getNombre()));
	}
	
	
	/**
	 * Retorna un ArrayList con los Equipos en su interior
	 * @return ArrayList de equipos
	 */
	public ArrayList <Equipo> obtenerLista(){
		return lista;
	}
	
	/**
	 * Retorna un DefaultListModel para insertar los datos en las respectivas JList
	 * @param model contiene elementos con el nombre de cada Equipo en su interior
	 * @return model un DefaultListModel con los nombres de equipos
	 */
	public DefaultListModel<String> listarAInterfazAgregar(DefaultListModel<String> model){
		for (int i = 0; i < lista.size(); i++)
			// INGRESA EN LA LISTA CADA ELEMENTO
			model.addElement(lista.get(i).getNombre());
		return model;
	}
}
