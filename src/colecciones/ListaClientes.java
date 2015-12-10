package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaClientes implements Validador {

	private ArrayList <Cliente> clientes;

	// CONSTRUCTOR
	public ListaClientes(){
		this.clientes = new ArrayList<Cliente>();
	}

	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	/////////////////////////// * METODOS * /////////////////////////////////////////////
	@Override
	public void validarAgregar(Object o) {
		if(validarCliente(((Cliente)o).getRut()) == false)
			agregarCliente((Cliente)o);
	}

	public void agregarCliente(Cliente c) {
		// Si Cliente no existe, se agrega cliente
		if ((validarCliente(c.getRut())) == false)	
			clientes.add(c);								
	}

	public Cliente buscarCliente (String rut){
		for(int i=0;i<clientes.size();i++){
			if (clientes.get(i).getRut().equals(rut)){
				return clientes.get(i);
			}
		}
		return null;
	}

	public boolean validarCliente (String rut){
		for (int i = 0; i<clientes.size();i++){
			if (clientes.get(i).getRut().equals(rut)){
				return true;	//	Cliente Existe
			}
		}
		return false;	//	Cliente no existe
	}

	public boolean eliminarCliente (String rut){
		for (int i = 0 ; i<clientes.size();i++){
			System.out.println(""+clientes.get(1).getApellido1());
			if ((clientes.get(i).getRut()).equalsIgnoreCase(rut)){
				clientes.remove(i);
				return true;
			}
		}
		return false;
	}

	public ArrayList <Cliente> obtenerClientes(){
		return clientes;
	}
}
