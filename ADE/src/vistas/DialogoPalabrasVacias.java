package vistas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DialogoPalabrasVacias extends JDialog{

	private static final long serialVersionUID = 1L;
	private JTextArea txPalabrasVacias;

	public DialogoPalabrasVacias(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_PALABRAS_VACIAS);
		setSize(ConstantesGUI.DIALOGO_PALABRAS_VACIAS_ANCHO, ConstantesGUI.DIALOGO_PALABRAS_VACIAS_ALTO);
		setLocationRelativeTo(null);

		txPalabrasVacias = new JTextArea();
		cargarPalabrasVacias();
		add(new JScrollPane(txPalabrasVacias));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mostrarDialogoGuardar();
				super.windowClosing(e);
			}
		});
	}

	public void mostrarDialogoGuardar() {
		int opcion = JOptionPane.showConfirmDialog(this, ConstantesGUI.DIALOGO_GUARDAR_PALABRAS_VACIAS,
				ConstantesGUI.TITULO_GUARDAR, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcion == JOptionPane.OK_OPTION) {
			guardarPalabrasVacias();
		}
	}

	private void cargarPalabrasVacias() {
		StringBuilder lista = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(getClass().getResourceAsStream(ConstantesGUI.RUTA_PALABRAS_VACIAS), "UTF8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String texto = null;
		try {
			while ((texto = reader.readLine()) != null) {
				lista.append(texto);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_PALABRAS_VACIAS, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		txPalabrasVacias.setText(lista.toString());
	}

	public void guardarPalabrasVacias() {
		URL resourceUrl = getClass().getResource(ConstantesGUI.RUTA_PALABRAS_VACIAS);
		File file = null;
		try {
			file = new File(resourceUrl.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		try {
			new FileOutputStream(file).write(txPalabrasVacias.getText().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}