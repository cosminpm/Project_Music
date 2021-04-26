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
import umu.tds.modelo.ListaCanciones;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.util.*;
import pulsador.Luz;


public class VentanaMasReproducida extends JDialog {
	private JTable tablaMasReproducidas;

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
	public VentanaMasReproducida() {

		
		getContentPane().setBackground(Color.WHITE);
		setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 10, 10, 10, 189, 10, 10, 30, 30, 30, 30, 0, 0, 10, 0, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		Usuario usuario =AppMusicControlador.getInstancia().getUsuarioActual();
		String nombre = usuario.getNombre() +" " +usuario.getApellidos();
		
		JLabel lbnombreUsuario = new JLabel(nombre);
		GridBagConstraints gbc_lbnombreUsuario = new GridBagConstraints();
		gbc_lbnombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lbnombreUsuario.gridx = 4;
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
		gbc_luz.gridx = 10;
		gbc_luz.gridy = 1;
		getContentPane().add(luz, gbc_luz);
		
		JButton btnMejorarCuenta = new JButton("MEJORAR CUENTA");
		btnMejorarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(JOptionPane.showConfirmDialog(btnMejorarCuenta,"Confirmación", "¿Quieres ser usuario premium?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					VentanaPremium ventanaPremium = new VentanaPremium();
					ventanaPremium.setVisible(true);
					dispose();
					AppMusicControlador.getInstancia().setPremium(true);
					
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
		gbc_btnMejorarCuenta.gridx = 11;
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
		gbc_btnSALIR.gridx = 12;
		gbc_btnSALIR.gridy = 1;
		getContentPane().add(btnSALIR, gbc_btnSALIR);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 10;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 4;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		btnExplorar.setIcon(new ImageIcon(VentanaMasReproducida.class.getResource("/umu/tds/imagenes/LupaIcon.jpg")));
		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.anchor = GridBagConstraints.EAST;
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
		
		JButton btnMasReproducidas = new JButton("");
		btnMasReproducidas.setContentAreaFilled(false);
		btnMasReproducidas.setIcon(new ImageIcon(VentanaMasReproducida.class.getResource("/umu/tds/imagenes/TopIconReversed.jpg")));
		GridBagConstraints gbc_btnMasReproducidas = new GridBagConstraints();
		gbc_btnMasReproducidas.insets = new Insets(0, 0, 0, 5);
		gbc_btnMasReproducidas.gridx = 0;
		gbc_btnMasReproducidas.gridy = 9;
		panel.add(btnMasReproducidas, gbc_btnMasReproducidas);
		
		JLabel lblNewLabel = new JLabel("MAS REPRODUCIDAS");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 9;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 10;
		gbc_panel_1.gridwidth = 5;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 8;
		gbc_panel_1.gridy = 4;
		getContentPane().add(panel_1, gbc_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		tablaMasReproducidas = new JTable();
		tablaMasReproducidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CANCION", "INTERPRETE", "N. REPRO"
			}
		));
		tablaMasReproducidas.getColumnModel().getColumn(0).setPreferredWidth(59);
		scrollPane.setViewportView(tablaMasReproducidas);
		
		
		String[] array = AppMusicControlador.getInstancia().recuperarEstilos().stream().toArray(String[]::new);
		
		//Rellenar tabla
		List<Cancion> listaCanciones = AppMusicControlador.getInstancia().obtenerMasReproducidas();
		for (Cancion c : listaCanciones) {
				String autores = "";
				autores = AppMusicControlador.getInstancia().printAutoresNice(c.getListaInterpretes());
				((DefaultTableModel) tablaMasReproducidas.getModel()).addRow(new Object[] {c.getTitulo(), autores, c.getNumReproducciones()});
			}
	
	}
}