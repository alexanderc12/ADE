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
import modelo.ConstantesTexto;

public class GestorArchivos {

	public static final String ERROR_RUTA_ARCHIVO = "Error en la ruta del archivo";
	private static final Object ERROR_CREAR_ARCHIVO = "Error al crear el archivo ADE.";
	private static final Object ERROR_CERRAR_ARCHIVO = "Error al cerrar el archivo ADE.";
	private static final Object ERROR_LEER_ARCHIVO = "Error al leer el archivo ADE.";
	
	public static void guardarArchivo(ArticuloCientifico articuloCientifico, JFrame ventana) {
		JFileChooser dialogoGuardar = new JFileChooser("./articulos");
		dialogoGuardar.setDialogTitle("Exportar articulo a archivo ADE");
		int returnVal = dialogoGuardar.showSaveDialog(ventana);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileOutputStream salida = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				salida = new FileOutputStream(dialogoGuardar.getSelectedFile() + ".ade");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, ERROR_RUTA_ARCHIVO, ConstantesTexto.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
			try {
				objectOutputStream = new ObjectOutputStream(salida);
				objectOutputStream.writeObject(articuloCientifico);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ERROR_CREAR_ARCHIVO, ConstantesTexto.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, ERROR_CERRAR_ARCHIVO, ConstantesTexto.TITULO_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public static ArticuloCientifico cargarArchivo(JFrame ventana) {
		JFileChooser dialogoAbrirArchivo = new JFileChooser("./articulos");
		dialogoAbrirArchivo.setDialogTitle("Importar a archivo ADE");
		dialogoAbrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = dialogoAbrirArchivo.showOpenDialog(ventana);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			FileInputStream entrada = null;
			ObjectInputStream objectInputStream = null;
			try {
				entrada = new FileInputStream(dialogoAbrirArchivo.getSelectedFile());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, ERROR_RUTA_ARCHIVO, ConstantesTexto.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
			try {
				objectInputStream = new ObjectInputStream(entrada);
				return (ArticuloCientifico) objectInputStream.readObject();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, ERROR_LEER_ARCHIVO, ConstantesTexto.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, ERROR_CERRAR_ARCHIVO, ConstantesTexto.TITULO_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return null;
	}
}
