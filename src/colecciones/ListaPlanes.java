package colecciones;

import javax.swing.DefaultListModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

public class ListaPlanes implements Validador {
	private SuperColeccion lista;
	
	// CONSTRUCTOR
	
	public ListaPlanes(){
		this.lista = new SuperColeccion();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public SuperColeccion getLista() {
		return lista;
	}

	public void setLista(SuperColeccion lista) {
		this.lista = lista;
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
		lista.append(plan);
	}
	
	public boolean validarPlan(int id){
		for (int i = 1; i<lista.count();i++){
			lista.setPos(i);
			if (((Plan) lista.actualValue()).getIdPlan() == id){
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
		for (int i = 1; i < lista.count(); i++){
			lista.setPos(i);
			if (((Plan) lista.actualValue()).getIdPlan() == id)
				// si la id ingresada se encuentra
				return (Plan)lista.actualValue();
		}
		//System.err.println("No existe plan en el contrato");
		return null;	
	}
	
	public Plan buscarPlan (String nom){
		for (int i=1; i < lista.count(); i++){
			if ((((Plan) lista.actualValue()).getNombre().equalsIgnoreCase(nom)))
			{
				System.out.println("obtuvo Plan!");
				return (Plan)lista.actualValue();
			}
		}
		return null;
	}
	
	public void reportarPlanes(int id,Document doc) throws DocumentException
	{
		for(int i=1 ; i < lista.count() ; i++)
		{
			lista.setPos(i);
			if(((Plan) lista.actualValue()).getIdPlan() == id)
					doc.add(
					new Paragraph("----- Plan contratado :          " + ((Plan)lista.actualValue()).getNombre()));

		}
	}
	
	public int size(){
		return lista.count();
	}
	
	public DefaultListModel<String> listarAInterfazAgregar(DefaultListModel<String> model){
		for (int i = 0; i < size(); i++)
			// INGRESA EN LA LISTA CADA ELEMENTO
			model.addElement(((Plan)lista.actualValue(i)).getNombre());
		return model;
	}
	
	public DefaultListModel<String> listarAInterfazVer(DefaultListModel<String> model){
		for(int i=0; i<lista.count();i++){		
			model.addElement((i+1)+" - Nombre: " +((Plan)lista.actualValue(i)).getNombre()+
			" - Cuota Navegación: " +((Plan)lista.actualValue(i)).getGigas() + " - Minutos: " +((Plan)lista.actualValue(i)).getMinutos());
		}
		return model;
	}
}
