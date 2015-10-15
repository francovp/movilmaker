import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class FrameInterfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInterfaz frame = new FrameInterfaz(datosEmpresa);
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
	public FrameInterfaz(Compania datosEmpresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Interfaz Vomistar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 318, 229);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 2, 0, 0));

		//Boton Agregar Clientes
		JButton btnAgregarClientes = new JButton("Agregar Clientes");
		btnAgregarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameAgregarCliente fAgregaCliente = new FrameAgregarCliente(datosEmpresa);
				fAgregaCliente.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(btnAgregarClientes);

		JButton btnIngresarContratoclientes = new JButton("Ingresar contrato (clientes existentes)");
		btnIngresarContratoclientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameAgregarOtroContrato fOtroContrato = new FrameAgregarOtroContrato(datosEmpresa);
				fOtroContrato.setVisible(true);
				dispose();
			}
		});
		panel.add(btnIngresarContratoclientes);

		JButton btnTerminarContrato = new JButton("Terminar contrato");
		btnTerminarContrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameEliminarContrato fEliminarContrato= new FrameEliminarContrato(datosEmpresa);
				fEliminarContrato.setVisible(true);
				dispose();
			}
		});
		panel.add(btnTerminarContrato);

		JButton btnNewButton = new JButton("Eliminar cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameEliminarCliente fEliminarCliente = new FrameEliminarCliente(datosEmpresa);
				fEliminarCliente.setVisible(true);
				setVisible(false);
			}
		});
		panel.add(btnNewButton);

		JButton btnVerClientesActuales = new JButton("Ver Clientes Actuales");
		btnVerClientesActuales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameVerClientes verClientes = new FrameVerClientes(datosEmpresa);
				verClientes.setVisible(true);
				dispose();
			}
		});
		panel.add(btnVerClientesActuales);

		JButton btnSalir = new JButton("Salir");
		panel.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
}
