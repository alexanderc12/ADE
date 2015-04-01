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
	
	private VentanaPrincipal ventana;
	private VerificadorPalabrasClave verificadorPalabrasClave;
	private ArticuloCientifico articulo;
	
	public static final String A_VERIFICAR_PALABRAS_CLAVE = "VERIFICAR_PALABRAS_CLAVE";
	public static final String A_CREAR_ARCHIVO = "CREAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO = "CARGAR_ARCHIVO";
	public static final String A_EXPORTAR_ARCHIVO = "EXPORTAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO_WEB = "A_CARGAR_ARCHIVO_WEB";
	public static final String NL = System.getProperty("line.separator") + System.getProperty("line.separator");
	
	public void iniciar() {
		this.ventana = new VentanaPrincipal();
		ventana.setControlador(this);
		ventana.init();
		ventana.setVisible(true);
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
				GestorArchivos.guardarArchivo(articulo, ventana);
				break;
			case A_CARGAR_ARCHIVO:
				cargarArticuloLocal();
				break;
			case A_CARGAR_ARCHIVO_WEB:
				cargarArticuloWeb();
				break;
		}
	}
	
	public void cargarArticuloWeb() {
		String url = JOptionPane.showInputDialog(ventana, "Ingrese la URL corta del articulo",
				"Cargar Articulo Web", JOptionPane.QUESTION_MESSAGE);
		if (url != null) {
			ventana.mostrarDialogoCargando();
			SwingWorker<Void, Void> bWorker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					verificadorPalabrasClave = new VerificadorPalabrasClave(url);
					articulo = verificadorPalabrasClave.getArticulo();
					return null;
				}
				@Override
				protected void done() {
					mostrarArticulo();
				}
			};
			bWorker.execute();
		}
	}
	
	public void cargarArticuloLocal() {
		articulo = GestorArchivos.cargarArchivo(ventana);
		if (articulo != null) {
			ventana.mostrarDialogoCargando();
			SwingWorker<Void, Void> aWorker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {
					verificadorPalabrasClave = new VerificadorPalabrasClave(articulo);
					return null;
				}
				@Override
				protected void done() {
					mostrarArticulo();
				}
			};
			aWorker.execute();
		}
	}
	
	public void mostrarArticulo() {
		ventana.limpiarPanelArticulo();
		ventana.agregarTexto("Revista: " + articulo.getRevista() + "\t Volumen: " + articulo.getVolumen()
		+ "\tNumero: " + articulo.getNumero() + NL, VentanaPrincipal.ESTILO_NORMAL);
		ventana.agregarTexto(articulo.getTitulo() + NL, VentanaPrincipal.ESTILO_TITULO);
		for (String autor : articulo.getListaAutores()) {
			ventana.agregarTexto(autor + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventana.agregarTexto("\n\nFecha de recepción:" + articulo.getFechaRecepcion()
		+ "\tFecha de aprobación:" + articulo.getFechaAprobacion() + NL, VentanaPrincipal.ESTILO_NORMAL);
		ventana.agregarTexto("Resumen:\n" + articulo.getResumen() + NL, VentanaPrincipal.ESTILO_NORMAL);
		ventana.agregarTexto("Palabras clave:\n", VentanaPrincipal.ESTILO_NORMAL);
		for (String palabra : articulo.getListaPalabrasClave()) {
			ventana.agregarTexto(palabra + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventana.agregarTexto(NL, VentanaPrincipal.ESTILO_NORMAL);
		for (int i = 0; i < articulo.getListaTitulosCapitulos().size(); i++) {
			ventana.agregarTexto(articulo.getListaTitulosCapitulos().get(i) + NL,
					VentanaPrincipal.ESTILO_TITULO_CAPITULO);
			ventana.agregarTexto(articulo.getListaContenidoCapitulos().get(i) + NL,
					VentanaPrincipal.ESTILO_NORMAL);
		}
		ventana.agregarTexto("\nReferencias:\n", VentanaPrincipal.ESTILO_TITULO_CAPITULO);
		ventana.agregarTexto(articulo.getListaReferencias(), VentanaPrincipal.ESTILO_NORMAL);
		ventana.getBarraEstado().setNombreArticulo(articulo.getTitulo());
		cargarPalabrasClave();
		ventana.mostrarInicioArticulo();
		ventana.ocultarDialogoCargando();
	}
	
	public void cargarPalabrasClave() {
		ventana.getPanelResultados().limpiarLista();
		for (String palabra : articulo.getListaPalabrasClave()) {
			ventana.getPanelResultados().agregarPalabraClave(palabra);
		}
		ventana.getPanelResultados().activarSeleccion();
	}
	
	public void analizarPalabraClave(){
		String palabra = ventana.getPanelResultados().obtenerPalabraSelecionada();
		verificadorPalabrasClave.contarPalabras(palabra);
		ventana.getPanelResultados().limiparTabla();
		for (ParteArticulo parteArticulo : verificadorPalabrasClave.getLista()) {
			ventana.getPanelResultados().agregarResultado(
					parteArticulo.getZonaArticulo().toString(),
					Long.toString(parteArticulo.getValorElemento()),
					Long.toString(parteArticulo.getMaximoElementos()), "0");
		}
		ventana.getBarraEstado().setPalabraClave(palabra);
	}
	
	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciar();
	}
}