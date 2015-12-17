package interfaz;

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

import colecciones.Compania;
import extras.DatabaseConnection;
import interfaz.agregar.FrameAgregarAdmin;
import interfaz.agregar.FrameAgregarCliente;
import interfaz.agregar.FrameAgregarOtroContrato;
import interfaz.agregar.FrameAgregarPlan;
import interfaz.eliminar.FrameEliminarCliente;
import interfaz.eliminar.FrameEliminarContrato;
import interfaz.eliminar.FrameEliminarPlan;
import interfaz.modificar.FrameModificarAdmin;
import interfaz.modificar.FrameModificarCliente;
import interfaz.modificar.FrameModificarEquipo;
import interfaz.modificar.FrameModificarPlan;
import interfaz.reportar.FrameVerAdmins;
import interfaz.reportar.FrameVerClientes;
import interfaz.reportar.FrameVerEquipos;
import interfaz.reportar.FrameVerPlanes;
import javax.swing.JTabbedPane;

public class FrameInterfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
	public FrameInterfaz(Compania datos) {
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
		setBounds(100, 100, 813, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bienvenido a",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creaci�n del logo
		JLabel label = new JLabel("");
		label.setBounds(296, 21, 217, 54);
		contentPane.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("resources\\logo.png"));

		JPanel panelAgregar = new JPanel();
		panelAgregar.setBounds(10, 89, 189, 326);
		contentPane.add(panelAgregar);

		JButton btnAgregarAdmin = new JButton("Agregar Administrador");
		btnAgregarAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarAdmin fAdmin = new FrameAgregarAdmin(datos);
				fAdmin.setVisible(true);
				// dispose();
			}
		});

		panelAgregar.setLayout(new GridLayout(0, 1, 0, 0));
		panelAgregar.add(btnAgregarAdmin);

		// Boton Agregar Clientes
		JButton btnAgregarClientes = new JButton("Agregar Clientes");
		panelAgregar.add(btnAgregarClientes);

		btnAgregarClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameAgregarCliente fAgregaCliente = new FrameAgregarCliente(datos);
				fAgregaCliente.setVisible(true);
				// dispose();
			}
		});

		JButton btnAgregarPlan = new JButton("Agregar Plan");
		btnAgregarPlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarPlan fAgregarPlan = new FrameAgregarPlan(datos);
				fAgregarPlan.setVisible(true);
				// dispose();
			}
		});
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnAgregarPlan.setEnabled(false);
		panelAgregar.add(btnAgregarPlan);

		JButton btnAgregarEquipo = new JButton("Agregar Equipo");
		btnAgregarEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelAgregar.add(btnAgregarEquipo);

		JButton btnAgregarContratocliente = new JButton("Agregar contrato a cliente");
		panelAgregar.add(btnAgregarContratocliente);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnAgregarContratocliente.setEnabled(false);
		btnAgregarContratocliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarOtroContrato fOtroContrato = new FrameAgregarOtroContrato(datos);
				fOtroContrato.setVisible(true);
				// dispose();
			}
		});
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnAgregarClientes.setEnabled(false);

		JPanel panelVer = new JPanel();
		panelVer.setBounds(209, 89, 189, 326);
		contentPane.add(panelVer);
		panelVer.setLayout(new GridLayout(0, 1, 0, 0));
		
				JButton btnVerAdministradores = new JButton("Ver Administradores");
				// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
				btnVerAdministradores.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						FrameVerAdmins verAdmins = new FrameVerAdmins(datos);
						verAdmins.setVisible(true);
						// dispose();
					}
				});
				panelVer.add(btnVerAdministradores);

		JButton btnVerClientesActuales = new JButton("Ver Clientes Actuales");
		panelVer.add(btnVerClientesActuales);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
		btnVerClientesActuales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameVerClientes verClientes = new FrameVerClientes(datos);
				verClientes.setVisible(true);
				// dispose();
			}
		});

		JButton btnVerEquipos = new JButton("Ver Equipos");
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
		btnVerEquipos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameVerEquipos verEquipos = new FrameVerEquipos(datos);
				verEquipos.setVisible(true);
				// dispose();
			}
		});
		panelVer.add(btnVerEquipos);

		JButton btnVerPlanes = new JButton("Ver Planes");
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
		btnVerPlanes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameVerPlanes frame = new FrameVerPlanes(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelVer.add(btnVerPlanes);

		JButton btnSalir = new JButton("Salir del Programa");
		btnSalir.setBounds(408, 439, 189, 54);
		contentPane.add(btnSalir);

		JPanel panelModificar = new JPanel();
		panelModificar.setBounds(408, 89, 189, 326);
		contentPane.add(panelModificar);
		panelModificar.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnModificarCliente = new JButton("Modificar datos de un cliente");
		btnModificarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameModificarCliente fActualizarCliente = new FrameModificarCliente(datos);
				fActualizarCliente.setVisible(true);
				// dispose();
			}
		});
		
				JButton btnModificarAdmin = new JButton("Modificar datos de un Admin");
				btnModificarAdmin.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						FrameModificarAdmin frame= new FrameModificarAdmin(datos);
						frame.setVisible(true);
						// dispose();
					}
				});
				panelModificar.add(btnModificarAdmin);
		panelModificar.add(btnModificarCliente);
		
		JButton btnModificarUnPlan = new JButton("Modificar un Plan");
		btnModificarUnPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameModificarPlan frame= new FrameModificarPlan(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelModificar.add(btnModificarUnPlan);
		
		JButton btnModificarUnEquipo = new JButton("Modificar un Equipo");
		btnModificarUnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameModificarEquipo frame= new FrameModificarEquipo(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelModificar.add(btnModificarUnEquipo);

		JPanel panelEliminar = new JPanel();
		panelEliminar.setBounds(607, 89, 189, 326);
		contentPane.add(panelEliminar);
		panelEliminar.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton btnEliminarAdmin = new JButton("Eliminar un Administrador");
				panelEliminar.add(btnEliminarAdmin);
		
				JButton btnEliminarCliente = new JButton("Eliminar un cliente");
				panelEliminar.add(btnEliminarCliente);
				// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
				btnEliminarCliente.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FrameEliminarCliente fEliminarCliente = new FrameEliminarCliente(datos);
						fEliminarCliente.setVisible(true);
						// dispose();
					}
				});
				
				JButton btnEliminarPlan = new JButton("Eliminar un Plan");
				btnEliminarPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrameEliminarPlan frame = new FrameEliminarPlan(datos);
						frame.setVisible(true);
						// dispose();
					}
				});
				panelEliminar.add(btnEliminarPlan);
				
				JButton btnEliminarEquipo = new JButton("Eliminar un Equipo");
				panelEliminar.add(btnEliminarEquipo);
		
				JButton btnEliminarContrato = new JButton("Terminar contrato");
				panelEliminar.add(btnEliminarContrato);
				// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
				// if (falta == 0 || falta == 1)
				// btnTerminarContrato.setEnabled(false);
				btnEliminarContrato.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FrameEliminarContrato fEliminarContrato = new FrameEliminarContrato(datos);
						fEliminarContrato.setVisible(true);
						// dispose();
					}
				});
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnNewButton.setEnabled(false);

		// BOT�N QUE GENERA UN ARCHIVO EN PDF DE LOS CLIENTES
		JButton btnGenerarReporte = new JButton("Generar Reporte de la Empresa");
		btnGenerarReporte.setBounds(209, 439, 189, 54);
		contentPane.add(btnGenerarReporte);
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnGenerarReporte.setEnabled(false);
		btnGenerarReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// BOT�N QUE GENERA UN ARCHIVO EN PDF DE LOS CLIENTES
				try {
					datos.reporte();
					JOptionPane.showMessageDialog(null, "Reporte empresa creado", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException | DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Si no hay admin (Falta = 0 o 1) no se puede activar este bot�n
		// if (falta == 0 || falta == 1)
		// btnActualizarDatosDe.setEnabled(false);

		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarTodo();
			}
		});
	}

	private void cerrarTodo() {
		dispose();
		DatabaseConnection.cerrarConnection();
		System.exit(0);
	}
}