package vistas;

public class ConstantesGUI {

	/**
	 * Constantes BarraMenu
	 */
	public static final String T_MENU_ARCHIVO = "Archivo";
	public static final String T_MENU_AYUDA = "Ayuda";
	public static final String T_ITEM_CREAR_ARTICULO = "Nuevo Articulo";
	public static final String T_ITEM_EXPORTAR_ARTICULO = "Exportar Articulo a ADE";
	public static final String T_ITEM_CARGAR_ARTICULO_WEB = "Importar Articulo Web";
	public static final String T_ITEM_CARGAR_ARTICULO_ADE = "Importar Articulo ADE";
	public static final String T_ITEM_ACERCA_DE = "Acerca de";
	public static final String T_MENU_HERRAMIENTAS = "Herramientas";
	public static final String T_ITEM_VERIFICAR_PALABRAS_CLAVE = "Verificar Palabras Clave";
	
	/**
	 * Constantes VentanaPrincipal
	 */
	public static final String ICONO_VENTANA = "/images/icon.png";
	public static final String T_VENTANA_TITULO = "ADE - ANALIZADOR DE ETIQUETAS";
	public static final String T_PANEL_ARTICULO = "Articulo:";
	public static final int VENTANA_ANCHO = 1000;
	public static final int VENTANA_ALTO = 600;

	/**
	 * Constantes BarraHerramientas
	 */
	
	public static final String ICONO_CREAR = "/images/new.png";
	public static final String ICONO_CARGAR = "/images/open.png";
	public static final String ICONO_CARGAR_WEB = "/images/open_web.png";
	public static final String ICONO_EXPORTAR = "/images/export.png";
	public static final String ICONO_ANALIZAR = "/images/search.png";
	public static final String ICONO_ACERCA_DE = "/images/help.png";
	
	/**
	 * Constantes DialogoCargando
	 */
	public static final String T_TITULO_DIALOGO_CARGANDO = "Mira el gato mientras carga el articulo...";
	public static final String IMAGEN_CARGANDO_ARTICULO = "/images/cargando.gif";
	public static final int DIALOGO_CARGANDO_ANCHO = 400;
	public static final int DIALOGO_CARGANDOVENTANA_ALTO = 300;
	
	/**
	 * Constantes PanelResultados
	 */
	public static final String T_PANEL_RESULTADOS = "Resultados";
	public static final String T_LISTA_PALABRAS = "Lista palabras clave:";
	public static final String T_TABLA = "Estadisticas:";
	public static final String[] T_COLUMNAS_TABLA = { "Parte del articulo", "Numero de incidencias", "Numero elementos",
	"% Incidencias" };

	/**
	 * Constantes Dialogos
	 */
	public static final String TITULO_ERROR = "Error en el proceso :(";
	public static final String ERROR_RUTA_ARCHIVO = "Error en la ruta del archivo";
	public static final String ERROR_CREAR_ARCHIVO = "Error al crear el archivo ADE.";
	public static final String ERROR_CERRAR_ARCHIVO = "Error al cerrar el archivo ADE.";
	public static final String ERROR_LEER_ARCHIVO = "Error al leer el archivo ADE.";
	public static final String DIALOGO_EXPORTAR_TITULO = "Exportar articulo a archivo ADE";
	public static final String DIALOGO_IMPORTAR_TITULO = "Importar articulo en archivo ADE";
	public static final String RUTA_PERSISTENCIA = "./articulos";
	public static final String EXTENSION_ARCHIVO = ".ade";
}
