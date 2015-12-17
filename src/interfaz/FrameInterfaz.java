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
import interfaz.agregar.FrameAgregarEjecutivo;
import interfaz.agregar.FrameAgregarOtroContrato;
import interfaz.agregar.FrameAgregarPlan;
import interfaz.eliminar.FrameEliminarAdmin;
import interfaz.eliminar.FrameEliminarCliente;
import interfaz.eliminar.FrameEliminarContrato;
import interfaz.eliminar.FrameEliminarEquipo;
import interfaz.eliminar.FrameEliminarPlan;
import interfaz.modificar.FrameModificarAdmin;
import interfaz.modificar.FrameModificarCliente;
import interfaz.modificar.FrameModificarEjecutivo;
import interfaz.modificar.FrameModificarEquipo;
import interfaz.modificar.FrameModificarPlan;
import interfaz.reportar.FrameVerAdmins;
import interfaz.reportar.FrameVerClientes;
import interfaz.reportar.FrameVerEjecutivo;
import interfaz.reportar.FrameVerEquipos;
import interfaz.reportar.FrameVerPlanes;
import javax.swing.JTabbedPane;

public class FrameInterfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datos, int tipo) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameInterfaz frame = new FrameInterfaz(datos, tipo);
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
	public FrameInterfaz(Compania datos, int tipo) {
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
		// Si no hay admin no se puede activar este botón
		if (tipo != 0)
			btnAgregarAdmin.setEnabled(false);

		panelAgregar.setLayout(new GridLayout(0, 1, 0, 0));
		panelAgregar.add(btnAgregarAdmin);
		
		JButton btnAgregarEjecutivo = new JButton("Agregar Ejecutivo");
		btnAgregarEjecutivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarEjecutivo frame = new FrameAgregarEjecutivo(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelAgregar.add(btnAgregarEjecutivo);

		// Si no hay admin no se puede activar este botón
		if (tipo != 0)
			btnAgregarEjecutivo.setEnabled(false);

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
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnAgregarClientes.setEnabled(false);
//		if (tipo != 0)
//			btnAgregarEquipo.setEnabled(false);

		JButton btnAgregarPlan = new JButton("Agregar Plan");
		btnAgregarPlan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarPlan fAgregarPlan = new FrameAgregarPlan(datos);
				fAgregarPlan.setVisible(true);
				// dispose();
			}
		});
		panelAgregar.add(btnAgregarPlan);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnAgregarPlan.setEnabled(false);
		if (tipo != 0)
			btnAgregarPlan.setEnabled(false);

		JButton btnAgregarEquipo = new JButton("Agregar Equipo");
		btnAgregarEquipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelAgregar.add(btnAgregarEquipo);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnAgregarPlan.setEnabled(false);
		if (tipo != 0)
			btnAgregarEquipo.setEnabled(false);

		JButton btnAgregarContratocliente = new JButton("Agregar contrato a cliente");
		panelAgregar.add(btnAgregarContratocliente);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnAgregarContratocliente.setEnabled(false);
//		if (tipo != 0)
//			btnAgregarEquipo.setEnabled(false);
		
		btnAgregarContratocliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameAgregarOtroContrato fOtroContrato = new FrameAgregarOtroContrato(datos);
				fOtroContrato.setVisible(true);
				// dispose();
			}
		});

		JPanel panelVer = new JPanel();
		panelVer.setBounds(209, 89, 189, 326);
		contentPane.add(panelVer);
		panelVer.setLayout(new GridLayout(0, 1, 0, 0));
		
				JButton btnVerAdministradores = new JButton("Ver Administradores");
				btnVerAdministradores.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						FrameVerAdmins verAdmins = new FrameVerAdmins(datos);
						verAdmins.setVisible(true);
						// dispose();
					}
				});
				panelVer.add(btnVerAdministradores);
		
		JButton btnVerEjecutivos = new JButton("Ver Ejecutivos");
		panelVer.add(btnVerEjecutivos);
		btnVerAdministradores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameVerEjecutivo frame = new FrameVerEjecutivo(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
				// Si no hay admin no se puede activar este botón
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
//				if (tipo != 0)
//				btnAgregarEquipo.setEnabled(false);

		JButton btnVerClientesActuales = new JButton("Ver Clientes Actuales");
		panelVer.add(btnVerClientesActuales);
		btnVerClientesActuales.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameVerClientes verClientes = new FrameVerClientes(datos);
				verClientes.setVisible(true);
				// dispose();
			}
		});
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
//		if (tipo != 0)
//		btnAgregarEquipo.setEnabled(false);

		JButton btnVerEquipos = new JButton("Ver Equipos");
		btnVerEquipos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameVerEquipos verEquipos = new FrameVerEquipos(datos);
				verEquipos.setVisible(true);
				// dispose();
			}
		});
		panelVer.add(btnVerEquipos);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
//		if (tipo != 0)
//		btnAgregarEquipo.setEnabled(false);

		JButton btnVerPlanes = new JButton("Ver Planes");
		btnVerPlanes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameVerPlanes frame = new FrameVerPlanes(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelVer.add(btnVerPlanes);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
//		if (tipo != 0)
//		btnAgregarEquipo.setEnabled(false);

		JButton btnSalir = new JButton("Salir del Programa");
		btnSalir.setBounds(408, 439, 189, 54);
		contentPane.add(btnSalir);

		JPanel panelModificar = new JPanel();
		panelModificar.setBounds(408, 89, 189, 326);
		contentPane.add(panelModificar);
		panelModificar.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnModificarCliente = new JButton("Modificar datos de un cliente");
		panelModificar.add(btnModificarCliente);
		btnModificarCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameModificarCliente fActualizarCliente = new FrameModificarCliente(datos);
				fActualizarCliente.setVisible(true);
				// dispose();
			}
		});
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
//		if (tipo != 0)
//		btnAgregarEquipo.setEnabled(false);
		
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
				// Si no hay admin no se puede activar este botón
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
				if (tipo != 0)
					btnModificarAdmin.setEnabled(false);
		
		JButton btnModificarEjecutivo = new JButton("Modificar datos de un Ejecutivo");
		panelModificar.add(btnModificarEjecutivo);
		btnModificarEjecutivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameModificarEjecutivo frame= new FrameModificarEjecutivo(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
		if (tipo != 0)
			btnModificarAdmin.setEnabled(false);
		
		JButton btnModificarUnPlan = new JButton("Modificar un Plan");
		btnModificarUnPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameModificarPlan frame= new FrameModificarPlan(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelModificar.add(btnModificarUnPlan);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
		if (tipo != 0)
			btnModificarUnPlan.setEnabled(false);
		
		JButton btnModificarUnEquipo = new JButton("Modificar un Equipo");
		btnModificarUnEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameModificarEquipo frame= new FrameModificarEquipo(datos);
				frame.setVisible(true);
				// dispose();
			}
		});
		panelModificar.add(btnModificarUnEquipo);
		// Si no hay admin no se puede activar este botón
		// if (falta == 0 || falta == 1)
		// btnVerClientesActuales.setEnabled(false);
		if (tipo != 0)
			btnModificarUnEquipo.setEnabled(false);

		JPanel panelEliminar = new JPanel();
		panelEliminar.setBounds(607, 89, 189, 326);
		contentPane.add(panelEliminar);
		panelEliminar.setLayout(new GridLayout(0, 1, 0, 0));
				
				JButton btnEliminarAdmin = new JButton("Eliminar un Administrador");
				btnEliminarAdmin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrameEliminarAdmin frame= new FrameEliminarAdmin(datos);
						frame.setVisible(true);
						// dispose();
					}
				});
				panelEliminar.add(btnEliminarAdmin);
				
				JButton btnEliminarEjecutivo = new JButton("Eliminar un Ejecutivo");
				panelEliminar.add(btnEliminarEjecutivo);
		
				JButton btnEliminarCliente = new JButton("Eliminar un cliente");
				panelEliminar.add(btnEliminarCliente);
				btnEliminarCliente.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FrameEliminarCliente fEliminarCliente = new FrameEliminarCliente(datos);
						fEliminarCliente.setVisible(true);
						// dispose();
					}
				});
				// Si no hay admin no se puede activar este botón
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
				//if (tipo != 0)
				//	btnAgregarEquipo.setEnabled(false);
				
				JButton btnEliminarPlan = new JButton("Eliminar un Plan");
				btnEliminarPlan.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						FrameEliminarPlan frame = new FrameEliminarPlan(datos);
						frame.setVisible(true);
						// dispose();
					}
				});
				panelEliminar.add(btnEliminarPlan);
				// Si no hay admin no se puede activar este botón
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
				if (tipo != 0)
					btnEliminarPlan.setEnabled(false);
				
				JButton btnEliminarEquipo = new JButton("Eliminar un Equipo");
				panelEliminar.add(btnEliminarEquipo);
				btnEliminarEquipo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FrameEliminarEquipo frame = new FrameEliminarEquipo(datos);
						frame.setVisible(true);
						// dispose();
					}
				});
		
				JButton btnEliminarContrato = new JButton("Terminar contrato");
				panelEliminar.add(btnEliminarContrato);
				btnEliminarContrato.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						FrameEliminarContrato fEliminarContrato = new FrameEliminarContrato(datos);
						fEliminarContrato.setVisible(true);
						// dispose();
					}
				});
				// Si no hay admin no se puede activar este botón
				// if (falta == 0 || falta == 1)
				// btnVerClientesActuales.setEnabled(false);
				//				if (tipo != 0)
				//				btnAgregarEquipo.setEnabled(false);

		// BOT�N QUE GENERA UN ARCHIVO EN PDF DE LOS CLIENTES
		JButton btnGenerarReporte = new JButton("Generar Reporte de la Empresa");
		btnGenerarReporte.setBounds(209, 439, 189, 54);
		contentPane.add(btnGenerarReporte);
		// Si no hay admin no se puede activar este botón
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