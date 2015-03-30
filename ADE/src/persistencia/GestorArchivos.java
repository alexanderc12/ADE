package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import modelo.ArticuloCientifico;

public class GestorArchivos {

	public static void guardarArchivo(ArticuloCientifico articuloCientifico, JFrame ventana) {
		JFileChooser dialogoGuardar = new JFileChooser();
		dialogoGuardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = dialogoGuardar.showSaveDialog(ventana);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileOutputStream salida = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				salida = new FileOutputStream(dialogoGuardar.getSelectedFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				objectOutputStream = new ObjectOutputStream(salida);
				objectOutputStream.writeObject(articuloCientifico);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static ArticuloCientifico cargarArchivo(JFrame ventana) {
		JFileChooser dialogoAbrirArchivo = new JFileChooser();
		dialogoAbrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = dialogoAbrirArchivo.showOpenDialog(ventana);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileInputStream entrada = null;
			ObjectInputStream objectInputStream = null;
			try {
				entrada = new FileInputStream(dialogoAbrirArchivo.getSelectedFile());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				objectInputStream = new ObjectInputStream(entrada);
				return (ArticuloCientifico) objectInputStream.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
