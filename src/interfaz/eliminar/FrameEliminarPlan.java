package interfaz.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import interfaz.FrameInterfaz;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameEliminarPlan extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPlan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameEliminarPlan frame = new FrameEliminarPlan(datosEmpresa);
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
	public FrameEliminarPlan(Compania datosEmpresa) {
		setTitle("Eliminar Plan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Buscar Plan", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelPlan = new JLabel("Nombre Plan");
		lblNombreDelPlan.setBounds(10, 23, 84, 14);
		contentPane.add(lblNombreDelPlan);
		
		textFieldPlan = new JTextField();
		textFieldPlan.setBounds(104, 20, 136, 20);
		contentPane.add(textFieldPlan);
		textFieldPlan.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 56, 429, 142);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datosEmpresa.getPlanes().EliminarPlan(textFieldPlan.getText());
				JOptionPane.showMessageDialog(null, "Equipo modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnEliminar.setBounds(330, 11, 89, 23);
		panel.add(btnEliminar);
		
		JLabel lblDatosDelPlan = new JLabel("");
		lblDatosDelPlan.setBounds(10, 11, 200, 14);
		panel.add(lblDatosDelPlan);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (datosEmpresa.getPlanes().buscarPlan(textFieldPlan.getText())!=null){
			// PLAN EXISTE
			
			textFieldPlan.setEnabled(false);
			btnEliminar.setEnabled(true);
			lblDatosDelPlan.setBackground(Color.blue);
			lblDatosDelPlan.setText("Se ha encontrado el plan");
			
				}else {
					lblDatosDelPlan.setBackground(Color.RED);
					lblDatosDelPlan.setText("El plan no existe");
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
