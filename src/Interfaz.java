

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class Interfaz extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setTitle("Interfaz principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnSiguiente = new JButton("siguiente");
		btnSiguiente.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Creacion y llamado del PanelCliente
				PanelCliente window2 = new PanelCliente();
				window2.setVisible(true);
				dispose();	//Cierra ventana actual
			}
		});
		btnSiguiente.setBounds(335, 227, 89, 23);
		contentPane.add(btnSiguiente);
		
		JLabel lblVentanaInterface = new JLabel("Ventana interface , password o lo que sea");
		lblVentanaInterface.setBounds(10, 11, 249, 29);
		contentPane.add(lblVentanaInterface);
	}
}
