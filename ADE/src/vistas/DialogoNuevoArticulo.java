package vistas;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DialogoNuevoArticulo extends JDialog {
	
	/**
	 *
	 */
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
	private JTextField txTituloCapitulo;
	private JTextArea txContenidoCapitulo;
	private JTextArea txReferencias;

	public DialogoNuevoArticulo(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setLayout(new GridLayout(4, 1));
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_NUEVO_ARTICULO);
		setSize(ConstantesGUI.DIALOGO_NUEVO_ARTICULO_ANCHO,
				ConstantesGUI.DIALOGO_NUEVO_ARTICULO_ALTO);
		setLocationRelativeTo(null);
		
		JPanel panelDatosRevista = new JPanel(new FlowLayout());

		txRevista = new JTextField(20);
		txRevista.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_REVISTA));
		panelDatosRevista.add(txRevista);
		
		txVolumen = new JTextField(10);
		txVolumen.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_VOLUMEN));
		panelDatosRevista.add(txVolumen);
		
		txNumero = new JTextField(10);
		txNumero.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_NUMERO));
		panelDatosRevista.add(txNumero);
		
		txFechaRecepcion = new JTextField(15);
		txFechaRecepcion.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_FECHA_RECEPCION));
		panelDatosRevista.add(txFechaRecepcion);
		
		txFechaAprobacion = new JTextField(15);
		txFechaAprobacion.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_FECHA_APROBACION));
		panelDatosRevista.add(txFechaAprobacion);
		
		txUrl = new JTextField(20);
		txUrl.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_URL));
		panelDatosRevista.add(txUrl);
		
		add(panelDatosRevista);

		JPanel panelDatosArticulo = new JPanel(new FlowLayout());

		txTitulo = new JTextField(30);
		txTitulo.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_TITULO));
		panelDatosArticulo.add(txTitulo);
		
		txAutores = new JTextField(20);
		txAutores.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_AUTORES));
		panelDatosArticulo.add(txAutores);
		
		txPalabrasClave = new JTextField(30);
		txPalabrasClave.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_PALABRAS_CLAVE));
		panelDatosArticulo.add(txPalabrasClave);
		
		txResumen = new JTextArea(5, 300);
		txResumen.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_RESUMEN));
		panelDatosArticulo.add(txResumen);
		
		add(panelDatosArticulo);

		JPanel panelContenidos = new JPanel(new FlowLayout());

		txTituloCapitulo = new JTextField(30);
		txTituloCapitulo.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_TITULOS_CAPITULOS));
		panelContenidos.add(txTituloCapitulo);
		
		txContenidoCapitulo = new JTextArea(5, 300);
		txContenidoCapitulo.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_CONTENIDO_CAPITULOS));
		panelContenidos.add(txContenidoCapitulo);
		
		add(panelContenidos);

		txReferencias = new JTextArea();
		txReferencias.setBorder(BorderFactory
				.createTitledBorder(ConstantesGUI.T_REFERENCIAS));
		add(txReferencias);
	}

}
