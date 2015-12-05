package colecciones;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Cliente extends Persona {
	private String direccion1;
	private String direccion2;
	private int deuda;
	public ListaContratos contratos;

	// CONSTRUCTOR
	public Cliente(String rut, String idCompania, String nombre1, String nombre2, String apellido1, String apellido2,
			int fonoCel, int fonoFijo, String email, int tipo, String direccion1, String direccion2, int deuda,
			String password) {

		super(rut, idCompania, nombre1, nombre2, apellido1, apellido2, fonoCel, fonoFijo, email, tipo);
		
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.deuda = deuda;
		this.contratos=new ListaContratos();
	}
	
	/////////////////////////// * GETTERS & SETTERS *////////////////////////////////////

	public String getDireccion1() {
		return direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}

	public String getDireccion2() {
		return direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}

	public int getDeuda() {
		return deuda;
	}

	public void setDeuda(int deudaTotal) {
		deuda = deudaTotal;
	}
	
	public ListaContratos getContratos() {
		return contratos;
	}

	public void setContratos(ListaContratos contratos) {
		this.contratos = contratos;
	}
	
	
	/////////////////////////// * METODOS * /////////////////////////////////////////////

	public Contrato crearContrato(Cliente cliente, Plan plan, Equipo movil, int numCuotas, Compania e) {
		return contratos.crearContrato(cliente, plan, movil, numCuotas, e);
	}

	@Override
	public String identificarse() {
		// TODO Auto-generated method stub
		
		return null;
	}
}
