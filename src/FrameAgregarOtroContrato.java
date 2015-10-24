import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class FrameAgregarOtroContrato extends JFrame {

	private JPanel contentPane;
	private JTextField textRut;
	private JButton btnVolver;
	private JLabel lblAviso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Compania datosEmpresa) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAgregarOtroContrato frame = new FrameAgregarOtroContrato(datosEmpresa);
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
	public FrameAgregarOtroContrato(Compania datosEmpresa) {
		setTitle("Contrato adicional");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 343, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Ingrese rut de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textRut = new JTextField();
		textRut.setBounds(10, 27, 188, 20);
		contentPane.add(textRut);
		textRut.setColumns(10);

		lblAviso = new JLabel("");
		lblAviso.setBounds(10, 86, 188, 14);
		contentPane.add(lblAviso);

		JButton btnBuscar = new JButton("Ingresar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// BUSCA CLIENTE EN CLASE COMPAÑIA Y LO ASIGNA A c, SI LO ENCUENTRA LO RETORNA SI NO DEVUELVE NULL
				Cliente c = datosEmpresa.buscarCliente(textRut.getText());
				
				//SI EXISTE LLAMA A VENTANA FrameContrato Y LE ENVIA datosEmpresa y c (Cliente)
				if (c!=null){
					FrameContrato fContrato = new FrameContrato(datosEmpresa,c);
					fContrato.setVisible(true);
					dispose();
					
				}else	{
					lblAviso.setForeground(Color.RED);
					lblAviso.setText("El cliente no existe");
				}
			}
		});
		btnBuscar.setBounds(10, 58, 89, 23);
		contentPane.add(btnBuscar);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LLAMA AL MENU PRINCIPAL Y CIERRA ESTA VENTANA
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(109, 58, 89, 23);
		contentPane.add(btnVolver);
	}
}
