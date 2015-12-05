import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FrameAccesoProxy extends JFrame implements IFrameAcceso {	//	implementa metodos de interfaz IFrameAcceso

	private JPanel contentPane;
	private JLabel lblAviso;
	private String password="123";
	private JPasswordField passwordField;
	FrameAcceso access;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAccesoProxy frame = new FrameAccesoProxy(datosEmpresa);
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
	public FrameAccesoProxy(Compania datosEmpresa) {
		setResizable(false);
		setTitle("Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 272, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Ingrese password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 236, 107);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblAviso = new JLabel("");
		lblAviso.setBounds(10, 66, 46, 14);
		panel.add(lblAviso);
		
		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (password.equals(passwordField.getText())){
					System.out.println("Password Valido");
					acceder(datosEmpresa);
				}
			}
		});
		btnNewButton.setBounds(10, 73, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.setBounds(137, 73, 89, 23);
		panel.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 32, 216, 23);
		panel.add(passwordField);
	}
	
	@Override
	public void acceder(Compania datosEmpresa) {
		access = new FrameAcceso();
		access.acceder(datosEmpresa);
		
	}
}
