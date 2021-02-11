package umu.tds;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Usuario;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class VentanaExplorar extends JDialog {
	
	private JTextField textFieldTitulo;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaExplorar dialog = new VentanaExplorar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaExplorar() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 189, 10, 10, 30, 30, 30, 30, 0, 0, 10, 0, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Usuario usuario =AppMusicControlador.getInstancia().getUsuarioActual();
		String nombre = usuario.getNombre() +" " +usuario.getApellidos();
		
		JLabel lbnombreUsuario = new JLabel(nombre);
		GridBagConstraints gbc_lbnombreUsuario = new GridBagConstraints();
		gbc_lbnombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lbnombreUsuario.gridx = 1;
		gbc_lbnombreUsuario.gridy = 1;
		getContentPane().add(lbnombreUsuario, gbc_lbnombreUsuario);
		
		JButton btnMejorarCuenta = new JButton("MEJORAR CUENTA");
		btnMejorarCuenta.setBorderPainted(false);
		btnMejorarCuenta.setForeground(Color.WHITE);
		btnMejorarCuenta.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnMejorarCuenta = new GridBagConstraints();
		gbc_btnMejorarCuenta.anchor = GridBagConstraints.EAST;
		gbc_btnMejorarCuenta.insets = new Insets(0, 0, 5, 5);
		gbc_btnMejorarCuenta.gridx = 8;
		gbc_btnMejorarCuenta.gridy = 1;
		getContentPane().add(btnMejorarCuenta, gbc_btnMejorarCuenta);
		
		JButton btnSALIR = new JButton("SALIR");
		btnSALIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSALIR.setForeground(Color.WHITE);
		btnSALIR.setBackground(Color.BLACK);
		btnSALIR.setBorderPainted(false);
		GridBagConstraints gbc_btnSALIR = new GridBagConstraints();
		gbc_btnSALIR.anchor = GridBagConstraints.EAST;
		gbc_btnSALIR.insets = new Insets(0, 0, 5, 5);
		gbc_btnSALIR.gridx = 9;
		gbc_btnSALIR.gridy = 1;
		getContentPane().add(btnSALIR, gbc_btnSALIR);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 10;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 30, 0, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnExplorar = new JButton("");
		btnExplorar.setContentAreaFilled(false);
		btnExplorar.setBorderPainted(false);
		btnExplorar.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/LupaIconrReversed.jpg")));
		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.insets = new Insets(0, 0, 5, 5);
		gbc_btnExplorar.gridx = 0;
		gbc_btnExplorar.gridy = 1;
		panel.add(btnExplorar, gbc_btnExplorar);
		
		JLabel lblExplorar = new JLabel("EXPLORAR");
		lblExplorar.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblExplorar = new GridBagConstraints();
		gbc_lblExplorar.insets = new Insets(0, 0, 5, 0);
		gbc_lblExplorar.gridx = 2;
		gbc_lblExplorar.gridy = 1;
		panel.add(lblExplorar, gbc_lblExplorar);
		
		JButton btnNuevaLista = new JButton("");
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaNuevaLista ventanaNuevaLista = new VentanaNuevaLista();
				ventanaNuevaLista.setVisible(true);
				dispose();
			}
		});
		btnNuevaLista.setContentAreaFilled(false);
		btnNuevaLista.setBorderPainted(false);
		btnNuevaLista.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/NuevaListaIcon.jpg")));
		GridBagConstraints gbc_btnNuevaLista = new GridBagConstraints();
		gbc_btnNuevaLista.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaLista.gridx = 0;
		gbc_btnNuevaLista.gridy = 3;
		panel.add(btnNuevaLista, gbc_btnNuevaLista);
		
		JLabel lblNuevaLista = new JLabel("NUEVA LISTA");
		lblNuevaLista.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNuevaLista = new GridBagConstraints();
		gbc_lblNuevaLista.insets = new Insets(0, 0, 5, 0);
		gbc_lblNuevaLista.gridx = 2;
		gbc_lblNuevaLista.gridy = 3;
		panel.add(lblNuevaLista, gbc_lblNuevaLista);
		
		JButton btnReciente = new JButton("");
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaRecientes ventanaRecientes = new VentanaRecientes();
				ventanaRecientes.setVisible(true);
				dispose();
				
				
			}
		});
		btnReciente.setBorderPainted(false);
		btnReciente.setContentAreaFilled(false);
		btnReciente.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/RecentIcon.jpg")));
		GridBagConstraints gbc_btnReciente = new GridBagConstraints();
		gbc_btnReciente.insets = new Insets(0, 0, 5, 5);
		gbc_btnReciente.gridx = 0;
		gbc_btnReciente.gridy = 5;
		panel.add(btnReciente, gbc_btnReciente);
		
		JLabel lblReciente = new JLabel("RECIENTE");
		lblReciente.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblReciente = new GridBagConstraints();
		gbc_lblReciente.insets = new Insets(0, 0, 5, 0);
		gbc_lblReciente.gridx = 2;
		gbc_lblReciente.gridy = 5;
		panel.add(lblReciente, gbc_lblReciente);
		
		JButton btnMisListas = new JButton("");
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaMisListas ventanaMisListas = new VentanaMisListas();
				ventanaMisListas.setVisible(true);
				dispose();
				
				
			}
		});
		btnMisListas.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/ListIcon.jpg")));
		btnMisListas.setContentAreaFilled(false);
		btnMisListas.setBorderPainted(false);
		GridBagConstraints gbc_btnMisListas = new GridBagConstraints();
		gbc_btnMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_btnMisListas.gridx = 0;
		gbc_btnMisListas.gridy = 7;
		panel.add(btnMisListas, gbc_btnMisListas);
		
		JLabel lblMisListas = new JLabel("MIS LISTAS");
		lblMisListas.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblMisListas = new GridBagConstraints();
		gbc_lblMisListas.insets = new Insets(0, 0, 5, 0);
		gbc_lblMisListas.gridx = 2;
		gbc_lblMisListas.gridy = 7;
		panel.add(lblMisListas, gbc_lblMisListas);
		
		textField = new JTextField();
		textField.setText("INT\u00C9RPRETE");
		textField.setForeground(Color.WHITE);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.GRAY);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		getContentPane().add(textField, gbc_textField);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBorder(null);
		textFieldTitulo.setForeground(Color.WHITE);
		textFieldTitulo.setBackground(Color.GRAY);
		textFieldTitulo.setText("T\u00CDTULO");
		GridBagConstraints gbc_textFieldTitulo = new GridBagConstraints();
		gbc_textFieldTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitulo.gridx = 8;
		gbc_textFieldTitulo.gridy = 3;
		getContentPane().add(textFieldTitulo, gbc_textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JComboBox comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setBorder(null);
		comboBoxEstilo.setForeground(Color.WHITE);
		comboBoxEstilo.setBackground(Color.GRAY);
		comboBoxEstilo.setModel(new DefaultComboBoxModel(new String[] {"ESTILO", "ROCK", "POP", "ELECTRO", "CL\u00C1SICA", "RETRO", "80'S", "90'S", "OTRO"}));
		GridBagConstraints gbc_comboBoxEstilo = new GridBagConstraints();
		gbc_comboBoxEstilo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilo.gridx = 9;
		gbc_comboBoxEstilo.gridy = 3;
		getContentPane().add(comboBoxEstilo, gbc_comboBoxEstilo);
		
		JButton btnBuscar = new JButton("BUSCAR");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.EAST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 6;
		gbc_btnBuscar.gridy = 4;
		getContentPane().add(btnBuscar, gbc_btnBuscar);
		btnBuscar.setBackground(Color.GRAY);
		btnBuscar.setForeground(Color.WHITE);
		
		final JPanel panelBuscar = new JPanel();
		panelBuscar.setVisible(false);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelBuscar.setVisible(true);
			}
		});
		
		JButton btnCancelar = new JButton("CANCELAR");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 8;
		gbc_btnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.GRAY);
		
		
		panelBuscar.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelBuscar = new GridBagConstraints();
		gbc_panelBuscar.gridheight = 7;
		gbc_panelBuscar.gridwidth = 5;
		gbc_panelBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_panelBuscar.fill = GridBagConstraints.BOTH;
		gbc_panelBuscar.gridx = 5;
		gbc_panelBuscar.gridy = 6;
		getContentPane().add(panelBuscar, gbc_panelBuscar);
		GridBagLayout gbl_panelBuscar = new GridBagLayout();
		gbl_panelBuscar.columnWidths = new int[]{30, 30, 0, 0, 0, 0, 0, 30, 0};
		gbl_panelBuscar.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelBuscar.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelBuscar.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelBuscar.setLayout(gbl_panelBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		panelBuscar.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
				{"", null},
			},
			new String[] {
				"T\u00CDTULO", "ARTISTA"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnPlay = new JButton("");
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/PlayIcon.jpg")));
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 4;
		gbc_btnPlay.gridy = 5;
		panelBuscar.add(btnPlay, gbc_btnPlay);
		
		JButton btnBackSong = new JButton("");
		btnBackSong.setContentAreaFilled(false);
		btnBackSong.setBorderPainted(false);
		btnBackSong.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/BackSongIcon.jpg")));
		GridBagConstraints gbc_btnBackSong = new GridBagConstraints();
		gbc_btnBackSong.insets = new Insets(0, 0, 0, 5);
		gbc_btnBackSong.gridx = 3;
		gbc_btnBackSong.gridy = 6;
		panelBuscar.add(btnBackSong, gbc_btnBackSong);
		
		JButton btnStop = new JButton("");
		btnStop.setContentAreaFilled(false);
		btnStop.setBorderPainted(false);
		btnStop.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/PauseIcon.jpg")));
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 4;
		gbc_btnStop.gridy = 6;
		panelBuscar.add(btnStop, gbc_btnStop);
		
		JButton btnNextSong = new JButton("");
		btnNextSong.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/NextSongIcon.jpg")));
		btnNextSong.setContentAreaFilled(false);
		btnNextSong.setBorderPainted(false);
		GridBagConstraints gbc_btnNextSong = new GridBagConstraints();
		gbc_btnNextSong.insets = new Insets(0, 0, 0, 5);
		gbc_btnNextSong.gridx = 5;
		gbc_btnNextSong.gridy = 6;
		panelBuscar.add(btnNextSong, gbc_btnNextSong);
	}

}