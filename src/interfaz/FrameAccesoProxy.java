package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import colecciones.Administrador;
import colecciones.Compania;
import colecciones.Ejecutivo;
import colecciones.ProxyAcceso;
import extras.DatabaseConnection;
import colecciones.IFrameAcceso;
import colecciones.Principal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Window.Type;

//implementa metodos de interfaz IFrameAcceso
public class FrameAccesoProxy extends JFrame implements IFrameAcceso {	

	private JPanel contentPane;
	private JLabel lblAviso;
	private JPasswordField passwordField;
	ProxyAcceso access;
	private JTextField rutField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datos) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAccesoProxy frame = new FrameAccesoProxy(datos);
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
	public FrameAccesoProxy(Compania datos) {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 397, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ingrese datos de login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 74, 297, 169);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAviso = new JLabel("");
		lblAviso.setBounds(42, 91, 216, 14);
		panel.add(lblAviso);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rut = rutField.getText();
				String password = passwordField.getText();
				if(comprobarIngreso(lblAviso)){
					Administrador admin = datos.getAdministradores().buscarAdmin(rut);
					if(admin == null){
						Ejecutivo ejec = datos.getEjecutivos().buscarEjecutivo(rut);
						if(ejec == null){
							lblAviso.setForeground(Color.RED);
							lblAviso.setText("Este RUT no está registrado");
						}
						else
						{
							rut = ejec.getRut();
							password = ejec.getPassword();
							if (!password.equals(password)){
								lblAviso.setForeground(Color.RED);
								lblAviso.setText("Password incorrecta");
							}
							else if(password.equals(password) && rut.equals(rut))
									acceder(datos,ejec.getTipo());
						}
					}
					else{
						rut = admin.getRut();
						password = admin.getPassword();
						if (!password.equals(password)){
							lblAviso.setForeground(Color.RED);
							lblAviso.setText("Password incorrecta");
						}
						else if(password.equals(password) && rut.equals(rut))
								acceder(datos,admin.getTipo());
					}
				}
			}
		});
		btnAcceder.setBounds(42, 121, 89, 23);
		panel.add(btnAcceder);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(169, 121, 89, 23);
		panel.add(btnSalir);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(111, 60, 147, 20);
		panel.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(42, 64, 72, 14);
		panel.add(lblPassword);
		
		JLabel lblRut = new JLabel("Rut");
		lblRut.setBounds(42, 35, 46, 14);
		panel.add(lblRut);
		
		rutField = new JTextField();
		rutField.setBounds(111, 32, 147, 20);
		panel.add(rutField);
		rutField.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(71, 25, 236, 32);
		label.setIcon(new ImageIcon("resources\\logo.png"));
		contentPane.add(label);
		
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarTodo();
			}
		});
	}
	
	/**
	 * Comprueba si los ingresos en las casillas violan restricciones
	 * @return un boolean si no se encuentra ninguna restriccion o no
	 */
	public boolean comprobarIngreso(JLabel aviso){
		if(rutField.getText().length() == 0){
			aviso.setForeground(Color.RED);
			aviso.setText("RUT no puede estar vacio");
			return false;
		}
		
		if(passwordField.getText().length() == 0){
			aviso.setForeground(Color.RED);
			aviso.setText("Password no puede estar vacio");
			return false;
		}
		
		if(!Principal.validarRut(rutField.getText())){
			aviso.setForeground(Color.RED);
			aviso.setText("Ingrese un RUT valido");
			return false;
		}
		return true;
	}

	private void cerrarTodo() {
		dispose();
		DatabaseConnection.cerrarConnection();
		System.exit(0);
	}
	
	@Override
	public void acceder(Compania datos, int tipo) {
		access = new ProxyAcceso();
		access.acceder(datos, tipo);
		dispose();
	}
}
