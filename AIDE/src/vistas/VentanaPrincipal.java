package vistas;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controladores.ControladorEventos;

public class VentanaPrincipal extends JFrame {

	public static final String E_CARGAR_WEB = "CARGAR_WEB";
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ControladorEventos controladorEventos;

	public VentanaPrincipal() {
		controladorEventos = new ControladorEventos(this);

	}

	protected ImageIcon createImageIcon(String path, String description) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {

	}

}
