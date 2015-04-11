package vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogoCargando extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel progressBar;

	public DialogoCargando(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		getContentPane().setBackground(Color.WHITE);
		progressBar = new JLabel(createImageIcon(ConstantesGUI.IMAGEN_CARGANDO_ARTICULO));
		add(progressBar);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	public void configuarParaArticulo() {
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_CARGANDO_ARTICULO);
		setSize(ConstantesGUI.DIALOGO_CARGANDO_ARTICULO_ANCHO, ConstantesGUI.DIALOGO_CARGANDO_ARTICULO_ALTO);
		progressBar.setIcon(createImageIcon(ConstantesGUI.IMAGEN_CARGANDO_ARTICULO));
		setLocationRelativeTo(null);
	}

	public void configuarParaResultados() {
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_CARGANDO_RESULTADOS);
		setSize(ConstantesGUI.DIALOGO_CARGANDO_RESULTADOS_ANCHO, ConstantesGUI.DIALOGO_CARGANDO_RESULTADOS_ALTO);
		progressBar.setIcon(createImageIcon(ConstantesGUI.IMAGEN_CARGANDO_RESULTADOS));
		setLocationRelativeTo(null);
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