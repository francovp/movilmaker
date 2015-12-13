package colecciones;
import java.util.ArrayList;

public class ListaPlanes implements Validador {
	private ArrayList <Plan> lista;
	
	// CONSTRUCTOR
	
	public ListaPlanes(){
		this.lista = new ArrayList <Plan>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Plan> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Plan> planes) {
		this.lista = planes;
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	@Override
	public boolean validarAgregar(Object o) {
		if(validarPlan(((Plan)o).getIdPlan()) == false){
			agregarPlan((Plan)o);
			return true;
		}
		else return false;
	}
	
	public void agregarPlan (Plan plan){
		lista.add(plan);
	}
	
	public boolean validarPlan(int id){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getIdPlan() == id){
				return true;	//	 Existe
			}
		}
		return false;	//	 no existe
	}
	
	/**
	 * Busca un Plan especificado mediante una ID y lo retorna
	 * @param id - un entero con la ID del plan a seleccionar
	 * @return Un objeto Plan con el plan elejido
	 */
	public Plan buscarPlan (int id){
		for (int i = 0; i < lista.size(); i++)
			if (lista.get(i).getIdPlan() == id)
				// si la id ingresada se encuentra
				return lista.get(i);
			else {
				//System.err.println("No se encontro plan");
			}
		//System.err.println("No existe plan en el contrato");
		return null;	
	}
	
	public Plan buscarPlan (String nom){
		for (int i=0; i < lista.size(); i++){
			if ((lista.get(i).getNombre()).equalsIgnoreCase(nom)){
				System.out.println("obtuvo Plan!");
				return lista.get(i);
			}
		}
		return null;
	}
	
}
