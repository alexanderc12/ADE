package vistas;

import javax.swing.JLabel;
import javax.swing.JToolBar;

public class BarraEstado extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JLabel lbNombreArticulo;
	private JLabel lbPlabraClave;
	private JLabel lbEstadisticas;

	public BarraEstado() {
		setFloatable(false);
		lbNombreArticulo = new JLabel();
		add(lbNombreArticulo);
		addSeparator();
		
		lbPlabraClave = new JLabel();
		add(lbPlabraClave);
		addSeparator();
		
		lbEstadisticas = new JLabel();
		add(lbEstadisticas);
	}

	public void setNombreArticulo(String nombre) {
		lbNombreArticulo.setText(nombre);
	}
	
	public void setPalabraClave(String palabra) {
		lbPlabraClave.setText(palabra);
	}
	
	public void setEstadisticas(String estaditica) {
		lbEstadisticas.setText(estaditica);
	}
}
