import java.io.File;

/**
 * 
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Clientes {
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private String rut;
	private int fonoCel;
	private int fonoFijo;
	private String email;
	private String direccion1;
	private String direccion2;
	
	/**
	 * @param nombre1
	 * @param nombre2
	 * @param apellido1
	 * @param apellido2
	 * @param rut
	 * @param fonoCel
	 * @param fonoFijo
	 * @param email
	 * @param direccion1
	 * @param direccion2
	 */
	public Clientes(String nombre1, String nombre2, String apellido1, String apellido2, String rut, int fonoCel,
			int fonoFijo, String email, String direccion1, String direccion2) {
		super();
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
		
	}

	/**
	 * @return the nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @return the nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @return the fonoCel
	 */
	public int getFonoCel() {
		return fonoCel;
	}

	/**
	 * @return the fonoFijo
	 */
	public int getFonoFijo() {
		return fonoFijo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the direccion1
	 */
	public String getDireccion1() {
		return direccion1;
	}

	/**
	 * @return the direccion2
	 */
	public String getDireccion2() {
		return direccion2;
	}

	/**
	 * @param nombre1 the nombre1 to set
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	/**
	 * @param nombre2 the nombre2 to set
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @param fonoCel the fonoCel to set
	 */
	public void setFonoCel(int fonoCel) {
		this.fonoCel = fonoCel;
	}

	/**
	 * @param fonoFijo the fonoFijo to set
	 */
	public void setFonoFijo(int fonoFijo) {
		this.fonoFijo = fonoFijo;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param direccion1 the direccion1 to set
	 */
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	/**
	 * @param direccion2 the direccion2 to set
	 */
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}
	
	
	
}
