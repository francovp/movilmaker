package colecciones;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaClientes implements ListaElementos {

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
		public void agregarElemento(Object o) {
			agregarCliente((Cliente)o);
		}

		public void agregarCliente(Cliente c) {
			if ((validarCliente(c.getRut())) == false){;	//	Si Cliente no existe en DB
			clientes.add(c);								//	Cliente agregado
			}
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
					return true;	//	Cliente Existe	en DB
				}
			}
			return false;	//	Cliente no existe en DB
		}
		
		public ArrayList<Cliente> mostrarClientes() {
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			for (int i = 0; i < clientes.size(); i++){
				if(clientes.get(i) instanceof Cliente){
					clientes.add((Cliente)clientes.get(i));
				}
			}
			return clientes;
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
