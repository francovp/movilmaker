/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Equipo {

	private int idEquipo;
	private String nombreEquipo;
	private String modelo;
	private String manufactor;
	
	private int valorConPlan;
	private int valorSinPlan;
	private String capacidad;
	private String idCompania; // ID de la compania a la que pertenece el Equipo

	// Constructor Simple
	/**
	 * @param idEquipo
	 * @param nombreEquipo
	 * @param valorConPlan
	 * @param valorSinPlan
	 * @param capacidad
	 * @param idCompania
	 */
	public Equipo(int idEquipo, String nombreEquipo, int valorConPlan, int valorSinPlan, String capacidad,
			String idCompania) {
		super();
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
		this.valorConPlan = valorConPlan;
		this.valorSinPlan = valorSinPlan;
		this.capacidad = capacidad;
		this.idCompania = idCompania;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getValorConPlan() {
		return valorConPlan;
	}

	public void setValorConPlan(int valorConPlan) {
		this.valorConPlan = valorConPlan;
	}

	public int getValorSinPlan() {
		return valorSinPlan;
	}

	public void setValorSinPlan(int valorSinPlan) {
		this.valorSinPlan = valorSinPlan;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
	
	

}
