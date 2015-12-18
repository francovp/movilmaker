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

import colecciones.Ejecutivo;
import colecciones.Compania;
import interfaz.FrameInterfaz;

import javax.swing.JLabel;

public class FrameVerEjecutivo extends JFrame {

	private JPanel contentPane;
	private JButton btnVolver;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameVerEjecutivo frame = new FrameVerEjecutivo(datosEmpresa);
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
	public FrameVerEjecutivo(Compania datosEmpresa) {
		ArrayList<Ejecutivo> lista = datosEmpresa.getEjecutivos().getLista();
		setTitle("Reporte de Ejecutivos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Ejecutivos de la compa\u00F1ia", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
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

		JList listEjecutivos = new JList();
		listEjecutivos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setViewportView(listEjecutivos);
		
		
/*		//BOTÓN QUE GENERA UN  ARCHIVO EN PDF DE LOS Ejecutivos
		JButton btnImprimirEnPdf = new JButton("Generar PDF");
		btnImprimirEnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {;
				try {
					if (listEjecutivos.isSelectedIndex(listEjecutivos.getSelectedIndex())) {
							Ejecutivo c = listaEjecutivos.get(listEjecutivos.getSelectedIndex());
							c.reporte(datosEmpresa);
							JOptionPane.showMessageDialog(null, 
									"Reporte del Ejecutivo creado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
		
				// MUESTRA DATOS DE Ejecutivos EN LA Compañia (rut,nombre1,apellido1,apellido2) , se agrego scroll panel
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
								dispose();
							}
						});
				btnMostrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DefaultListModel listModel = new DefaultListModel();	// OBJETO DEL TIPO LISTA
						if(lista.size() == 0){		// MENSAJE EN EL JList POR SI NO EXISTEN Ejecutivos
							listModel.addElement("No existen Ejecutivos");
							listEjecutivos.setModel(listModel);
						}
						// AGREGA 1 A 1 DATOS BASICOS DE CADA Ejecutivo
						for(int i=0; i<lista.size();i++){		
							listModel.addElement((i+1)+" - Nombre: " +lista.get(i).getNombre1()+" "
									+lista.get(i).getApellido1()+" "
									+lista.get(i).getApellido2() + " - Rut: " +lista.get(i).getRut());
							
							listEjecutivos.setModel(listModel);	// VUELVE VISIBLE LOS ELEMENTOS
						}
					}
				});
	}
}
