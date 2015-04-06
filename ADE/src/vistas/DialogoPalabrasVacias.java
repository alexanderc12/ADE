package vistas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		setSize(ConstantesGUI.DIALOGO_PALABRAS_VACIAS_ANCHO,
				ConstantesGUI.DIALOGO_PALABRAS_VACIAS_ALTO);
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
		int opcion = JOptionPane.showConfirmDialog(this,
				ConstantesGUI.DIALOGO_GUARDAR_PALABRAS_VACIAS,
				ConstantesGUI.TITULO_GUARDAR, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opcion == JOptionPane.OK_OPTION) {
			guardarPalabrasVacias();
		}
	}

	private void cargarPalabrasVacias() {
		StringBuilder texto = new StringBuilder();
		try {
			for (String palabra : Files.readAllLines(Paths
					.get(ConstantesGUI.RUTA_PALABRAS_VACIAS))) {
				texto.append(palabra + "\n");
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					ConstantesGUI.ERROR_LEER_PALABRAS_VACIAS,
					ConstantesGUI.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
		}
		txPalabrasVacias.setText(texto.toString());
	}
	
	public void guardarPalabrasVacias() {
		try {
			Files.write(Paths.get(ConstantesGUI.ERROR_LEER_PALABRAS_VACIAS),
					txPalabrasVacias.getText().getBytes());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					ConstantesGUI.ERROR_GUARDAR_PALABRAS_VACIAS,
					ConstantesGUI.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
}