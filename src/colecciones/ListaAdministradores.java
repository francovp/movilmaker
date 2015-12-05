package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaAdministradores implements ListaElementos {

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
	public void agregarElemento(Object o) {
		agregarAdministrador((Administrador)o);
	}

	public void agregarAdministrador(Administrador admin) {
		administradores.add(admin);
		
		}
	
	public ArrayList <Administrador> obtenerAdministradores (){
		return administradores;
	}
	
}
