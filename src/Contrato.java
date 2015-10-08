/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Contrato {
	private int idContrato;
	private int monto;
	private int cuotas;
	private String fechaInicio; // FECHA DE PAGO SERA LA FECHA DE INICIO DEL CONTRATO
	private String fechaTermino;
	private Equipo equipoContratado; // Referencia al Equipo contratado
	private Plan planContratado; // Referencia al Plan contratado

	/**
	 * @param idContrato
	 * @param fechaInicio
	 * @param fechaTermino
	 * @param equipoContratado
	 * @param planContratado
	 */
	public Contrato(int idContrato, String fechaInicio, String fechaTermino, Equipo equipoContratado,
			Plan planContratado,int cuotas) {
		super();
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.equipoContratado = equipoContratado;
		this.planContratado = planContratado;
		monto=equipoContratado.getPrecio() + planContratado.getPrecio();
		this.cuotas=cuotas;
	}



	public int getCuotas() {
		return cuotas;
	}



	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}



	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getIdContrato() {
		return idContrato;
	}
	
	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
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