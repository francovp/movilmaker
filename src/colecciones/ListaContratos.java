package colecciones;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JOptionPane;

import extras.Database;

public class ListaContratos implements ListaElementos {
	private ArrayList <Contrato> contratos;
	
	// CONSTRUCTOR
	
	public ListaContratos (){
		contratos = new ArrayList <Contrato> ();
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	@Override
	public void agregarElemento(Object o) {
		agregarContrato ((Contrato)o);
	}
	
	public void agregarContrato (Contrato cont){
		contratos.add(cont);
		
	}
	
	/**
	 * Crea un contrato nuevo con datos obtenidos desde la interfaz
	 * @param numPlan - una ID del Plan elejido en la Interfaz para el nuevo contrato
	 * @param numEquipo - una ID del Equipo elejido en la interfaz para el nuevo contrato
	 * @param numCuotas - La cantidad de cuotas elejidas para pagar el nuevo contrato
	 * @param e - Una referencia a la empresa para obtener algunas variables
	 * @return Un objeto Contrato del contrato nuevo
	 */
	public Contrato crearContrato(Cliente cliente, Plan plan, Equipo movil, int numCuotas, Compania e) {
		Random rnd = new Random();
		int idRandom, monto, valorCuota;
		Contrato contrato = null;

		// Datos para usar fecha real
		Calendar fechaF = new GregorianCalendar();
		Calendar fechaI = new GregorianCalendar();
		DateFormat dfi = DateFormat.getDateInstance();
		DateFormat dff = DateFormat.getDateInstance();
		Date di = fechaI.getTime();
		fechaF.add(Calendar.MONTH, 5); // 5 meses como minimo con el plan
		Date d = fechaF.getTime();
		String fi = dfi.format(di);
		String ff = dff.format(d);
		// Genera un numero random entre 0 y 100000 que sera el id con contrato
		idRandom = rnd.nextInt(100000);
		// Calcula el monto total de la deuda del contrato
		monto = movil.getValorConPlan() + plan.getPrecio();
		// Actualiza la deuda del cliente antes de crear el contrato
		cliente.setDeuda(cliente.getDeuda()+monto);

		// Calcula el valor de cada cuota (sin interes)
		valorCuota = movil.getValorConPlan() / numCuotas + plan.getPrecio();

		// Se crea el obj contrato y se retorna
		contrato = new Contrato(idRandom, fi, ff, movil.getIdEquipo(), plan.getIdPlan(), movil, plan, monto, valorCuota,
				numCuotas, cliente.getRut());
		// SE LE OTORGA NUEVO CONTRATO AL CLIENTE
		agregarContrato(contrato);
		
		System.out.println("\nINFORMACION DEL CONTRATO\n" + "Fecha de inicio del contrato: " + fi
				+ ". El dia de esta fecha se estipulara como fecha de pago. ");
		System.out.println("\nEl cliente debera estar 5 meses como minimo con el plan contratado de lo contrario"
				+ " debera cancelar los meses restantes.");
		System.out.println("\nFecha de termino: " + ff
				+ ". Despues de esta fecha el cliente seguira con el plan por el tiempo que el estime conveniente.");
		System.out.println("\nMonto total de la deuda a pagar: " + monto);
		System.out.println("\nCantidad de cuotas: " + numCuotas);
		System.out.println("\nValor de cada cuota: " + valorCuota);
		return contrato;
	}
	
	public boolean eliminarContrato (String rut, int indice){
		
		contratos.remove(indice);
		try {
			// Creacion de conexion a base de datos
			Database.eliminarContratoBD(rut);
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println("Contrato no se pudo eliminar de la Base de Datos.\n"
					+ "\nDetalles de la excepción:");
			System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
		}
		return false;
	}
	
}
