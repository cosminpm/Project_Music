package umu.tds;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.SwingConstants;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Insets;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import pulsador.Luz;
import java.beans.PropertyChangeListener;
//import java.io.File;
import java.beans.PropertyChangeEvent;

public class PanelPrincipal {
	private String nombre = "";
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPrincipal window = new PanelPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PanelPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	public void mostrarVentana() {
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		}
	
	private void initialize() {
		Usuario usuario =AppMusicControlador.getInstancia().getUsuarioActual();
		nombre = usuario.getNombre() +" " +usuario.getApellidos();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 10, 10, 10, 10, 10, 10, 10, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel nombreUsuario = new JLabel(nombre);
		nombreUsuario.setForeground(Color.BLACK);
		nombreUsuario.setBackground(new Color(255, 0, 102));
		nombreUsuario.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		GridBagConstraints gbc_nombreUsuario = new GridBagConstraints();
		gbc_nombreUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_nombreUsuario.gridx = 1;
		gbc_nombreUsuario.gridy = 1;
		frame.getContentPane().add(nombreUsuario, gbc_nombreUsuario);
		
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
		gbc_luz.gridx = 5;
		gbc_luz.gridy = 1;
		frame.getContentPane().add(luz, gbc_luz);
		
		JButton btnPremium = new JButton("MEJORAR CUENTA");
		btnPremium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(btnPremium,"Confirmación", "¿Quieres ser usuario premium?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					VentanaPremium ventanaPremium = new VentanaPremium();
					ventanaPremium.setVisible(true);
					frame.setVisible(false);
					AppMusicControlador.getInstancia().setPremium(true);
				}
				
				else {
					
				}
			}
		});
		btnPremium.setForeground(Color.WHITE);
		btnPremium.setBackground(Color.BLACK);
		GridBagConstraints gbc_btnPremium = new GridBagConstraints();
		gbc_btnPremium.anchor = GridBagConstraints.EAST;
		gbc_btnPremium.insets = new Insets(0, 0, 5, 5);
		gbc_btnPremium.gridx = 8;
		gbc_btnPremium.gridy = 1;
		frame.getContentPane().add(btnPremium, gbc_btnPremium);
		
		JButton btnLogout = new JButton("SALIR");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setBackground(Color.BLACK);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.anchor = GridBagConstraints.EAST;
		gbc_btnLogout.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogout.gridx = 9;
		gbc_btnLogout.gridy = 1;
		frame.getContentPane().add(btnLogout, gbc_btnLogout);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.gridheight = 7;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 0, 0, 10, 5, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 30, 0, 30, 0, 30, 0, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnExplorar = new JButton("");
		btnExplorar.setBorderPainted(false);
		btnExplorar.setContentAreaFilled(false);
		btnExplorar.setBackground(new Color(240, 240, 240));
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					VentanaExplorar ventanaExplorar = new VentanaExplorar();
					ventanaExplorar.setVisible(true);
					frame.setVisible(false);
						
				}			
		});
		btnExplorar.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/umu/tds/imagenes/LupaIcon.jpg")));
		GridBagConstraints gbc_btnExplorar = new GridBagConstraints();
		gbc_btnExplorar.insets = new Insets(0, 0, 5, 5);
		gbc_btnExplorar.gridx = 1;
		gbc_btnExplorar.gridy = 1;
		panel.add(btnExplorar, gbc_btnExplorar);
		
		JLabel lbExplorar = new JLabel("EXPLORAR");
		lbExplorar.setBackground(Color.WHITE);
		lbExplorar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbExplorar.setHorizontalAlignment(SwingConstants.LEFT);
		lbExplorar.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lbExplorar = new GridBagConstraints();
		gbc_lbExplorar.insets = new Insets(0, 0, 5, 5);
		gbc_lbExplorar.anchor = GridBagConstraints.WEST;
		gbc_lbExplorar.gridx = 4;
		gbc_lbExplorar.gridy = 1;
		panel.add(lbExplorar, gbc_lbExplorar);
		
		JButton btnNuevaLista = new JButton("");
		btnNuevaLista.setContentAreaFilled(false);
		btnNuevaLista.setBorderPainted(false);
		btnNuevaLista.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/umu/tds/imagenes/NuevaListaIcon.jpg")));
		GridBagConstraints gbc_btnNuevaLista = new GridBagConstraints();
		gbc_btnNuevaLista.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaLista.gridx = 1;
		gbc_btnNuevaLista.gridy = 3;
		panel.add(btnNuevaLista, gbc_btnNuevaLista);
		
		JLabel lblNuevaLista = new JLabel("NUEVA LISTA");
		lblNuevaLista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNuevaLista.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNuevaLista = new GridBagConstraints();
		gbc_lblNuevaLista.anchor = GridBagConstraints.WEST;
		gbc_lblNuevaLista.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuevaLista.gridx = 4;
		gbc_lblNuevaLista.gridy = 3;
		panel.add(lblNuevaLista, gbc_lblNuevaLista);
		
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					VentanaNuevaLista ventanaNuevaLista = new VentanaNuevaLista();
					ventanaNuevaLista.setVisible(true);
					frame.setVisible(false);
				}			
		});			
		
		JButton btnReciente = new JButton("");
		btnReciente.setContentAreaFilled(false);
		btnReciente.setBorderPainted(false);
		btnReciente.setSelected(true);
		btnReciente.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/umu/tds/imagenes/RecentIcon.jpg")));
		GridBagConstraints gbc_btnReciente = new GridBagConstraints();
		gbc_btnReciente.insets = new Insets(0, 0, 5, 5);
		gbc_btnReciente.gridx = 1;
		gbc_btnReciente.gridy = 5;
		panel.add(btnReciente, gbc_btnReciente);
		
		btnReciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					VentanaRecientes ventanaRecientes = new VentanaRecientes();
					ventanaRecientes.setVisible(true);
					frame.setVisible(false);
				}			
		});		
		
		JLabel lbReciente = new JLabel("RECIENTE");
		lbReciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbReciente.setIcon(null);
		lbReciente.setForeground(Color.WHITE);
		GridBagConstraints gbc_lbReciente = new GridBagConstraints();
		gbc_lbReciente.anchor = GridBagConstraints.WEST;
		gbc_lbReciente.insets = new Insets(0, 0, 5, 5);
		gbc_lbReciente.gridx = 4;
		gbc_lbReciente.gridy = 5;
		panel.add(lbReciente, gbc_lbReciente);
		
		JButton btnMisListas = new JButton("");
		btnMisListas.setContentAreaFilled(false);
		btnMisListas.setBorderPainted(false);
		btnMisListas.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/umu/tds/imagenes/ListIcon.jpg")));
		GridBagConstraints gbc_btnMisListas = new GridBagConstraints();
		gbc_btnMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_btnMisListas.gridx = 1;
		gbc_btnMisListas.gridy = 7;
		panel.add(btnMisListas, gbc_btnMisListas);
		btnMisListas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					VentanaMisListas ventanaMisListas = new VentanaMisListas();
					ventanaMisListas.setVisible(true);
					frame.setVisible(false);
				}			
		});		
		JLabel lblMisListas = new JLabel("MIS LISTAS");
		lblMisListas.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblMisListas = new GridBagConstraints();
		gbc_lblMisListas.anchor = GridBagConstraints.WEST;
		gbc_lblMisListas.insets = new Insets(0, 0, 5, 5);
		gbc_lblMisListas.gridx = 4;
		gbc_lblMisListas.gridy = 7;
		panel.add(lblMisListas, gbc_lblMisListas);
	}
	

}