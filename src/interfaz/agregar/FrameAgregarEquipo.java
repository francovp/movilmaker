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

public class FrameAgregarEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textProcesador;
	private JTextField textValorPlan;
	private JTextField textCamara;
	private JTextField textValorPrepago;
	private JTextField textPantalla;
	private JTextField textSo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa, int falta) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameAgregarEquipo frame = new FrameAgregarEquipo(datosEmpresa);
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
	public FrameAgregarEquipo(Compania datosEmpresa) {
		setResizable(false);
		setTitle("Agregar equipo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Datos del equipo", TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(10, 315, 295, 14);
		contentPane.add(lblAviso);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 25, 295, 237);
		contentPane.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Detalles Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 18, 89, 14);
		panel_1.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(121, 13, 164, 20);
		panel_1.add(textNombre);

		JLabel lblProcesador = new JLabel("Procesador");
		lblProcesador.setBounds(10, 142, 70, 14);
		panel_1.add(lblProcesador);

		textProcesador = new JTextField();
		textProcesador.setColumns(10);
		textProcesador.setBounds(121, 138, 164, 20);
		panel_1.add(textProcesador);
		textProcesador.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Telefono fijo";
				textNumericoValidador(textProcesador, lblAviso, var, evt);
			}
		});

		JLabel lblValorPlan = new JLabel("Valor con Plan");
		lblValorPlan.setBounds(10, 170, 89, 14);
		panel_1.add(lblValorPlan);

		textValorPlan = new JTextField();
		textValorPlan.setColumns(10);
		textValorPlan.setBounds(121, 167, 164, 20);
		panel_1.add(textValorPlan);
		
		JLabel lblPrecio = new JLabel("C\u00E1mara");
		lblPrecio.setBounds(10, 76, 89, 14);
		panel_1.add(lblPrecio);
		
		textCamara = new JTextField();
		textCamara.setColumns(10);
		textCamara.setBounds(121, 76, 164, 20);
		panel_1.add(textCamara);
		
		JLabel lblValorPrepago = new JLabel("Valor Prepago");
		lblValorPrepago.setBounds(10, 202, 89, 14);
		panel_1.add(lblValorPrepago);
		
		textValorPrepago = new JTextField();
		textValorPrepago.setColumns(10);
		textValorPrepago.setBounds(121, 200, 164, 20);
		panel_1.add(textValorPrepago);
		
		JLabel lblPantalla = new JLabel("Pantalla");
		lblPantalla.setBounds(10, 44, 101, 14);
		panel_1.add(lblPantalla);
		
		textPantalla = new JTextField();
		textPantalla.setColumns(10);
		textPantalla.setBounds(121, 43, 164, 20);
		panel_1.add(textPantalla);
		
		textSo = new JTextField();
		textSo.setColumns(10);
		textSo.setBounds(121, 107, 164, 20);
		panel_1.add(textSo);
		
		JLabel lblSistemaOperativo = new JLabel("Sistema Operativo");
		lblSistemaOperativo.setBounds(10, 107, 89, 14);
		panel_1.add(lblSistemaOperativo);
		textValorPlan.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Celular";
				textNumericoValidador(textValorPlan, lblAviso, var, evt);
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 273, 294, 42);
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
					nuevoPlan = datosNuevoEquipo(datosEmpresa);
					if (nuevoPlan != null) {
						// Si el Administrador se crea exitosamente se escribira
						// Administrador en la BD
						try {
							// Creacion de conexion a base de datos
							Database.ingresarPlanBD(nuevoPlan);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Equipo no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepcion:");
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}

						// Muestra mensaje que el Administrador fue ingresado
						// exitosamente!
						JOptionPane.showMessageDialog(null, "Equipo creado con exito!", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						// // Se volverá a Interfaz principal
						//FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa, -1);
						FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
						fInterfaz.setVisible(true);
						dispose();
					} else {
						// Sino, se informa que el Administrador ya existe y se
						// vuelve al menu
						lblAviso.setForeground(Color.RED);
						lblAviso.setText("Equipo ya existe!");
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
				//FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa, -1);
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
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
	public Plan datosNuevoEquipo(Compania datosEmpresa) {
		String nombre = null, pantalla = null, camara = null, so= null, procesador = null;
		int valorPlan = 0, valorPrepago = 0;
				
		if(!textNombre.getText().isEmpty()) nombre = textNombre.getText();
		if(!textPantalla.getText().isEmpty()) pantalla = textPantalla.getText();
		if(!textCamara.getText().isEmpty()) camara = textCamara.getText();
		if(!textSo.getText().isEmpty()) so = textSo.getText();
		if(!textProcesador.getText().isEmpty()) procesador = textProcesador.getText();
		if(!textValorPlan.getText().isEmpty()) valorPlan = Integer.parseInt(textValorPlan.getText());
		if(!textCamara.getText().isEmpty()) valorPrepago = Integer.parseInt(textValorPrepago.getText());
		
		// Se crea Plan nuevo y se ingresa, se espera un resultado del ingreso
		Plan equipoNuevo = datosEmpresa.crearPlan(
				new Plan (0, nombre, precio, minutos, gigas, sms, valorMin, datosEmpresa.getRut()));
		if (equipoNuevo != null)
			// Si el plan no existe, todo bien
			return equipoNuevo;
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
		textNombre.setText("");
		textProcesador.setText("");
		textValorPlan.setText("");
		textCamara.setText("");
		textValorPrepago.setText("");
		textPantalla.setText("");
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