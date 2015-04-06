package persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.ArticuloCientifico;
import vistas.ConstantesGUI;

public class GestorArchivos {
	
	public static void guardarArchivoArticulo(ArticuloCientifico articuloCientifico, JFrame ventana) {
		JFileChooser dialogoGuardar = new JFileChooser(ConstantesGUI.RUTA_PERSISTENCIA);
		dialogoGuardar.setDialogTitle(ConstantesGUI.DIALOGO_EXPORTAR_TITULO);
		int returnVal = dialogoGuardar.showSaveDialog(ventana);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileOutputStream salida = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				salida = new FileOutputStream(dialogoGuardar.getSelectedFile() + ConstantesGUI.EXTENSION_ARCHIVO);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_RUTA_ARCHIVO, ConstantesGUI.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
			try {
				objectOutputStream = new ObjectOutputStream(salida);
				objectOutputStream.writeObject(articuloCientifico);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CREAR_ARCHIVO, ConstantesGUI.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CERRAR_ARCHIVO, ConstantesGUI.TITULO_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	public static ArticuloCientifico cargarArchivoArticulo(JFrame ventana) {
		JFileChooser dialogoAbrirArchivo = new JFileChooser(ConstantesGUI.RUTA_PERSISTENCIA);
		dialogoAbrirArchivo.setDialogTitle(ConstantesGUI.DIALOGO_IMPORTAR_TITULO);
		dialogoAbrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = dialogoAbrirArchivo.showOpenDialog(ventana);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileInputStream entrada = null;
			ObjectInputStream objectInputStream = null;
			try {
				entrada = new FileInputStream(dialogoAbrirArchivo.getSelectedFile());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_RUTA_ARCHIVO, ConstantesGUI.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
			try {
				objectInputStream = new ObjectInputStream(entrada);
				return (ArticuloCientifico) objectInputStream.readObject();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_ARCHIVO, ConstantesGUI.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CERRAR_ARCHIVO, ConstantesGUI.TITULO_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return null;
	}
}
