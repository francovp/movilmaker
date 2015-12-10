package colecciones;
import java.util.ArrayList;

public class ListaPlanes implements Validador {
	private ArrayList <Plan> planes;
	
	// CONSTRUCTOR
	
	public ListaPlanes(){
		this.planes = new ArrayList <Plan>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Plan> getPlanes() {
		return planes;
	}

	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	@Override
	public void validarAgregar(Object o) {
		agregarPlan((Plan)o);
	}
	
	public void agregarPlan (Plan plan){
		planes.add(plan);
	}
	
	public boolean validarPlan(int id){
		for (int i = 0; i<planes.size();i++){
			if (planes.get(i).getIdPlan() == id){
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
		for (int i = 0; i < planes.size(); i++)
			if (planes.get(i).getIdPlan() == id)
				// si la id ingresada se encuentra
				return planes.get(i);
			else {
				//System.err.println("No se encontro plan");
			}
		//System.err.println("No existe plan en el contrato");
		return null;	
	}
	
	public Plan buscarPlan (String nom){
		for (int i=0; i < planes.size(); i++){
			if ((planes.get(i).getNombrePlan()).equalsIgnoreCase(nom)){
				System.out.println("obtuvo Plan!");
				return planes.get(i);
			}
		}
		return null;
	}
	
}
