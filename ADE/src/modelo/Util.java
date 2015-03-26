package modelo;

import java.util.ArrayList;

public class Util {
	
	public static String pasarListaToString(ArrayList<String> lista) {
		StringBuilder sb = new StringBuilder();
		for (String texto : lista) {
			sb.append(texto);
		}
		return sb.toString();
	}
	
	public static String pasarListaToString(ArrayList<String> lista, int incio,
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
}
