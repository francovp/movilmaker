import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class FrameVerClientes extends JFrame {

	private JPanel contentPane;
	private JButton btnVolver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameVerClientes frame = new FrameVerClientes(datosEmpresa);
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
	public FrameVerClientes(Compania datosEmpresa) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Clientes de la compa\u00F1ia", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 22, 551, 320);
		contentPane.add(panel);
		panel.setLayout(null);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 427, 298);
		panel.add(scrollPane);

		JList listClientes = new JList();
		scrollPane.setViewportView(listClientes);

		// MUESTRA DATOS DE CLIENTES EN LA Compañia (rut,nombre1,apellido1,apellido2) , se agrego scroll panel
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel listModel = new DefaultListModel();	//	VARIABLE DEL TIPO LISTA

				if(datosEmpresa.getListaClientes().size()==0){		//	MENSAJE EN EL JList POR SI NO EXISTEN CLIENTES
					listModel.addElement("No existen Clientes");
					listClientes.setModel(listModel);
				}
				for(int i=0; i<datosEmpresa.getListaClientes().size();i++){		// AGREGA 1 A 1 DATOS DE CADA CLIENTE
					listModel.addElement(datosEmpresa.getListaClientes().get(i).getRut()+" "+datosEmpresa.getListaClientes().get(i).getNombre1()+" "+datosEmpresa.getListaClientes().get(i).getApellido1()+" "+datosEmpresa.getListaClientes().get(i).getApellido2());
					listClientes.setModel(listModel);
				}
			}
		});
		btnMostrar.setBounds(452, 10, 89, 23);
		panel.add(btnMostrar);


		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(452, 44, 89, 23);
		panel.add(btnVolver);
	}
}
