/**
 * 
 */
package colecciones;

import java.util.ArrayList;

/**
 * @author Franco
 *
 */
public class ListaRegistroDePagos implements Validador {
	
	private ArrayList <RegistroDePagos> lista;

	/* (non-Javadoc)
	 * @see colecciones.Validador#agregarElemento(java.lang.Object)
	 */
	@Override
	public boolean validarAgregar(Object o) {
		// TODO Auto-generated method stub
		return true;
	}
	
//	// METODO PAGAR MENSUALIDAD (SOBREESCRITURA )
//	public boolean pagar(int numeroCuotas, int valorCuota) {
//		System.out.println("Cliente: " + contratoAPagar.getRutCliente());
//		cuotasRestantes = getCuotasRestantes() - numeroCuotas;
//
//		if (cuotasRestantes <= 0) { // si cancelo todas las cuotas
//
//			System.out.println("Equipo movil se encuentra pagado");
//			montoPagado = contratoAPagar.getPlanContratado().getPrecio();
//
//			System.out.println("Nuevo monto Mensual: " + montoPagado);
//			// se actualiza el valor de la cuota
//			contratoAPagar.setValorCuota(contratoAPagar.getEquipoContratado().getValorConPlan() / cuotasRestantes + contratoAPagar.getPlanContratado().getPrecio()); 
//			return true;
//		} else {
//			System.out.println("Cuotas Pagadas: " + (contratoAPagar.getCuotas() - cuotasRestantes) + " de " + contratoAPagar.getCuotas());
//			return true;
//		}
//	}

}
