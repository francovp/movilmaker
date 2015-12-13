package colecciones;
/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Plan {

	private int idPlan;
	private String nombre;
	private int precio;
	private int minutos; // minutos para hablar
	private int gigas; // GB de navegacion
	private int sms;
	private int valorMin;
	private String idCompania; // ID de la compania a la que pertenece el plan;

	/**
	 * @param idPlan
	 * @param nombre
	 * @param precio
	 * @param minutos
	 * @param gigas
	 * @param idCompania
	 */
	public Plan(int idPlan, String nombre, int precio, int minutos, int gigas, int sms, int valorMin,
			String idCompania) {
		super();
		this.idPlan = idPlan;
		this.nombre = nombre;
		this.precio = precio;
		this.minutos = minutos;
		this.gigas = gigas;
		this.sms = sms;
		this.valorMin = valorMin;
		this.idCompania = idCompania;
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getGigas() {
		return gigas;
	}

	public void setGigas(int gigas) {
		this.gigas = gigas;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}
	
	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public int getValorMin() {
		return valorMin;
	}

	public void setValorMin(int valorMin) {
		this.valorMin = valorMin;
	}

}
