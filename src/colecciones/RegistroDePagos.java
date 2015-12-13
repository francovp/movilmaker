package colecciones;

public class RegistroDePagos{
	private int idContrato;
	private int idRegistro;
	private int montoPagado;
	private int montoAdeudado;
	private int cuotasRestantes;
	private Contrato contratoAPagar;

	// Constructor para cuando se paga una mensualidad
	public RegistroDePagos(int idContrato, int idRegistro, int montoPagado, int montoAdeudado, int cuotasRestantes, Contrato contratoAPagar) {

		this.idContrato = idContrato;
		this.idRegistro = idRegistro;
		this.montoPagado = montoPagado;
		this.montoAdeudado = montoAdeudado;
		this.cuotasRestantes = cuotasRestantes;
		this.contratoAPagar = contratoAPagar;
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public int getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(int montoPagado) {
		this.montoPagado = montoPagado;
	}

	public int getCuotasRestantes() {
		return cuotasRestantes;
	}

	public void setCuotasRestantes(int cuotasRestantes) {
		this.cuotasRestantes = cuotasRestantes;
	}

	public int getMontoAdeudado() {
		return montoAdeudado;
	}

	public void setMontoAdeudado(int montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public Contrato getContratoAPagar() {
		return contratoAPagar;
	}

	public void setContratoAPagar(Contrato contratoAPagar) {
		this.contratoAPagar = contratoAPagar;
	}
}
