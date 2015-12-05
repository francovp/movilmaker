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

import colecciones.Administrador;
import colecciones.Compania;
import colecciones.Principal;
import interfaz.FrameInterfaz;

public class FrameAgregarAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre1;
	private JTextField textNombre2;
	private JTextField textApellido1;
	private JTextField textApellido2;
	private JTextField textRut;
	private JTextField textEmail;
	private JTextField textFonoFijo;
	private JTextField textFonoCel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameAgregarAdmin frame = new FrameAgregarAdmin(datosEmpresa);
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
	public FrameAgregarAdmin(Compania datosEmpresa) {
		setResizable(false);
		setTitle("Agregar Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 268);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Datos de administrador", TitledBorder.LEADING, TitledBorder.TOP,
				null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Atributos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 25, 270, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(10, 206, 284, 14);
		contentPane.add(lblAviso);

		JLabel lblPrimerNombre = new JLabel("Nombre");
		lblPrimerNombre.setBounds(10, 24, 97, 14);
		panel.add(lblPrimerNombre);

		textNombre1 = new JTextField();
		textNombre1.setBounds(117, 21, 143, 20);
		panel.add(textNombre1);
		textNombre1.setColumns(10);

		JLabel lblSegundoNombre = new JLabel("Segundo Nombre");
		lblSegundoNombre.setBounds(10, 52, 106, 14);
		panel.add(lblSegundoNombre);

		textNombre2 = new JTextField();
		textNombre2.setColumns(10);
		textNombre2.setBounds(117, 49, 143, 20);
		panel.add(textNombre2);

		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno");
		lblApellidoPaterno.setBounds(10, 80, 97, 14);
		panel.add(lblApellidoPaterno);

		textApellido1 = new JTextField();
		textApellido1.setColumns(10);
		textApellido1.setBounds(117, 77, 143, 20);
		panel.add(textApellido1);

		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setBounds(10, 108, 97, 14);
		panel.add(lblApellidoMaterno);

		textApellido2 = new JTextField();
		textApellido2.setColumns(10);
		textApellido2.setBounds(117, 105, 143, 20);
		panel.add(textApellido2);

		JLabel lblRut = new JLabel("RUT");
		lblRut.setBounds(10, 136, 70, 14);
		panel.add(lblRut);

		textRut = new JTextField();
		textRut.setColumns(10);
		textRut.setBounds(117, 133, 143, 20);
		panel.add(textRut);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(290, 25, 294, 113);
		contentPane.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 25, 70, 14);
		panel_1.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(107, 22, 177, 20);
		panel_1.add(textEmail);

		JLabel lblFonoFijo = new JLabel("Telefono fijo");
		lblFonoFijo.setBounds(10, 53, 70, 14);
		panel_1.add(lblFonoFijo);

		textFonoFijo = new JTextField();
		textFonoFijo.setColumns(10);
		textFonoFijo.setBounds(107, 50, 177, 20);
		panel_1.add(textFonoFijo);
		textFonoFijo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Teléfono fijo";
				textNumericoValidador(textFonoFijo, lblAviso, var, evt);
			}
		});

		JLabel lblFonoCel = new JLabel("Celular");
		lblFonoCel.setBounds(10, 79, 70, 14);
		panel_1.add(lblFonoCel);

		textFonoCel = new JTextField();
		textFonoCel.setColumns(10);
		textFonoCel.setBounds(107, 76, 177, 20);
		panel_1.add(textFonoCel);
		textFonoCel.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Celular";
				textNumericoValidador(textFonoCel, lblAviso, var, evt);
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(290, 149, 294, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// Boton que caputura todos los datos del Administrador, crea objeto y
		// agrega a lista de Compania
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Administrador nuevoAdmin = null;

				// Comprobaciones de Datos ingresados
				if(comprobarIngreso(lblAviso)){					
					// Llama metodo para crear Administrador
					nuevoAdmin = datosNuevaPersona(datosEmpresa);
					if (nuevoAdmin != null) {
						// Si el Administrador se crea exitosamente se escribira
						// Administrador en la BD
						try {
							// Creacion de conexion a base de datos
							Database bd = new Database();
							bd.ingresarAdminBD(nuevoAdmin);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Administrador no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepci�n:");
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}

						// // Para guardar Administrador en un XML
						// // Objeto XML
						// XML xml = new XML();
						// if(xml.ingresarAdministradorXML(datosEmpresa,
						// nuevoAdmin))
						// System.out.println("Administrador guardado en XML.");
						// else System.err.println("Administrador no fue
						// guardado en XML.");
						//
						// Muestra mensaje que el Administrador fue ingresado
						// exitosamente!
						JOptionPane.showMessageDialog(null, "Administrador creado con exito!", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						// // Se volver� a Interfaz principal
						FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
						fInterfaz.setVisible(true);
						dispose();
					} else {
						// Sino, se informa que el Administrador ya existe y se
						// vuelve al menu
						lblAviso.setForeground(Color.RED);
						lblAviso.setText("Administrador ya existe!");
					}
				}
			}
		});
		btnAgregar.setBounds(10, 11, 89, 23);
		panel_2.add(btnAgregar);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				botonReset(lblAviso);
			}
		});
		btnReset.setBounds(104, 11, 89, 23);
		panel_2.add(btnReset);


	}

	// ========================METODOS====================

	/**
	 * Ingresa atributos capturados desde los JTextField de la ventana y los
	 * atribuye a un objeto Administrador que después se asocia a la empresaç
	 * @param datosEmpresa - una referencia a la Compania
	 * @return un objeto Administrador del administrador creado
	 **/
	public Administrador datosNuevaPersona(Compania datosEmpresa) {
		String nombre1 = null, nombre2 = null, apellido1 = null, apellido2 = null, rut = null, email = null;
		int fono1 = 0, fono2 = 0;
				
		if(!textNombre1.getText().isEmpty()) nombre1 = textNombre1.getText();
		if(!textNombre2.getText().isEmpty()) nombre2 = textNombre2.getText();
		if(!textApellido1.getText().isEmpty()) apellido1 = textApellido1.getText();
		if(!textApellido2.getText().isEmpty()) apellido2 = textApellido2.getText();
		if(!textRut.getText().isEmpty()) rut = textRut.getText();
		if(!textEmail.getText().isEmpty()) email = textEmail.getText();
		if(!textFonoCel.getText().isEmpty()) fono1 = Integer.parseInt(textFonoCel.getText());
		if(!textFonoCel.getText().isEmpty()) fono1 = Integer.parseInt(textFonoCel.getText());
		
		// Se crea Administrador nuevo
		Administrador adminNuevo = new Administrador(rut, datosEmpresa.getRut(), nombre1, nombre2, apellido1, apellido2,
				fono1, fono2, email, 0, null, null, 0, null);
		// Se ingresa Administrador nuevo y se espera un resultado del ingreso

		if (datosEmpresa.validarAdmin(adminNuevo.getRut()) == false){
			// Si cliente no existe, todo bien
			datosEmpresa.agregarAdministrador(adminNuevo);
			return adminNuevo;
		}
		else
			// Entonces el cliente ya existe
			return null;
	}

	/**
	 * Resetea todos los JText y JLabel ingresados de la ventana
	 * 
	 * @param lblAviso - una referencia al cuadro de texto para mensajes de aviso
	 **/
	public void botonReset(JLabel lblAviso) { 
		textNombre1.setText("");
		textNombre2.setText("");
		textApellido1.setText("");
		textApellido2.setText("");
		textRut.setText("");
		textEmail.setText("");
		textFonoFijo.setText("");
		textFonoCel.setText("");
		lblAviso.setText("");
	}
	
	/**
	 * Comprueba si los ingresos en las casillas violan restricciones
	 * @return un boolean si no se encuentra ninguna restriccion o no
	 */
	public boolean comprobarIngreso(JLabel aviso) {
		if(textNombre1.getText().length()==0){
			aviso.setForeground(Color.RED);
			aviso.setText("Nombre no puede estar vacío");
			return false;
		}
		if(textApellido1.getText().length() == 0){
			aviso.setForeground(Color.RED);
			aviso.setText("Apellido no puede estar vacío");
			return false;
		}
		if(textRut.getText().length() == 0){
			aviso.setForeground(Color.RED);
			aviso.setText("RUT no puede estar vacío");
			return false;
		}
		
		if(!Principal.validarRut(textRut.getText())){
			aviso.setForeground(Color.RED);
			aviso.setText("Ingrese un RUT válido");
			return false;
		}
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
		String str = tf.getText();
		char[] fuente = str.toCharArray();
		char[] resultado = new char[fuente.length];
		int j=0;
		boolean error=false;
		
		for(int i=0; i<fuente.length;i++){
			if(fuente[i]>='0' && fuente[i]<='9'){
				resultado[j++] = fuente[i];
				aviso.setText("");
			}
			else{
				error=true;
				Toolkit.getDefaultToolkit().beep();
			}
		}
			if(error){
		}
			tf.setText("");
			tf.setText(new String(resultado,0,j));
			aviso.setForeground(Color.RED);
			aviso.setText(var+" debe ser numerico");
	}
}