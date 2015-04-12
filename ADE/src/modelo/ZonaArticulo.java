package modelo;

import java.util.ArrayList;

public class ZonaArticulo {

	public static final String TITULO = "TITULO";
	public static final String RESUMEN = "RESUMEN";
	public static final String INTRODUCCION = "INTRODUCCION";
	public static final String TITULOS_CAPITULOS = "TITULOS_CAPITULOS";
	public static final String CONTENIDOS = "CONTENIDOS";
	public static final String CONCLUSIONES = "CONCLUSIONES";
	public static final String REFERENCIAS = "REFERENCIAS";
	private static int ponderadoTitulo;
	private static int ponderadoResumen;
	private static int ponderadoIntroduccion;
	private static int ponderadoTitulosCapitulos;
	private static int ponderadoContenidos;
	private static int ponderadoConclusiones;
	private static int ponderadoReferencias;
	private static final String[] lista = new String[] { TITULO, RESUMEN, INTRODUCCION, TITULOS_CAPITULOS, CONTENIDOS,
		CONCLUSIONES, REFERENCIAS };

	public static int valueOf(String parte) {
		switch (parte) {
			case TITULO:
				return ponderadoTitulo;
			case RESUMEN:
				return ponderadoResumen;
			case INTRODUCCION:
				return ponderadoIntroduccion;
			case TITULOS_CAPITULOS:
				return ponderadoTitulosCapitulos;
			case CONTENIDOS:
				return ponderadoContenidos;
			case CONCLUSIONES:
				return ponderadoConclusiones;
			case REFERENCIAS:
				return ponderadoReferencias;
		}
		return 0;
	}

	public static String toString(String parte) {
		return Util.pasarEnumAString(parte);
	}

	public static String[] values() {
		return lista;
	}

	public static void actualizarPonderados(ArrayList<String> list) {
		ponderadoTitulo = Integer.parseInt(list.get(0));
		ponderadoResumen = Integer.parseInt(list.get(1));
		ponderadoIntroduccion = Integer.parseInt(list.get(2));
		ponderadoTitulosCapitulos = Integer.parseInt(list.get(3));
		ponderadoContenidos = Integer.parseInt(list.get(4));
		ponderadoConclusiones = Integer.parseInt(list.get(5));
		ponderadoReferencias = Integer.parseInt(list.get(6));
	}
}