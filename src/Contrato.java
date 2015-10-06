/**
 * 
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Contrato {
	private int idContrato;
	private String fechaInicio; // FECHA DE PAGO SERA LA FECHA DE INICIO DEL CONTRATO
	private String fechaTermino;
	private Equipo movilCliente;
	private Plan tipoDePlan;
	
	

	/**
	 * @param fechaInicio
	 * @param fechaTermino
	 * @param client
	 * @param movilCliente
	 * @param tipoDePlan
	 */
	public Contrato(int id,String fechaInicio, String fechaTermino, Equipo movilCliente,
			Plan tipoDePlan) {
		super();
		idContrato=id;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.movilCliente = movilCliente;
		this.tipoDePlan = tipoDePlan;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	public Equipo getMovilCliente() {
		return movilCliente;
	}
	public void setMovilCliente(Equipo movilCliente) {
		this.movilCliente = movilCliente;
	}
	public Plan getTipoDePlan() {
		return tipoDePlan;
	}
	public void setTipoDePlan(Plan tipoDePlan) {
		this.tipoDePlan = tipoDePlan;
	}
	public int getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}
	
	
}

