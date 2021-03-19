package umu.tds;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.AppMusicControlador;
import umu.tds.modelo.DescuentoFijo;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VentanaPremium extends JDialog {
	private JTable tablaPrecios;

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
		gridBagLayout.columnWidths = new int[]{10, 10, 10, 0, 0, 0, 0, 10, 10, 0, 10, 10, 0, 10, 10, 10, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblTextoMejora = new JLabel("MEJORA PREMIUM");
		lblTextoMejora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblTextoMejora = new GridBagConstraints();
		gbc_lblTextoMejora.insets = new Insets(0, 0, 5, 5);
		gbc_lblTextoMejora.gridx = 5;
		gbc_lblTextoMejora.gridy = 2;
		getContentPane().add(lblTextoMejora, gbc_lblTextoMejora);
		
		tablaPrecios = new JTable();
		GridBagConstraints gbc_tablaPrecios = new GridBagConstraints();
		gbc_tablaPrecios.insets = new Insets(0, 0, 5, 5);
		gbc_tablaPrecios.fill = GridBagConstraints.BOTH;
		gbc_tablaPrecios.gridx = 9;
		gbc_tablaPrecios.gridy = 4;
		getContentPane().add(tablaPrecios, gbc_tablaPrecios);
		tablaPrecios.setModel(new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"PRECIO INICIAL", "DESCUENTO", "PRECIO FINAL"
				}
			));
		
		
		String[] array = {Constantes.descuentoFijo, Constantes.descuentoJoven};
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<String>(array));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 9;
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
					System.out.println("EL DESCUENTO ES: "+descuento2);
					 System.out.println("AAA");
					 System.out.println(AppMusicControlador.getInstancia().getTipoDescuento());
					 DefaultTableModel model = (DefaultTableModel) tablaPrecios.getModel();
					 model.setRowCount(0);
					 ((DefaultTableModel) tablaPrecios.getModel()).addRow(new Object[] {
				                precioInicial+"€",cantidadDescontada+"€",precioInicial-cantidadDescontada+"€"});
						
					 
				}
				else if(descuento.equals(Constantes.descuentoJoven)) {
					 AppMusicControlador.getInstancia().setTipoDescuento(descuento);
					cantidadDescontada = AppMusicControlador.getInstancia().calcularDescuentoJoven();
					System.out.println("BBB");
					System.out.println(AppMusicControlador.getInstancia().getTipoDescuento());
					System.out.println(AppMusicControlador.getInstancia().getTipoDescuento());
					DefaultTableModel model = (DefaultTableModel) tablaPrecios.getModel();
					model.setRowCount(0);
					 ((DefaultTableModel) tablaPrecios.getModel()).addRow(new Object[] {
				                precioInicial+"€",cantidadDescontada+"€",precioInicial-cantidadDescontada+"€"});
				}
				
			}
			    
			    
			
		});
		btnCalcularDescuento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_btnCalcularDescuento = new GridBagConstraints();
		gbc_btnCalcularDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcularDescuento.gridx = 12;
		gbc_btnCalcularDescuento.gridy = 3;
		getContentPane().add(btnCalcularDescuento, gbc_btnCalcularDescuento);
		
		//int precioInicial = 20;
		
		
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
		
		
		JButton btnPagar = new JButton("PAGAR");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppMusicControlador.getInstancia().setPremium(AppMusicControlador.getInstancia().getUsuarioActual(), true);
			}
		});
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_btnPagar = new GridBagConstraints();
		gbc_btnPagar.insets = new Insets(0, 0, 5, 5);
		gbc_btnPagar.gridx = 3;
		gbc_btnPagar.gridy = 9;
		getContentPane().add(btnPagar, gbc_btnPagar);
		
		JButton btnCancelar = new JButton("CANCELAR SUSCRIPCIÓN");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppMusicControlador.getInstancia().setPremium(AppMusicControlador.getInstancia().getUsuarioActual(), false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 9;
		gbc_btnCancelar.gridy = 9;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		{		
	
		}
	}

	
}
