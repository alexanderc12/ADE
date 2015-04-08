package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VerificadorTerminos {
	
	public static boolean verificarTermino(String termino, String listaTerminos) {
		try (BufferedReader reader = Files.newBufferedReader(Paths
				.get(listaTerminos))) {
			String texto = null;
			while ((texto = reader.readLine()) != null) {
				if (texto.equalsIgnoreCase(termino)) {
					return true;
				}
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		return false;
	}
}
