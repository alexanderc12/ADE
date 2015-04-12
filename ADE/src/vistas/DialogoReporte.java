package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import controladores.Controlador;

public class DialogoReporte extends JDialog {

	private static final long serialVersionUID = 1L;
	private JEditorPane panelReporte;
	private SimpleAttributeSet estiloPuntuacion;
	private HTMLEditorKit editorHTML;
	private Document reporte;

	public DialogoReporte(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		super(ventanaPrincipal);
		setLayout(new BorderLayout());
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_REPORTE);
		setSize(ConstantesGUI.DIALOGO_REPORTE_ANCHO, ConstantesGUI.DIALOGO_REPORTE_ALTO);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);

		estiloPuntuacion = new SimpleAttributeSet();
		estiloPuntuacion.addAttribute(StyleConstants.FontSize, 12);
		estiloPuntuacion.addAttribute(StyleConstants.FontFamily, "Arial");

		panelReporte = new JTextPane();
		editorHTML = new HTMLEditorKit();
		panelReporte.setEditorKit(editorHTML);

		StyleSheet styleSheet = editorHTML.getStyleSheet();
		try {
			styleSheet.loadRules(
					new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(ConstantesGUI.RUTA_CSS))),
					null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		reporte = editorHTML.createDefaultDocument();
		panelReporte.setDocument(reporte);
		JScrollPane panel = new JScrollPane(panelReporte);
		panelReporte.setEditable(false);
		panelReporte.setHighlighter(null);

		add(panel);
	}

	public void agregarTexto(String texto) {
		panelReporte.setText(texto);
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