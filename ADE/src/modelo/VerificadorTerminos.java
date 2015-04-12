package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VerificadorTerminos {

	public boolean verificarTermino(String termino, String listaTerminos) {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(listaTerminos)));
		String texto = null;
		try {
			while ((texto = reader.readLine()) != null) {
				if (texto.equalsIgnoreCase(termino)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}