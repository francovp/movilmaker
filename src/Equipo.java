/**
 *
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Equipo {

	private int idEquipo;
	private String modelo;
	private String manufactor;
	private int ram;
	private String procesador;
	private byte tamPantalla;
	private int resPantallaX;
	private int resPantallaY;
	private int densPantalla;
	private int cantMemInt;
	private int cantMemExt;
	private String os;
	private String versionOS;
	private int precio;
	private String capacidad;
	private int id_compania; // ID de la compania a la que pertenece el Equipo

	// Contructor catalogo Complejo (Sin uso)
	public Equipo(String modelo, String manufactor, int ram, String procesador, byte tamPantalla, int resPantallaX,
			int resPantallaY, int densPantalla, int cantMemInt, int cantMemExt, String os, String versionOS) {
		super();
		this.modelo = modelo;
		this.manufactor = manufactor;
		this.ram = ram;
		this.procesador = procesador;
		this.tamPantalla = tamPantalla;
		this.resPantallaX = resPantallaX;
		this.resPantallaY = resPantallaY;
		this.densPantalla = densPantalla;
		this.cantMemInt = cantMemInt;
		this.cantMemExt = cantMemExt;
		this.os = os;
		this.versionOS = versionOS;
	}

	// Constructor catalogo Simple
	public Equipo(String modelo, String capacidad, int precio) {
		super();
		this.modelo = modelo;
		this.capacidad = capacidad;
		this.precio = precio;

	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public byte getTamPantalla() {
		return tamPantalla;
	}

	public void setTamPantalla(byte tamPantalla) {
		this.tamPantalla = tamPantalla;
	}

	public int getResPantallaX() {
		return resPantallaX;
	}

	public void setResPantallaX(int resPantallaX) {
		this.resPantallaX = resPantallaX;
	}

	public int getResPantallaY() {
		return resPantallaY;
	}

	public void setResPantallaY(int resPantallaY) {
		this.resPantallaY = resPantallaY;
	}

	public int getDensPantalla() {
		return densPantalla;
	}

	public void setDensPantalla(int densPantalla) {
		this.densPantalla = densPantalla;
	}

	public int getCantMemInt() {
		return cantMemInt;
	}

	public void setCantMemInt(int cantMemInt) {
		this.cantMemInt = cantMemInt;
	}

	public int getCantMemExt() {
		return cantMemExt;
	}

	public void setCantMemExt(int cantMemExt) {
		this.cantMemExt = cantMemExt;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVersionOS() {
		return versionOS;
	}

	public void setVersionOS(String versionOS) {
		this.versionOS = versionOS;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public int getId_compania() {
		return id_compania;
	}

	public void setId_compania(int id_compania) {
		this.id_compania = id_compania;
	}

	
}
