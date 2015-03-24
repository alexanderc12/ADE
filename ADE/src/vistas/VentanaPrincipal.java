package vistas;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controladores.ControladorEventos;

public class VentanaPrincipal extends JFrame {

	public static final String E_CARGAR_WEB = "CARGAR_WEB";

	public static final String T_TITULO = "ADE";
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ControladorEventos controladorEventos;
	private JTextPane panelArticulo;
	private StyledDocument documento;
	private SimpleAttributeSet estiloSimple;
	private SimpleAttributeSet estiloTitulo;
	private SimpleAttributeSet estiloTituloCapitulo;
	public static final String ESTILO_NORMAL = "NORMAL";
	public static final String ESTILO_TITULO = "TITULO";
	public static final String ESTILO_TITULO_CAPITULO = "TITULO_CAPITULO";

	public VentanaPrincipal(ControladorEventos controladorEventos) {
		this.controladorEventos = controladorEventos;
		setTitle(T_TITULO);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setIconImage(createImageIcon("/img/icon.png", "").getImage());

		panelArticulo = new JTextPane();
		JScrollPane panel = new JScrollPane(panelArticulo);
		panelArticulo.setEditable(false);
		documento = panelArticulo.getStyledDocument();

		estiloSimple = new SimpleAttributeSet();
		estiloSimple.addAttribute(StyleConstants.FontSize, 12);
		estiloSimple.addAttribute(StyleConstants.FontFamily, "Arial");

		estiloTitulo = new SimpleAttributeSet();
		estiloTitulo.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_CENTER);
		estiloTitulo.addAttribute(StyleConstants.FontSize, 15);
		estiloTitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		estiloTituloCapitulo = new SimpleAttributeSet();
		estiloTituloCapitulo.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_CENTER);
		estiloTituloCapitulo.addAttribute(StyleConstants.FontSize, 13);
		estiloTituloCapitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTituloCapitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		add(panel, BorderLayout.CENTER);

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

	protected ImageIcon createImageIcon(String path, String description) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			return null;
		}
	}
}