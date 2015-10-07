/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Plan {

	private int idPlan;
	private String nombrePlan;
	private int precio;
	private int minutos; // minutos para hablar
	private int gigas; // GB de navegacion
	private String idCompania; // ID de la compania a la que pertenece el plan;
	
	/**
	 * @param idPlan
	 * @param nombrePlan
	 * @param precio
	 * @param minutos
	 * @param gigas
	 * @param idCompania
	 */
	public Plan(int idPlan, String nombrePlan, int precio, int minutos, int gigas, String idCompania) {
		super();
		this.idPlan = idPlan;
		this.nombrePlan = nombrePlan;
		this.precio = precio;
		this.minutos = minutos;
		this.gigas = gigas;
		this.idCompania = idCompania;
	}
	
	public int getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}
	public String getNombrePlan() {
		return nombrePlan;
	}
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
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

}
