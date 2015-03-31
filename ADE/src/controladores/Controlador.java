package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import modelo.ArticuloCientifico;
import modelo.ParteArticulo;
import modelo.VerificadorPalabrasClave;
import persistencia.GestorArchivos;
import vistas.VentanaPrincipal;

public class Controlador implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;
	private VerificadorPalabrasClave verificadorPalabrasClave;
	private ArticuloCientifico articulo;

	public static final String A_VERIFICAR_PALABRAS_CLAVE = "VERIFICAR_PALABRAS_CLAVE";
	public static final String A_CREAR_ARCHIVO = "CREAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO = "CARGAR_ARCHIVO";
	public static final String A_EXPORTAR_ARCHIVO = "EXPORTAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO_WEB = "A_CARGAR_ARCHIVO_WEB";
	public static final String SL = System.getProperty("line.separator");
	public static final String NL = SL + SL;

	public void iniciar() {
		this.ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.setControlador(this);
		ventanaPrincipal.init();
		ventanaPrincipal.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case A_VERIFICAR_PALABRAS_CLAVE:
				analizarPalabraClave();
				break;
			case A_CREAR_ARCHIVO:
				break;
			case A_EXPORTAR_ARCHIVO:
				GestorArchivos.guardarArchivo(articulo, ventanaPrincipal);
				break;
			case A_CARGAR_ARCHIVO:
				verificadorPalabrasClave = new VerificadorPalabrasClave(GestorArchivos.cargarArchivo(ventanaPrincipal));
				articulo = verificadorPalabrasClave.getArticulo();
				cargarArticulo();
				break;
			case A_CARGAR_ARCHIVO_WEB:
				String url = JOptionPane.showInputDialog(ventanaPrincipal, "Ingrese la URL corta del articulo",
						"Cargar Articulo Web", JOptionPane.QUESTION_MESSAGE);
				ventanaPrincipal.mostrarDialogoCargando();

				SwingWorker<Void, Void> aWorker = new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						verificadorPalabrasClave = new VerificadorPalabrasClave(url);
						articulo = verificadorPalabrasClave.getArticulo();
						return null;
					}
					@Override
					protected void done() {
						cargarArticulo();
						ventanaPrincipal.ocultarDialogoCargando();
					}
				};
				aWorker.execute();
				break;
		}
	}

	public void cargarArticulo() {
		ventanaPrincipal.limpiarPanelArticulo();
		ventanaPrincipal.agregarTexto("Revista: " + articulo.getRevista() + "\t Volumen: " + articulo.getVolumen()
		+ "\tNumero: " + articulo.getNumero() + NL, VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto(articulo.getTitulo() + NL, VentanaPrincipal.ESTILO_TITULO);
		for (String autor : articulo.getListaAutores()) {
			ventanaPrincipal.agregarTexto(autor + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventanaPrincipal.agregarTexto("\n\nFecha de recepción:" + articulo.getFechaRecepcion()
		+ "\tFecha de aprobación:" + articulo.getFechaAprobacion() + NL, VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Resumen:\n" + articulo.getResumen() + NL, VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Palabras clave:\n", VentanaPrincipal.ESTILO_NORMAL);
		for (String palabra : articulo.getListaPalabrasClave()) {
			ventanaPrincipal.agregarTexto(palabra + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
		for (int i = 0; i < articulo.getListaTitulosCapitulos().size(); i++) {
			ventanaPrincipal.agregarTexto(articulo.getListaTitulosCapitulos().get(i) + NL,
					VentanaPrincipal.ESTILO_TITULO_CAPITULO);
			ventanaPrincipal.agregarTexto(articulo.getListaContenidoCapitulos().get(i) + NL,
					VentanaPrincipal.ESTILO_NORMAL);
		}
		ventanaPrincipal.agregarTexto("\nReferencias:\n", VentanaPrincipal.ESTILO_TITULO_CAPITULO);
		ventanaPrincipal.agregarTexto(articulo.getListaReferencias(), VentanaPrincipal.ESTILO_NORMAL);
		cargarPalabrasClave();
	}

	public void cargarPalabrasClave() {
		ventanaPrincipal.getPanelResultados().limpiarLista();
		for (String palabra : articulo.getListaPalabrasClave()) {
			ventanaPrincipal.getPanelResultados().agregarPalabraClave(palabra);
		}
	}

	public void analizarPalabraClave(){
		String palabra = ventanaPrincipal.getPanelResultados()
				.obtenerPalabraSelecionada();
		verificadorPalabrasClave.contarPalabras(palabra);
		ventanaPrincipal.getPanelResultados().limiparTabla();
		for (ParteArticulo parteArticulo : verificadorPalabrasClave.getLista()) {
			ventanaPrincipal.getPanelResultados().agregarResultado(
					parteArticulo.getZonaArticulo().toString(),
					Long.toString(parteArticulo.getValorElemento()),
					Long.toString(parteArticulo.getMaximoElementos()), "0");
		}
	}

	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciar();
	}
}