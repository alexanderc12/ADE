package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.ArticuloCientifico;
import persistencia.GestorArchivos;

public class DialogoNuevoArticulo extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txRevista;
	private JTextField txVolumen;
	private JTextField txNumero;
	private JTextField txUrl;
	private JTextField txTitulo;
	private JTextField txAutores;
	private JTextField txPalabrasClave;
	private JTextArea txResumen;
	private JTextArea txReferencias;
	private JTable tablaContenidos;
	private DefaultTableModel modeloTablaContenidos;
	private JButton btnAgregarCapitulo;
	private JButton btnRemoverCapitulo;
	private JButton btnCrearArticulo;
	private JButton btnCancelar;

	public DialogoNuevoArticulo(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setLayout(new FlowLayout());
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_NUEVO_ARTICULO);
		setSize(ConstantesGUI.DIALOGO_NUEVO_ARTICULO_ANCHO, ConstantesGUI.DIALOGO_NUEVO_ARTICULO_ALTO);
		setLocationRelativeTo(null);

		JPanel panelDatosRevista = new JPanel(new FlowLayout());
		panelDatosRevista.setBackground(Color.WHITE);
		panelDatosRevista.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_DATOS_REVISTA));

		txRevista = new JTextField(16);
		txRevista.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_REVISTA));
		panelDatosRevista.add(txRevista);

		txVolumen = new JTextField(7);
		txVolumen.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_VOLUMEN));
		panelDatosRevista.add(txVolumen);

		txNumero = new JTextField(7);
		txNumero.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_NUMERO));
		panelDatosRevista.add(txNumero);

		txUrl = new JTextField(13);
		txUrl.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_URL));
		panelDatosRevista.add(txUrl);

		add(panelDatosRevista);

		JPanel panelDatosArticulo = new JPanel();
		panelDatosArticulo.setLayout(new BoxLayout(panelDatosArticulo, BoxLayout.Y_AXIS));
		panelDatosArticulo.setBackground(Color.WHITE);
		panelDatosArticulo.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_DATOS_ARTICULO));

		txTitulo = new JTextField(52);
		txTitulo.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_TITULO));
		panelDatosArticulo.add(txTitulo);

		txAutores = new JTextField();
		txAutores.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_AUTORES));
		panelDatosArticulo.add(txAutores);

		txPalabrasClave = new JTextField();
		txPalabrasClave.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PALABRAS_CLAVE));
		panelDatosArticulo.add(txPalabrasClave);

		txResumen = new JTextArea(2, 5);
		txResumen.setLineWrap(true);
		txResumen.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_RESUMEN));
		panelDatosArticulo.add(new JScrollPane(txResumen));

		add(panelDatosArticulo);

		JPanel panelContenidos = new JPanel(new FlowLayout());
		panelContenidos.setBackground(Color.WHITE);
		panelContenidos.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_CONTENIDOS));

		btnAgregarCapitulo = new JButton(createImageIcon(ConstantesGUI.ICONO_AGREGAR_CAPITULO));
		btnAgregarCapitulo.setFocusable(false);
		btnAgregarCapitulo.setPreferredSize(new Dimension(25, 25));
		btnAgregarCapitulo.setToolTipText(ConstantesGUI.T_AGREGAR_CAPITULO);
		btnAgregarCapitulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloTablaContenidos.addRow(new String[] { "", "" });
			}
		});
		panelContenidos.add(btnAgregarCapitulo);

		btnRemoverCapitulo = new JButton(createImageIcon(ConstantesGUI.ICONO_ELIMINAR_CAPITULO));
		btnRemoverCapitulo.setPreferredSize(new Dimension(25, 25));
		btnRemoverCapitulo.setFocusable(false);
		btnRemoverCapitulo.setToolTipText(ConstantesGUI.T_ELIMINAR_CAPITULO);
		btnRemoverCapitulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloTablaContenidos.removeRow(tablaContenidos.getSelectedRow());
			}
		});
		panelContenidos.add(btnRemoverCapitulo);

		modeloTablaContenidos = new DefaultTableModel(
				ConstantesGUI.T_COLUMNAS_TABLA_CONTENIDOS, 1);
		tablaContenidos = new JTable(modeloTablaContenidos);
		tablaContenidos.setBackground(Color.WHITE);
		tablaContenidos.setPreferredScrollableViewportSize(new Dimension(360, 90));
		JScrollPane panelTabla = new JScrollPane(tablaContenidos);
		panelContenidos.add(panelTabla);

		add(panelContenidos);

		txReferencias = new JTextArea(2, 52);
		txReferencias.setLineWrap(true);
		txReferencias.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_REFERENCIAS));
		add(new JScrollPane(txReferencias));

		JPanel panelBotones = new JPanel(new GridLayout(1, 2));
		panelContenidos.setBackground(Color.WHITE);

		btnCancelar = new JButton(ConstantesGUI.T_CANCELAR);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				limpiarInterfaz();
			}
		});
		panelBotones.add(btnCancelar);

		btnCrearArticulo = new JButton(ConstantesGUI.T_CREAR_ARTICULO);
		btnCrearArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GestorArchivos.guardarArchivoArticulo(crearArticulo(), ventanaPrincipal);
				dispose();
			}
		});
		panelBotones.add(btnCrearArticulo);

		add(panelBotones);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				limpiarInterfaz();
				super.windowClosing(e);
			}
		});
	}

	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}

	public void limpiarInterfaz() {
		txAutores.setText("");
		txNumero.setText("");
		txPalabrasClave.setText("");
		txReferencias.setText("");
		txResumen.setText("");
		txRevista.setText("");
		txTitulo.setText("");
		txUrl.setText("");
		txVolumen.setText("");
		modeloTablaContenidos = new DefaultTableModel(ConstantesGUI.T_COLUMNAS_TABLA_CONTENIDOS, 1);
		tablaContenidos.setModel(modeloTablaContenidos);
	}

	public ArticuloCientifico crearArticulo(){
		ArticuloCientifico articulo = new ArticuloCientifico(txUrl.getText());
		articulo.setRevista(txRevista.getText());
		articulo.setVolumen(Integer.parseInt(txVolumen.getText()));
		articulo.setNumero(Integer.parseInt(txNumero.getText()));
		articulo.setTitulo(txTitulo.getText());
		articulo.setListaAutores(new ArrayList<String>(Arrays.asList(txAutores.getText().split(","))));
		articulo.setListaPalabrasClave(new ArrayList<String>(Arrays.asList(txPalabrasClave.getText().split(","))));
		articulo.setResumen(txResumen.getText());
		for (int i = 0; i < modeloTablaContenidos.getRowCount(); i++) {
			articulo.agregarTituloCapitulo((String) modeloTablaContenidos.getValueAt(i, 0));
			articulo.agregarContenidoCapitulo((String) modeloTablaContenidos.getValueAt(i, 1));
		}
		articulo.agregarReferencia(txReferencias.getText());
		return articulo;
	}
}
