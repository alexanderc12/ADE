package vistas;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controladores.Controlador;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextPane panelArticulo;
	private PanelResultados panelResultados;
	private StyledDocument documento;
	private SimpleAttributeSet estiloSimple;
	private SimpleAttributeSet estiloTitulo;
	private SimpleAttributeSet estiloTituloCapitulo;
	public static final String ESTILO_NORMAL = "NORMAL";
	public static final String ESTILO_TITULO = "TITULO";
	public static final String ESTILO_TITULO_CAPITULO = "TITULO_CAPITULO";
	
	private BarraDeMenu jMenuBar;
	private BarraDeHerramientas jToolBar;
	private DialogoCargando dialogoProgreso;
	private BarraEstado barraEstado;

	private Controlador controlador;

	public void init() {

		jMenuBar = new BarraDeMenu(controlador);
		setJMenuBar(jMenuBar);

		jToolBar = new BarraDeHerramientas(controlador);
		add(jToolBar, BorderLayout.PAGE_START);
		
		JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));

		panelArticulo = new JTextPane();
		JScrollPane panel = new JScrollPane(panelArticulo);
		panel.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_ARTICULO));
		panelArticulo.setEditable(false);
		panelArticulo.setHighlighter(null);
		documento = panelArticulo.getStyledDocument();

		estiloSimple = new SimpleAttributeSet();
		estiloSimple.addAttribute(StyleConstants.FontSize, 12);
		estiloSimple.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloSimple.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

		estiloTitulo = new SimpleAttributeSet();
		estiloTitulo.addAttribute(StyleConstants.FontSize, 20);
		estiloTitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		estiloTituloCapitulo = new SimpleAttributeSet();
		estiloTituloCapitulo.addAttribute(StyleConstants.FontSize, 14);
		estiloTituloCapitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTituloCapitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		panelPrincipal.add(panel);

		panelResultados = new PanelResultados();
		panelPrincipal.add(panelResultados);
		
		add(panelPrincipal, BorderLayout.CENTER);

		barraEstado = new BarraEstado();
		add(barraEstado, BorderLayout.PAGE_END);
	}

	public VentanaPrincipal() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle(ConstantesGUI.T_VENTANA_TITULO);
		setSize(ConstantesGUI.VENTANA_ANCHO, ConstantesGUI.VENTANA_ALTO);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setIconImage(createImageIcon(ConstantesGUI.ICONO_VENTANA).getImage());
		setLocationRelativeTo(null);

		dialogoProgreso = new DialogoCargando(this);
	}

	public void agregarTexto(String texto, String estilo) {
		SimpleAttributeSet estiloTexto = null;
		switch (estilo) {
			case ESTILO_NORMAL:
				estiloTexto = estiloSimple;
				break;
			case ESTILO_TITULO:
				estiloTexto = estiloTitulo;
				break;
			case ESTILO_TITULO_CAPITULO:
				estiloTexto = estiloTituloCapitulo;
				break;
		}
		try {
			documento.insertString(documento.getLength(), texto, estiloTexto);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarInicioArticulo() {
		panelArticulo.setCaretPosition(0);
	}

	public void limpiarPanelArticulo() {
		panelArticulo.setText("");
	}
	
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public PanelResultados getPanelResultados() {
		return panelResultados;
	}

	public void mostrarDialogoCargando() {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		dialogoProgreso.setVisible(true);
	}

	public void ocultarDialogoCargando() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		dialogoProgreso.setVisible(false);
	}
	
	public BarraEstado getBarraEstado() {
		return barraEstado;
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