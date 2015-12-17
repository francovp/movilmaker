package colecciones;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;

import extras.Database;

public class ListaContratos implements Validador {
	private ArrayList <Contrato> lista;
	
	// CONSTRUCTOR
	
	public ListaContratos (){
		lista = new ArrayList <Contrato> ();
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public ArrayList<Contrato> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Contrato> contratos) {
		this.lista = contratos;
	}
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////
	
	@Override
	public boolean validarAgregar(Object o) {
		if(validarContrato(((Contrato)o).getIdContrato()) == false){
			agregarContrato((Contrato)o);
			return true;
		}
		else return false;
	}
	
	public void agregarContrato (Contrato cont){
		lista.add(cont);
	}
	
	public boolean validarContrato (int id){
		for (int i = 0; i<lista.size();i++){
			if (lista.get(i).getIdContrato() == id){
				return true;	//	Contrato Existe
			}
		}
		return false;	//	Contrato no existe
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
		monto = movil.getValorPlan() + plan.getPrecio();
		// Actualiza la deuda del cliente antes de crear el contrato
		cliente.setDeuda(cliente.getDeuda()+monto);

		// Calcula el valor de cada cuota (sin interes)
		valorCuota = movil.getValorPlan() / numCuotas + plan.getPrecio();

		// Se crea el obj contrato y se retorna
		contrato = new Contrato(idRandom, fi, ff, movil.getIdEquipo(), plan.getIdPlan(), movil, plan, monto, valorCuota,
				numCuotas, cliente.getRut());
		// SE LE OTORGA NUEVO CONTRATO AL CLIENTE
		if(contrato!= null)
			agregarContrato(contrato);
		else
			System.out.println("Puntero null de contrato");
		
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
		
		lista.remove(indice);
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
	
	public void reportarContratos(Document documento) throws DocumentException
	{

		// RECORRE CONTRATOS DE CLIENTE 
		// por CADA CONTRATO IMPRIME SUS DETALLES DE VALOR , EQUIPO Y PLAN
		for (int j = 0; j < lista.size(); j++) {
			// imprime en pdf id contrato y valor total a pagar de cada
			// cliente
			documento.add(
					new Paragraph("---- ID Contrato :                 " + lista.get(j).getIdContrato()));
			documento.add(
					new Paragraph("---- Valor total :                   $" + lista.get(j).getValorTotal()));
			lista.get(j).reportarPlanContratado(documento);
			lista.get(j).reportarEquipoContratado(documento);
				
		}
	}
}
