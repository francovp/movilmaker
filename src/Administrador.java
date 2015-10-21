public class Administrador extends Persona{
	private String password;
	
	//CONSTRUCTOR 
	public Administrador(String nombre1, String nombre2, String apellido1, String apellido2, String rut,
			int fonoCel, int fonoFijo, String email,String password, String idCompania) {
		
		super(rut,idCompania,nombre1,nombre2,apellido1,apellido2,fonoCel,fonoFijo,email);
		
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
