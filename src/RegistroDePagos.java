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

	// METODO PAGAR MENSUALIDAD (SOBREESCRITURA )
	public boolean pagar(int numeroCuotas, int valorCuota) {
		System.out.println("Cliente: " + contratoAPagar.getRutCliente());
		cuotasRestantes = getCuotasRestantes() - numeroCuotas;

		if (cuotasRestantes <= 0) { // si cancelo todas las cuotas

			System.out.println("Equipo movil se encuentra pagado");
			montoPagado = contratoAPagar.getPlanContratado().getPrecio();

			System.out.println("Nuevo monto Mensual: " + montoPagado);
			// se actualiza el valor de la cuota
			contratoAPagar.setValorCuota(contratoAPagar.getEquipoContratado().getValorConPlan() / cuotasRestantes + contratoAPagar.getPlanContratado().getPrecio()); 
			return true;
		} else {
			System.out.println("Cuotas Pagadas: " + (contratoAPagar.getCuotas() - cuotasRestantes) + " de " + contratoAPagar.getCuotas());
			return true;
		}
	}
}
