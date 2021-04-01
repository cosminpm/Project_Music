package umu.tds;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.CatalogoCanciones;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;
import umu.tds.persistencia.AdaptadorCancionTDS;
import umu.tds.persistencia.AdaptadorListaCancionesTDS;
import umu.tds.persistencia.AdaptadorUsuarioTDS;

import javax.swing.JScrollPane;
import pulsador.Luz;

public class VentanaNuevaLista extends JDialog {
	private JTextField txtNombreLista;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JTable tableCancionesSinAniadir;
	private JTable tableCancionesAniadidas;

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

	LinkedHashSet<Cancion> conjuntoCancionesSinAniadir = new LinkedHashSet<Cancion>();
	LinkedHashSet<Cancion> conjuntoCancionesAniadidas = new LinkedHashSet<Cancion>();
	
	/**
	 * Create the dialog.
	 */
	public VentanaNuevaLista() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 189, 10, 10, 30, 30, 30, 30, 0, 0, 10, 0, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Usuario usuario = AppMusicControlador.getInstancia().getUsuarioActual();
		String nombre = usuario.getNombre() +" " +usuario.getApellidos();
		
		
		JLabel lbnombreUsuario = new JLabel(nombre);
		GridBagConstraints gbc_lbnombreUsuario = new GridBagConstraints();
		gbc_lbnombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lbnombreUsuario.gridx = 1;
		gbc_lbnombreUsuario.gridy = 1;
		getContentPane().add(lbnombreUsuario, gbc_lbnombreUsuario);
		
		Luz luz = new Luz();
		luz.addEncendidoListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(null);
				
				if(chooser.getSelectedFile() != null) {
					String fichero = chooser.getSelectedFile().getAbsolutePath();
					AppMusicControlador.getInstancia().cargarCanciones(fichero);
				}
			}
		});
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.insets = new Insets(0, 0, 5, 5);
		gbc_luz.gridx = 6;
		gbc_luz.gridy = 1;
		getContentPane().add(luz, gbc_luz);
		
		JButton btnMejorarCuenta = new JButton("MEJORAR CUENTA");
		btnMejorarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(JOptionPane.showConfirmDialog(btnMejorarCuenta,"Confirmación", "¿Quieres ser usuario premium?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					VentanaPremium ventanaPremium = new VentanaPremium();
					ventanaPremium.setVisible(true);
					dispose();
					AppMusicControlador.getInstancia().setPremium(usuario, true);
					
				}
				
				else {
					
				}	
			}
		});
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
		
		JButton btnDefinitivo = new JButton("DEFINITIVO");
		btnDefinitivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(btnDefinitivo,"¿Crear nueva Lista?", "¿Crear nueva Lista?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// Opcion SI
					
					String nombrePlaylist = txtNombreLista.getText();
					List<Cancion> l = AppMusicControlador.getInstancia().setToList(conjuntoCancionesAniadidas);
					//System.err.println("Voy a printear todas las listas del sistema");
					/*for (ListaCanciones lista: AdaptadorListaCancionesTDS.getUnicaInstancia().recuperarTodasListasCanciones()) {
						//System.err.println(lista.getNombre());
					};*/
					
					if(AppMusicControlador.getInstancia().comprobarListaYaExiste(nombrePlaylist, usuario)) {
						// TODO MENSAJE DE ERROR NOMBRE YA EXISTE
						JOptionPane.showMessageDialog(btnDefinitivo, "Error, nombre ya existente", "La lista con ese nombre ya existe!",
								JOptionPane.ERROR_MESSAGE, null);
					}
					else {
						AppMusicControlador.getInstancia().registrarPlayListConVariasCanciones(nombrePlaylist, l, usuario);
					}
					
				}
				else {
					// Opcion NO
				}
				//AppMusicControlador.getInstancia().recuperarTodasListasCanciones();
			}
		});
		GridBagConstraints gbc_btnDefinitivo = new GridBagConstraints();
		gbc_btnDefinitivo.insets = new Insets(0, 0, 5, 5);
		gbc_btnDefinitivo.gridx = 8;
		gbc_btnDefinitivo.gridy = 2;
		getContentPane().add(btnDefinitivo, gbc_btnDefinitivo);
		
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
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaExplorar ventanaExplorar = new VentanaExplorar();
				ventanaExplorar.setVisible(true);
				dispose();
				
			}
		});
		btnExplorar.setContentAreaFilled(false);
		btnExplorar.setBorderPainted(false);
		btnExplorar.setIcon(new ImageIcon(VentanaNuevaLista.class.getResource("/umu/tds/imagenes/LupaIcon.jpg")));
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
		btnNuevaLista.setContentAreaFilled(false);
		btnNuevaLista.setBorderPainted(false);
		btnNuevaLista.setIcon(new ImageIcon(VentanaNuevaLista.class.getResource("/umu/tds/imagenes/NuevaListaIconReverse.jpg")));
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
		
		JButton btnRecientes = new JButton("");
		btnRecientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				VentanaRecientes ventanaRecientes = new VentanaRecientes();
				ventanaRecientes.setVisible(true);
				dispose();
				
			}
		});
		btnRecientes.setBorderPainted(false);
		btnRecientes.setContentAreaFilled(false);
		btnRecientes.setIcon(new ImageIcon(VentanaExplorar.class.getResource("/umu/tds/imagenes/RecentIcon.jpg")));
		GridBagConstraints gbc_btnRecientes = new GridBagConstraints();
		gbc_btnRecientes.insets = new Insets(0, 0, 5, 5);
		gbc_btnRecientes.gridx = 0;
		gbc_btnRecientes.gridy = 5;
		panel.add(btnRecientes, gbc_btnRecientes);
		
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
		
		final JPanel panelCrearLista = new JPanel();
		panelCrearLista.setVisible(false);
		
		final JButton btnCrear = new JButton("CREAR");
		btnCrear.setBackground(Color.GRAY);
		btnCrear.setForeground(Color.WHITE);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCrearLista.setVisible(true);
				System.err.println("TODAS LISTAS");
				//List<ListaCanciones>  listaPlaylist = AdaptadorListaCancionesTDS.getUnicaInstancia().recuperarTodasListasCanciones();
				List<ListaCanciones>  listaPlaylist = AppMusicControlador.getInstancia().recuperarTodasListasCanciones();
				for (ListaCanciones listaCanciones : listaPlaylist) {
					System.err.println(listaCanciones.getNombre());
				}
				System.err.println("LISTAS USUARIO");
				List<ListaCanciones>  listaPlaylist2 =	usuario.getListaCanciones();
				for (ListaCanciones listaCanciones : listaPlaylist2) {
					System.err.println(listaCanciones.getNombre());
				}				
				//JOptionPane.showConfirmDialog(btnCrear, "¿Crear nueva Lista?");	
				
			}
		});
		
		txtNombreLista = new JTextField();
		txtNombreLista.setText("Nombre Lista");
		GridBagConstraints gbc_txtNombreLista = new GridBagConstraints();
		gbc_txtNombreLista.gridwidth = 2;
		gbc_txtNombreLista.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreLista.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreLista.gridx = 5;
		gbc_txtNombreLista.gridy = 3;
		getContentPane().add(txtNombreLista, gbc_txtNombreLista);
		txtNombreLista.setColumns(10);
		GridBagConstraints gbc_btnCrear = new GridBagConstraints();
		gbc_btnCrear.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrear.gridx = 8;
		gbc_btnCrear.gridy = 3;
		getContentPane().add(btnCrear, gbc_btnCrear);
		
		
		panelCrearLista.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelCrearLista = new GridBagConstraints();
		gbc_panelCrearLista.gridheight = 10;
		gbc_panelCrearLista.gridwidth = 6;
		gbc_panelCrearLista.insets = new Insets(0, 0, 0, 5);
		gbc_panelCrearLista.fill = GridBagConstraints.BOTH;
		gbc_panelCrearLista.gridx = 5;
		gbc_panelCrearLista.gridy = 4;
		getContentPane().add(panelCrearLista, gbc_panelCrearLista);
		GridBagLayout gbl_panelCrearLista = new GridBagLayout();
		gbl_panelCrearLista.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCrearLista.rowHeights = new int[]{0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCrearLista.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCrearLista.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCrearLista.setLayout(gbl_panelCrearLista);
		
		txtInterprete = new JTextField();
		txtInterprete.setText("INTERPRETE");
		GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
		gbc_txtInterprete.anchor = GridBagConstraints.NORTH;
		gbc_txtInterprete.gridwidth = 3;
		gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterprete.gridx = 0;
		gbc_txtInterprete.gridy = 0;
		panelCrearLista.add(txtInterprete, gbc_txtInterprete);
		txtInterprete.setColumns(10);
		
		JComboBox comboBoxEstilo = new JComboBox();
		comboBoxEstilo.setBackground(Color.GRAY);
		comboBoxEstilo.setForeground(Color.WHITE);
		
		String[] array = AppMusicControlador.getInstancia().recuperarEstilos().stream().toArray(String[]::new);
		
		txtTitulo = new JTextField();
		txtTitulo.setText("TITULO");
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridwidth = 2;
		gbc_txtTitulo.anchor = GridBagConstraints.NORTH;
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitulo.gridx = 3;
		gbc_txtTitulo.gridy = 0;
		panelCrearLista.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		comboBoxEstilo.setModel(new DefaultComboBoxModel(array));
		GridBagConstraints gbc_comboBoxEstilo = new GridBagConstraints();
		gbc_comboBoxEstilo.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxEstilo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEstilo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilo.gridx = 5;
		gbc_comboBoxEstilo.gridy = 0;
		panelCrearLista.add(comboBoxEstilo, gbc_comboBoxEstilo);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setBackground(Color.GRAY);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableCancionesSinAniadir.getModel();
				model.setRowCount(0);
				conjuntoCancionesSinAniadir.clear();
				
				String interprete = txtInterprete.getText();
				String titulo = txtTitulo.getText();
				String estilo = comboBoxEstilo.getSelectedItem().toString();
				
				if(txtInterprete.getText().isEmpty()) {
					interprete = null;
				}
				
				else {
					interprete = AppMusicControlador.getInstancia().comprobarCorrecionInterprete(interprete);
				}
				
				if(txtTitulo.getText().isEmpty()) {
					titulo = null;
				}
				
				else {
					titulo = AppMusicControlador.getInstancia().comprobarCorrecionTitulo(titulo);
				}
				
				if(estilo.equals("Cualquiera")) {
					estilo = null;
				}
				List<Cancion> canciones = AppMusicControlador.getInstancia().filtrarCanciones(interprete, titulo, estilo);
				String aux;
				for (Cancion cancion : canciones) {
					aux = AppMusicControlador.getInstancia().printAutoresNice(cancion.getListaInterpretes());
					((DefaultTableModel) tableCancionesSinAniadir.getModel()).addRow(new Object[] {
			                cancion.getTitulo(), aux});
					conjuntoCancionesSinAniadir.add(cancion);
				}
			}
		});
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.NORTH;
		gbc_btnBuscar.gridwidth = 2;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 6;
		gbc_btnBuscar.gridy = 0;
		panelCrearLista.add(btnBuscar, gbc_btnBuscar);
		
		JLabel lblPlaylist = new JLabel("PLAYLIST");
		GridBagConstraints gbc_lblPlaylist = new GridBagConstraints();
		gbc_lblPlaylist.anchor = GridBagConstraints.SOUTH;
		gbc_lblPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlaylist.gridx = 7;
		gbc_lblPlaylist.gridy = 1;
		panelCrearLista.add(lblPlaylist, gbc_lblPlaylist);
		
		JScrollPane scrollPaneSinAniadir = new JScrollPane();
		scrollPaneSinAniadir.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPaneSinAniadir = new GridBagConstraints();
		gbc_scrollPaneSinAniadir.gridheight = 4;
		gbc_scrollPaneSinAniadir.gridwidth = 4;
		gbc_scrollPaneSinAniadir.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneSinAniadir.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneSinAniadir.gridx = 0;
		gbc_scrollPaneSinAniadir.gridy = 2;
		panelCrearLista.add(scrollPaneSinAniadir, gbc_scrollPaneSinAniadir);
		
		tableCancionesSinAniadir = new JTable();
		tableCancionesSinAniadir.setRowSelectionAllowed(true);
		tableCancionesSinAniadir.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableCancionesSinAniadir.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00CDTULO", "INT\u00C9RPRETE"
			}
		));
		scrollPaneSinAniadir.setViewportView(tableCancionesSinAniadir);
		
		JScrollPane scrollPaneAniadidas = new JScrollPane();
		GridBagConstraints gbc_scrollPaneAniadidas = new GridBagConstraints();
		gbc_scrollPaneAniadidas.gridwidth = 2;
		gbc_scrollPaneAniadidas.gridheight = 4;
		gbc_scrollPaneAniadidas.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneAniadidas.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneAniadidas.gridx = 6;
		gbc_scrollPaneAniadidas.gridy = 2;
		panelCrearLista.add(scrollPaneAniadidas, gbc_scrollPaneAniadidas);
		
		tableCancionesAniadidas = new JTable();
		//tableCancionesAniadidas.setForeground(Color.WHITE);
		//tableCancionesAniadidas.setBackground(Color.WHITE);
		tableCancionesAniadidas.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"T\u00CDTULO", "INT\u00C9RPRETE"
			}
		));
		scrollPaneAniadidas.setViewportView(tableCancionesAniadidas);
		
		
		
		
		JButton btnSendBack = new JButton("<<");
		btnSendBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] indicesSeleccionados = tableCancionesAniadidas.getSelectedRows();
				
				if (tableCancionesSinAniadir.getSelectedRows().length > 0) {
					return;
				}
				
				int aux = 0;
				//List<Cancion> listaCancionesSinAniadir = AdaptadorCancionTDS.getUnicaInstancia().setToList(conjuntoCancionesSinAniadir); 
				//List<Cancion> listaCancionesAnididas = AdaptadorCancionTDS.getUnicaInstancia().setToList(conjuntoCancionesAniadidas);
				
				List<Cancion> listaCancionesSinAniadir = AppMusicControlador.getInstancia().setToList(conjuntoCancionesSinAniadir);
				List<Cancion> listaCancionesAnididas = AppMusicControlador.getInstancia().setToList(conjuntoCancionesAniadidas);
				
				
				//Para cada fila seleccionada, sacamos los valores de su primera columna (titulo) y su segunda columna (interprete)
				for(int i = 0; i < indicesSeleccionados.length; i++) {
					listaCancionesSinAniadir.add(listaCancionesAnididas.get(indicesSeleccionados[i]-aux));
					listaCancionesAnididas.remove(indicesSeleccionados[i] - aux);
					aux++;
				}	
				
				//listaCancionesAnididas = AdaptadorCancionTDS.getUnicaInstancia().rmRepetidas(listaCancionesAnididas);
				//listaCancionesSinAniadir = AdaptadorCancionTDS.getUnicaInstancia().rmRepetidas(listaCancionesSinAniadir);
				
				listaCancionesAnididas = AppMusicControlador.getInstancia().rmRepetidas(listaCancionesAnididas);
				listaCancionesSinAniadir = AppMusicControlador.getInstancia().rmRepetidas(listaCancionesSinAniadir);
				
				DefaultTableModel modelAniadidos = (DefaultTableModel) tableCancionesAniadidas.getModel();
				modelAniadidos.setRowCount(0);
				
				DefaultTableModel modelSinAniadir = (DefaultTableModel) tableCancionesSinAniadir.getModel();
				modelSinAniadir.setRowCount(0);
				
				
				String auxAutor = "";
				for (Cancion c : listaCancionesAnididas) {
					auxAutor = AppMusicControlador.getInstancia().printAutoresNice(c.getListaInterpretes());
					((DefaultTableModel) tableCancionesAniadidas.getModel()).addRow(new Object[] {
			                c.getTitulo(),	auxAutor});
				}
				
				for (Cancion c : listaCancionesSinAniadir) {
					auxAutor = AppMusicControlador.getInstancia().printAutoresNice(c.getListaInterpretes());
					((DefaultTableModel) tableCancionesSinAniadir.getModel()).addRow(new Object[] {
			                c.getTitulo(),	auxAutor});
				}
				
				conjuntoCancionesAniadidas = (LinkedHashSet<Cancion>) AppMusicControlador.getInstancia().listToSet(listaCancionesAnididas);
				conjuntoCancionesSinAniadir = (LinkedHashSet<Cancion>) AppMusicControlador.getInstancia().listToSet(listaCancionesSinAniadir);	
			}
		});
		btnSendBack.setForeground(Color.WHITE);
		btnSendBack.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnSendBack = new GridBagConstraints();
		gbc_btnSendBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnSendBack.gridx = 5;
		gbc_btnSendBack.gridy = 4;
		panelCrearLista.add(btnSendBack, gbc_btnSendBack);
		
		JButton btnSendToList = new JButton(">>");
		btnSendToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Coger valor de la tabla sin aniadir, borrarlo y pasarlo a las canciones aniadidas
				int[] indicesSeleccionados = tableCancionesSinAniadir.getSelectedRows();
				
				if (tableCancionesAniadidas.getSelectedRows().length > 0) {
					return;
				}
				
				int aux = 0;
				
				List<Cancion> listaCancionesSinAniadir = AppMusicControlador.getInstancia().setToList(conjuntoCancionesSinAniadir); 
				List<Cancion> listaCancionesAnididas =  AppMusicControlador.getInstancia().setToList(conjuntoCancionesAniadidas); 
				
				//Para cada fila seleccionada, sacamos los valores de su primera columna (titulo) y su segunda columna (interprete)
				for(int i = 0; i < indicesSeleccionados.length; i++) {
					listaCancionesAnididas.add(listaCancionesSinAniadir.get(indicesSeleccionados[i]-aux));
					listaCancionesSinAniadir.remove(indicesSeleccionados[i] - aux);
					aux++;
				}
				
				//listaCancionesAnididas = AdaptadorCancionTDS.getUnicaInstancia().rmRepetidas(listaCancionesAnididas);
				  listaCancionesAnididas = CatalogoCanciones.getUnicaInstancia().rmRepetidas(listaCancionesAnididas);
				//listaCancionesSinAniadir= AdaptadorCancionTDS.getUnicaInstancia().rmRepetidas(listaCancionesSinAniadir);
				  listaCancionesSinAniadir = CatalogoCanciones.getUnicaInstancia().rmRepetidas(listaCancionesSinAniadir);
				DefaultTableModel modelAniadidos = (DefaultTableModel) tableCancionesAniadidas.getModel();
				modelAniadidos.setRowCount(0);
				
				DefaultTableModel modelSinAniadir = (DefaultTableModel) tableCancionesSinAniadir.getModel();
				modelSinAniadir.setRowCount(0);
				
				String auxAutor = "";
				for (Cancion c : listaCancionesAnididas) {
					auxAutor = AppMusicControlador.getInstancia().printAutoresNice(c.getListaInterpretes());
					((DefaultTableModel) tableCancionesAniadidas.getModel()).addRow(new Object[] {
			                c.getTitulo(),	auxAutor});
				}
				
				for (Cancion c : listaCancionesSinAniadir) {
					auxAutor = AppMusicControlador.getInstancia().printAutoresNice(c.getListaInterpretes());
					((DefaultTableModel) tableCancionesSinAniadir.getModel()).addRow(new Object[] {
			                c.getTitulo(),	auxAutor});
				}
				
				conjuntoCancionesAniadidas = (LinkedHashSet<Cancion>) AppMusicControlador.getInstancia().listToSet(listaCancionesAnididas);
				conjuntoCancionesSinAniadir = (LinkedHashSet<Cancion>) AppMusicControlador.getInstancia().listToSet(listaCancionesSinAniadir);
			}
		});
		btnSendToList.setForeground(Color.WHITE);
		btnSendToList.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnSendToList = new GridBagConstraints();
		gbc_btnSendToList.anchor = GridBagConstraints.NORTH;
		gbc_btnSendToList.insets = new Insets(0, 0, 5, 5);
		gbc_btnSendToList.gridx = 5;
		gbc_btnSendToList.gridy = 5;
		panelCrearLista.add(btnSendToList, gbc_btnSendToList);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBackground(Color.GRAY);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.gridwidth = 2;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 6;
		panelCrearLista.add(btnAceptar, gbc_btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.GRAY);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 7;
		gbc_btnCancelar.gridy = 6;
		panelCrearLista.add(btnCancelar, gbc_btnCancelar);
	}

}
