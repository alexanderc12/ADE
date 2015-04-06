package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.net.URL;

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

public class DialogoNuevoArticulo extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JTextField txRevista;
	private JTextField txVolumen;
	private JTextField txNumero;
	private JTextField txFechaAprobacion;
	private JTextField txFechaRecepcion;
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

	public DialogoNuevoArticulo(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setLayout(new FlowLayout());
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_NUEVO_ARTICULO);
		setSize(ConstantesGUI.DIALOGO_NUEVO_ARTICULO_ANCHO,
				ConstantesGUI.DIALOGO_NUEVO_ARTICULO_ALTO);
		setLocationRelativeTo(null);
		
		JPanel panelDatosRevista = new JPanel(new GridLayout(2, 3));
		panelDatosRevista.setBackground(Color.WHITE);
		panelDatosRevista.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_PANEL_DATOS_REVISTA));

		txRevista = new JTextField(16);
		txRevista.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_REVISTA));
		panelDatosRevista.add(txRevista);
		
		txVolumen = new JTextField(7);
		txVolumen.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_VOLUMEN));
		panelDatosRevista.add(txVolumen);
		
		txNumero = new JTextField(7);
		txNumero.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_NUMERO));
		panelDatosRevista.add(txNumero);
		
		txFechaRecepcion = new JTextField(12);
		txFechaRecepcion.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_FECHA_RECEPCION));
		panelDatosRevista.add(txFechaRecepcion);
		
		txFechaAprobacion = new JTextField(13);
		txFechaAprobacion.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_FECHA_APROBACION));
		panelDatosRevista.add(txFechaAprobacion);
		
		txUrl = new JTextField(13);
		txUrl.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_URL));
		panelDatosRevista.add(txUrl);
		
		add(panelDatosRevista);
		
		JPanel panelDatosArticulo = new JPanel();
		panelDatosArticulo.setLayout(new BoxLayout(panelDatosArticulo,
				BoxLayout.Y_AXIS));
		panelDatosArticulo.setBackground(Color.WHITE);
		panelDatosArticulo.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_PANEL_DATOS_ARTICULO));

		txTitulo = new JTextField(52);
		txTitulo.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_TITULO));
		panelDatosArticulo.add(txTitulo);
		
		txAutores = new JTextField();
		txAutores.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_AUTORES));
		panelDatosArticulo.add(txAutores);
		
		txPalabrasClave = new JTextField();
		txPalabrasClave.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_PALABRAS_CLAVE));
		panelDatosArticulo.add(txPalabrasClave);
		
		txResumen = new JTextArea(2, 5);
		txResumen.setLineWrap(true);
		txResumen.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_RESUMEN));
		panelDatosArticulo.add(new JScrollPane(txResumen));
		
		add(panelDatosArticulo);

		JPanel panelContenidos = new JPanel(new FlowLayout());
		panelContenidos.setBackground(Color.WHITE);
		panelContenidos.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_PANEL_CONTENIDOS));
		
		btnAgregarCapitulo = new JButton(
				createImageIcon(ConstantesGUI.ICONO_AGREGAR_CAPITULO));
		btnAgregarCapitulo.setFocusable(false);
		btnAgregarCapitulo.setPreferredSize(new Dimension(25, 25));
		btnAgregarCapitulo.setToolTipText(ConstantesGUI.T_AGREGAR_CAPITULO);
		// tbnAgregarCapitulo.addActionListener(controlador);
		panelContenidos.add(btnAgregarCapitulo);
		
		btnRemoverCapitulo = new JButton(
				createImageIcon(ConstantesGUI.ICONO_ELIMINAR_CAPITULO));
		btnRemoverCapitulo.setPreferredSize(new Dimension(25, 25));
		btnRemoverCapitulo.setFocusable(false);
		btnRemoverCapitulo.setToolTipText(ConstantesGUI.T_ELIMINAR_CAPITULO);
		// tbnRemoverCapitulo.addActionListener(controlador);
		panelContenidos.add(btnRemoverCapitulo);

		modeloTablaContenidos = new DefaultTableModel(
				ConstantesGUI.T_COLUMNAS_TABLA_CONTENIDOS, 1);
		tablaContenidos = new JTable(modeloTablaContenidos);
		tablaContenidos.setBackground(Color.WHITE);
		tablaContenidos.setPreferredScrollableViewportSize(new Dimension(360,
				60));
		JScrollPane panelTabla = new JScrollPane(tablaContenidos);
		panelContenidos.add(panelTabla);
		
		add(panelContenidos);

		txReferencias = new JTextArea(2, 52);
		txReferencias.setLineWrap(true);
		txReferencias.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_REFERENCIAS));
		add(new JScrollPane(txReferencias));
	}
	

	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}
}
