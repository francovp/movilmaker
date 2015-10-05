/**
 * 
 */

/**
 * @author FValerio, DMayorga, MSilva, LMondaca
 *
 */
public class Planes {
	private int precio;
	private String tipoPlan; //S,M,L
	private int min; // minutos para hablar
	private int net; //GB de navegacion
	/**
	 * @param precio
	 * @param tipoPlan
	 * @param min
	 * @param net
	 */
	public Planes(int precio, String tipoPlan, int min, int net) {
		super();
		this.precio = precio;
		this.tipoPlan = tipoPlan;
		this.min = min;
		this.net = net;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getTipoPlan() {
		return tipoPlan;
	}
	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getNet() {
		return net;
	}
	public void setNet(int net) {
		this.net = net;
	}
	
}
