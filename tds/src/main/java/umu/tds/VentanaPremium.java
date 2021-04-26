package umu.tds;



import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusicControlador;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

import java.text.DecimalFormat;

public class VentanaPremium extends JDialog {
	private JTable tablaPrecios;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			VentanaPremium dialog = new VentanaPremium();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPremium() {
		setBounds(Constantes.ventana_x_size, Constantes.ventana_y_size, Constantes.x_size, Constantes.y_size);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 10, 10, 0, 10, 10, 0, 10, 10, 10, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTextoMejora = new JLabel("MEJORA PREMIUM");
		lblTextoMejora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTextoMejora = new GridBagConstraints();
		gbc_lblTextoMejora.insets = new Insets(0, 0, 5, 5);
		gbc_lblTextoMejora.gridx = 8;
		gbc_lblTextoMejora.gridy = 2;
		getContentPane().add(lblTextoMejora, gbc_lblTextoMejora);
		
		
		String[] array = {Constantes.descuentoFijo, Constantes.descuentoJoven};
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<String>(array));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 12;
		gbc_comboBox.gridy = 3;
		getContentPane().add(comboBox, gbc_comboBox);
		//int cantidadDescontada = 0;
		int precioInicial = 20;
		JButton btnCalcularDescuento = new JButton("Calcular Descuento");
		btnCalcularDescuento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String descuento = comboBox.getSelectedItem().toString();
				int descuento2 = 0;
				int cantidadDescontada = 0;
				if(descuento.equals(Constantes.descuentoFijo)) {
					 AppMusicControlador.getInstancia().setTipoDescuento(descuento);
					cantidadDescontada = AppMusicControlador.getInstancia().calcularDescuentoFijo();
					 DefaultTableModel model = (DefaultTableModel) tablaPrecios.getModel();
					 model.setRowCount(0);
					 float porcentaje = ((float)cantidadDescontada / (float)precioInicial) *100;
					 DecimalFormat df = new DecimalFormat("##.##");
					 ((DefaultTableModel) tablaPrecios.getModel()).addRow(new Object[] {
				                precioInicial+"€",cantidadDescontada+"€ "+"("+df.format(porcentaje)+"%)",precioInicial-cantidadDescontada+"€"});
					 
				}
				else if(descuento.equals(Constantes.descuentoJoven)) {
					 AppMusicControlador.getInstancia().setTipoDescuento(descuento);
					cantidadDescontada = AppMusicControlador.getInstancia().calcularDescuentoJoven();
					DefaultTableModel model = (DefaultTableModel) tablaPrecios.getModel();
					model.setRowCount(0);
					float porcentaje =  ((float)cantidadDescontada / (float)precioInicial)* 100;
					DecimalFormat df = new DecimalFormat("##.##");
					 ((DefaultTableModel) tablaPrecios.getModel()).addRow(new Object[] {
				                precioInicial+"€",cantidadDescontada+"€ "+"("+df.format(porcentaje)+"%)",precioInicial-cantidadDescontada+"€"});
				}
				
			}
			    
			    
			
		});
		btnCalcularDescuento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnCalcularDescuento = new GridBagConstraints();
		gbc_btnCalcularDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcularDescuento.gridx = 15;
		gbc_btnCalcularDescuento.gridy = 3;
		getContentPane().add(btnCalcularDescuento, gbc_btnCalcularDescuento);
		
		/*int precioInicial = 20;
		
		
		//int cantidadDescontada = 0;
		if (AppMusicControlador.getInstancia().compararDescuento(Constantes.descuentoFijo)) {
			System.out.println("ES DESCUENTO FIJO");
			//cantidadDescontada = AppMusicControlador.getInstancia().calcularDescuentoFijo();
			//cantidadDescontada = AppMusicControlador.getInstancia().calcularDescuentoFijo();
		}
		
		else if (AppMusicControlador.getInstancia().compararDescuento(Constantes.descuentoJoven)) {
			System.out.println("ES DESCUENTO JOVEN");
			//cantidadDescontada = AppMusicControlador.getInstancia().calcularDescuentoJoven();
		}
		
		
		//int precioFinal = precioInicial - cantidadDescontada;
		*/
		
		JButton btnPagar = new JButton("PAGAR");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(AppMusicControlador.getInstancia().getUsuarioActual().getEsPremium()) {
					JOptionPane.showMessageDialog(btnPagar, "¡¡¡Ya eras premium!!!, No hace falta que vuelvas a sucribirte", "InfoPremium", JOptionPane.INFORMATION_MESSAGE, null);
				}
				else {
					JOptionPane.showMessageDialog(btnPagar, "Bienvenido a la familia, ¡¡Ya eres premium!!", "InfoPremium", JOptionPane.INFORMATION_MESSAGE, null);
				}
				AppMusicControlador.getInstancia().setPremium(true);
			}
		});
		

		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 0, 51));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
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
		btnNuevaLista.setIcon(new ImageIcon(PanelPrincipal.class.getResource("/umu/tds/imagenes/NuevaListaIcon.jpg")));
		GridBagConstraints gbc_btnNuevaLista = new GridBagConstraints();
		gbc_btnNuevaLista.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaLista.gridx = 0;
		gbc_btnNuevaLista.gridy = 3;
		panel.add(btnNuevaLista, gbc_btnNuevaLista);
		
		btnNuevaLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					VentanaNuevaLista ventanaNuevaLista = new VentanaNuevaLista();
					ventanaNuevaLista.setVisible(true);
					dispose();
				}			
		});		
		
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
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaMasReproducida ventanaMasReproducida= new VentanaMasReproducida();
				ventanaMasReproducida.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(VentanaPremium.class.getResource("/umu/tds/imagenes/TopIcon.jpg")));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel = new JLabel("MAS REPRODUCIDAS");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 9;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 12;
		gbc_panel_1.gridy = 4;
		getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{25, 25, 0, 20, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_tablaPrecios = new GridBagConstraints();
		gbc_tablaPrecios.insets = new Insets(0, 0, 5, 5);
		gbc_tablaPrecios.fill = GridBagConstraints.BOTH;
		gbc_tablaPrecios.gridx = 9;
		gbc_tablaPrecios.gridy = 5;
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		tablaPrecios = new JTable();
		//getContentPane().add(tablaPrecios, gbc_table);
		tablaPrecios.setModel(new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"PRECIO INICIAL", "DESCUENTO", "PRECIO FINAL"
				}
			));
		scrollPane.setViewportView(tablaPrecios);
		
		
		
		/*tablaPrecios = new JTable();
		GridBagConstraints gbc_tablaPrecios = new GridBagConstraints();
		gbc_tablaPrecios.insets = new Insets(0, 0, 5, 5);
		gbc_tablaPrecios.fill = GridBagConstraints.BOTH;
		gbc_tablaPrecios.gridx = 9;
		gbc_tablaPrecios.gridy = 5;
		getContentPane().add(tablaPrecios, gbc_tablaPrecios);
		tablaPrecios.setModel(new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"PRECIO INICIAL", "DESCUENTO", "PRECIO FINAL"
				}
			));
		*/
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnPagar = new GridBagConstraints();
		gbc_btnPagar.insets = new Insets(0, 0, 5, 5);
		gbc_btnPagar.gridx = 6;
		gbc_btnPagar.gridy = 9;
		getContentPane().add(btnPagar, gbc_btnPagar);
		
		JButton btnCancelar = new JButton("CANCELAR SUSCRIPCIÓN");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(AppMusicControlador.getInstancia().getUsuarioActual().getEsPremium()) {
					JOptionPane.showMessageDialog(btnCancelar, "Has cancelado tu suscripcion :(", "InfoPremium", JOptionPane.INFORMATION_MESSAGE, null);
				}
				else
				{
					JOptionPane.showMessageDialog(btnCancelar, "No puedes cancelar tu suscripion si ya estas suscrito", "InfoPremium", JOptionPane.INFORMATION_MESSAGE, null);
				}
				AppMusicControlador.getInstancia().setPremium(false);
			}
		});
		

		
		
		
		
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 12;
		gbc_btnCancelar.gridy = 9;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		{		
	
		}

	}

	
}