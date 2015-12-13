package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
