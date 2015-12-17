package interfaz.modificar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import colecciones.Equipo;
import colecciones.Plan;
import interfaz.FrameInterfaz;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameModificarEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEquipo;
	private JTextField textFieldNombre;
	private JTextField textFieldPantalla;
	private JTextField textFieldValorPlan;
	private JTextField textFieldCamara;
	private JTextField textFieldValorPrepago;
	private JTextField textFieldProcesador;
	private JTextField textFieldSo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameModificarEquipo frame = new FrameModificarEquipo(datosEmpresa);
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
	public FrameModificarEquipo(Compania datosEmpresa) {
		setTitle("Modificar Equipo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Equipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atributos asociados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 83, 504, 147);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 26, 97, 14);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(117, 23, 146, 20);
		panel.add(textFieldNombre);
		
		JLabel lblPantalla = new JLabel("Pantalla");
		lblPantalla.setBounds(10, 54, 97, 14);
		panel.add(lblPantalla);
		
		textFieldPantalla = new JTextField();
		textFieldPantalla.setEditable(false);
		textFieldPantalla.setColumns(10);
		textFieldPantalla.setBounds(117, 51, 146, 20);
		panel.add(textFieldPantalla);
		
		JLabel lblValorPlan = new JLabel("Valor Plan");
		lblValorPlan.setBounds(280, 54, 70, 14);
		panel.add(lblValorPlan);
		
		textFieldValorPlan = new JTextField();
		textFieldValorPlan.setEditable(false);
		textFieldValorPlan.setColumns(10);
		textFieldValorPlan.setBounds(365, 51, 129, 20);
		panel.add(textFieldValorPlan);
		
		JLabel lblCamara = new JLabel("Camara");
		lblCamara.setBounds(10, 82, 97, 14);
		panel.add(lblCamara);
		
		textFieldCamara = new JTextField();
		textFieldCamara.setEditable(false);
		textFieldCamara.setColumns(10);
		textFieldCamara.setBounds(117, 79, 146, 20);
		panel.add(textFieldCamara);
		
		JLabel lblValorPrepago = new JLabel("Valor Prepago");
		lblValorPrepago.setBounds(280, 82, 70, 14);
		panel.add(lblValorPrepago);
		
		textFieldValorPrepago = new JTextField();
		textFieldValorPrepago.setEditable(false);
		textFieldValorPrepago.setColumns(10);
		textFieldValorPrepago.setBounds(365, 79, 129, 20);
		panel.add(textFieldValorPrepago);
		
		JLabel lblProcesador = new JLabel("Procesador");
		lblProcesador.setBounds(280, 26, 97, 14);
		panel.add(lblProcesador);
		
		textFieldProcesador = new JTextField();
		textFieldProcesador.setEditable(false);
		textFieldProcesador.setColumns(10);
		textFieldProcesador.setBounds(365, 23, 129, 20);
		panel.add(textFieldProcesador);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// ASIGNA NUEVOS VALORES AL PLAN SELECCIONADO
				Equipo eq = datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText());
				eq.setNombreEquipo(textFieldNombre.getText());
				eq.setPantalla(textFieldPantalla.getText());
				eq.setValorPlan(Integer.parseInt(textFieldValorPlan.getText()));
				eq.setCamara(textFieldCamara.getText());
				eq.setValorPrepago(Integer.parseInt(textFieldValorPrepago.getText()));
				eq.setProcesador(textFieldProcesador.getText());
				JOptionPane.showMessageDialog(null, "Equipo modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				dispose();	
				
			}
		});
		btnModificar.setBounds(405, 113, 89, 23);
		panel.add(btnModificar);
		
		JLabel lblSo = new JLabel("S.O");
		lblSo.setBounds(10, 110, 97, 14);
		panel.add(lblSo);
		
		textFieldSo = new JTextField();
		textFieldSo.setEditable(false);
		textFieldSo.setColumns(10);
		textFieldSo.setBounds(117, 107, 146, 20);
		panel.add(textFieldSo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 454, 61);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombreDelEquipo = new JLabel("Nombre equipo");
		lblNombreDelEquipo.setBounds(10, 14, 77, 14);
		panel_1.add(lblNombreDelEquipo);
		
		textFieldEquipo = new JTextField();
		textFieldEquipo.setColumns(10);
		textFieldEquipo.setBounds(97, 11, 149, 20);
		panel_1.add(textFieldEquipo);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(10, 39, 256, 14);
		panel_1.add(lblInfo);
		lblInfo.setForeground(Color.BLUE);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText())!=null){
					//SI EQUIPO EXISTE
					
					Equipo eq = datosEmpresa.getEquipos().buscarEquipo(textFieldEquipo.getText());
					
					// CONVIERTE EN EDITABLES LOS JTextField Nombre, Pantalla,
					// ValorPlan, Camara, ValorPrepago, Procesador y textFieldEquipo COMO NO EDITABLE
					
					textFieldNombre.setEditable(true);
					textFieldPantalla.setEditable(true);
					textFieldValorPlan.setEditable(true);
					textFieldCamara.setEditable(true);
					textFieldValorPrepago.setEditable(true);
					textFieldProcesador.setEditable(true);
					textFieldSo.setEditable(true);
					btnModificar.setEnabled(true);
					textFieldEquipo.setEditable(false);
					
					// CARGA A SUS RESPECTIVOS JTextField la información previa
					
					textFieldNombre.setText(eq.getNombre());
					textFieldPantalla.setText(eq.getPantalla());
					textFieldValorPlan.setText(Integer.toString(eq.getValorPlan()));
					textFieldCamara.setText(eq.getCamara());
					textFieldValorPrepago.setText(Integer.toString(eq.getValorPrepago()));;
					textFieldProcesador.setText(eq.getProcesador());
					textFieldSo.setText(eq.getSo());
				} else {
					lblInfo.setForeground(Color.RED);
					lblInfo.setText("Equipo no existe");
				}
			}
		});
		btnNewButton.setBounds(256, 10, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(355, 10, 89, 23);
		panel_1.add(btnCancelar);
	
	}
}
