import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.DocumentException;
import javax.swing.JLabel;

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrameVerClientes(Compania datosEmpresa) {
		ArrayList<Cliente> listaClientes = datosEmpresa.obtenerClientes();
		setTitle("Reporte de clientes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Clientes de la compa\u00F1ia", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 22, 584, 320);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 427, 298);
		panel.add(scrollPane);

		JList listClientes = new JList();
		listClientes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(listClientes);

		// MUESTRA DATOS DE CLIENTES EN LA Compañia (rut,nombre1,apellido1,apellido2) , se agrego scroll panel
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel listModel = new DefaultListModel();	// OBJETO DEL TIPO LISTA
				if(listaClientes.size() == 0){		// MENSAJE EN EL JList POR SI NO EXISTEN CLIENTES
					listModel.addElement("No existen Clientes");
					listClientes.setModel(listModel);
				}
				// AGREGA 1 A 1 DATOS BASICOS DE CADA CLIENTE
				for(int i=0; i<listaClientes.size();i++){		
					listModel.addElement((i+1)+" - Rut: "+listaClientes.get(i).getRut()+" - Nombre: "
					+listaClientes.get(i).getNombre1()+" "
					+listaClientes.get(i).getApellido1()+" "
					+listaClientes.get(i).getApellido2());
					listClientes.setModel(listModel);	// VUELVE VISIBLE LOS ELEMENTOS
				}
			}
		});
		
		btnMostrar.setBounds(447, 10, 127, 23);
		panel.add(btnMostrar);
		
		
/*		//BOTÓN QUE GENERA UN  ARCHIVO EN PDF DE LOS CLIENTES
		JButton btnImprimirEnPdf = new JButton("Generar PDF");
		btnImprimirEnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {;
				try {
					if (listClientes.isSelectedIndex(listClientes.getSelectedIndex())) {
							Cliente c = listaClientes.get(listClientes.getSelectedIndex());
							c.reporte(datosEmpresa);
							JOptionPane.showMessageDialog(null, 
									"Reporte del cliente creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnImprimirEnPdf.setBounds(447, 286, 127, 23);
		panel.add(btnImprimirEnPdf);
*/

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(447, 44, 127, 23);
		panel.add(btnVolver);
		
		JLabel lblReporteDetallado = new JLabel("Reporte detallado");
		lblReporteDetallado.setBounds(447, 261, 127, 14);
		panel.add(lblReporteDetallado);
	}
}
