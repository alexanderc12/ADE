package vistas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private BarraDeMenu jMenuBar;
	private BarraDeHerramientas jToolBar;
	private BarraEstado barraEstado;
	private StyledDocument documento;
	private SimpleAttributeSet estiloSimple;
	private SimpleAttributeSet estiloTitulo;
	private SimpleAttributeSet estiloTituloCapitulo;


	public void init(Controlador controlador) {
		jMenuBar = new BarraDeMenu(controlador);
		setJMenuBar(jMenuBar);

		jToolBar = new BarraDeHerramientas(controlador);
		add(jToolBar, BorderLayout.PAGE_START);

		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		GridBagConstraints cBagConstraints = new GridBagConstraints();

		panelArticulo = new JTextPane();
		JScrollPane panel = new JScrollPane(panelArticulo);
		panel.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_ARTICULO));
		panelArticulo.setEditable(false);
		panelArticulo.setHighlighter(null);
		documento = panelArticulo.getStyledDocument();

		estiloSimple = new SimpleAttributeSet();
		estiloSimple.addAttribute(StyleConstants.FontSize, 12);
		estiloSimple.addAttribute(StyleConstants.FontFamily, "Arial");

		estiloTitulo = new SimpleAttributeSet();
		estiloTitulo.addAttribute(StyleConstants.FontSize, 20);
		estiloTitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		estiloTituloCapitulo = new SimpleAttributeSet();
		estiloTituloCapitulo.addAttribute(StyleConstants.FontSize, 14);
		estiloTituloCapitulo.addAttribute(StyleConstants.FontFamily, "Arial");
		estiloTituloCapitulo.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);

		cBagConstraints.fill = GridBagConstraints.BOTH;
		cBagConstraints.weightx = 0.5;
		cBagConstraints.weighty = 2.0;
		cBagConstraints.gridx = 0;
		cBagConstraints.gridy = 0;
		panelPrincipal.add(panel, cBagConstraints);

		panelResultados = new PanelResultados();
		cBagConstraints.weightx = 1;
		cBagConstraints.weighty = 1.0;
		cBagConstraints.gridx = 1;
		cBagConstraints.gridy = 0;
		panelPrincipal.add(panelResultados, cBagConstraints);

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

	public void mostrarInicioArticulo() {
		panelArticulo.setCaretPosition(0);
	}

	public void limpiarPanelArticulo() {
		panelArticulo.setText("");
	}

	public PanelResultados getPanelResultados() {
		return panelResultados;
	}

	public BarraEstado getBarraEstado() {
		return barraEstado;
	}

	public SimpleAttributeSet getEstiloSimple() {
		return estiloSimple;
	}

	public SimpleAttributeSet getEstiloTitulo() {
		return estiloTitulo;
	}

	public SimpleAttributeSet getEstiloTituloCapitulo() {
		return estiloTituloCapitulo;
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