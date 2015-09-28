/**
 * 
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Equipos {
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
	
	public Equipos(String modelo, String manufactor, int ram, String procesador, byte tamPantalla, int resPantallaX,
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
	
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @return the manufactor
	 */
	public String getManufactor() {
		return manufactor;
	}
	/**
	 * @return the ram
	 */
	public int getRam() {
		return ram;
	}
	/**
	 * @return the procesador
	 */
	public String getProcesador() {
		return procesador;
	}
	/**
	 * @return the tamPantalla
	 */
	public byte getTamPantalla() {
		return tamPantalla;
	}
	/**
	 * @return the resPantallaX
	 */
	public int getResPantallaX() {
		return resPantallaX;
	}
	/**
	 * @return the resPantallaY
	 */
	public int getResPantallaY() {
		return resPantallaY;
	}
	/**
	 * @return the densPantalla
	 */
	public int getDensPantalla() {
		return densPantalla;
	}
	/**
	 * @return the cantMemInt
	 */
	public int getCantMemInt() {
		return cantMemInt;
	}
	/**
	 * @return the cantMemExt
	 */
	public int getCantMemExt() {
		return cantMemExt;
	}
	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}
	/**
	 * @return the versionOS
	 */
	public String getVersionOS() {
		return versionOS;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @param manufactor the manufactor to set
	 */
	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}
	/**
	 * @param ram the ram to set
	 */
	public void setRam(int ram) {
		this.ram = ram;
	}
	/**
	 * @param procesador the procesador to set
	 */
	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}
	/**
	 * @param tamPantalla the tamPantalla to set
	 */
	public void setTamPantalla(byte tamPantalla) {
		this.tamPantalla = tamPantalla;
	}
	/**
	 * @param resPantallaX the resPantallaX to set
	 */
	public void setResPantallaX(int resPantallaX) {
		this.resPantallaX = resPantallaX;
	}
	/**
	 * @param resPantallaY the resPantallaY to set
	 */
	public void setResPantallaY(int resPantallaY) {
		this.resPantallaY = resPantallaY;
	}
	/**
	 * @param densPantalla the densPantalla to set
	 */
	public void setDensPantalla(int densPantalla) {
		this.densPantalla = densPantalla;
	}
	/**
	 * @param cantMemInt the cantMemInt to set
	 */
	public void setCantMemInt(int cantMemInt) {
		this.cantMemInt = cantMemInt;
	}
	/**
	 * @param cantMemExt the cantMemExt to set
	 */
	public void setCantMemExt(int cantMemExt) {
		this.cantMemExt = cantMemExt;
	}
	/**
	 * @param os the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}
	/**
	 * @param versionOS the versionOS to set
	 */
	public void setVersionOS(String versionOS) {
		this.versionOS = versionOS;
	}
	/**
	 * @param modelo
	 * @param manufactor
	 * @param ram
	 * @param procesador
	 * @param tamPantalla
	 * @param resPantallaX
	 * @param resPantallaY
	 * @param densPantalla
	 * @param cantMemInt
	 * @param cantMemExt
	 * @param os
	 * @param versionOS
	 */
}
