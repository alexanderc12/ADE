package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import vistas.ConstantesGUI;

public class VerificadorTerminos {

	public static boolean verificarTermino(String termino, String listaTerminos) {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(listaTerminos))) {
			String texto = null;
			while ((texto = reader.readLine()) != null) {
				if (texto.equalsIgnoreCase(termino)) {
					return true;
				}
			}
		} catch (IOException x) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_ARCHIVO, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
}