package colecciones;
import java.util.ArrayList;

public class ListaEquipos implements ListaElementos {
	private ArrayList <Equipo> equipos;
		
	// CONSTRUCTOR
	
	public ListaEquipos(){
		equipos = new ArrayList <Equipo> ();
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	@Override
	public void agregarElemento(Object o) {
		agregarEquipo((Equipo)o);
	}

	public void agregarEquipo(Equipo movil) {
		equipos.add(movil);
	}
	
	/**
	 * Busca un Equipo especificado mediante una ID y lo retorna
	 * @param id - un entero con la ID del Equipo a seleccionar
	 * @return Un objeto Equipo con el Equipo elejido
	 */
	public Equipo buscarEquipo(int id) {
		for (int i = 0; i < equipos.size(); i++)
			if (equipos.get(i).getIdEquipo() == id)
				// si la id ingresada se encuentra
				return equipos.get(i);
			else {
				//System.err.println("No se encontro equipo");
			}
		//System.err.println("No existe equipo en el contrato");
		return null;
	}
	
	public Equipo buscarEquipo (String nombre){
		for (int i = 0; i < equipos.size(); i++){
		if ((equipos.get(i).getCapacidad()).equalsIgnoreCase(nombre)){
			System.out.println("Obtuvo equipo!");
			return equipos.get(i);
			}
		}
		return null;
	}

}
