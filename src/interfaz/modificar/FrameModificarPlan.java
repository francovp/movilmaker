package interfaz.modificar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import colecciones.Compania;
import colecciones.Plan;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameModificarPlan extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPlan;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldMinuto;
	private JTextField textFieldGigas;
	private JTextField textFieldSms;
	private JTextField textFieldVerMinuto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameModificarPlan frame = new FrameModificarPlan(datosEmpresa);
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
	public FrameModificarPlan(Compania datosEmpresa) {
		setTitle("Modificar plan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modificar plan", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atributos asociados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 98, 414, 153);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 26, 97, 14);
		panel.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(117, 23, 153, 20);
		panel.add(textFieldNombre);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(10, 54, 97, 14);
		panel.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setEditable(false);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(117, 51, 79, 20);
		panel.add(textFieldPrecio);
		
		JLabel lblMinutos = new JLabel("Minutos");
		lblMinutos.setBounds(206, 57, 97, 14);
		panel.add(lblMinutos);
		
		textFieldMinuto = new JTextField();
		textFieldMinuto.setEditable(false);
		textFieldMinuto.setColumns(10);
		textFieldMinuto.setBounds(313, 54, 79, 20);
		panel.add(textFieldMinuto);
		
		JLabel lblGigas = new JLabel("Gigas");
		lblGigas.setBounds(10, 82, 97, 14);
		panel.add(lblGigas);
		
		textFieldGigas = new JTextField();
		textFieldGigas.setEditable(false);
		textFieldGigas.setColumns(10);
		textFieldGigas.setBounds(117, 79, 79, 20);
		panel.add(textFieldGigas);
		
		JLabel lblSms = new JLabel("sms");
		lblSms.setBounds(206, 85, 97, 14);
		panel.add(lblSms);
		
		textFieldSms = new JTextField();
		textFieldSms.setEditable(false);
		textFieldSms.setColumns(10);
		textFieldSms.setBounds(313, 82, 79, 20);
		panel.add(textFieldSms);
		
		JLabel lblValorMinuto = new JLabel("Valor Minuto");
		lblValorMinuto.setBounds(10, 110, 97, 14);
		panel.add(lblValorMinuto);
		
		textFieldVerMinuto = new JTextField();
		textFieldVerMinuto.setEditable(false);
		textFieldVerMinuto.setColumns(10);
		textFieldVerMinuto.setBounds(117, 107, 79, 20);
		panel.add(textFieldVerMinuto);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(298, 130, 89, 23);
		panel.add(btnModificar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 414, 76);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Nombre del plan");
		label.setBounds(10, 14, 77, 14);
		panel_1.add(label);
		
		textFieldPlan = new JTextField();
		textFieldPlan.setColumns(10);
		textFieldPlan.setBounds(127, 11, 131, 20);
		panel_1.add(textFieldPlan);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (datosEmpresa.getPlanes().buscarPlan(textFieldPlan.getText())!=null){
					//SI PLAN EXISTE
					
					Plan p = datosEmpresa.getPlanes().buscarPlan(textFieldPlan.getText());
					
					// CONVIERTE EN EDITABLES LOS JTextField Movil, Fono,
					// Direccion, Email y textFieldRut COMO NO EDITABLE
					
					textFieldNombre.setEditable(true);
					textFieldPrecio.setEditable(true);
					textFieldMinuto.setEditable(true);
					textFieldGigas.setEditable(true);
					textFieldSms.setEditable(true);
					textFieldVerMinuto.setEditable(true);
					btnModificar.setEnabled(true);
					textFieldPlan.setEditable(false);
					
					// CARGA A SUS RESPECTIVOS JTextField la información previa
					
					textFieldNombre.setText(p.getNombre());
					textFieldPrecio.setText(Integer.toString(p.getPrecio()));
					textFieldMinuto.setText(Integer.toString(p.getMinutos()));
					textFieldGigas.setText(p.getGigas());
					
					textFieldSms.setText(Integer.toString(p.getSms()));
					textFieldMinuto.setText(Integer.toString(p.getMinutos()));
				} else {
					
				}
				
				
			}
		});
		btnNewButton.setBounds(315, 10, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(315, 44, 89, 23);
		panel_1.add(btnCancelar);
		
		JLabel lblInfoPlan = new JLabel("");
		lblInfoPlan.setForeground(Color.BLUE);
		lblInfoPlan.setBounds(10, 252, 256, 14);
		contentPane.add(lblInfoPlan);
	}
}
