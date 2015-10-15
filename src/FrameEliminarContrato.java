import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrameEliminarContrato extends JFrame {

	private JPanel contentPane;
	private JTextField textRut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
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
		setTitle("Eliminar Contrato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIngreseRutDe = new JLabel("Ingrese rut de cliente");
		lblIngreseRutDe.setBounds(10, 26, 126, 14);
		contentPane.add(lblIngreseRutDe);

		textRut = new JTextField();
		textRut.setBounds(10, 51, 111, 20);
		contentPane.add(textRut);
		textRut.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(141, 11, 283, 194);
		contentPane.add(panel);
		panel.setLayout(null);

		JList list = new JList();
		list.setBounds(10, 26, 263, 157);
		panel.add(list);

		JLabel lblContratos = new JLabel("Contratos");
		lblContratos.setBounds(10, 11, 69, 14);
		panel.add(lblContratos);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente c = datosEmpresa.buscarCliente(textRut.getText());
				for (int i=0;i<c.getContratos().size();i++)
					c.getContratos().remove(list.getSelectedIndex());	//CONTRATO ELIMINADO
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(240, 216, 89, 23);
		contentPane.add(btnEliminar);


		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(10, 116, 89, 23);
		contentPane.add(btnVolver);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelar.setEnabled(false);
				btnEliminar.setEnabled(false);
				btnVolver.setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(335, 216, 89, 23);
		contentPane.add(btnCancelar);

		// BUSCA CONTRATOS DE X CLIENTE Y LO MUESTRA EN LISTA
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel listContratos = new DefaultListModel();
				Cliente c = datosEmpresa.buscarCliente(textRut.getText());
				for (int i=0;i<c.getContratos().size();i++){
					listContratos.addElement(c.getContratos().get(i).getIdContrato());	//AGREGA ELEMENTO A LA LISTA
					list.setModel(listContratos);	//APARECE ELEMENTO EN LA LISTA
				}
				btnEliminar.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnBuscar.setEnabled(false);
				btnVolver.setEnabled(false);

			}
		});
		btnBuscar.setBounds(10, 82, 89, 23);
		contentPane.add(btnBuscar);
	}

}
