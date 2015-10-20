import java.util.ArrayList;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Cliente extends Persona {
	private String direccion1;
	private String direccion2;
	public ArrayList<Contrato> contratos = new ArrayList<Contrato>();

	//CONSTRUCTOR 
	public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String rut,
			int fonoCel, int fonoFijo, String email, String direccion1, String direccion2, String idCompania) {
		
		super(nombre1,nombre2,apellido1,apellido2,rut,fonoCel,fonoFijo,email,idCompania);
		
		this.direccion1=direccion1;
		this.direccion2=direccion2;
	}


	///////////////////////////* GETTERS & SETTERS *////////////////////////////////////


	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

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


	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}

}
