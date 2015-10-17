import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	private int monto; // monto total (equipo + plan)
	private int cuotas; // numero de cuotas
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


	/// METODO PAGAR EL MONTO TOTAL DEL CONTRATO
	public boolean pagar(RegistroDePagos registro) throws IOException
	{	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// interes sera de 7% del monto del celular
		int interes= (int)(equipoContratado.getPrecio() * 0.07);
		int monto = (registro.getCuotasRestantes() * 
				( registro.getEquipoContratado().getPrecio() / registro.getCuotas() ));
		
		System.out.println("Monto Pendiente correspondiente al movil: "+monto);
		System.out.println("Debera cancelar un monto total de $"+ (monto+interes)
				+ " correspondientes al celular y un 7% del monto del celular, por terminos de contrato no cumplido");
		
		System.out.println("¿Pagar Monto?\n1-Si\n2-No");
		if(Integer.parseInt(bf.readLine())==1) // Si cancela lo que debe 
			return true;
		
		return false;
	}

}
