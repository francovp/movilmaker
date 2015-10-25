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
	private String fechaInicio; // FECHA DE PAGO SERA LA FECHA DE INICIO DEL
								// CONTRATO
	private String fechaTermino;
	private Equipo equipoContratado; // Referencia al Equipo contratado
	private Plan planContratado; // Referencia al Plan contratado

	public Contrato(int idContrato, String fechaInicio, String fechaTermino, int idEquipo, int idPlan,
			Equipo equipoContratado, Plan planContratado, int valorTotal, int valorCuota, int cuotas,
			String rutCliente) {
		super();
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.fechaTermino = fechaTermino;
		this.idEquipo = idEquipo;
		this.idPlan = idPlan;
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
		valorTotal = monto;
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

}
