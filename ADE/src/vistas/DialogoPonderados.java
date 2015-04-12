package vistas;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import modelo.ZonaArticulo;

public class DialogoPonderados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JSpinner jspTitulo;
	private JSpinner jspResumen;
	private JSpinner jspIntroduccion;
	private JSpinner jspTitulosCapitulos;
	private JSpinner jspContenidos;
	private JSpinner jspConclusiones;
	private JSpinner jspReferencias;

	public DialogoPonderados(VentanaPrincipal ventanaPrincipal) {
		super(ventanaPrincipal);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_PONDERADOS);
		setSize(ConstantesGUI.DIALOGO_TOP_TERMINOS_ANCHO, ConstantesGUI.DIALOGO_TOP_TERMINOS_ALTO);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);

		jspTitulo = new JSpinner(new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.TITULO), 0, 100, 1));
		jspTitulo.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_TITULO));
		add(jspTitulo);

		jspResumen = new JSpinner(new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.RESUMEN), 0, 100, 1));
		jspResumen.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_RESUMEN));
		add(jspResumen);

		jspIntroduccion = new JSpinner(
				new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.INTRODUCCION), 0, 100, 1));
		jspIntroduccion.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_INTRODUCCION));
		add(jspIntroduccion);

		jspTitulosCapitulos = new JSpinner(
				new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.TITULOS_CAPITULOS), 0, 100, 1));
		jspTitulosCapitulos.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_TITULOS_CAPITULOS));
		add(jspTitulosCapitulos);

		jspContenidos = new JSpinner(new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.CONTENIDOS), 0, 100, 1));
		jspContenidos.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_CONTENIDO_CAPITULOS));
		add(jspContenidos);

		jspConclusiones = new JSpinner(
				new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.CONCLUSIONES), 0, 100, 1));
		jspConclusiones.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_CONCLUSIONES));
		add(jspConclusiones);

		jspReferencias = new JSpinner(
				new SpinnerNumberModel(ZonaArticulo.valueOf(ZonaArticulo.REFERENCIAS), 0, 100, 1));
		jspReferencias.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_REFERENCIAS));
		add(jspReferencias);

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (mostrarDialogoGuardar()) {
					dispose();
				}
			}
		});
	}

	public void actualizarPonderados() {
		jspTitulo.setValue(ZonaArticulo.valueOf(ZonaArticulo.TITULO));
		jspResumen.setValue(ZonaArticulo.valueOf(ZonaArticulo.RESUMEN));
		jspIntroduccion.setValue(ZonaArticulo.valueOf(ZonaArticulo.INTRODUCCION));
		jspTitulosCapitulos.setValue(ZonaArticulo.valueOf(ZonaArticulo.TITULOS_CAPITULOS));
		jspContenidos.setValue(ZonaArticulo.valueOf(ZonaArticulo.CONTENIDOS));
		jspConclusiones.setValue(ZonaArticulo.valueOf(ZonaArticulo.CONCLUSIONES));
		jspReferencias.setValue(ZonaArticulo.valueOf(ZonaArticulo.REFERENCIAS));
	}

	public boolean mostrarDialogoGuardar() {
		int opcion = JOptionPane.showConfirmDialog(this, ConstantesGUI.DIALOGO_GUARDAR_PALABRAS_VACIAS,
				ConstantesGUI.TITULO_GUARDAR, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (opcion == JOptionPane.OK_OPTION) {
			return guardarPoderados();
		}
		return true;
	}

	public boolean guardarPoderados() {

		if ((int) jspTitulo.getValue() + (int) jspResumen.getValue() + (int) jspIntroduccion.getValue()
		+ (int) jspTitulosCapitulos.getValue() + (int) jspContenidos.getValue()
		+ (int) jspConclusiones.getValue() + (int) jspReferencias.getValue() == 100) {

			String listaPonderados = (int) jspTitulo.getValue() + "\n" + (int) jspResumen.getValue() + "\n"
					+ (int) jspIntroduccion.getValue() + "\n" + (int) jspTitulosCapitulos.getValue() + "\n"
					+ (int) jspContenidos.getValue() + "\n" + (int) jspConclusiones.getValue() + "\n"
					+ (int) jspReferencias.getValue();
			try {
				Files.write(Paths.get(ConstantesGUI.RUTA_PONDERADOS), listaPonderados.getBytes());
				return true;
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_GUARDAR_PALABRAS_VACIAS,
						ConstantesGUI.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_GUARDAR_PONDERADOS, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
}