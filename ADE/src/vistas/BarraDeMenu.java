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
	private JMenuItem itemVerificarPalabrasClave;
	private JMenuItem itemEditarPalabrasVacias;
	private JMenuItem itemBuscarEnListas;
	private JMenu menuAyuda;
	private JMenuItem itemAcercaDe;
	
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
		
		itemVerificarPalabrasClave = new JMenuItem(ConstantesGUI.T_ITEM_VERIFICAR_PALABRAS_CLAVE,
				createImageIcon(ConstantesGUI.ICONO_EXPORTAR));
		itemVerificarPalabrasClave.setActionCommand(Controlador.A_VERIFICAR_PALABRAS_CLAVE);
		itemVerificarPalabrasClave.addActionListener(controlador);
		menuHerramientas.add(itemVerificarPalabrasClave);

		itemEditarPalabrasVacias = new JMenuItem(
				ConstantesGUI.T_ITEM_EDITAR_PALABRAS_VACIAS,
				createImageIcon(ConstantesGUI.ICONO_EDITAR_PALABRAS_VACIAS));
		itemEditarPalabrasVacias
		.setActionCommand(Controlador.A_EDITAR_PALABRAS_VACIAS);
		itemEditarPalabrasVacias.addActionListener(controlador);
		menuHerramientas.add(itemEditarPalabrasVacias);

		itemBuscarEnListas = new JMenuItem(ConstantesGUI.T_ITEM_BUSCAR_EN_LISTAS,
				createImageIcon(ConstantesGUI.ICONO_BUSCAR_EN_LISTAS));
		itemBuscarEnListas.setActionCommand(Controlador.A_BUSCAR_EN_LISTAS);
		itemBuscarEnListas.addActionListener(controlador);
		menuHerramientas.add(itemBuscarEnListas);
		
		add(menuHerramientas);

		menuAyuda = new JMenu(ConstantesGUI.T_MENU_AYUDA);

		itemAcercaDe = new JMenuItem(ConstantesGUI.T_ITEM_ACERCA_DE, createImageIcon(ConstantesGUI.ICONO_ACERCA_DE));
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