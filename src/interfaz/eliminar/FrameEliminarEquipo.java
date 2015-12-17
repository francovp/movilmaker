package interfaz.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import colecciones.Equipo;
import interfaz.FrameInterfaz;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JList;

public class FrameEliminarEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEquipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEliminarEquipo frame = new FrameEliminarEquipo(datosEmpresa);
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
	public FrameEliminarEquipo(Compania datosEmpresa) {
		setTitle("Eliminar Equipo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelPlan = new JLabel("Nombre Equipo");
		lblNombreDelPlan.setBounds(10, 23, 84, 14);
		contentPane.add(lblNombreDelPlan);
		
		textFieldEquipo = new JTextField();
		textFieldEquipo.setBounds(104, 20, 136, 20);
		contentPane.add(textFieldEquipo);
		textFieldEquipo.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 71, 429, 168);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datosEmpresa.getEquipos().eliminar(textFieldEquipo.getText());
				JOptionPane.showMessageDialog(null, "Equipo Eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnEliminar.setBounds(330, 132, 89, 23);
		panel.add(btnEliminar);
		
		JList listInfoEquipo = new JList();
		listInfoEquipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listInfoEquipo.setBounds(10, 11, 192, 146);
		panel.add(listInfoEquipo);
		
		JLabel lblDatosDelEquipo = new JLabel("");
		lblDatosDelEquipo.setBounds(10, 48, 200, 14);
		contentPane.add(lblDatosDelEquipo);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
			
			if (datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText())!=null){
			// PLAN EXISTE
				
				textFieldEquipo.setEnabled(false);
				btnBuscar.setEnabled(false);
				btnEliminar.setEnabled(true);
				lblDatosDelEquipo.setForeground(Color.blue);
				lblDatosDelEquipo.setText("Se ha encontrado el equipo");
				
				// LISTA INFORMACIÓN DEL PLAN A ELIMINAR
				DefaultListModel listmodel = new DefaultListModel();
				Equipo eq = datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText());
					
					listmodel.addElement("-ID  "+eq.getIdEquipo());
					listmodel.addElement("-Nombre  "+eq.getNombre());
					listmodel.addElement("-Procesador  "+eq.getProcesador());
					listmodel.addElement("-Pantalla  "+eq.getPantalla());
					listmodel.addElement("-Camara  "+eq.getCamara());
					listmodel.addElement("-s.o  "+eq.getSo());
					listmodel.addElement("-Prepago  "+eq.getValorPrepago());
					
					listInfoEquipo.setModel(listmodel);
			
				}else {
					lblDatosDelEquipo.setForeground(Color.RED);
					lblDatosDelEquipo.setText("El equipo no existe");
					}
			
			}
			
			
		});
		btnBuscar.setBounds(250, 19, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(350, 19, 89, 23);
		contentPane.add(btnCancelar);
		
	
	
	}
}
