

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DropMode;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre1;
	private JLabel lblNombre;
	private JTextField tfNombre2;
	private JLabel lblApellido;
	private JTextField tfApellido1;
	private JLabel lblApellido_1;
	private JTextField tfApellido2;
	private JLabel lblRut;
	private JTextField tfRut;
	private JLabel lblNewLabel_1;
	private JPanel panel;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private ButtonGroup botones = new ButtonGroup();
	private JButton btnNewButton;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelCliente frame = new PanelCliente();
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
	public PanelCliente() {
		setTitle("Datos de cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rellenar datos de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(7, 1, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Nombre1");
		contentPane.add(lblNewLabel);
		
		tfNombre1 = new JTextField();
		contentPane.add(tfNombre1);
		tfNombre1.setColumns(10);
		
		
		lblNombre = new JLabel("Nombre2");
		contentPane.add(lblNombre);
		
		tfNombre2 = new JTextField();
		contentPane.add(tfNombre2);
		tfNombre2.setColumns(10);
		
		lblApellido = new JLabel("Apellido1");
		contentPane.add(lblApellido);
		
		tfApellido1 = new JTextField();
		contentPane.add(tfApellido1);
		tfApellido1.setColumns(10);
		
		lblApellido_1 = new JLabel("Apellido2");
		contentPane.add(lblApellido_1);
		
		tfApellido2 = new JTextField();
		contentPane.add(tfApellido2);
		tfApellido2.setColumns(10);
		
		lblRut = new JLabel("Rut");
		contentPane.add(lblRut);
		
		tfRut = new JTextField();
		contentPane.add(tfRut);
		tfRut.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Sexo");
		contentPane.add(lblNewLabel_1);
		
		panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnMasculino = new JRadioButton("Masculino");
		panel.add(rdbtnMasculino);
		
		
		rdbtnFemenino = new JRadioButton("Femenino");
		panel.add(rdbtnFemenino);
		
		//Para seleccionar solo 1 de las 2 radionbutton
		botones.add(rdbtnMasculino);
		botones.add(rdbtnFemenino);
		
		btnNewButton = new JButton("Ingresar cliente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre1,nombre2,apellido1,apellido2,rut,sexo="";
				nombre1=tfNombre1.getText();
				nombre2=tfNombre2.getText();
				apellido1=tfApellido1.getText();
				apellido2=tfApellido2.getText();
				rut=tfRut.getText();
				if(rdbtnMasculino.isSelected()){
				sexo="Masculino";
				} else {
					if (rdbtnFemenino.isSelected())
						sexo="Femenino";
				}
				//Prueba resultados
				System.out.print("nombre:"+ nombre1 + " "+ nombre2 + " "+ apellido1 + " "+apellido2+"\nRut: "+ rut);
				System.out.print("\nSexo:"+ sexo);
				
				Cliente c = new Cliente(nombre1,nombre2,apellido1,apellido2,rut,sexo);
			}
		});
		
		contentPane.add(btnNewButton);
		
		btnSalir = new JButton("Salir");
		contentPane.add(btnSalir);
	}

	
	
	

}
