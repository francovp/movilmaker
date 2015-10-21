import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	private int valorTotal; // monto total (equipo + plan)
	private int valorCuota; 
	private int cuotas; // numero de cuotas
	private String fechaInicio; // FECHA DE PAGO SERA LA FECHA DE INICIO DEL CONTRATO
	private String fechaTermino;
	private Equipo equipoContratado; // Referencia al Equipo contratado
	private Plan planContratado; // Referencia al Plan contratado
	public ArrayList<RegistroDePagos> boletas = new ArrayList<RegistroDePagos>();
	
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
	public Contrato(int idContrato, int idEquipo, int idPlan, String fechaInicio,
			String fechaTermino, String rutCliente, int monto, int valorCuota, int cuotas) {
		super();
		this.rutCliente = rutCliente;
		this.idContrato = idContrato;
		this.idEquipo = idEquipo;
		this.idPlan = idPlan;
		this.valorTotal = monto;
		this.valorCuota = valorCuota;
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
			Plan planContratado, int valorTotal, int valorCuota, int cuotas, String rutCliente) {
		super();
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.equipoContratado = equipoContratado;
		this.planContratado = planContratado;
		this.valorTotal = valorTotal;
		this.valorCuota = valorCuota;
		this.cuotas = cuotas;
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

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int monto) {
		this.valorTotal = monto;
	}

	public int getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(int valorCuota) {
		this.valorCuota = valorCuota;
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
	
	public ArrayList<RegistroDePagos> getBoletas() {
		return boletas;
	}

	public void setBoletas(ArrayList<RegistroDePagos> boletas) {
		this.boletas = boletas;
	}

	/// METODO PAGAR EL MONTO TOTAL DEL CONTRATO
	public boolean pagar(RegistroDePagos registro, int cuotasRestantes) throws IOException
	{	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		// interes sera de 7% del monto del celular
		int interes= (int)(equipoContratado.getPrecio() * 0.07);
		int valorTotal = (cuotasRestantes * ( registro.getPlanContratado().getPrecio() / registro.getCuotas() ));
		
		System.out.println("Monto Pendiente correspondiente al movil: "+valorTotal);
		System.out.println("Debera cancelar un monto total de $"+ (valorTotal+interes)
				+ " correspondientes al celular y un 7% del monto del celular, por terminos de contrato no cumplido");
		
		System.out.println("¿Pagar Monto?\n1-Si\n2-No");
		if(Integer.parseInt(bf.readLine())==1) // Si cancela lo que debe 
			return true;
		
		return false;
	}
	
	// METODO PARA BUSCAR UN PAGO Y RETORNARLO
	public RegistroDePagos buscarPago (String rut) {
		int actual = 0, min = getCuotas();
		RegistroDePagos boleta = null;
		for (int i = 0; i < boletas.size(); i++)
			if (boletas.get(i).getRutCliente().equals(rut)){ // si el rut ingresado se encuenta
				actual = boletas.get(i).getCuotasRestantes();
				if(actual < min){
					min = actual;
					boleta = boletas.get(i);
				}
			}
		return boleta; // se retorna la boleta
	}

}
