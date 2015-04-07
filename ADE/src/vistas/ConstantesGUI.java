package vistas;


public class ConstantesGUI {

	/**
	 * Constantes BarraMenu
	 */
	public static final String T_MENU_ARCHIVO = "Archivo";
	public static final String T_ITEM_CREAR_ARTICULO = "Nuevo Articulo";
	public static final String T_ITEM_EXPORTAR_ARTICULO = "Exportar Articulo a ADE";
	public static final String T_ITEM_CARGAR_ARTICULO_WEB = "Importar Articulo Web";
	public static final String T_ITEM_CARGAR_ARTICULO_ADE = "Importar Articulo ADE";
	public static final String T_MENU_HERRAMIENTAS = "Herramientas";
	public static final String T_ITEM_VERIFICAR_PALABRAS_CLAVE = "Verificar Palabras Clave";
	public static final String T_ITEM_EDITAR_PALABRAS_VACIAS = "Editar Palabras Vacias";
	public static final String T_MENU_AYUDA = "Ayuda";
	public static final String T_ITEM_ACERCA_DE = "Acerca de";
	
	/**
	 * Constantes VentanaPrincipal
	 */
	public static final String ICONO_VENTANA = "/images/icon.png";
	public static final String T_VENTANA_TITULO = "ADE - ANALIZADOR DE ETIQUETAS";
	public static final String T_PANEL_ARTICULO = "Articulo:";
	public static final int VENTANA_ANCHO = 1000;
	public static final int VENTANA_ALTO = 650;

	/**
	 * Constantes BarraHerramientas
	 */
	
	public static final String ICONO_CREAR = "/images/new.png";
	public static final String ICONO_CARGAR = "/images/open.png";
	public static final String ICONO_CARGAR_WEB = "/images/open_web.png";
	public static final String ICONO_EXPORTAR = "/images/export.png";
	public static final String ICONO_ANALIZAR = "/images/search.png";
	public static final String ICONO_EDITAR_PALABRAS_VACIAS = "/images/stop_words.png";
	public static final String ICONO_ACERCA_DE = "/images/help.png";
	
	/**
	 * Constantes DialogoCargando
	 */
	public static final String T_TITULO_DIALOGO_CARGANDO = "Mira el gato mientras carga el articulo...";
	public static final String IMAGEN_CARGANDO_ARTICULO = "/images/cargando.gif";
	public static final int DIALOGO_CARGANDO_ANCHO = 400;
	public static final int DIALOGO_CARGANDO_ALTO = 300;
	
	/**
	 * Constantes DialogoPalabrasVacias
	 */
	public static final String T_TITULO_DIALOGO_PALABRAS_VACIAS = "Editar lista palabras vacias";
	public static final int DIALOGO_PALABRAS_VACIAS_ANCHO = 400;
	public static final int DIALOGO_PALABRAS_VACIAS_ALTO = 300;
	
	/**
	 * Constantes DialogoNuevoArticulo
	 */
	public static final String T_TITULO_DIALOGO_NUEVO_ARTICULO = "Nuevo articulo";
	public static final int DIALOGO_NUEVO_ARTICULO_ANCHO = 460;
	public static final int DIALOGO_NUEVO_ARTICULO_ALTO = 580;
	public static final String T_REVISTA = "Nombre revista: ";
	public static final String T_VOLUMEN = "Volumen: ";
	public static final String T_NUMERO = "Numero: ";
	public static final String T_FECHA_RECEPCION = "Fecha recepción: ";
	public static final String T_FECHA_APROBACION = "Fecha aprobación: ";
	public static final String T_URL = "Enlace: ";
	public static final String T_TITULO = "Titulo: ";
	public static final String T_AUTORES = "Autores: ";
	public static final String T_PALABRAS_CLAVE = "Palabras clave: ";
	public static final String T_RESUMEN = "Resumen: ";
	public static final String T_TITULOS_CAPITULOS = "Titulos de los capitulos";
	public static final String T_CONTENIDO_CAPITULOS = "Contenido capitulos";
	public static final String T_REFERENCIAS = "Referencias: ";
	public static final String T_PANEL_DATOS_REVISTA = "Datos de la revista:";
	public static final String T_PANEL_DATOS_ARTICULO = "Datos generales articulo:";
	public static final String T_PANEL_CONTENIDOS = "Titulos y contenidos de los capitulos";
	public static final String[] T_COLUMNAS_TABLA_CONTENIDOS = { "Titulo",
			"Contenido" };
	public static final String ICONO_AGREGAR_CAPITULO = "/images/add.png";
	public static final String T_AGREGAR_CAPITULO = "Agregar capitulo";
	public static final String ICONO_ELIMINAR_CAPITULO = "/images/remove.png";
	public static final String T_ELIMINAR_CAPITULO = "Remover Capitulo";
	public static final String T_CREAR_ARTICULO = "Guardar articulo";
	public static final String T_CANCELAR = "Cancelar";
	
	/**
	 * Constantes PanelResultados
	 */
	public static final String T_PANEL_RESULTADOS = "Resultados";
	public static final String T_LISTA_PALABRAS = "Lista palabras clave:";
	public static final String T_TABLA_RESULTADOS = "Estadisticas:";
	public static final String[] T_COLUMNAS_TABLA_RESULTADOS = {
			"Parte del articulo",
			"Numero de incidencias", "Numero elementos",
			"Numero elementos analizables",
	"% Incidencias" };

	/**
	 * Constantes Dialogos y Errores
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
	public static final String RUTA_PALABRAS_VACIAS = "src/data/listaPalabrasVacias.txt";
	public static final String TITULO_GUARDAR = "Guardar";
	public static final String DIALOGO_GUARDAR_PALABRAS_VACIAS = "¿Desea guardar los cambios?";
	public static final String ERROR_LEER_PALABRAS_VACIAS = "Error al leer el archivo de palabras vacias.";
	public static final String ERROR_GUARDAR_PALABRAS_VACIAS = "Error al guardar el archivo de palabras vacias.";

}