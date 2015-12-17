package interfaz.reportar;
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

import colecciones.Equipo;
import colecciones.Plan;
import colecciones.SuperColeccion;
import colecciones.Compania;
import interfaz.FrameInterfaz;

import javax.swing.JLabel;
import javax.swing.JTextPane;

public class FrameVerPlanes extends JFrame {

	private JPanel contentPane;
	private JButton btnVolver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameVerPlanes frame = new FrameVerPlanes(datosEmpresa);
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
	public FrameVerPlanes(Compania datosEmpresa) {
		setTitle("Reporte de Planes");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Planes de la compa\u00F1ia", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 22, 443, 320);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 427, 298);
		panel.add(scrollPane);

		JList<String> listPlanes = new JList<String>();
		listPlanes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(listPlanes);
		
		
/*		//BOTÓN QUE GENERA UN  ARCHIVO EN PDF DE LOS Planes
		JButton btnImprimirEnPdf = new JButton("Generar PDF");
		btnImprimirEnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {;
				try {
					if (listPlanes.isSelectedIndex(listPlanes.getSelectedIndex())) {
							Equipo c = listaPlanes.get(listPlanes.getSelectedIndex());
							c.reporte(datosEmpresa);
							JOptionPane.showMessageDialog(null, 
									"Reporte del Equipo creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
		
				// MUESTRA DATOS DE Planes EN LA Compañia (rut,nombre1,apellido1,apellido2) , se agrego scroll panel
				JButton btnMostrar = new JButton("Mostrar");
				btnMostrar.setBounds(10, 352, 127, 23);
				contentPane.add(btnMostrar);
				
						btnVolver = new JButton("Volver");
						btnVolver.setBounds(147, 352, 127, 23);
						contentPane.add(btnVolver);
						
						JLabel lblReporteDetallado = new JLabel("Reporte detallado");
						lblReporteDetallado.setBounds(10, 386, 127, 14);
						contentPane.add(lblReporteDetallado);
						
						btnVolver.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
								fInterfaz.setVisible(true);
								dispose();
							}
						});
				btnMostrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultListModel<String> listModel = new DefaultListModel<String>();	// OBJETO DEL TIPO LISTA
						if(datosEmpresa.getPlanes().getLista().count() == 0){		// MENSAJE EN EL JList POR SI NO EXISTEN Planes
							listModel.addElement("No existen Planes");
							listPlanes.setModel(listModel);
						}
						
						listModel = datosEmpresa.getPlanes().listarAInterfazVer(listModel);
						
						listPlanes.setModel(listModel);	// VUELVE VISIBLE LOS ELEMENTOS
					}
				});
	}
}
