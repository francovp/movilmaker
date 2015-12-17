package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaClientes implements Validador {

	private ArrayList <Cliente> lista;

	// CONSTRUCTOR
	public ListaClientes(){
		this.lista = new ArrayList<Cliente>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public ArrayList<Cliente> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Cliente> clientes) {
		this.lista = clientes;
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	@Override
	public boolean validarAgregar(Object o) {
		if(validarCliente(((Cliente)o).getRut()) == false){
			agregarCliente((Cliente)o);
			return true;
		}
		else return false;
	}

	public void agregarCliente(Cliente c) {
		lista.add(c);								
	}

	public Cliente buscarCliente (String rut){
		for(int i=0;i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return lista.get(i);
			}
		}
		return null;
	}

	public boolean validarCliente (String rut){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getRut().equals(rut)){
				return true;	//	Cliente Existe
			}
		}
		return false;	//	Cliente no existe
	}

	public boolean eliminarCliente (String rut){
		for (int i = 0 ; i<lista.size();i++){
			if ((lista.get(i).getRut()).equalsIgnoreCase(rut)){
				lista.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList <Cliente> obtenerLista(){
		return lista;
	}
}
