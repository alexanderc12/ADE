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
	private JButton btnBuscarNuevaPalabra;
	private JButton btnMostarTerminosTop;
	private JButton btnBuscarSinonimos;
	private JButton btnGenerarReporte;

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

		btnVerificarPalabrasClave = new JButton(createImageIcon(ConstantesGUI.ICONO_ANALIZAR_PALABRA_CLAVE));
		btnVerificarPalabrasClave.setFocusable(false);
		btnVerificarPalabrasClave.setToolTipText(ConstantesGUI.T_ITEM_ANALIZAR_PALABRA_CLAVE);
		btnVerificarPalabrasClave.addActionListener(controlador);
		btnVerificarPalabrasClave.setActionCommand(Controlador.A_VERIFICAR_PALABRAS_CLAVE);
		add(btnVerificarPalabrasClave);

		btnBuscarNuevaPalabra = new JButton(createImageIcon(ConstantesGUI.ICONO_ANALIZAR_NUEVA_PALABRA_CLAVE));
		btnBuscarNuevaPalabra.setFocusable(false);
		btnBuscarNuevaPalabra.setToolTipText(ConstantesGUI.T_ITEM_ANALIZAR_NUEVA_PALABRA);
		btnBuscarNuevaPalabra.addActionListener(controlador);
		btnBuscarNuevaPalabra.setActionCommand(Controlador.A_BUSCAR_NUEVA_PALABRA);
		add(btnBuscarNuevaPalabra);

		btnMostarTerminosTop = new JButton(createImageIcon(ConstantesGUI.ICONO_VER_TERMINOS_TOP));
		btnMostarTerminosTop.setFocusable(false);
		btnMostarTerminosTop.setToolTipText(ConstantesGUI.T_ITEM_VER_TERMINOS_TOP);
		btnMostarTerminosTop.addActionListener(controlador);
		btnMostarTerminosTop.setActionCommand(Controlador.A_VER_TERMINOS_TOP);
		add(btnMostarTerminosTop);

		btnBuscarSinonimos = new JButton(createImageIcon(ConstantesGUI.ICONO_CONSULTAR_SINONIMOS));
		btnBuscarSinonimos.setFocusable(false);
		btnBuscarSinonimos.setToolTipText(ConstantesGUI.T_ITEM_CONSULTAR_SINONIMOS);
		btnBuscarSinonimos.addActionListener(controlador);
		btnBuscarSinonimos.setActionCommand(Controlador.A_CONSULTAR_SINONIMOS);
		add(btnBuscarSinonimos);

		btnGenerarReporte = new JButton(createImageIcon(ConstantesGUI.ICONO_GENERAR_REPORTE));
		btnGenerarReporte.setFocusable(false);
		btnGenerarReporte.setToolTipText(ConstantesGUI.T_ITEM_GENERAR_REPORTE);
		btnGenerarReporte.addActionListener(controlador);
		btnGenerarReporte.setActionCommand(Controlador.A_GENERAR_REPORTE);
		add(btnGenerarReporte);
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