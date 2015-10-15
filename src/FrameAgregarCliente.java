import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.Window.Type;

public class FrameAgregarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre1;
	private JTextField textNombre2;
	private JTextField textApellido1;
	private JTextField textApellido2;
	private JTextField textRut;
	private JTextField textDireccion1;
	private JTextField textDireccion2;
	private JTextField textEmail;
	private JTextField textFonoFijo;
	private JTextField textFonoCel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAgregarCliente frame = new FrameAgregarCliente(datosEmpresa);
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
	public FrameAgregarCliente(Compania datosEmpresa) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Datos de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Atributos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 25, 294, 170);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblPrimerNombre = new JLabel("Nombre");
		lblPrimerNombre.setBounds(10, 24, 70, 14);
		panel.add(lblPrimerNombre);

			textNombre1 = new JTextField();
			textNombre1.setBounds(117, 21, 153, 20);
			panel.add(textNombre1);
			textNombre1.setColumns(10);

		JLabel lblSegundoNombre = new JLabel("Segundo Nombre");
		lblSegundoNombre.setBounds(10, 52, 97, 14);
		panel.add(lblSegundoNombre);

			textNombre2 = new JTextField();
			textNombre2.setColumns(10);
			textNombre2.setBounds(117, 49, 153, 20);
			panel.add(textNombre2);

		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno");
		lblApellidoPaterno.setBounds(10, 80, 97, 14);
		panel.add(lblApellidoPaterno);

			textApellido1 = new JTextField();
			textApellido1.setColumns(10);
			textApellido1.setBounds(117, 77, 153, 20);
			panel.add(textApellido1);

		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setBounds(10, 108, 97, 14);
		panel.add(lblApellidoMaterno);

		textApellido2 = new JTextField();
		textApellido2.setColumns(10);
		textApellido2.setBounds(117, 105, 153, 20);
		panel.add(textApellido2);

		JLabel lblRut = new JLabel("RUT");
		lblRut.setBounds(10, 136, 70, 14);
		panel.add(lblRut);

			textRut = new JTextField();
			textRut.setColumns(10);
			textRut.setBounds(117, 133, 153, 20);
			panel.add(textRut);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(314, 25, 260, 204);
		contentPane.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 25, 70, 14);
		panel_1.add(lblEmail);
		
			textEmail = new JTextField();
			textEmail.setColumns(10);
			textEmail.setBounds(107, 22, 143, 20);
			panel_1.add(textEmail);

		JLabel lblFonoFijo = new JLabel("Telefono fijo");
		lblFonoFijo.setBounds(10, 53, 70, 14);
		panel_1.add(lblFonoFijo);
		
			textFonoFijo = new JTextField();
			textFonoFijo.setColumns(10);
			textFonoFijo.setBounds(107, 50, 143, 20);
			panel_1.add(textFonoFijo);

		JLabel lblFonoCel = new JLabel("Celular");
		lblFonoCel.setBounds(10, 79, 70, 14);
		panel_1.add(lblFonoCel);

			textFonoCel = new JTextField();
			textFonoCel.setColumns(10);
			textFonoCel.setBounds(107, 76, 143, 20);
			panel_1.add(textFonoCel);
		
		JLabel lblDireccion1 = new JLabel("Direccion");
		lblDireccion1.setBounds(10, 115, 52, 14);
		panel_1.add(lblDireccion1);
		
			textDireccion1 = new JTextField();
			textDireccion1.setBounds(107, 104, 143, 40);
			panel_1.add(textDireccion1);
			textDireccion1.setColumns(10);

		JLabel lblDireccion2 = new JLabel("Ciudad");
		lblDireccion2.setBounds(10, 163, 52, 14);
		panel_1.add(lblDireccion2);
		
			textDireccion2 = new JTextField();
			textDireccion2.setBounds(107, 152, 143, 40);
			panel_1.add(textDireccion2);
			textDireccion2.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 199, 294, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(20, 252, 284, 14);
		contentPane.add(lblAviso);

		//Boton que caputura todos los datos del cliente, crea objeto y agrega a lista de Compañia
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente nuevoCliente = null;
				// Creación de conección a base de datos
				Database bd = null;
				try {
					bd = new Database();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				// Comprobaciones de Datos
				if (!comprobarFonoCel(textFonoCel.getText())){
					lblAviso.setForeground(Color.RED);
					lblAviso.setText("Datos de Telefono debe ser numerico");
				}
				else{
					//Llama metodo para crear Cliente
					nuevoCliente = datosNuevoCliente(datosEmpresa);
					if (nuevoCliente != null){
						System.out.println("Cliente creado...");
						// Si el cliente se creó exitosamente se escribira cliente en la BD
						try {
							bd.ingresarClienteBD(nuevoCliente);
							System.out.println("Cliente agregado a la base de datos...");						
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Cliente no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepción:");
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}
						
						// Se creará un contrato
						FrameContrato fContrato = new FrameContrato(datosEmpresa,nuevoCliente);
						fContrato.setVisible(true);
						dispose();
					}
					else
						//Sino, se informa que el cliente ya existe y se vuelve al men�
						System.err.println("Cliente ya existe...");
				}
			}
		});
		btnAgregar.setBounds(10, 11, 89, 23);
		panel_2.add(btnAgregar);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonReset();
			}
		});
		btnReset.setBounds(104, 11, 89, 23);
		panel_2.add(btnReset);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(195, 11, 89, 23);
		panel_2.add(btnCancelar);

	}
		
	//========================METODOS====================

	//Obtiene los datos ingresados en las casillas y crea una nueva clase Cliente, luego es enviado a la listaClientes en clase Compañia
	public Cliente datosNuevoCliente(Compania datosEmpresa){
		String nombre1,nombre2,apellido1,apellido2,rut,email,direccion1,direccion2;
		int fono1,fono2;
		nombre1=textNombre1.getText();
		nombre2=textNombre2.getText();
		apellido1=textApellido1.getText();
		apellido2=textApellido2.getText();
		rut=textRut.getText();
		email=textEmail.getText();
		fono1=Integer.parseInt(textFonoFijo.getText());
		fono2=Integer.parseInt(textFonoCel.getText());
		direccion1=textDireccion1.getText();
		direccion2=textDireccion2.getText();
		// Se crea cliente nuevo
		Cliente clienteNuevo = new Cliente(nombre1,nombre2,apellido1,apellido2,rut,fono1,fono2,email,direccion1,direccion2,datosEmpresa.getRut());
		// Se ingresa cliente nuevo y se espera un resultado del ingreso
		Cliente resultado = datosEmpresa.interfazCrearClienteNuevo(clienteNuevo);
		if(resultado != null)
			// Si cliente no existe, todo bien
			return clienteNuevo;
		else 
			// Entonces el cliente ya existe
			return null;
	}

	//Resetea todos los campos de ingreso en la ventana
	public void botonReset() {

		textNombre1.setText("");
		textNombre2.setText("");
		textApellido1.setText("");
		textApellido2.setText("");
		textRut.setText("");
		textEmail.setText("");
		textFonoFijo.setText("");
		textFonoCel.setText("");
		textDireccion1.setText("");
		textDireccion2.setText("");
	}

	//========================METODOS DE COMPROBACIONES ====================
	//Comprueba si el ingreso en casilla de telefono es un numero INT
	public boolean comprobarFonoCel (String fonoCel) {
		try {
		    Integer.parseInt(fonoCel);	//Si es INT devuelve true
			return true;
		}
		catch (Exception a) {
		     //Not an integer
			return false;
		}
	}
}