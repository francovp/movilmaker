public class RegistroDePagos extends Contrato { 
	private int idRegistro;
	private int montoPagado;
	private int cuotasRestantes;
	
	// Constructor para cuando se paga una mensualidad
	public RegistroDePagos(int idContrato, int idEquipo, int idPlan, String fechaInicio, String fechaTermino,
			String rutCliente, int valorTotal, int valorCuota, int cuotas, int idRegistro,
			int montoPagado, int cuotasRestantes) {
		
		super(idContrato, idEquipo, idPlan, fechaInicio, fechaTermino, rutCliente, valorTotal, valorCuota, cuotas);
		
		this.idRegistro = idRegistro;
		this.montoPagado = montoPagado;
		this.cuotasRestantes = cuotasRestantes;
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

	// METODO PAGAR MENSUALIDAD (SOBREESCRITURA )
	public boolean pagar(int numeroCuotas, int valorCuota){
		System.out.println("Cliente: "+ getRutCliente());
		cuotasRestantes = getCuotasRestantes() - numeroCuotas;
		
		if(cuotasRestantes <= 0 ){ // si cancelo todas las cuotas 
			
			System.out.println("Equipo movil se encuentra pagado");
			montoPagado = getPlanContratado().getPrecio();
			
			System.out.println("Nuevo monto Mensual: " + montoPagado);
			setValorCuota((getEquipoContratado().getPrecio() / cuotasRestantes) + getPlanContratado().getPrecio()); // se actualiza el valor de la cuota
			return true;
		}
		else{
			System.out.println("Cuotas Pagadas: "+ (getCuotas() - cuotasRestantes) +" de "+getCuotas());
			return true;
		}
	}
}
