import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField textFono1;
	private JTextField textFono2;

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

		JLabel lblPrimerNombre = new JLabel("Primer Nombre");
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

		JLabel lblRut = new JLabel("Rut");
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

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(107, 22, 143, 20);
		panel_1.add(textEmail);

		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(10, 25, 70, 14);
		panel_1.add(lblEmail);

		textFono1 = new JTextField();
		textFono1.setColumns(10);
		textFono1.setBounds(107, 50, 143, 20);
		panel_1.add(textFono1);

		JLabel lblFono = new JLabel("Telefono fijo");
		lblFono.setBounds(10, 53, 70, 14);
		panel_1.add(lblFono);

		JLabel lblCelular = new JLabel("Telefono movil");
		lblCelular.setBounds(10, 79, 70, 14);
		panel_1.add(lblCelular);

		textFono2 = new JTextField();
		textFono2.setColumns(10);
		textFono2.setBounds(107, 76, 143, 20);
		panel_1.add(textFono2);

		textDireccion2 = new JTextField();
		textDireccion2.setBounds(107, 152, 143, 40);
		panel_1.add(textDireccion2);
		textDireccion2.setColumns(10);

		JLabel lblDireccion_1 = new JLabel("Direccion 2");
		lblDireccion_1.setBounds(10, 163, 52, 14);
		panel_1.add(lblDireccion_1);

		JLabel lblDireccion = new JLabel("Direccion 1");
		lblDireccion.setBounds(10, 115, 52, 14);
		panel_1.add(lblDireccion);

		textDireccion1 = new JTextField();
		textDireccion1.setBounds(107, 104, 143, 40);
		panel_1.add(textDireccion1);
		textDireccion1.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 199, 294, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(20, 252, 284, 14);
		contentPane.add(lblAviso);

		//Boton que caputura todos los datos del cliente, crea objeto y agrega a lista de Compañia
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comprobarNumero(textFono1.getText())==true && comprobarNumero(textFono2.getText())==true){
					//Llama metodo para crear Cliente
					Cliente nuevoCliente;
					nuevoCliente=datosNuevoCliente(datosEmpresa);
					FrameContrato fContrato = new FrameContrato(datosEmpresa,nuevoCliente);
					fContrato.setVisible(true);
					dispose();
				}else {
					lblAviso.setForeground(Color.RED);
					lblAviso.setText("Datos de Telefono deben ser números");
					}

			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botonReset();
			}
		});
		btnNewButton_1.setBounds(104, 11, 89, 23);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(195, 11, 89, 23);
		panel_2.add(btnNewButton_2);


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
		fono1=Integer.parseInt(textFono1.getText());
		fono2=Integer.parseInt(textFono2.getText());
		direccion1=textDireccion1.getText();
		direccion2=textDireccion2.getText();
		Cliente clienteNuevo = new Cliente(nombre1,nombre2,apellido1,apellido2,rut,fono1,fono2,email,direccion1,direccion2,datosEmpresa.getRut());
		datosEmpresa.interfazCrearClienteNuevo(clienteNuevo);
		return clienteNuevo;
	}

	//Resetea todos los campos de ingreso en la ventana
	public void botonReset(){

		textNombre1.setText("");
		textNombre2.setText("");
		textApellido1.setText("");
		textApellido2.setText("");
		textRut.setText("");
		textEmail.setText("");
		textFono1.setText("");
		textFono2.setText("");
		textDireccion1.setText("");
		textDireccion2.setText("");
	}


	//Comprueba si el ingreso en casilla de telefono es un numero INT
	public boolean comprobarNumero(String numero){
		try {
		     Integer.parseInt(numero);	//Si es INT devuelve true

		     return true;
		}
		catch (Exception a) {
		     //Not an integer
		}
		return false;
	}
}