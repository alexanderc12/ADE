package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import controladores.Controlador;

public class DialogoReporte extends JDialog {

	private static final long serialVersionUID = 1L;
	private StyledDocument documento;
	private SimpleAttributeSet estiloSimple;
	private SimpleAttributeSet estiloTitulo;
	private SimpleAttributeSet estiloTituloCapitulo;
	private JTextPane panelReporte;

	public DialogoReporte(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		super(ventanaPrincipal);
		setLayout(new BorderLayout());
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_TOP_TERMINOS);
		setSize(ConstantesGUI.DIALOGO_TOP_TERMINOS_ANCHO, ConstantesGUI.DIALOGO_TOP_TERMINOS_ALTO);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		estiloSimple = ventanaPrincipal.getEstiloSimple();
		estiloTitulo = ventanaPrincipal.getEstiloTitulo();
		estiloTituloCapitulo = ventanaPrincipal.getEstiloTituloCapitulo();

		panelReporte = new JTextPane();
		JScrollPane panel = new JScrollPane(panelReporte);
		panel.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_ARTICULO));
		panelReporte.setEditable(false);
		panelReporte.setHighlighter(null);
		documento = panelReporte.getStyledDocument();

		add(panel);
	}

	public void agregarTexto(String texto, String estilo) {
		SimpleAttributeSet estiloTexto = null;
		switch (estilo) {
			case ConstantesGUI.ESTILO_NORMAL:
				estiloTexto = estiloSimple;
				break;
			case ConstantesGUI.ESTILO_TITULO:
				estiloTexto = estiloTitulo;
				break;
			case ConstantesGUI.ESTILO_TITULO_CAPITULO:
				estiloTexto = estiloTituloCapitulo;
				break;
		}
		try {
			documento.insertString(documento.getLength(), texto, estiloTexto);
		} catch (BadLocationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), ConstantesGUI.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
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

}