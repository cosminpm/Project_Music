package umu.tds;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Usuario;

import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textUsuario;
	private JPasswordField textClave1;
	private JPasswordField textClave2;
	private JTextField textMail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro(null);
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
	public VentanaRegistro(final JFrame invocante) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (invocante != null) {

					invocante.setVisible(true);

				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 555, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Registro", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 20, 0, 0, 20, 0, 20, 0 };
		gbl_panel.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 3;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 2;
		gbc_textNombre.gridy = 1;
		panel.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textApellidos = new JTextField();
		GridBagConstraints gbc_textApellidos = new GridBagConstraints();
		gbc_textApellidos.gridwidth = 3;
		gbc_textApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_textApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textApellidos.gridx = 2;
		gbc_textApellidos.gridy = 2;
		panel.add(textApellidos, gbc_textApellidos);
		textApellidos.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Usuario:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textUsuario = new JTextField();
		GridBagConstraints gbc_textUsuario = new GridBagConstraints();
		gbc_textUsuario.anchor = GridBagConstraints.WEST;
		gbc_textUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_textUsuario.gridx = 2;
		gbc_textUsuario.gridy = 3;
		panel.add(textUsuario, gbc_textUsuario);
		textUsuario.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Clave:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textClave1 = new JPasswordField();
		textClave1.setColumns(10);
		GridBagConstraints gbc_textClave1 = new GridBagConstraints();
		gbc_textClave1.anchor = GridBagConstraints.WEST;
		gbc_textClave1.insets = new Insets(0, 0, 5, 5);
		gbc_textClave1.gridx = 2;
		gbc_textClave1.gridy = 4;
		panel.add(textClave1, gbc_textClave1);

		JLabel lblNewLabel_4 = new JLabel("Repetir:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 4;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textClave2 = new JPasswordField();
		textClave2.setColumns(10);
		GridBagConstraints gbc_textClave2 = new GridBagConstraints();
		gbc_textClave2.insets = new Insets(0, 0, 5, 5);
		gbc_textClave2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textClave2.gridx = 4;
		gbc_textClave2.gridy = 4;
		panel.add(textClave2, gbc_textClave2);

		JLabel lblNewLabel_5 = new JLabel("Mail:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		textMail = new JTextField();
		GridBagConstraints gbc_textMail = new GridBagConstraints();
		gbc_textMail.gridwidth = 3;
		gbc_textMail.insets = new Insets(0, 0, 5, 5);
		gbc_textMail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMail.gridx = 2;
		gbc_textMail.gridy = 5;
		panel.add(textMail, gbc_textMail);
		textMail.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Fecha:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 6;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		final JDateChooser dateFecha = new JDateChooser();
		dateFecha.setDateFormatString(Constantes.fecha_format);
		GridBagConstraints gbc_dateFecha = new GridBagConstraints();
		gbc_dateFecha.insets = new Insets(0, 0, 5, 5);
		gbc_dateFecha.fill = GridBagConstraints.BOTH;
		gbc_dateFecha.gridx = 2;
		gbc_dateFecha.gridy = 6;
		panel.add(dateFecha, gbc_dateFecha);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vaciarCampos();

			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 7;
		panel.add(btnCancelar, gbc_btnCancelar);

		final JButton botonRegistrar = new JButton("Registrar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String login = textUsuario.getText();
				String clave1 = new String(textClave1.getPassword());
				String clave2 = new String(textClave2.getPassword());
				String nombre = textNombre.getText();
				String apellidos = textApellidos.getText();
				String email = textMail.getText();
				Date fechaRAW = dateFecha.getDate();

				// Metodo antiguo//LocalDate fechaNacimiento =
				// LocalDate.of(dateFecha.getDate().getYear(),dateFecha.getDate().getMonth(),
				// dateFecha.getDate().getDay());
				// Metodo sacado de internet, date ---> localDate
				LocalDate fechaNacimiento = fechaRAW.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				if (clave1.equals(clave2)) {
					// EXTRA Comprobacion de que la fecha actual es posterior a la fecha de
					// Nacimiento

					if (fechaNacimiento.isAfter(LocalDate.now())) {
						JOptionPane.showMessageDialog(botonRegistrar, "La fecha actual no puede ser en el futuro!!",
								"Registro usuario", JOptionPane.INFORMATION_MESSAGE, null);
					}
					else if (!email.endsWith(".com") && !email.endsWith(".es") && !email.endsWith(".org")) {
						JOptionPane.showMessageDialog(botonRegistrar, "El correo no es correcto, solo se acepta .org, .com o .es", "Registro usuario",
								JOptionPane.ERROR_MESSAGE, null);
					}
					
					else if (!email.contains("@")){
						JOptionPane.showMessageDialog(botonRegistrar, "El formato del correo no es correcto", "Registro usuario",
								JOptionPane.ERROR_MESSAGE, null);
					}
					// En caso de que la fecha este bien puesta...
					else {
						// Se intenta registrar al usuario
						if (AppMusicControlador.getInstancia().registrarUsuario(nombre, apellidos, email, login, clave1,
								fechaNacimiento)) {
							JOptionPane.showMessageDialog(botonRegistrar, "Usuario registrado con exito",
									"Registro usuario", JOptionPane.INFORMATION_MESSAGE, null);
							Usuario usu = new Usuario(nombre, apellidos, email, login, clave1, fechaNacimiento);
							AppMusicControlador.getInstancia().setUsuarioActual(usu);
							vaciarCampos();

						} 
						
						

						else {
							JOptionPane.showMessageDialog(botonRegistrar, "Usuario ya registrado", "Registro usuario",
									JOptionPane.ERROR_MESSAGE, null);
						}
					}
				}

				else {
					JOptionPane.showMessageDialog(botonRegistrar, "Claves no coinciden", "Fallo Registro",
							JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.anchor = GridBagConstraints.EAST;
		gbc_botonRegistrar.insets = new Insets(0, 0, 0, 5);
		gbc_botonRegistrar.gridx = 4;
		gbc_botonRegistrar.gridy = 7;
		panel.add(botonRegistrar, gbc_botonRegistrar);
	}

	private void vaciarCampos() {

		textUsuario.setText("");
		textClave1.setText("");
		textClave2.setText("");
		textNombre.setText("");
		textApellidos.setText("");
		textMail.setText("");

	}

}
