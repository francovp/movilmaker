import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class FrameEliminarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textRut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEliminarCliente frame = new FrameEliminarCliente(datosEmpresa);
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
	public FrameEliminarCliente(Compania datosEmpresa) {
		setTitle("Eliminar cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Eliminaci\u00F3n de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 376, 75);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblIngreseRutDel = new JLabel("Ingrese rut del cliente a eliminar");
		lblIngreseRutDel.setBounds(10, 11, 175, 14);
		panel.add(lblIngreseRutDel);

		textRut = new JTextField();
		textRut.setBounds(10, 36, 175, 20);
		panel.add(textRut);
		textRut.setColumns(10);

		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rut;
				rut=textRut.getText();

				//Llama a metodo en clase Compañia para eliminar Cliente
				datosEmpresa.interfazEliminarCliente(rut);
			}
		});

		btnNewButton_1.setBounds(248, 21, 118, 37);
		panel.add(btnNewButton_1);

		JLabel lblEliminarDeCompaia = new JLabel("Eliminar de compa\u00F1ia");
		lblEliminarDeCompaia.setBounds(248, 0, 118, 14);
		panel.add(lblEliminarDeCompaia);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 107, 224, 143);
		contentPane.add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JButton btnNewButton = new JButton("Ver Clientes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel listmodel = new DefaultListModel();
				for(int i=0; i<datosEmpresa.getListaClientes().size();i++){
				listmodel.addElement("rut: "+datosEmpresa.getListaClientes().get(i).getRut());
				list.setModel(listmodel);
				}
			}
		});
		btnNewButton.setBounds(258, 132, 126, 23);
		contentPane.add(btnNewButton);

		JLabel lblClientesIngresadosEn = new JLabel("LIsta clientes");
		lblClientesIngresadosEn.setBounds(260, 107, 136, 14);
		contentPane.add(lblClientesIngresadosEn);

		JButton btnNewButton_2 = new JButton("Volver");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz (datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(258, 227, 128, 23);
		contentPane.add(btnNewButton_2);

	}
}
