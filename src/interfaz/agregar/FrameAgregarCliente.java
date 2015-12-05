package interfaz.agregar;
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

import colecciones.Cliente;
import colecciones.Compania;
import extras.Database;
import interfaz.FrameInterfaz;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

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
	 * Crea y lanza la ventana de la aplicación.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				crearYMostrarUI(datosEmpresa);
			}
		});
	}
	
	/**
     * Crea la ventana de esta interfaz
     */
    private static void crearYMostrarUI(Compania datosEmpresa) {
        //Creaa y configura la ventana
    	FrameAgregarCliente frame = new FrameAgregarCliente(datosEmpresa);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Muestra la ventana
        frame.setVisible(true);
    }


	/**
	 * Crea el Frame.
	 */
	public FrameAgregarCliente(Compania datosEmpresa) {
		setResizable(false);
		setTitle("Agregar Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 321);
		contentPane = new JPanel();
		contentPane.setBorder(
				new TitledBorder(null, "Datos de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Atributos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 25, 294, 170);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(20, 252, 284, 14);
		contentPane.add(lblAviso);

		JLabel lblPrimerNombre = new JLabel("Nombre");
		lblPrimerNombre.setBounds(10, 24, 97, 14);
		panel.add(lblPrimerNombre);

		textNombre1 = new JTextField();
		textNombre1.setBounds(117, 21, 153, 20);
		panel.add(textNombre1);
		textNombre1.setColumns(10);
		textNombre1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				//textNombre1KeyReleased(evt);
				}
		});
		
		JLabel lblSegundoNombre = new JLabel("Segundo Nombre");
		lblSegundoNombre.setBounds(10, 52, 106, 14);
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
		panel_1.setBounds(314, 25, 260, 183);
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
		textFonoCel.setBounds(107, 76, 143, 20);
		panel_1.add(textFonoCel);
		textFonoCel.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				String var = "Celular";
				textNumericoValidador(textFonoCel, lblAviso, var, evt);
			}
		});

		JLabel lblDireccion1 = new JLabel("Direccion");
		lblDireccion1.setBounds(10, 115, 87, 14);
		panel_1.add(lblDireccion1);

		textDireccion1 = new JTextField();
		textDireccion1.setBounds(107, 104, 143, 40);
		panel_1.add(textDireccion1);
		textDireccion1.setColumns(10);

		JLabel lblDireccion2 = new JLabel("Ciudad");
		lblDireccion2.setBounds(10, 155, 52, 14);
		panel_1.add(lblDireccion2);

		textDireccion2 = new JTextField();
		textDireccion2.setBounds(107, 152, 143, 20);
		panel_1.add(textDireccion2);
		textDireccion2.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 199, 294, 42);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		// Boton que caputura todos los datos del cliente, crea objeto y agrega
		// a lista de Compania
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente nuevoCliente = null;
				
				// Comprobaciones de Datos ingresados
				if(comprobarIngreso(lblAviso)){
					// Llama metodo para crear Cliente
					nuevoCliente = datosNuevaPersona(datosEmpresa);
					if (nuevoCliente != null) {
						// Si el cliente se crea exitosamente 
						//se escribira
						// cliente en la BD
						try {
							// Creacion de conexion a base de datos
							Database bd = new Database();
							bd.ingresarClienteBD(nuevoCliente);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Cliente no se pudo escribir en la Base de Datos.\n"
									+ "\nDetalles de la excepción:");
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}
	
	//						// Para guardar cliente en un XML
	//						// Objeto XML
	//						XML xml = new XML();
	//						if (xml.ingresarClienteXML(datosEmpresa, nuevoCliente))
	//							System.out.println("Cliente guardado en XML.");
	//						else
	//							System.err.println("Cliente no fue guardado en XML.");
	
						// Muestra mensaje que cilente fue ingresado
						// exitosamente!
						JOptionPane.showMessageDialog(null,
								"Cliente creado con exito!\nProceda en asignarle un contrato", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
						// Se creara� un contrato
						FrameAgregarContrato fContrato = new FrameAgregarContrato(datosEmpresa, nuevoCliente);
						fContrato.setVisible(true);
						dispose();
					} else {
						// Sino, se informa que el cliente ya existe y se vuelve al menu
						lblAviso.setForeground(Color.RED);
						lblAviso.setText("Cliente ya existe!");
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
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(195, 11, 89, 23);
		panel_2.add(btnCancelar);

	}

	// ========================METODOS====================

	/**
	 * Ingresa atributos capturados desde los JTextField de la ventana y los
	 * atribuye a un objeto Cliente que después se asocia a la empresa
	 * @param datosEmpresa - una referencia a la Compania
	 * @return un objeto Cliente del cliente creado
	 **/
	public Cliente datosNuevaPersona(Compania datosEmpresa) {
		String nombre1 = null, nombre2 = null, apellido1 = null, apellido2 = null, rut = null, email = null, direccion1 = null, direccion2 = null;
		int fono1 = 0, fono2 = 0;
				
		if(!textNombre1.getText().isEmpty()) nombre1 = textNombre1.getText();
		if(!textNombre2.getText().isEmpty()) nombre2 = textNombre2.getText();
		if(!textApellido1.getText().isEmpty()) apellido1 = textApellido1.getText();
		if(!textApellido2.getText().isEmpty()) apellido2 = textApellido2.getText();
		if(!textRut.getText().isEmpty()) rut = textRut.getText();
		if(!textEmail.getText().isEmpty()) email = textEmail.getText();
		if(!textFonoCel.getText().isEmpty()) fono1 = Integer.parseInt(textFonoCel.getText());
		if(!textFonoCel.getText().isEmpty()) fono1 = Integer.parseInt(textFonoCel.getText());
		if(!textDireccion1.getText().isEmpty()) direccion1 = textDireccion1.getText();
		if(!textDireccion2.getText().isEmpty()) direccion2 = textDireccion2.getText();
		
		// Se crea cliente nuevo
		Cliente clienteNuevo = new Cliente(rut, datosEmpresa.getRut(), nombre1, nombre2, apellido1, apellido2, fono1,
				fono2, email, 1, direccion1, direccion2, 0, null);
		// Se ingresa cliente nuevo y se espera un resultado del ingreso
		
		if (datosEmpresa.validarCliente(clienteNuevo.getRut()) == false){
			// Si cliente no existe, todo bien
			datosEmpresa.agregarCliente(clienteNuevo);
			return clienteNuevo;
		}
		else
			// Entonces el cliente ya existe
			return null;
	}

	/**
	 * Resetea todos los JText y JLabel ingresados de la ventana
	 * 
	 * @param lblAviso una referencia al cuadro de texto para mensajes de aviso
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
		textDireccion1.setText("");
		textDireccion2.setText("");
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
			tf.setText("");
			tf.setText(new String(resultado,0,j));
			aviso.setForeground(Color.RED);
			aviso.setText(var+" debe ser numerico");
		}
	}
}