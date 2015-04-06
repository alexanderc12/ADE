package vistas;

import java.awt.Cursor;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DialogoCargando extends JDialog {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DialogoCargando(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_CARGANDO);
		setSize(ConstantesGUI.DIALOGO_CARGANDO_ANCHO,
				ConstantesGUI.DIALOGO_CARGANDO_ALTO);
		setLocationRelativeTo(null);
		JLabel progressBar = new JLabel(createImageIcon(ConstantesGUI.IMAGEN_CARGANDO_ARTICULO));
		add(progressBar);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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
