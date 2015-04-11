package vistas;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controladores.Controlador;

public class BarraDeMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenu menuArchivo;
	private JMenuItem itemCargarArticuloWeb;
	private JMenuItem itemCrearArticulo;
	private JMenuItem itemExportarArticulo;
	private JMenuItem itemCargarArticuloADE;
	private JMenu menuHerramientas;
	private JMenuItem itemAnalizarPalabraClave;
	private JMenuItem itemAnalizarNuevaPalabraClave;
	private JMenuItem itemMostarTerminosTop;
	private JMenuItem itemconsultarTeusaro;
	private JMenu menuConfiguracion;
	private JMenuItem itemEditarPalabrasVacias;
	private JMenu menuAyuda;
	private JMenuItem itemAcercaDe;
	private JMenuItem itemGenerarReporte;

	public BarraDeMenu(Controlador controlador) {

		menuArchivo = new JMenu(ConstantesGUI.T_MENU_ARCHIVO);

		itemCrearArticulo = new JMenuItem(ConstantesGUI.T_ITEM_CREAR_ARTICULO,
				createImageIcon(ConstantesGUI.ICONO_CREAR));
		itemCrearArticulo.addActionListener(controlador);
		itemCrearArticulo.setActionCommand(Controlador.A_CREAR_ARCHIVO);
		menuArchivo.add(itemCrearArticulo);

		itemCargarArticuloWeb = new JMenuItem(ConstantesGUI.T_ITEM_CARGAR_ARTICULO_WEB,
				createImageIcon(ConstantesGUI.ICONO_CARGAR_WEB));
		itemCargarArticuloWeb.addActionListener(controlador);
		itemCargarArticuloWeb.setActionCommand(Controlador.A_CARGAR_ARCHIVO_WEB);
		menuArchivo.add(itemCargarArticuloWeb);

		itemCargarArticuloADE = new JMenuItem(ConstantesGUI.T_ITEM_CARGAR_ARTICULO_ADE,
				createImageIcon(ConstantesGUI.ICONO_CARGAR));
		itemCargarArticuloADE.addActionListener(controlador);
		itemCargarArticuloADE.setActionCommand(Controlador.A_CARGAR_ARCHIVO);
		menuArchivo.add(itemCargarArticuloADE);

		itemExportarArticulo = new JMenuItem(ConstantesGUI.T_ITEM_EXPORTAR_ARTICULO,
				createImageIcon(ConstantesGUI.ICONO_EXPORTAR));
		itemExportarArticulo.addActionListener(controlador);
		itemExportarArticulo.setActionCommand(Controlador.A_EXPORTAR_ARCHIVO);
		menuArchivo.add(itemExportarArticulo);

		add(menuArchivo);

		menuHerramientas = new JMenu(ConstantesGUI.T_MENU_HERRAMIENTAS);

		itemAnalizarPalabraClave = new JMenuItem(ConstantesGUI.T_ITEM_ANALIZAR_PALABRA_CLAVE,
				createImageIcon(ConstantesGUI.ICONO_ANALIZAR_PALABRA_CLAVE));
		itemAnalizarPalabraClave.setActionCommand(Controlador.A_VERIFICAR_PALABRAS_CLAVE);
		itemAnalizarPalabraClave.addActionListener(controlador);
		menuHerramientas.add(itemAnalizarPalabraClave);

		itemAnalizarNuevaPalabraClave = new JMenuItem(ConstantesGUI.T_ITEM_ANALIZAR_NUEVA_PALABRA,
				createImageIcon(ConstantesGUI.ICONO_ANALIZAR_NUEVA_PALABRA_CLAVE));
		itemAnalizarNuevaPalabraClave.setActionCommand(Controlador.A_BUSCAR_NUEVA_PALABRA);
		itemAnalizarNuevaPalabraClave.addActionListener(controlador);
		menuHerramientas.add(itemAnalizarNuevaPalabraClave);

		itemGenerarReporte = new JMenuItem(ConstantesGUI.T_ITEM_GENERAR_REPORTE,
				createImageIcon(ConstantesGUI.ICONO_GENERAR_REPORTE));
		itemGenerarReporte.setActionCommand(Controlador.A_GENERAR_REPORTE);
		itemGenerarReporte.addActionListener(controlador);
		menuHerramientas.add(itemGenerarReporte);

		itemMostarTerminosTop = new JMenuItem(ConstantesGUI.T_ITEM_VER_TERMINOS_TOP,
				createImageIcon(ConstantesGUI.ICONO_VER_TERMINOS_TOP));
		itemMostarTerminosTop.setActionCommand(Controlador.A_VER_TERMINOS_TOP);
		itemMostarTerminosTop.addActionListener(controlador);
		menuHerramientas.add(itemMostarTerminosTop);

		itemconsultarTeusaro = new JMenuItem(ConstantesGUI.T_ITEM_CONSULTAR_SINONIMOS,
				createImageIcon(ConstantesGUI.ICONO_CONSULTAR_SINONIMOS));
		itemconsultarTeusaro.setActionCommand(Controlador.A_CONSULTAR_SINONIMOS);
		itemconsultarTeusaro.addActionListener(controlador);
		menuHerramientas.add(itemconsultarTeusaro);

		add(menuHerramientas);

		menuConfiguracion = new JMenu(ConstantesGUI.T_MENU_CONFIGURACION);

		itemEditarPalabrasVacias = new JMenuItem(ConstantesGUI.T_ITEM_EDITAR_PALABRAS_VACIAS,
				createImageIcon(ConstantesGUI.ICONO_EDITAR_PALABRAS_VACIAS));
		itemEditarPalabrasVacias
		.setActionCommand(Controlador.A_EDITAR_PALABRAS_VACIAS);
		itemEditarPalabrasVacias.addActionListener(controlador);
		menuConfiguracion.add(itemEditarPalabrasVacias);

		add(menuConfiguracion);

		menuAyuda = new JMenu(ConstantesGUI.T_MENU_AYUDA);

		itemAcercaDe = new JMenuItem(ConstantesGUI.T_ITEM_ACERCA_DE, createImageIcon(ConstantesGUI.ICONO_ACERCA_DE));
		itemAcercaDe.setActionCommand(Controlador.A_VER_ACERCA_DE);
		itemAcercaDe.addActionListener(controlador);
		menuAyuda.add(itemAcercaDe);

		add(menuAyuda);
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