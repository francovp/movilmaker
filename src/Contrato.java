/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Contrato {
	private String rutCliente;
	private int idContrato;
	private int idEquipo;
	private int idPlan;
	private int monto; // Cantidad de dinero que el cliente va a pagar el cliente mensualmente
	private int cuotas;
	private String fechaInicio; // FECHA DE PAGO SERA LA FECHA DE INICIO DEL CONTRATO
	private String fechaTermino;
	private Equipo equipoContratado; // Referencia al Equipo contratado
	private Plan planContratado; // Referencia al Plan contratado
	
	// Constructor para cuando se obtienen los contratos desde la BD
	/**
	 * @param idContrato
	 * @param idCliente
	 * @param idEquipo
	 * @param idPlan
	 * @param monto
	 * @param cuotas
	 * @param fechaInicio
	 * @param fechaTermino
	 * @param equipoContratado
	 * @param planContratado
	 */
	public Contrato(String rutCliente, int idContrato, int idEquipo, int idPlan, String fechaInicio,
			String fechaTermino, int monto, int cuotas) {
		super();
		this.rutCliente = rutCliente;
		this.idContrato = idContrato;
		this.idEquipo = idEquipo;
		this.idPlan = idPlan;
		this.monto = monto;
		this.cuotas = cuotas;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
	}

	// Constructor para contratos creados desde el programa
	/**
	 * @param idContrato
	 * @param fechaInicio
	 * @param fechaTermino
	 * @param equipoContratado
	 * @param planContratado
	 */
	public Contrato(int idContrato, String fechaInicio, String fechaTermino, Equipo equipoContratado,
			Plan planContratado, int cuotas, String rutCliente) {
		super();
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.equipoContratado = equipoContratado;
		this.planContratado = planContratado;
		monto=equipoContratado.getPrecio() + planContratado.getPrecio();
		this.cuotas=cuotas;
		this.rutCliente = rutCliente;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
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

	public Equipo getEquipoContratado() {
		return equipoContratado;
	}

	public void setEquipoContratado(Equipo equipoContratado) {
		this.equipoContratado = equipoContratado;
	}

	public Plan getPlanContratado() {
		return planContratado;
	}

	public void setPlanContratado(Plan planContratado) {
		this.planContratado = planContratado;
	}


	/// METODO PAGAR CUOTA
	public void pagar(int numeroCuota)
	{
		cuotas=cuotas-numeroCuota;
		if(cuotas<=0)
		{
			System.out.println("Meses obligatorios Cancelados");
			monto=planContratado.getPrecio();
			fechaTermino="Indefinido";
			System.out.println("Fecha de termino de contrato: "+fechaTermino+".\n Monto a cancelar $"+monto
								+" correspondientes al plan suscrito.");
		}
		else
			System.out.println(numeroCuota+". Cuotas restantes "+cuotas+".");
	}

}