package modelo;

import java.util.ArrayList;

public class Util {

	public static String pasarListaAString(ArrayList<String> lista) {
		StringBuilder sb = new StringBuilder();
		for (String texto : lista) {
			sb.append(texto);
		}
		return sb.toString();
	}

	public static String pasarListaAString(ArrayList<String> lista, int incio,
			int fin) {
		StringBuilder sb = new StringBuilder();
		for (int i = incio; i < fin; i++) {
			sb.append(lista.get(i));
		}
		return sb.toString();
	}

	public static void pasarListaAMinusculas(ParteArticulo[] lista) {
		for (int i = 0; i < lista.length; i++) {
			lista[i].setTexto(lista[i].getTexto().toLowerCase());
		}
	}

	public static String pasarEnumAString(String enumerado){
		return enumerado.substring(0, 1) + enumerado.toLowerCase().substring(1).replaceAll("_", " ");
	}
}
