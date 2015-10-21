import java.util.ArrayList;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Cliente {
	private String rut;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private int fonoCel;
	private int fonoFijo;
	private String email;
	private String direccion1;
	private String direccion2;
	private String idCompania;
	private int deuda;
	public ArrayList<Contrato> contratos = new ArrayList<Contrato>();

	// Constructor para cuando se leen datos desde la Base de datos
	// Contiene id_cliente
	public Cliente(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, String direccion1, String direccion2, int deuda) {
		this.rut = rut;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fonoCel = fonoCel;
		this.fonoFijo = fonoFijo;
		this.email = email;
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
		this.idCompania = idCompania;
	}

	// Constructor para cuando se crea un cliente nuevo para escribirlo posteriormente en la Base de Datos
	// No contiene id_cliente
	
	public Cliente(String nombre1, String nombre2, String apellido1, String apellido2, String rut,
			int fonoCel, int fonoFijo, String email, String direccion1, String direccion2, 
			int deuda, String idCompania) {
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.rut = rut;
		this.fonoCel = fonoCel;
		this.fonoFijo = fonoFijo;
		this.email = email;
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
		this.idCompania = idCompania;
	}


	///////////////////////////* GETTERS & SETTERS *////////////////////////////////////

	public String getNombre1() {
		return nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public int getFonoCel() {
		return fonoCel;
	}

	public void setFonoCel(int fonoCel) {
		this.fonoCel = fonoCel;
	}

	public int getFonoFijo() {
		return fonoFijo;
	}

	public void setFonoFijo(int fonoFijo) {
		this.fonoFijo = fonoFijo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getDeuda() {
		return deuda;
	}

	public void setDeuda(int deudaTotal) {
		this.deuda = deudaTotal;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
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
