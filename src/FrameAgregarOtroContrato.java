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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Ingrese rut de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textRut = new JTextField();
		textRut.setBounds(10, 27, 137, 20);
		contentPane.add(textRut);
		textRut.setColumns(10);

		lblAviso = new JLabel("");
		lblAviso.setBounds(177, 27, 137, 14);
		contentPane.add(lblAviso);

		JButton btnBuscar = new JButton("Ingresar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente C;

				C = datosEmpresa.buscarCliente(textRut.getText());	// busca cliente en clase Compañia, si existe lo retorna

				if(C==null){
					lblAviso.setForeground(Color.BLUE);
					lblAviso.setText("Este cliente no existe");
				}else{
				FrameContrato fContrato = new FrameContrato(datosEmpresa,C);
				fContrato.setVisible(true);
				dispose();
				}
			}
		});
		btnBuscar.setBounds(10, 66, 89, 23);
		contentPane.add(btnBuscar);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInterfaz fInterfaz = new FrameInterfaz(datosEmpresa);
				fInterfaz.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(175, 66, 89, 23);
		contentPane.add(btnVolver);




	}

}
