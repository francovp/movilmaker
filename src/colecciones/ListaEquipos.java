package colecciones;
import java.util.ArrayList;

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

	public void agregarEquipo(Equipo m) { 
		lista.add(m);
	}

	public boolean validarEquipo (String id){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getNombre() == id){
				return true;	//	 Existe
			}
		}
		return false;	//	 no existe
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
				//System.err.println("No se encontro equipo");
			}
		//System.err.println("No existe equipo en el contrato");
		return null;
	}
	
	public Equipo buscarEquipo (String id){
		for (int i = 0; i < lista.size(); i++){
			if ((lista.get(i).getNombre()).equalsIgnoreCase(id))
				// si la id ingresada se encuentra
				return lista.get(i);
			else{
			//System.err.println("No se encontro equipo");
			}
		}
		//System.err.println("No existe equipo en el contrato");
		return null;
	}
	
	public ArrayList <Equipo> obtenerLista(){
		return lista;
	}
}
