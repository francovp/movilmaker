public class Administrador extends Persona{
	private String password;
	
	//CONSTRUCTOR 
	public Administrador(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, int tipo, String direccion1, String Direccion2, int deuda, String password) {
		
		super(rut,idCompania,nombre1,nombre2,apellido1,apellido2,fonoCel,fonoFijo,email,tipo);
		
		this.password=password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// SOBREESCRITURA
	public void identificarPersona()
	{
		System.out.println("Administrador: "+ getNombre1() + " " + getApellido1());
	}
	

}
