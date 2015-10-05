/**
 * 
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Contratos {
	private int idContrato;
	private String fechaInicio; // FECHA DE PAGO SERA LA FECHA DE INICIO DEL CONTRATO
	private String fechaTermino;
	private Equipos movilCliente;
	private Planes tipoDePlan;
	
	

	/**
	 * @param fechaInicio
	 * @param fechaTermino
	 * @param client
	 * @param movilCliente
	 * @param tipoDePlan
	 */
	public Contratos(int id,String fechaInicio, String fechaTermino, Equipos movilCliente,
			Planes tipoDePlan) {
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

	public Equipos getMovilCliente() {
		return movilCliente;
	}
	public void setMovilCliente(Equipos movilCliente) {
		this.movilCliente = movilCliente;
	}
	public Planes getTipoDePlan() {
		return tipoDePlan;
	}
	public void setTipoDePlan(Planes tipoDePlan) {
		this.tipoDePlan = tipoDePlan;
	}
	public int getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}
	
	
}

