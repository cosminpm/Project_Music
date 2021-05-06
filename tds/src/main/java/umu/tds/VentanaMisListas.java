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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.DocumentException;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Cancion;
import umu.tds.modelo.ListaCanciones;
import umu.tds.modelo.Usuario;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;

import pulsador.Luz;
import java.awt.Dimension;

public class VentanaMisListas extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaCanciones;

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
	public VentanaMisListas() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 40, 189, 10, 10, 30, 30, 0, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 20, 0, 0, 0, 0, 0, 0, 40, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
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
		gbc_btnSALIR.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSALIR.insets = new Insets(0, 0, 5, 5);
		gbc_btnSALIR.gridx = 6;
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
		gbl_panel.rowHeights = new int[] { 30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 0, 0, 0, 40, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
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
		gbc_btnExplorar.fill = GridBagConstraints.HORIZONTAL;
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
		gbc_btnNuevaLista.fill = GridBagConstraints.HORIZONTAL;
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
		btnReciente.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/RecentIcon.jpg")));
		GridBagConstraints gbc_btnReciente = new GridBagConstraints();
		gbc_btnReciente.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReciente.insets = new Insets(0, 0, 5, 5);
		gbc_btnReciente.gridx = 1;
		gbc_btnReciente.gridy = 5;
		panel.add(btnReciente, gbc_btnReciente);

		JLabel lblReciente = new JLabel("RECIENTE");
		lblReciente.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblReciente = new GridBagConstraints();
		gbc_lblReciente.insets = new Insets(0, 0, 5, 0);
		gbc_lblReciente.gridx = 2;
		gbc_lblReciente.gridy = 5;
		panel.add(lblReciente, gbc_lblReciente);

		JButton btnNewButton = new JButton("");
		btnNewButton
				.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/ListIconReverse.jpg")));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		panel.add(btnNewButton, gbc_btnNewButton);

		JLabel lblMisListas = new JLabel("MIS LISTAS");
		lblMisListas.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblMisListas = new GridBagConstraints();
		gbc_lblMisListas.insets = new Insets(0, 0, 5, 0);
		gbc_lblMisListas.gridx = 2;
		gbc_lblMisListas.gridy = 7;
		panel.add(lblMisListas, gbc_lblMisListas);

		JButton btnMasReproducidas = new JButton("");
		btnMasReproducidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMasReproducida ventanaMasReproducida = new VentanaMasReproducida();
				ventanaMasReproducida.setVisible(true);
				dispose();
			}
		});
		btnMasReproducidas.setContentAreaFilled(false);
		btnMasReproducidas.setIcon(new ImageIcon(VentanaMisListas.class.getResource("/umu/tds/imagenes/TopIcon.jpg")));
		GridBagConstraints gbc_btnMasReproducidas = new GridBagConstraints();
		gbc_btnMasReproducidas.fill = GridBagConstraints.HORIZONTAL;
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

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 10;
		panel.add(scrollPane, gbc_scrollPane);
		JList listaPlayList = new JList();
		List<Cancion> listaCancionesSeleccionada = new LinkedList<Cancion>();
		listaPlayList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) tablaCanciones.getModel();
				model.setRowCount(0);
				String aux = (String) listaPlayList.getSelectedValue();
				List<ListaCanciones> listaPlaylistUsuario = usuario.getListaPlayList();
				for (ListaCanciones listaCanciones : listaPlaylistUsuario) {
					if (listaCanciones.getNombre().equals(aux)) {
						String autores = "";
						for (Cancion cancion : listaCanciones.getCanciones()) {
							autores = AppMusicControlador.getInstancia()
									.printAutoresNice(cancion.getListaInterpretes());
							((DefaultTableModel) tablaCanciones.getModel())
									.addRow(new Object[] { cancion.getTitulo(), autores });
							listaCancionesSeleccionada.add(cancion);
						}

					}
				}
			}
		});
		listaPlayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPlayList.setForeground(Color.WHITE);
		listaPlayList.setBackground(Color.GRAY);
		// Rellenar lista con nombres playlist del usuario
		List<ListaCanciones> listaPlayListUsu = AppMusicControlador.getInstancia().getUsuarioActual()
				.getListaPlayList();

		List<String> nombresPlaylist = new ArrayList<String>();

		for (ListaCanciones listaCanciones : listaPlayListUsu) {

			nombresPlaylist.add(listaCanciones.getNombre());
		}
		String[] array = nombresPlaylist.toArray(new String[0]);
		listaPlayList.setModel(new AbstractListModel() {
			String[] values = array;

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(listaPlayList);

		JPanel panelCanciones = new JPanel();
		panelCanciones.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelCanciones = new GridBagConstraints();
		gbc_panelCanciones.gridheight = 5;
		gbc_panelCanciones.gridwidth = 2;
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

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 8;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		panelCanciones.add(scrollPane_1, gbc_scrollPane_1);

		tablaCanciones = new JTable();
		tablaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCanciones
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00CDTULO", "INT\u00C9RPRETE" }));
		scrollPane_1.setViewportView(tablaCanciones);

		JButton btnPlay = new JButton("");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int indiceSeleccionado = tablaCanciones.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionParaReproducir = listaCancionesSeleccionada.get(indiceSeleccionado);
					AppMusicControlador.getInstancia().play(cancionParaReproducir);
				}
			}
		});

		JButton btnGenerarPDF = new JButton("GENERAR PDF");
		GridBagConstraints gbc_btnGenerarPDF = new GridBagConstraints();
		gbc_btnGenerarPDF.gridwidth = 8;
		gbc_btnGenerarPDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenerarPDF.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerarPDF.gridx = 0;
		gbc_btnGenerarPDF.gridy = 8;
		panelCanciones.add(btnGenerarPDF, gbc_btnGenerarPDF);
		btnGenerarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (usuario.getEsPremium()) {
					try {
						try {
							AppMusicControlador.getInstancia().generarPDF();
						} catch (DocumentException e) {
							e.printStackTrace();
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				} else {

					JOptionPane.showMessageDialog(btnGenerarPDF, "No eres usuario premium",
							"Funcionalidad no accesible", JOptionPane.ERROR_MESSAGE, null);

				}
			}
		});
		btnGenerarPDF.setForeground(Color.WHITE);
		btnGenerarPDF.setBorderPainted(false);
		btnGenerarPDF.setBackground(Color.BLACK);
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
				int indiceSeleccionado = tablaCanciones.getSelectedRow();
				Cancion cancionParaParar;
				Cancion cancionParaReproducir;
				if (indiceSeleccionado == 0) {

					int aux = listaCancionesSeleccionada.size() - 1;
					cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
					AppMusicControlador.getInstancia().play(cancionParaParar);
					AppMusicControlador.getInstancia().stop(cancionParaParar);
					cancionParaReproducir = listaCancionesSeleccionada.get(aux);
					AppMusicControlador.getInstancia().play(cancionParaReproducir);

				}

				else {
					if (indiceSeleccionado != -1) {
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
				int indiceSeleccionado = tablaCanciones.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionParaReproducir = listaCancionesSeleccionada.get(indiceSeleccionado);
					AppMusicControlador.getInstancia().stop(cancionParaReproducir);
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

				int indiceSeleccionado = tablaCanciones.getSelectedRow();
				if (indiceSeleccionado != -1) {
					Cancion cancionParaParar;
					Cancion cancionParaReproducir;
					// Comprobar si es el último con indiceSeleccionado == size - 1
					// Si es la ultima cancion cambiar a la primera
					if (indiceSeleccionado == listaCancionesSeleccionada.size() - 1) {

						int aux = 0;
						cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
						AppMusicControlador.getInstancia().play(cancionParaParar);
						AppMusicControlador.getInstancia().stop(cancionParaParar);
						cancionParaReproducir = listaCancionesSeleccionada.get(aux);
						AppMusicControlador.getInstancia().play(cancionParaReproducir);
					}

					else {
						if (indiceSeleccionado != -1) {
							cancionParaParar = listaCancionesSeleccionada.get(indiceSeleccionado);
							AppMusicControlador.getInstancia().play(cancionParaParar);
							AppMusicControlador.getInstancia().stop(cancionParaParar);
							cancionParaReproducir = listaCancionesSeleccionada.get(indiceSeleccionado + 1);
							AppMusicControlador.getInstancia().play(cancionParaReproducir);
						}
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
