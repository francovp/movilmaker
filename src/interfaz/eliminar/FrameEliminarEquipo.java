package interfaz.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import colecciones.Equipo;
import extras.Database;
import interfaz.FrameInterfaz;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

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
		setBounds(100, 100, 465, 269);
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
		
		JLabel lblDatosDelEquipo = new JLabel("");
		lblDatosDelEquipo.setBounds(10, 48, 200, 14);
		contentPane.add(lblDatosDelEquipo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 63, 429, 165);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JList listInfoEquipo = new JList();
		listInfoEquipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listInfoEquipo.setBounds(10, 11, 192, 146);
		panel.add(listInfoEquipo);

		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Equipo eq = datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText());
				
				datosEmpresa.getEquipos().eliminar(textFieldEquipo.getText());
					
					try {
						// Creacion de conexion a base de datos
						Database.eliminarEquipoBD(eq.getIdEquipo());
						// Cuadro de dialogo, que informa eliminacion exitosa
						JOptionPane.showMessageDialog(null, "El Cliente ha sido eliminado", "Aviso",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.err.println(
								"Equipo no se pudo eliminar de la Base de Datos.\n" + "\nDetalles de la excepciï¿½n:");
						System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
					}
					
					JOptionPane.showMessageDialog(null, "Equipo Eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
					dispose();
			}
		});
		btnEliminar.setBounds(330, 11, 89, 23);
		panel.add(btnEliminar);
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
			
			if (datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText())!=null){
			// PLAN EXISTE
			
			textFieldEquipo.setEnabled(false);
			btnEliminar.setEnabled(true);
			lblDatosDelEquipo.setBackground(Color.blue);
			lblDatosDelEquipo.setText("Se ha encontrado el equipo");
			
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
					lblDatosDelEquipo.setBackground(Color.RED);
					lblDatosDelEquipo.setText("El equipo no existe");
					}
			
			}
			
			
		});
		btnBuscar.setBounds(250, 19, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(350, 19, 89, 23);
		contentPane.add(btnCancelar);
	
	}
}
