package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	public static final String A_CARGAR_ARCHIVO_WEB = "A_CARGAR_ARCHIVO_WEB";

	public void iniciar() {
		this.ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.setControlador(this);
		ventanaPrincipal.init();
		cargarArticulo();
		cargarPalabrasClave();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case A_VERIFICAR_PALABRAS_CLAVE:
			analizarPalabraClave();
			break;
		case A_CREAR_ARCHIVO:
			GestorArchivos.guardarArchivo(articulo, ventanaPrincipal);
			break;
		case A_CARGAR_ARCHIVO:
			verificadorPalabrasClave = new VerificadorPalabrasClave("");
			articulo = verificadorPalabrasClave.getArticulo();
			break;
		case A_CARGAR_ARCHIVO_WEB:
			verificadorPalabrasClave = new VerificadorPalabrasClave("");
			articulo = verificadorPalabrasClave.getArticulo();
			break;
		}
	}

	public void cargarArticulo() {
		ventanaPrincipal.agregarTexto("Revista:" + articulo.getRevista() + "\t" + "Volumen: " + articulo.getVolumen()
		+ "\t"
		+ "Numero: " + articulo.getNumero() + "\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto(articulo.getTitulo() + "\n\n",
				VentanaPrincipal.ESTILO_TITULO);
		for (String autor : articulo.getListaAutores()) {
			ventanaPrincipal.agregarTexto(autor + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Fecha de recepción:" + articulo.getFechaRecepcion() + "\t"
				+ "Fecha de aprobación:" + articulo.getFechaAprobacion() + "\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Resumen: \n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto(articulo.getResumen() + "\n\n",
				VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Palabras clave: \n", VentanaPrincipal.ESTILO_NORMAL);
		for (String palabra : articulo.getPalabrasClave()) {
			ventanaPrincipal.agregarTexto(palabra + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
		for (int i = 0; i < articulo.getListaCapitulos().size(); i++) {
			ventanaPrincipal.agregarTexto(articulo.getListaCapitulos().get(i) + "\n\n",
					VentanaPrincipal.ESTILO_TITULO_CAPITULO);
			ventanaPrincipal.agregarTexto(articulo.getContenidoCapitulos().get(i)
					+ "\n\n",
					VentanaPrincipal.ESTILO_NORMAL);

		}
		ventanaPrincipal.agregarTexto("\n\nReferencias: \n",
				VentanaPrincipal.ESTILO_NORMAL);
		for (String referencia : articulo.getListaReferencias()) {
			ventanaPrincipal.agregarTexto(referencia + "\n", VentanaPrincipal.ESTILO_NORMAL);
		}
	}

	public void cargarPalabrasClave() {
		for (String palabra : articulo.getPalabrasClave()) {
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