package vistas;

import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controladores.Controlador;

public class VentanaPrincipal extends JFrame {

	public static final String E_CARGAR_WEB = "CARGAR_WEB";

	private static final String T_TITULO = "ADE - ANALIZADOR DE ETIQUETAS";
	private static final String T_MENU_ARCHIVO = "Archivo";
	private static final String T_MENU_AYUDA = "Ayuda";
	private static final String RUTA_ICONO = "/images/icon.png";
	private static final int WIDTH = 900;
	private static final int HEIGHT = 500;
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
	private static final String T_ITEM_CREAR_ARTICULO = "Crear Articulo";
	private static final String T_ITEM_CARGAR_ARTICULO_WEB = "Cargar Articulo Web";
	private static final String T_ITEM_CARGAR_ARTICULO_ADE = "Cargar Articulo ADE";
	private static final String T_ITEM_ACERCA_DE = "Acerca de";
	private static final String T_MENU_HERRAMIENTAS = "Herramientas";
	private static final String T_ITEM_VERIFICAR_PALABRAS_CLAVE = "Verificar Palabras Clave";
	private static final String T_PANEL_ARTICULO = "Articulo:";

	private JMenuBar jMenuBar;
	private JMenu menuArchivo;
	private JMenuItem itemCargarArticuloWeb;
	private JMenuItem itemCrearArticulo;
	private JMenuItem itemCargarArticuloADE;
	private JMenu menuHerramientas;
	private JMenu menuAyuda;
	private JMenuItem itemAcercaDe;
	private JMenuItem itemVerificarPalabrasClave;

	private Controlador controlador;

	public void init() {
		jMenuBar = new JMenuBar();

		menuArchivo = new JMenu(T_MENU_ARCHIVO);

		itemCrearArticulo = new JMenuItem(T_ITEM_CREAR_ARTICULO);
		itemCrearArticulo.addActionListener(controlador);
		itemCrearArticulo.setActionCommand(Controlador.A_CREAR_ARCHIVO);
		menuArchivo.add(itemCrearArticulo);

		itemCargarArticuloWeb = new JMenuItem(T_ITEM_CARGAR_ARTICULO_WEB);
		itemCargarArticuloWeb.addActionListener(controlador);
		itemCargarArticuloWeb.setActionCommand(Controlador.A_CARGAR_ARCHIVO_WEB);
		menuArchivo.add(itemCargarArticuloWeb);

		itemCargarArticuloADE = new JMenuItem(T_ITEM_CARGAR_ARTICULO_ADE);
		itemCargarArticuloADE.addActionListener(controlador);
		itemCargarArticuloADE.setActionCommand(Controlador.A_CARGAR_ARCHIVO_WEB);
		menuArchivo.add(itemCargarArticuloADE);

		jMenuBar.add(menuArchivo);

		menuHerramientas = new JMenu(T_MENU_HERRAMIENTAS);
		itemVerificarPalabrasClave = new JMenuItem(
				T_ITEM_VERIFICAR_PALABRAS_CLAVE);
		itemVerificarPalabrasClave
		.setActionCommand(Controlador.A_VERIFICAR_PALABRAS_CLAVE);
		itemVerificarPalabrasClave.addActionListener(controlador);
		menuHerramientas.add(itemVerificarPalabrasClave);
		jMenuBar.add(menuHerramientas);

		menuAyuda = new JMenu(T_MENU_AYUDA);

		itemAcercaDe = new JMenuItem(T_ITEM_ACERCA_DE);
		itemAcercaDe.addActionListener(controlador);
		menuAyuda.add(itemAcercaDe);

		jMenuBar.add(menuAyuda);

		setJMenuBar(jMenuBar);

		panelArticulo = new JTextPane();
		JScrollPane panel = new JScrollPane(panelArticulo);
		panel.setBorder(BorderFactory.createTitledBorder(T_PANEL_ARTICULO));
		panelArticulo.setEditable(false);
		documento = panelArticulo.getStyledDocument();

		estiloSimple = new SimpleAttributeSet();
		estiloSimple.addAttribute(StyleConstants.FontSize, 12);
		estiloSimple.addAttribute(StyleConstants.FontFamily, "Arial");

		estiloTitulo = new SimpleAttributeSet();
		estiloTitulo.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_CENTER);
		estiloTitulo.addAttribute(StyleConstants.FontSize, 20);
		estiloTitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		estiloTituloCapitulo = new SimpleAttributeSet();
		estiloTituloCapitulo.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_CENTER);
		estiloTituloCapitulo.addAttribute(StyleConstants.FontSize, 13);
		estiloTituloCapitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTituloCapitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		add(panel);

		panelResultados = new PanelResultados();
		add(panelResultados);
	}

	public VentanaPrincipal() {
		setTitle(T_TITULO);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 2));
		setIconImage(createImageIcon(RUTA_ICONO).getImage());
		setLocationRelativeTo(null);

		setVisible(true);
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

	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public PanelResultados getPanelResultados() {
		return panelResultados;
	}
}