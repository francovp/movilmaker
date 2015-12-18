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
import extras.Database;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FrameModificarPlan extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPlan;
	private JTextField textFieldNombre;
	private JTextField textFieldPrecio;
	private JTextField textFieldMinuto;
	private JTextField textFieldGigas;
	private JTextField textFieldSms;
	private JTextField textFieldValorMinuto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datos) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameModificarPlan frame = new FrameModificarPlan(datos);
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
		setBounds(100, 100, 468, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Modificar plan", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atributos asociados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 87, 415, 153);
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
		lblMinutos.setBounds(260, 57, 43, 14);
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
		
		JLabel lblSms = new JLabel("SMS");
		lblSms.setBounds(260, 85, 43, 14);
		panel.add(lblSms);
		
		textFieldSms = new JTextField();
		textFieldSms.setEditable(false);
		textFieldSms.setColumns(10);
		textFieldSms.setBounds(313, 82, 79, 20);
		panel.add(textFieldSms);
		
		JLabel lblValorMinuto = new JLabel("Valor Minuto");
		lblValorMinuto.setBounds(10, 110, 97, 14);
		panel.add(lblValorMinuto);
		
		textFieldValorMinuto = new JTextField();
		textFieldValorMinuto.setEditable(false);
		textFieldValorMinuto.setColumns(10);
		textFieldValorMinuto.setBounds(117, 107, 79, 20);
		panel.add(textFieldValorMinuto);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ASIGNA NUEVOS VALORES AL PLAN SELECCIONADO
				Plan p = datosEmpresa.getPlanes().buscar(textFieldPlan.getText());
				p.setNombre(textFieldNombre.getText());
				p.setPrecio(Integer.parseInt(textFieldPrecio.getText()));
				p.setMinutos(Integer.parseInt(textFieldMinuto.getText()));
				p.setGigas(textFieldGigas.getText());
				p.setSms(Integer.parseInt(textFieldSms.getText()));
				p.setValorMin(Integer.parseInt(textFieldValorMinuto.getText()));
				
				// Se actualizará Equipo en la BD
				try {
					// Creacion de conexion a base de datos
					Database.modificarPlanes(p);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.err.println("Equipo no se pudo actualizar en la Base de Datos.\n"
							+ "\nDetalles de la excepciï¿½n:");
					System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
				}
				
				JOptionPane.showMessageDialog(null, "Equipo modificado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
				dispose();	
			}
		});
		btnModificar.setBounds(303, 119, 89, 23);
		panel.add(btnModificar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 440, 65);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Nombre del plan");
		label.setBounds(10, 14, 77, 14);
		panel_1.add(label);
		
		textFieldPlan = new JTextField();
		textFieldPlan.setColumns(10);
		textFieldPlan.setBounds(97, 11, 131, 20);
		panel_1.add(textFieldPlan);
		
		JLabel lblInfo = new JLabel("");
		lblInfo.setBounds(10, 39, 256, 14);
		panel_1.add(lblInfo);
		lblInfo.setForeground(Color.BLUE);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (datosEmpresa.getPlanes().buscar(textFieldPlan.getText())!=null){
					//SI PLAN EXISTE
					Plan p = datosEmpresa.getPlanes().buscar(textFieldPlan.getText());
					
					// CONVIERTE EN EDITABLES LOS JTextField Movil, Fono,
					// Direccion, Email y textFieldRut COMO NO EDITABLE
					
					textFieldNombre.setEditable(true);
					textFieldPrecio.setEditable(true);
					textFieldMinuto.setEditable(true);
					textFieldGigas.setEditable(true);
					textFieldSms.setEditable(true);
					textFieldValorMinuto.setEditable(true);
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
					lblInfo.setForeground(Color.RED);
					lblInfo.setText("Plan no existe");
				}
			}
		});
		btnNewButton.setBounds(238, 10, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(337, 10, 89, 23);
		panel_1.add(btnCancelar);
	}
}
