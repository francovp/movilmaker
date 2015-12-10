package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaAdministradores implements Validador {

	private ArrayList <Administrador> administradores;
	
	//	CONSTRUCTOR
	public ListaAdministradores (){
		this.administradores = new ArrayList<Administrador>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////
	
	public ArrayList<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(ArrayList<Administrador> administradores) {
		this.administradores = administradores;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	@Override
	public void validarAgregar(Object o) {
		if(validarAdmin(((Administrador)o).getRut()) == false)
			agregarAdministrador((Administrador)o);
	}

	public void agregarAdministrador(Administrador admin) {
		administradores.add(admin);
		
		}
	
	public ArrayList <Administrador> obtenerAdministradores (){
		return administradores;
	}

	public boolean validarAdmin (String rut){
		for (int i = 0; i<administradores.size();i++){
			if (administradores.get(i).getRut().equals(rut)){
				return true;	//	Admin Existe
			}
		}
		return false;	//	Admin no existe
	}
	
}
