package interfaz.agregar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import colecciones.Plan;
import extras.Database;
import interfaz.FrameInterfaz;

public class FrameAgregarPlan extends JFrame {

	private JPanel contentPane;
	private JTextField textNombrePlan;
	private JTextField textMinutos;
	private JTextField textGigas;
	private JTextField textPrecio;
	private JTextField textSms;
	private JTextField textValorMin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa, int falta) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameAgregarPlan frame = new FrameAgregarPlan(datosEmpresa);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameAgregarPlan(Compania datosEmpresa) {
		setResizable(false);
		setTitle("Agregar pLAN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 321, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Datos del plN", TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(20, 302, 284, 14);
		contentPane.add(lblAviso);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 25, 420, 213);
		contentPane.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);

		JLabel lblNombrePlan = new JLabel("Nombre del Plan");
		lblNombrePlan.setBounds(10, 18, 89, 14);
		panel_1.add(lblNombrePlan);

		textNombrePlan = new JTextField();
		textNombrePlan.setColumns(10);
		textNombrePlan.setBounds(121, 13, 164, 20);
		panel_1.add(textNombrePlan);

		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(10, 50, 70, 14);
		panel_1.add(lblMinutos);

		textMinutos = new JTextField();
		textMinutos.setColumns(10);
		textMinutos.setBounds(121, 46, 164, 20);
		panel_1.add(textMinutos);
		textMinutos.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Teléfono fijo";
				textNumericoValidador(textMinutos, lblAviso, var, evt);
			}
		});

		JLabel lblGigas = new JLabel("Cuota Navegación");
		lblGigas.setBounds(10, 82, 89, 14);
		panel_1.add(lblGigas);

		textGigas = new JTextField();
		textGigas.setColumns(10);
		textGigas.setBounds(121, 79, 164, 20);
		panel_1.add(textGigas);
		
		JLabel lblPrecio = new JLabel("Precio (Mensual)");
		lblPrecio.setBounds(10, 178, 89, 14);
		panel_1.add(lblPrecio);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(121, 178, 164, 20);
		panel_1.add(textPrecio);
		
		JLabel lblSms = new JLabel("SMS");
		lblSms.setBounds(10, 114, 89, 14);
		panel_1.add(lblSms);
		
		textSms = new JTextField();
		textSms.setColumns(10);
		textSms.setBounds(121, 112, 164, 20);
		panel_1.add(textSms);
		
		JLabel lblValorMinAdicional = new JLabel("Valor min. adicional");
		lblValorMinAdicional.setBounds(10, 146, 101, 14);
		panel_1.add(lblValorMinAdicional);
		
		textValorMin = new JTextField();
		textValorMin.setColumns(10);
		textValorMin.setBounds(121, 145, 164, 20);
		panel_1.add(textValorMin);
		textGigas.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Celular";
				textNumericoValidador(textGigas, lblAviso, var, evt);
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 249, 294, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// Boton que caputura todos los datos del Administrador, crea objeto y
		// agrega a lista de Compania
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Plan nuevoPlan = null;

				// Comprobaciones de Datos ingresados
				if(comprobarIngreso(lblAviso)){					
					// Llama metodo para crear Administrador
					nuevoPlan = datosNuevoPlan(datosEmpresa);
					if (nuevoPlan != null) {
						// Si el Administrador se crea exitosamente se escribira
						// Administrador en la BD
						try {
							// Creacion de conexion a base de datos
							Database.ingresarPlanBD(nuevoPlan);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Plan no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepción:");
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}

						// Muestra mensaje que el Administrador fue ingresado
						// exitosamente!
						JOptionPane.showMessageDialog(null, "Plan creado con exito!", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} else {
						// Sino, se informa que el Administrador ya existe y se
						// vuelve al menu
						lblAviso.setForeground(Color.RED);
						lblAviso.setText("Plan ya existe!");
					}
				}
			}
		});
		btnAgregar.setBounds(6, 11, 89, 23);
		panel_2.add(btnAgregar);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				botonReset(lblAviso);
			}
		});
		btnReset.setBounds(101, 11, 89, 23);
		panel_2.add(btnReset);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(196, 11, 89, 23);
		panel_2.add(btnCancelar);

	}

	// ========================METODOS====================

	/**
	 * Ingresa atributos capturados desde los JTextField de la ventana y los
	 * atribuye a un objeto Plan que después se asocia a la empresaç
	 * @param datosEmpresa - una referencia a la Compania
	 * @return un objeto Plan del plan creado
	 **/
	public Plan datosNuevoPlan(Compania datosEmpresa) {
		String nombre = null, gigas = null;
		int minutos = 0, precio = 0, sms = 0, valorMin = 0;
				
		if(!textNombrePlan.getText().isEmpty()) nombre = textNombrePlan.getText();
		if(!textMinutos.getText().isEmpty()) minutos = Integer.parseInt(textMinutos.getText());
		if(!textGigas.getText().isEmpty()) gigas = textGigas.getText();
		if(!textPrecio.getText().isEmpty()) precio = Integer.parseInt(textPrecio.getText());
		if(!textSms.getText().isEmpty()) sms = Integer.parseInt(textSms.getText());
		if(!textValorMin.getText().isEmpty()) valorMin = Integer.parseInt(textValorMin.getText());
		
		// Se crea Plan nuevo y se ingresa, se espera un resultado del ingreso
		Plan nuevo = new Plan (0, nombre, precio, minutos, gigas, sms, valorMin, datosEmpresa.getRut());
		if (datosEmpresa.getPlanes().validarAgregar(nuevo) == true)
			// Si el plan no existe, todo bien
			return nuevo;
		else
			// Entonces el plan ya existe
			return null;
	}

	/**
	 * Resetea todos los JText y JLabel ingresados de la ventana
	 * 
	 * @param lblAviso - una referencia al cuadro de texto para mensajes de aviso
	 **/
	public void botonReset(JLabel lblAviso) { 
		textNombrePlan.setText("");
		textMinutos.setText("");
		textGigas.setText("");
		textPrecio.setText("");
		textSms.setText("");
		textValorMin.setText("");
		lblAviso.setText("");
	}
	
	/**
	 * Comprueba si los ingresos en las casillas violan restricciones
	 * @return un boolean si no se encuentra ninguna restriccion o no
	 */
	public boolean comprobarIngreso(JLabel aviso) {
//		if(textNombre.getText().length()==0){
//			aviso.setForeground(Color.RED);
//			aviso.setText("Nombre no puede estar vacío");
//			return false;
//		}
//		if(textApellido1.getText().length() == 0){
//			aviso.setForeground(Color.RED);
//			aviso.setText("Apellido no puede estar vacío");
//			return false;
//		}
//		if(textRut.getText().length() == 0){
//			aviso.setForeground(Color.RED);
//			aviso.setText("RUT no puede estar vacío");
//			return false;
//		}
//		
//		if(!Principal.validarRut(textRut.getText())){
//			aviso.setForeground(Color.RED);
//			aviso.setText("Ingrese un RUT válido");
//			return false;
//		}
		return true;
	}
	
	/**
	 * Comprueba si el ingreso en el cuadro de texto de Teléfono Fijo o Celular son numéricos
	 * @param tf - una referencia al elemento JTextField con el texto a verificar
	 * @param aviso - una referencia al cuadro de texto para mensajes de aviso
	 * @param var - La variable que se está verificando
	 * @param evt - una referencia a la tecla que se está pulsando actualmente para comprobarla
	 */
	private void textNumericoValidador (JTextField tf, JLabel aviso, String var, KeyEvent evt) {
//		String str = tf.getText();
//		char[] fuente = str.toCharArray();
//		char[] resultado = new char[fuente.length];
//		int j=0;
//		boolean error=false;
//		for(int i=0; i<fuente.length;i++){
//			if(fuente[i]>='0' && fuente[i]<='9'){
//				resultado[j++] = fuente[i];
//				aviso.setText("");
//			}
//			else{
//				error=true;
//				Toolkit.getDefaultToolkit().beep();
//			}
//		}
//		if(error){
//			tf.setText("");
//			tf.setText(new String(resultado,0,j));
//			aviso.setForeground(Color.RED);
//			aviso.setText(var+" debe ser numerico");
//		}
	}
}