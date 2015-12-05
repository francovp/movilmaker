import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FrameEliminarContrato extends JFrame {

	private JPanel contentPane;
	private JTextField textRut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FrameEliminarContrato frame = new FrameEliminarContrato(datosEmpresa);
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
	public FrameEliminarContrato(Compania datosEmpresa) {
		setResizable(false);
		setTitle("Eliminar Contrato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, null, TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIngreseRutDe = new JLabel("Ingrese rut de cliente");
		lblIngreseRutDe.setBounds(10, 11, 126, 14);
		contentPane.add(lblIngreseRutDe);

		textRut = new JTextField();
		textRut.setBounds(10, 36, 111, 20);
		contentPane.add(textRut);
		textRut.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(141, 11, 214, 194);
		contentPane.add(panel);
		panel.setLayout(null);

		JList list = new JList();
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBounds(10, 26, 194, 157);
		panel.add(list);

		JLabel lblContratos = new JLabel("Contratos");
		lblContratos.setBounds(10, 11, 69, 14);
		panel.add(lblContratos);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(275, 216, 80, 23);
		contentPane.add(btnCancelar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Contrato contratoAEliminar = null;

				// SI EL ID DEL CONTRATO ESTA SELECCIONADO EN LA LISTA, SE
				// PROCEDE A ELIMINAR CONTRATO
				if (list.isSelectedIndex(list.getSelectedIndex())) {
					Cliente c = datosEmpresa.buscarCliente(textRut.getText());
					if (c.getContratos().getContratos().remove(list.getSelectedIndex()) != null) {
						// CONTRATO ELIMINADO
						JOptionPane.showMessageDialog(null, "Contrato eliminado con exito!", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);

						// Se eliminará contrato en la BD
						try {
							// Creacion de conexion a base de datos
							Database bd = new Database();
							bd.eliminarContratoBD(c.getRut(),list.getSelectedIndex());
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							System.err.println("Contrato no se pudo eliminar de la Base de Datos.\n"
									+ "\nDetalles de la excepción:");
							System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
						}
					} else {
						System.err.println("No se eliminó contrato");
						JOptionPane.showMessageDialog(null, "No se eliminó contrato!", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				btnCancelar.setText("Volver");
			}
		});

		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(185, 216, 80, 23);
		contentPane.add(btnEliminar);

		JLabel lblAviso = new JLabel("");
		lblAviso.setBounds(10, 216, 156, 19);
		contentPane.add(lblAviso);

		// BUSCA CONTRATOS DE X CLIENTE Y LO MUESTRA EN LISTA
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel listContratos = new DefaultListModel();

				Cliente c = datosEmpresa.buscarCliente(textRut.getText());
				if (c != null) {
					textRut.setEnabled(false);
					if (c.getContratos().getContratos().size() == 0) {
						lblAviso.setForeground(Color.RED);
						lblAviso.setText("Cliente no tiene contratos");
						btnCancelar.setText("Volver");
					} else {
						lblAviso.setForeground(Color.BLUE);
						lblAviso.setText("Cliente encontrado!");
						btnCancelar.setText("Volver");
						for (int i = 0; i < c.getContratos().getContratos().size(); i++) {
							listContratos.addElement(c.getContratos().getContratos().get(i).getIdContrato()); // AGREGA
																								// ELEMENTO
																								// A
																								// LA
																								// LISTA
							list.setModel(listContratos); // APARECE ELEMENTO EN
															// LA LISTA
						}
						btnEliminar.setEnabled(true);
					}
				} else {
					lblAviso.setForeground(Color.RED);
					lblAviso.setText("Cliente no existe!");
					btnCancelar.setText("Volver");
				}
			}
		});
		btnBuscar.setBounds(10, 67, 89, 23);
		contentPane.add(btnBuscar);
	}

}
