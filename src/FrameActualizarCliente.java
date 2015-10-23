import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;

public class FrameActualizarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRut;
	private JTextField textFieldMovil;
	private JTextField textFieldFono;
	private JTextField textFieldDireccion;
	private JTextField textFieldEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameActualizarCliente frame = new FrameActualizarCliente(datosEmpresa);
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
	public FrameActualizarCliente(Compania datosEmpresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Actualizar datos cliente");
		setResizable(false);
		setBounds(100, 100, 424, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Actualizaci\u00F3n de datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 17, 395, 263);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRut = new JLabel("Rut");
		lblRut.setBounds(10, 11, 46, 14);
		panel.add(lblRut);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(66, 8, 122, 20);
		panel.add(textFieldRut);
		textFieldRut.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 36, 375, 216);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblInfoCliente = new JLabel("");
		lblInfoCliente.setForeground(Color.BLUE);
		lblInfoCliente.setBounds(109, 11, 256, 14);
		panel_1.add(lblInfoCliente);
		
		JLabel lblTelefonoMovil = new JLabel("Movil");
		lblTelefonoMovil.setBounds(20, 39, 79, 14);
		panel_1.add(lblTelefonoMovil);
		
		JLabel lblTelofono = new JLabel("Telefono");
		lblTelofono.setBounds(20, 64, 79, 14);
		panel_1.add(lblTelofono);
		
		JLabel lblNewLabel = new JLabel("Direccion");
		lblNewLabel.setBounds(20, 89, 79, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(20, 150, 79, 14);
		panel_1.add(lblEmail);
		
		textFieldMovil = new JTextField();
		textFieldMovil.setEditable(false);
		textFieldMovil.setBounds(109, 36, 256, 20);
		panel_1.add(textFieldMovil);
		textFieldMovil.setColumns(10);
		
		textFieldFono = new JTextField();
		textFieldFono.setEditable(false);
		textFieldFono.setBounds(109, 61, 256, 17);
		panel_1.add(textFieldFono);
		textFieldFono.setColumns(10);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setEditable(false);
		textFieldDireccion.setBounds(109, 86, 256, 50);
		panel_1.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(109, 147, 256, 17);
		panel_1.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// ASIGNA NUEVOS VALORES AL CLIENTE SELECCIONADO
				Cliente c = datosEmpresa.buscarCliente(textFieldRut.getText());
				c.setFonoCel(Integer.parseInt(textFieldMovil.getText()));
				c.setFonoFijo(Integer.parseInt(textFieldFono.getText()));
				c.setDireccion1(textFieldDireccion.getText());
				c.setEmail(textFieldEmail.getText());
				JOptionPane.showMessageDialog(null, "Cliente modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(276, 182, 89, 23);
		panel_1.add(btnModificar);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(20, 11, 46, 14);
		panel_1.add(lblCliente);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (datosEmpresa.buscarCliente(textFieldRut.getText())!=null){	// SI CLIENTE EXISTE
					
					Cliente c = datosEmpresa.buscarCliente(textFieldRut.getText());
					
					// CONVIERTE EN EDITABLES LOS JTextField Movil, Fono, Direccion, Email
					// textFieldRut COMO NO EDITABLE
					textFieldMovil.setEditable(true);
					textFieldFono.setEditable(true);
					textFieldDireccion.setEditable(true);
					textFieldEmail.setEditable(true);
					btnModificar.setEnabled(true);
					textFieldRut.setEditable(false);
					
					// CARGA A lblInfoCliente LA IDENTIFICACION DEL CLIENTE (NOMBRE)
					lblInfoCliente.setForeground(Color.BLUE);
					lblInfoCliente.setText(c.getNombre1()+" "+c.getNombre2()+" "+c.getApellido1()+" "+c.getApellido2());
					// CARGA A SUS RESPECTIVOS JTextField información previa
					textFieldMovil.setText(Integer.toString(c.getFonoCel()));	//convierto a string debido a que FonoCel = int
					textFieldFono.setText(Integer.toString(c.getFonoFijo()));	//convierto a string debido a que FOnoFijo = int
					textFieldDireccion.setText(c.getDireccion1());
					textFieldEmail.setText(c.getEmail());
					
				}
				else {	// SI CLIENTE NO EXISTE
					lblInfoCliente.setForeground(Color.RED);
					lblInfoCliente.setText("Cliente no existe");
				}
			}
		});
		btnBuscar.setBounds(198, 7, 89, 23);
		panel.add(btnBuscar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(296, 7, 89, 23);
		panel.add(btnCancelar);
	}
}
