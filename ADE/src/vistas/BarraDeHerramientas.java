package vistas;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controladores.Controlador;

public class BarraDeHerramientas extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JButton btnCargarArticuloWeb;
	private JButton btnCrearArticulo;
	private JButton btnExportarArticulo;
	private JButton btnCargarArticuloADE;
	private JButton btnVerificarPalabrasClave;
	private JButton btnEditarPalabrasVacias;

	public BarraDeHerramientas(Controlador controlador) {
		setFloatable(false);
		setRollover(true);

		btnCrearArticulo = new JButton(createImageIcon(ConstantesGUI.ICONO_CREAR));
		btnCrearArticulo.setFocusable(false);
		btnCrearArticulo.setToolTipText(ConstantesGUI.T_ITEM_CREAR_ARTICULO);
		btnCrearArticulo.addActionListener(controlador);
		btnCrearArticulo.setActionCommand(Controlador.A_CREAR_ARCHIVO);
		add(btnCrearArticulo);

		btnCargarArticuloWeb = new JButton(createImageIcon(ConstantesGUI.ICONO_CARGAR_WEB));
		btnCargarArticuloWeb.setFocusable(false);
		btnCargarArticuloWeb.setToolTipText(ConstantesGUI.T_ITEM_CARGAR_ARTICULO_WEB);
		btnCargarArticuloWeb.addActionListener(controlador);
		btnCargarArticuloWeb.setActionCommand(Controlador.A_CARGAR_ARCHIVO_WEB);
		add(btnCargarArticuloWeb);

		btnCargarArticuloADE = new JButton(createImageIcon(ConstantesGUI.ICONO_CARGAR));
		btnCargarArticuloADE.setFocusable(false);
		btnCargarArticuloADE.setToolTipText(ConstantesGUI.T_ITEM_CARGAR_ARTICULO_ADE);
		btnCargarArticuloADE.addActionListener(controlador);
		btnCargarArticuloADE.setActionCommand(Controlador.A_CARGAR_ARCHIVO);
		add(btnCargarArticuloADE);

		btnExportarArticulo = new JButton(createImageIcon(ConstantesGUI.ICONO_EXPORTAR));
		btnExportarArticulo.setFocusable(false);
		btnExportarArticulo.setToolTipText(ConstantesGUI.T_ITEM_EXPORTAR_ARTICULO);
		btnExportarArticulo.addActionListener(controlador);
		btnExportarArticulo.setActionCommand(Controlador.A_EXPORTAR_ARCHIVO);
		add(btnExportarArticulo);

		addSeparator();

		btnVerificarPalabrasClave = new JButton(createImageIcon(ConstantesGUI.ICONO_ANALIZAR));
		btnVerificarPalabrasClave.setFocusable(false);
		btnVerificarPalabrasClave.setToolTipText(ConstantesGUI.T_ITEM_VERIFICAR_PALABRAS_CLAVE);
		btnVerificarPalabrasClave.addActionListener(controlador);
		btnVerificarPalabrasClave.setActionCommand(Controlador.A_VERIFICAR_PALABRAS_CLAVE);
		add(btnVerificarPalabrasClave);
		
		btnEditarPalabrasVacias = new JButton(
				createImageIcon(ConstantesGUI.ICONO_EDITAR_PALABRAS_VACIAS));
		btnEditarPalabrasVacias.setFocusable(false);
		btnEditarPalabrasVacias
				.setToolTipText(ConstantesGUI.T_ITEM_EDITAR_PALABRAS_VACIAS);
		btnEditarPalabrasVacias.addActionListener(controlador);
		btnEditarPalabrasVacias
				.setActionCommand(Controlador.A_EDITAR_PALABRAS_VACIAS);
		add(btnEditarPalabrasVacias);
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