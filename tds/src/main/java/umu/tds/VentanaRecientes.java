package umu.tds;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;
import javax.swing.JScrollPane;
import pulsador.Luz;
import java.awt.Dimension;

public class VentanaRecientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaCancionesRecientes;

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
	public VentanaRecientes() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40, 189, 10, 10, 30, 30, 0, 0, 10, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 20, 0, 0, 0, 0, 0, 40, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Usuario usuario = AppMusicControlador.getInstancia().getUsuarioActual();
		String nombre = usuario.getNombre() + " " + usuario.getApellidos();

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

				if (chooser.getSelectedFile() != null) {

					String fichero = chooser.getSelectedFile().getAbsolutePath();
					AppMusicControlador.getInstancia().cargarCanciones(fichero);
				}
			}
		});
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.insets = new Insets(0, 0, 5, 5);
		gbc_luz.gridx = 4;
		gbc_luz.gridy = 1;
		getContentPane().add(luz, gbc_luz);

		JButton btnSALIR = new JButton("SALIR");
		btnSALIR.setPreferredSize(new Dimension(123, 23));
		btnSALIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JButton btnMejorarCuenta = new JButton("MEJORAR CUENTA");
		btnMejorarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(btnMejorarCuenta, "Confirmación", "¿Quieres ser usuario premium?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					VentanaPremium ventanaPremium = new VentanaPremium();
					ventanaPremium.setVisible(true);
					dispose();

				}

				else {

				}
			}
		});
		btnMejorarCuenta.setBorderPainted(false);
		btnMejorarCuenta.setForeground(Color.WHITE);
		btnMejorarCuenta.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnMejorarCuenta = new GridBagConstraints();
		gbc_btnMejorarCuenta.anchor = GridBagConstraints.WEST;
		gbc_btnMejorarCuenta.insets = new Insets(0, 0, 5, 5);
		gbc_btnMejorarCuenta.gridx = 5;
		gbc_btnMejorarCuenta.gridy = 1;
		getContentPane().add(btnMejorarCuenta, gbc_btnMejorarCuenta);
		btnSALIR.setForeground(Color.WHITE);
		btnSALIR.setBackground(Color.BLACK);
		btnSALIR.setBorderPainted(false);
		GridBagConstraints gbc_btnSALIR = new GridBagConstraints();
		gbc_btnSALIR.anchor = GridBagConstraints.EAST;
		gbc_btnSALIR.insets = new Insets(0, 0, 5, 5);
		gbc_btnSALIR.gridx = 7;
		gbc_btnSALIR.gridy = 1;
		getContentPane().add(btnSALIR, gbc_btnSALIR);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 5;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 10, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
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
		btnExplorar.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/LupaIcon.jpg")));
		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.insets = new Insets(0, 0, 5, 5);
		gbc_btnExplorar.gridx = 1;
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
		gbc_btnNuevaLista.gridx = 1;
		gbc_btnNuevaLista.gridy = 3;
		panel.add(btnNuevaLista, gbc_btnNuevaLista);

		JLabel lblNuevaLista = new JLabel("NUEVA LISTA");
		lblNuevaLista.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblNuevaLista = new GridBagConstraints();
		gbc_lblNuevaLista.insets = new Insets(0, 0, 5, 0);
		gbc_lblNuevaLista.gridx = 2;
		gbc_lblNuevaLista.gridy = 3;
		panel.add(lblNuevaLista, gbc_lblNuevaLista);

		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4
				.setIcon(new ImageIcon(VentanaRecientes.class.getResource("/umu/tds/imagenes/RecentIconReverse.jpg")));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 5;
		panel.add(btnNewButton_4, gbc_btnNewButton_4);

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
		btnMisListas.setIcon(new ImageIcon(VentanaRecientes.class.getResource("/umu/tds/imagenes/ListIcon.jpg")));
		btnMisListas.setContentAreaFilled(false);
		btnMisListas.setBorderPainted(false);
		GridBagConstraints gbc_btnMisListas = new GridBagConstraints();
		gbc_btnMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_btnMisListas.gridx = 1;
		gbc_btnMisListas.gridy = 7;
		panel.add(btnMisListas, gbc_btnMisListas);

		JLabel lblMisListas = new JLabel("MIS LISTAS");
		lblMisListas.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblMisListas = new GridBagConstraints();
		gbc_lblMisListas.insets = new Insets(0, 0, 5, 0);
		gbc_lblMisListas.gridx = 2;
		gbc_lblMisListas.gridy = 7;
		panel.add(lblMisListas, gbc_lblMisListas);

		JButton btnMasReproducidas = new JButton((String) null);
		btnMasReproducidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMasReproducida ventanaMasReproducida = new VentanaMasReproducida();
				ventanaMasReproducida.setVisible(true);
				dispose();
			}
		});
		btnMasReproducidas.setContentAreaFilled(false);
		btnMasReproducidas.setIcon(new ImageIcon(VentanaRecientes.class.getResource("/umu/tds/imagenes/TopIcon.jpg")));
		GridBagConstraints gbc_btnMasReproducidas = new GridBagConstraints();
		gbc_btnMasReproducidas.insets = new Insets(0, 0, 5, 5);
		gbc_btnMasReproducidas.gridx = 1;
		gbc_btnMasReproducidas.gridy = 9;
		panel.add(btnMasReproducidas, gbc_btnMasReproducidas);

		JLabel lblMasReproducidas = new JLabel("MAS REPRODUCIDAS");
		lblMasReproducidas.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblMasReproducidas = new GridBagConstraints();
		gbc_lblMasReproducidas.insets = new Insets(0, 0, 5, 0);
		gbc_lblMasReproducidas.gridx = 2;
		gbc_lblMasReproducidas.gridy = 9;
		panel.add(lblMasReproducidas, gbc_lblMasReproducidas);

		JPanel panelCanciones = new JPanel();
		panelCanciones.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelCanciones = new GridBagConstraints();
		gbc_panelCanciones.gridheight = 5;
		gbc_panelCanciones.gridwidth = 3;
		gbc_panelCanciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelCanciones.fill = GridBagConstraints.BOTH;
		gbc_panelCanciones.gridx = 5;
		gbc_panelCanciones.gridy = 3;
		getContentPane().add(panelCanciones, gbc_panelCanciones);
		GridBagLayout gbl_panelCanciones = new GridBagLayout();
		gbl_panelCanciones.columnWidths = new int[] { 10, 10, 10, 50, 0, 0, 0, 0, 0 };
		gbl_panelCanciones.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCanciones.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelCanciones.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelCanciones.setLayout(gbl_panelCanciones);

		JScrollPane scrllPane = new JScrollPane();
		scrllPane.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrllPane = new GridBagConstraints();
		gbc_scrllPane.gridwidth = 8;
		gbc_scrllPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrllPane.fill = GridBagConstraints.BOTH;
		gbc_scrllPane.gridx = 0;
		gbc_scrllPane.gridy = 0;
		panelCanciones.add(scrllPane, gbc_scrllPane);
		List<Cancion> listaCancionesSeleccionada = new LinkedList<Cancion>();
		tablaCancionesRecientes = new JTable();
		tablaCancionesRecientes
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00CDTULO", "INT\u00C9RPRETE" }));
		scrllPane.setViewportView(tablaCancionesRecientes);
		// Rellenar tabla
		List<ListaCanciones> listaPlaylistUsuario = usuario.getListaPlayList();
		for (ListaCanciones listaCanciones : listaPlaylistUsuario) {
			if (listaCanciones.getNombre().equals("Recientes")) {
				String autores = "";
				for (Cancion cancion : listaCanciones.getCanciones()) {
					autores = AppMusicControlador.getInstancia().printAutoresNice(cancion.getListaInterpretes());
					((DefaultTableModel) tablaCancionesRecientes.getModel())
							.addRow(new Object[] { cancion.getTitulo(), autores });
					listaCancionesSeleccionada.add(cancion);
				}
			}
		}

		JButton btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indiceSeleccionado = tablaCancionesRecientes.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionParaReproducir = listaCancionesSeleccionada.get(indiceSeleccionado);
					AppMusicControlador.getInstancia().play(cancionParaReproducir);
				}
			}
		});
		btnPlay.setContentAreaFilled(false);
		btnPlay.setBorderPainted(false);
		btnPlay.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/PlayIcon.jpg")));
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 5, 5);
		gbc_btnPlay.gridx = 5;
		gbc_btnPlay.gridy = 9;
		panelCanciones.add(btnPlay, gbc_btnPlay);

		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indiceSeleccionado = tablaCancionesRecientes.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionParaParar;
					Cancion cancionParaReproducir;
					// Comprobar si es el primero, indiceSeleccionado == 0
					// Si es la primera, cambiar a la ultima
					if (indiceSeleccionado == 0) {
						int aux = listaCancionesSeleccionada.size() - 1;
						cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
						AppMusicControlador.getInstancia().play(cancionParaParar);
						AppMusicControlador.getInstancia().stop(cancionParaParar);
						cancionParaReproducir = listaCancionesSeleccionada.get(aux);
						AppMusicControlador.getInstancia().play(cancionParaReproducir);
					} else {
						cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
						AppMusicControlador.getInstancia().play(cancionParaParar);
						AppMusicControlador.getInstancia().stop(cancionParaParar);
						cancionParaReproducir = listaCancionesSeleccionada.get(indiceSeleccionado - 1);
						AppMusicControlador.getInstancia().play(cancionParaReproducir);
					}
				}
			}
		});
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/BackSongIcon.jpg")));
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 4;
		gbc_btnBack.gridy = 10;
		panelCanciones.add(btnBack, gbc_btnBack);

		JButton btnStop = new JButton("");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int indiceSeleccionado = tablaCancionesRecientes.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionPausa = listaCancionesSeleccionada.get(indiceSeleccionado);
					AppMusicControlador.getInstancia().stop(cancionPausa);
				}
			}
		});
		btnStop.setBorderPainted(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/PauseIcon.jpg")));
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 5;
		gbc_btnStop.gridy = 10;
		panelCanciones.add(btnStop, gbc_btnStop);

		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int indiceSeleccionado = tablaCancionesRecientes.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionParaParar;
					Cancion cancionParaReproducir;
					// Comprobar si es el último, indiceSeleccionado == size - 1
					// Si es la ultima cambiar a la primera
					if (indiceSeleccionado == listaCancionesSeleccionada.size() - 1) {
						int aux = 0;
						cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
						AppMusicControlador.getInstancia().play(cancionParaParar);
						AppMusicControlador.getInstancia().stop(cancionParaParar);
						cancionParaReproducir = listaCancionesSeleccionada.get(aux);
						AppMusicControlador.getInstancia().play(cancionParaReproducir);
					}

					else {
						cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
						AppMusicControlador.getInstancia().play(cancionParaParar);
						AppMusicControlador.getInstancia().stop(cancionParaParar);
						cancionParaReproducir = listaCancionesSeleccionada.get(indiceSeleccionado + 1);
						AppMusicControlador.getInstancia().play(cancionParaReproducir);
					}
				}
			}
		});
		btnNext.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/NextSongIcon.jpg")));
		btnNext.setBorderPainted(false);
		btnNext.setContentAreaFilled(false);
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.insets = new Insets(0, 0, 0, 5);
		gbc_btnNext.gridx = 6;
		gbc_btnNext.gridy = 10;
		panelCanciones.add(btnNext, gbc_btnNext);

		if (!usuario.getEsPremium()) {
			btnMasReproducidas.setVisible(false);
			lblMasReproducidas.setVisible(false);
		} else {
			btnMasReproducidas.setVisible(true);
			lblMasReproducidas.setVisible(true);
		}

	}

}
