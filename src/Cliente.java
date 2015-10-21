import java.util.ArrayList;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Cliente extends Persona {
	private String direccion1;
	private String direccion2;
	private int deuda;
	public ArrayList<Contrato> contratos = new ArrayList<Contrato>();

	//CONSTRUCTOR 
	public Cliente(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, String direccion1, String direccion2, int deuda) {

		super(rut,idCompania,nombre1,nombre2,apellido1,apellido2,fonoCel,fonoFijo,email);
		
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
	}
	///////////////////////////* GETTERS & SETTERS *////////////////////////////////////

	public String getDireccion1() {
		return direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	public int getDeuda() {
		return deuda;
	}

	public void setDeuda(int deudaTotal) {
		this.deuda = deudaTotal;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

	// METODO PARA BUSCAR UN CONTRATO Y RETORNARLO
	public Contrato buscarContrato(int id) {
		for (int i = 0; i < contratos.size(); i++)
			if (contratos.get(i).getIdContrato() == id) // si la id ingresada se encuenta
				return contratos.get(i); // se retorna al cliente
		return null;
	}
}
