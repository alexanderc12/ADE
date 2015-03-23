package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vistas.VentanaPrincipal;

public class ControladorEventos implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;

	public ControladorEventos(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case VentanaPrincipal.E_CARGAR_WEB:

			break;
		}
	}
}