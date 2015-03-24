package vistas;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controladores.ControladorEventos;

public class VentanaPrincipal extends JFrame {

	public static final String E_CARGAR_WEB = "CARGAR_WEB";

	public static final String T_TITULO = "ADE";
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ControladorEventos controladorEventos;

	public VentanaPrincipal() {
		controladorEventos = new ControladorEventos(this);
		setTitle(T_TITULO);
		setSize(WIDTH, HEIGHT);
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
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.setVisible(true);
	}

}
