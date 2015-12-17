package interfaz.eliminar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import colecciones.Plan;
import interfaz.FrameInterfaz;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 465, 265);
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
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 64, 429, 151);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datosEmpresa.getPlanes().eliminar(textFieldPlan.getText());
				JOptionPane.showMessageDialog(null, "Plan Eliminado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnEliminar.setBounds(330, 117, 89, 23);
		panel.add(btnEliminar);
		
		JList listPlanInfo = new JList();
		listPlanInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n del Plan", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		listPlanInfo.setBounds(20, 11, 190, 129);
		panel.add(listPlanInfo);
		
		JLabel lblDatosDelPlan = new JLabel("");
		lblDatosDelPlan.setBounds(10, 48, 200, 14);
		contentPane.add(lblDatosDelPlan);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (datosEmpresa.getPlanes().buscar(textFieldPlan.getText())!=null){
			// PLAN EXISTE
			
			textFieldPlan.setEnabled(false);
			btnBuscar.setEnabled(false);
			btnEliminar.setEnabled(true);
			lblDatosDelPlan.setForeground(Color.BLUE);
			lblDatosDelPlan.setText("Se ha encontrado el plan");
			
			// LISTA INFORMACIÓN DEL PLAN A ELIMINAR
			DefaultListModel listmodel = new DefaultListModel();
			Plan p = datosEmpresa.getPlanes().buscar(textFieldPlan.getText());
				
				listmodel.addElement("-ID  "+p.getIdPlan());
				listmodel.addElement("-Nombre  "+p.getNombre());
				listmodel.addElement("-Gb  "+p.getGigas());
				listmodel.addElement("-Minutos  "+p.getMinutos());
				listmodel.addElement("-Precio  "+p.getPrecio());
				listmodel.addElement("-Sms  "+p.getSms());
				listmodel.addElement("-ValorMin  "+p.getValorMin());
				
				listPlanInfo.setModel(listmodel);
			
				}else {
					lblDatosDelPlan.setForeground(Color.RED);
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
