import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
	public static void main(String[] args, Compania datosEmpresa, int falta) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameAgregarAdmin frame = new FrameAgregarAdmin(datosEmpresa, falta);
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
	public FrameAgregarAdmin(Compania datosEmpresa, int falta) {
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

		JLabel lblFonoCel = new JLabel("Celular");
		lblFonoCel.setBounds(10, 79, 70, 14);
		panel_1.add(lblFonoCel);

		textFonoCel = new JTextField();
		textFonoCel.setColumns(10);
		textFonoCel.setBounds(107, 76, 177, 20);
		panel_1.add(textFonoCel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(290, 149, 294, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(10, 206, 284, 14);
		contentPane.add(lblAviso);

		// Boton que caputura todos los datos del Administrador, crea objeto y
		// agrega a lista de Compania
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Administrador nuevoAdmin = null;

				// Comprobaciones de Datos, numeros de telefono deben ser int o
				// el sistema se cae
				if (!comprobarFono(textFonoCel.getText())) {
					lblAviso.setForeground(Color.RED);
					lblAviso.setText("Datos de telefono debe ser numerico");
				} else {
					// Llama metodo para crear Administrador
					nuevoAdmin = datosNuevaPersona(datosEmpresa);
					if (nuevoAdmin != null) {
						System.out.println("Administrador creado...");

						// Si el Administrador se crea exitosamente se escribira
						// Administrador en la BD
						try {
							// Creacion de conexion a base de datos
							Database bd = new Database();
							bd.ingresarAdminBD(nuevoAdmin);
							System.out.println("Administrador agregado a la base de datos...");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Administrador no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepción:");
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
						// // Se volverá a Interfaz principal
						FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa, -1);
						fInterfaz.setVisible(true);
						dispose();
					} else {
						// Sino, se informa que el Administrador ya existe y se
						// vuelve al menu
						System.err.println("Administrador ya existe...");
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

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (falta == 0 || falta == 1) {
					FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa, falta);
					fInterfaz.setVisible(true);
					dispose();
				} else {
					FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa, -1);
					fInterfaz.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setBounds(195, 11, 89, 23);
		panel_2.add(btnCancelar);

	}

	// ========================METODOS====================

	/**
	 * Ingresa atributos capturados desde los JTextField de la ventana y los
	 * atribuye a un objeto Administrador y envia a clase compa�ia
	 **/
	// Obtiene los datos ingresados en las casillas y crea una nueva clase
	// Administrador, luego es enviado a la listaAdministradors en clase
	// Compañia
	public Administrador datosNuevaPersona(Compania datosEmpresa) {
		String nombre1, nombre2, apellido1, apellido2, rut, email, direccion1, direccion2;
		int fono1, fono2;
		nombre1 = textNombre1.getText();
		nombre2 = textNombre2.getText();
		apellido1 = textApellido1.getText();
		apellido2 = textApellido2.getText();
		rut = textRut.getText();
		email = textEmail.getText();
		fono1 = Integer.parseInt(textFonoFijo.getText());
		fono2 = Integer.parseInt(textFonoCel.getText());
		// Se crea Administrador nuevo
		Administrador adminNuevo = new Administrador(rut, datosEmpresa.getRut(), nombre1, nombre2, apellido1, apellido2,
				fono1, fono2, email, 0, null, null, 0, null);
		// Se ingresa Administrador nuevo y se espera un resultado del ingreso
		Administrador resultado = datosEmpresa.crearAdminNuevo(adminNuevo);
		if (resultado != null)
			// Si la persona no existe, todo bien
			return adminNuevo;
		else
			// Entonces la persona ya existe
			return null;
	}

	/**
	 * Resetea todos los JText y JLabel ingresados de la ventana
	 * 
	 * @param lblAviso
	 **/
	public void botonReset(JLabel lblAviso) { // Resetea todos los campos de
												// ingreso en la ventana

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
	 * Comprueba si en textFonoFijo y textFonoCel se han insertado datos del
	 * tipo int
	 * 
	 * @param fono
	 * @return boolean
	 **/

	public boolean comprobarFono(String fono) { // Comprueba si el ingreso en
												// casilla de telefono es un
												// numero INT
		try {
			Integer.parseInt(fono); // Si es INT devuelve true
			return true;
		} catch (Exception a) {
			// Not an integer
			return false;
		}
	}

}