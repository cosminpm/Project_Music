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
//import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Usuario;
import umu.tds.modelo.Cancion;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.util.*;


public class VentanaExplorar extends JDialog {
	
	private JTextField textTitulo;
	private JTable tablaCanciones;
	private JTextField textInterprete;

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
		gridBagLayout.rowHeights = new int[]{10, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JLabel InterpreteShow = new JLabel("INTERPRETE");
		GridBagConstraints gbc_InterpreteShow = new GridBagConstraints();
		gbc_InterpreteShow.insets = new Insets(0, 0, 5, 5);
		gbc_InterpreteShow.gridx = 5;
		gbc_InterpreteShow.gridy = 3;
		getContentPane().add(InterpreteShow, gbc_InterpreteShow);
		
		JLabel tituloShow = new JLabel("TITULO");
		GridBagConstraints gbc_tituloShow = new GridBagConstraints();
		gbc_tituloShow.insets = new Insets(0, 0, 5, 5);
		gbc_tituloShow.gridx = 8;
		gbc_tituloShow.gridy = 3;
		getContentPane().add(tituloShow, gbc_tituloShow);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 10;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
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
		
		textInterprete = new JTextField();
		textInterprete.setText("INTERPRETE");
		textInterprete.setForeground(Color.WHITE);
		textInterprete.setColumns(10);
		textInterprete.setBorder(null);
		textInterprete.setBackground(Color.GRAY);
		GridBagConstraints gbc_textInterprete = new GridBagConstraints();
		gbc_textInterprete.gridwidth = 4;
		gbc_textInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_textInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textInterprete.gridx = 4;
		gbc_textInterprete.gridy = 4;
		getContentPane().add(textInterprete, gbc_textInterprete);
		
		textTitulo = new JTextField();
		textTitulo.setBorder(null);
		textTitulo.setForeground(Color.WHITE);
		textTitulo.setBackground(Color.GRAY);
		textTitulo.setText("TITULO");
		GridBagConstraints gbc_textTitulo = new GridBagConstraints();
		gbc_textTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_textTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTitulo.gridx = 8;
		gbc_textTitulo.gridy = 4;
		getContentPane().add(textTitulo, gbc_textTitulo);
		textTitulo.setColumns(10);
		
		JComboBox<String> comboBoxEstilo = new JComboBox<String>();
		comboBoxEstilo.setBorder(null);
		comboBoxEstilo.setForeground(Color.WHITE);
		comboBoxEstilo.setBackground(Color.GRAY);
		
		
		String[] array = AppMusicControlador.getInstancia().recuperarEstilos().stream().toArray(String[]::new);
		comboBoxEstilo.setModel(new DefaultComboBoxModel<String>(array));
		GridBagConstraints gbc_comboBoxEstilo = new GridBagConstraints();
		gbc_comboBoxEstilo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilo.gridx = 9;
		gbc_comboBoxEstilo.gridy = 4;
		getContentPane().add(comboBoxEstilo, gbc_comboBoxEstilo);
		
		JButton btnBuscar = new JButton("BUSCAR");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.EAST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 6;
		gbc_btnBuscar.gridy = 5;
		getContentPane().add(btnBuscar, gbc_btnBuscar);
		btnBuscar.setBackground(Color.GRAY);
		btnBuscar.setForeground(Color.WHITE);
		
		final JPanel panelBuscar = new JPanel();
		panelBuscar.setVisible(false);
		

		
		JButton btnCancelar = new JButton("CANCELAR");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.EAST;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 8;
		gbc_btnCancelar.gridy = 5;
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
		gbc_panelBuscar.gridy = 7;
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
		
		tablaCanciones = new JTable();
		tablaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCanciones.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
				"T\u00CDTULO", "ARTISTA"
			}
		));
		scrollPane.setViewportView(tablaCanciones);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelBuscar.setVisible(true);
				
				DefaultTableModel model = (DefaultTableModel) tablaCanciones.getModel();
				model.setRowCount(0);
				
				String interprete = textInterprete.getText();
				if(textInterprete.getText().isEmpty()) {
					interprete = null;
				}
				String titulo = textTitulo.getText();
				if(textTitulo.getText().isEmpty()) {
					titulo = null;
				}
				
				String estilo = comboBoxEstilo.getSelectedItem().toString();
				if(estilo == "Cualquiera") {
					estilo = null;
				}
				
				interprete = AppMusicControlador.getInstancia().comprobarCorrecionInterprete(interprete);
				titulo = AppMusicControlador.getInstancia().comprobarCorrecionTitulo(titulo);
				
				System.out.println("AA: " + titulo);
				System.out.println("BB: " + interprete);
				
				
				List<Cancion> canciones = AppMusicControlador.getInstancia().filtrarCanciones(interprete, titulo, estilo);
				
				String aux;
				for (Cancion cancion : canciones) {
					aux = AppMusicControlador.getInstancia().printAutoresNice(cancion.getListaInterpretes());
					((DefaultTableModel) tablaCanciones.getModel()).addRow(new Object[] {
			                cancion.getTitulo(), aux});
				}
			}
		});
		
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
