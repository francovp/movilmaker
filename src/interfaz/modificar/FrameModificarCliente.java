package interfaz.modificar;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import colecciones.Cliente;
import colecciones.Compania;
import interfaz.FrameInterfaz;

public class FrameModificarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRut;
	private JTextField textFieldMovil;
	private JTextField textFieldFono;
	private JTextField textFieldDireccion;
	private JTextField textFieldNombre;
	private JTextField textFieldNombre2;
	private JTextField textFieldApellido1;
	private JTextField textFieldApellido2;
	private JTextField textFieldCiudad;
	private JTextField textFieldEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameModificarCliente frame = new FrameModificarCliente(datosEmpresa);
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
	public FrameModificarCliente(Compania datosEmpresa) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Actualizar datos cliente");
		setResizable(false);
		setBounds(100, 100, 621, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Actualizaci\u00F3n de datos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 603, 278);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRut = new JLabel("Rut");
		lblRut.setBounds(10, 10, 46, 14);
		panel.add(lblRut);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(315, 43, 279, 170);
		panel.add(panel_1);
		panel_1.setBorder(
				new TitledBorder(null, "Datos de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);

		JLabel lblTelefonoMovil = new JLabel("Movil");
		lblTelefonoMovil.setBounds(20, 52, 79, 14);
		panel_1.add(lblTelefonoMovil);

		JLabel lblTelofono = new JLabel("Telefono");
		lblTelofono.setBounds(20, 80, 79, 14);
		panel_1.add(lblTelofono);

		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(20, 108, 79, 14);
		panel_1.add(lblDireccion);

		textFieldMovil = new JTextField();
		textFieldMovil.setEditable(false);
		textFieldMovil.setBounds(109, 49, 152, 20);
		panel_1.add(textFieldMovil);
		textFieldMovil.setColumns(10);

		textFieldFono = new JTextField();
		textFieldFono.setEditable(false);
		textFieldFono.setBounds(109, 77, 152, 20);
		panel_1.add(textFieldFono);
		textFieldFono.setColumns(10);

		textFieldDireccion = new JTextField();
		textFieldDireccion.setEditable(false);
		textFieldDireccion.setBounds(109, 105, 152, 20);
		panel_1.add(textFieldDireccion);
		textFieldDireccion.setColumns(10);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(20, 136, 52, 14);
		panel_1.add(lblCiudad);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		textFieldCiudad.setBounds(109, 133, 152, 20);
		panel_1.add(textFieldCiudad);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(20, 21, 79, 14);
		panel_1.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(109, 18, 152, 20);
		panel_1.add(textFieldEmail);

		textFieldRut = new JTextField();
		textFieldRut.setBounds(66, 6, 122, 23);
		panel.add(textFieldRut);
		textFieldRut.setColumns(10);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(487, 224, 107, 23);
		panel.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ASIGNA NUEVOS VALORES AL CLIENTE SELECCIONADO
				Cliente c = datosEmpresa.getClientes().buscarCliente(textFieldRut.getText());

				c.setNombre1(textFieldNombre.getText());
				c.setNombre2(textFieldNombre2.getText());
				c.setApellido1(textFieldApellido1.getText());
				c.setApellido2(textFieldApellido2.getText());
				c.setFonoCel(Integer.parseInt(textFieldMovil.getText()));
				c.setFonoFijo(Integer.parseInt(textFieldFono.getText()));
				c.setDireccion1(textFieldDireccion.getText());
				c.setDireccion2(textFieldCiudad.getText());
				c.setEmail(textFieldEmail.getText());
				JOptionPane.showMessageDialog(null, "Cliente modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});

		btnModificar.setEnabled(false);

		JLabel lblInfoCliente = new JLabel("");
		lblInfoCliente.setBounds(10, 224, 256, 14);
		panel.add(lblInfoCliente);
		lblInfoCliente.setForeground(Color.BLUE);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(198, 6, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (datosEmpresa.getClientes().buscarCliente(textFieldRut.getText()) != null) { 
					// SI CLIENTE EXISTE
					Cliente c = datosEmpresa.getClientes().buscarCliente(textFieldRut.getText());

					// CONVIERTE EN EDITABLES LOS JTextField Movil, Fono,
					// Direccion, Email y textFieldRut COMO NO EDITABLE
					textFieldNombre.setEditable(true);
					textFieldNombre2.setEditable(true);
					textFieldApellido1.setEditable(true);
					textFieldApellido2.setEditable(true);
					textFieldEmail.setEditable(true);
					textFieldMovil.setEditable(true);
					textFieldFono.setEditable(true);
					textFieldDireccion.setEditable(true);
					textFieldCiudad.setEditable(true);
					btnModificar.setEnabled(true);
					textFieldRut.setEditable(false);

					// CARGA A SUS RESPECTIVOS JTextField información previa

					textFieldNombre.setText(c.getNombre1());
					textFieldNombre2.setText(c.getNombre2());
					textFieldApellido1.setText(c.getApellido1());
					textFieldApellido2.setText(c.getApellido2());
					textFieldMovil.setText(Integer.toString(c.getFonoCel())); 
					textFieldFono.setText(Integer.toString(c.getFonoFijo())); 
					textFieldDireccion.setText(c.getDireccion1());
					textFieldEmail.setText(c.getEmail());
					textFieldCiudad.setText(c.getDireccion2());
				} else { // SI CLIENTE NO EXISTE
					lblInfoCliente.setForeground(Color.RED);
					lblInfoCliente.setText("Cliente no existe");
				}
			}
		});
		panel.add(btnBuscar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(297, 6, 86, 23);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancelar);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 43, 295, 170);
		panel_2.setLayout(null);
		panel_2.setBorder(
				new TitledBorder(null, "Atributos personales", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_2);

		JLabel label = new JLabel("Nombre");
		label.setBounds(10, 24, 97, 14);
		panel_2.add(label);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(117, 21, 153, 20);
		panel_2.add(textFieldNombre);

		JLabel label_1 = new JLabel("Segundo Nombre");
		label_1.setBounds(10, 52, 106, 14);
		panel_2.add(label_1);

		textFieldNombre2 = new JTextField();
		textFieldNombre2.setEditable(false);
		textFieldNombre2.setColumns(10);
		textFieldNombre2.setBounds(117, 49, 153, 20);
		panel_2.add(textFieldNombre2);

		JLabel label_2 = new JLabel("Apellido Paterno");
		label_2.setBounds(10, 80, 97, 14);
		panel_2.add(label_2);

		textFieldApellido1 = new JTextField();
		textFieldApellido1.setEditable(false);
		textFieldApellido1.setColumns(10);
		textFieldApellido1.setBounds(117, 77, 153, 20);
		panel_2.add(textFieldApellido1);

		JLabel label_3 = new JLabel("Apellido Materno");
		label_3.setBounds(10, 108, 97, 14);
		panel_2.add(label_3);

		textFieldApellido2 = new JTextField();
		textFieldApellido2.setEditable(false);
		textFieldApellido2.setColumns(10);
		textFieldApellido2.setBounds(117, 105, 153, 20);
		panel_2.add(textFieldApellido2);

	}
}
