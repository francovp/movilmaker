import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.DocumentException;

public class FrameInterfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa, int falta) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameInterfaz frame = new FrameInterfaz(datosEmpresa, falta);
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
	public FrameInterfaz(Compania datosEmpresa, int falta) {
		/* Use an appropriate Look and Feel */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setResizable(false);
		setTitle("MovilMaker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bienvenido a",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creación del logo
		JLabel label = new JLabel("");
		label.setBounds(92, 24, 217, 54);
		contentPane.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("resources\\logo.png"));

		JPanel panel = new JPanel();
		panel.setBounds(10, 89, 189, 244);
		contentPane.add(panel);

		JButton btnAgregarAdmin = new JButton("Agregar Administrador");
		btnAgregarAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarAdmin fAdmin = new FrameAgregarAdmin(datosEmpresa, falta);
				fAdmin.setVisible(true);
				dispose();
			}
		});

		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(btnAgregarAdmin);

		// Boton Agregar Clientes
		JButton btnAgregarClientes = new JButton("Agregar Clientes");
		panel.add(btnAgregarClientes);

		btnAgregarClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameAgregarCliente fAgregaCliente = new FrameAgregarCliente(datosEmpresa);
				fAgregaCliente.setVisible(true);
				dispose();
			}
		});

		JButton btnIngresarContratoclientes = new JButton("Ingresar contrato a cliente");
		panel.add(btnIngresarContratoclientes);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnIngresarContratoclientes.setEnabled(false);
		btnIngresarContratoclientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarOtroContrato fOtroContrato = new FrameAgregarOtroContrato(datosEmpresa);
				fOtroContrato.setVisible(true);
				dispose();
			}
		});
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnAgregarClientes.setEnabled(false);

		JButton btnActualizarDatosDe = new JButton("Actualizar datos de cliente");
		panel.add(btnActualizarDatosDe);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnActualizarDatosDe.setEnabled(false);
		btnActualizarDatosDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameActualizarCliente fActualizarCliente = new FrameActualizarCliente(datosEmpresa);
				fActualizarCliente.setVisible(true);
				dispose();
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(209, 89, 189, 244);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnVerClientesActuales = new JButton("Ver Clientes Actuales");
		panel_1.add(btnVerClientesActuales);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnVerClientesActuales.setEnabled(false);
		btnVerClientesActuales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameVerClientes verClientes = new FrameVerClientes(datosEmpresa);
				verClientes.setVisible(true);
				dispose();
			}
		});

		JButton btnNewButton = new JButton("Eliminar cliente");
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameEliminarCliente fEliminarCliente = new FrameEliminarCliente(datosEmpresa);
				fEliminarCliente.setVisible(true);
				dispose();
			}
		});

		JButton btnTerminarContrato = new JButton("Terminar contrato");
		panel_1.add(btnTerminarContrato);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnTerminarContrato.setEnabled(false);
		btnTerminarContrato.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameEliminarContrato fEliminarContrato = new FrameEliminarContrato(datosEmpresa);
				fEliminarContrato.setVisible(true);
				dispose();
			}
		});
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnNewButton.setEnabled(false);

		//BOTÓN QUE GENERA UN  ARCHIVO EN PDF DE LOS CLIENTES
		JButton btnGenerarReporte = new JButton("Generar Reporte PDF");
		panel_1.add(btnGenerarReporte);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este botón
		if (falta == 0 || falta == 1)
			btnGenerarReporte.setEnabled(false);
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BOTÓN QUE GENERA UN  ARCHIVO EN PDF DE LOS CLIENTES
				try {
					datosEmpresa.reporte();
					JOptionPane.showMessageDialog(null, "Reporte empresa creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(111, 344, 189, 61);
		contentPane.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});

	}
}
