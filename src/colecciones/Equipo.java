package colecciones;
/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Equipo {

	private int idEquipo;
	private String nombre;
	private String pantalla;
	private String camara;
	private String so;
	private String procesador;
	private int valorPlan;
	private int valorPrepago;
	private String idCompania; // ID de la compania a la que pertenece el Equipo

	// Constructor
	/**
	 * @param idEquipo
	 * @param nombreEquipo
	 * @param pantalla
	 * @param camara
	 * @param so
	 * @param procesador
	 * @param valorPlan
	 * @param valorPrepago
	 * @param idCompania
	 */
	public Equipo(int idEquipo, String nombre, String pantalla, String camara, String so, String procesador,
			int valorConPlan, int valorPrepago, String idCompania) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.pantalla = pantalla;
		this.camara = camara;
		this.so = so;
		this.procesador = procesador;
		this.valorPlan = valorConPlan;
		this.valorPrepago = valorPrepago;
		this.idCompania = idCompania;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombre = nombreEquipo;
	}

	public String getPantalla() {
		return pantalla;
	}

	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}

	public String getCamara() {
		return camara;
	}

	public void setCamara(String camara) {
		this.camara = camara;
	}

	public String getSo() {
		return so;
	}

	public void setSo(String so) {
		this.so = so;
	}

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public int getValorPlan() {
		return valorPlan;
	}

	public void setValorPlan(int valorConPlan) {
		this.valorPlan = valorConPlan;
	}

	public int getValorPrepago() {
		return valorPrepago;
	}

	public void setValorPrepago(int valorPrepago) {
		this.valorPrepago = valorPrepago;
	}

	public String getIdCompania() {
		return idCompania;
	}

	public void setIdCompania(String idCompania) {
		this.idCompania = idCompania;
	}
}
