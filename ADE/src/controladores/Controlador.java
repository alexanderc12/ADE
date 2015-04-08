package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import modelo.ArticuloCientifico;
import modelo.ParteArticulo;
import modelo.VerificadorPalabrasClave;
import modelo.VerificadorTerminos;
import persistencia.GestorArchivos;
import vistas.ConstantesGUI;
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
	public static final String A_EDITAR_PALABRAS_VACIAS = "A_EDITAR_PALABRAS_VACIAS";
	public static final String A_BUSCAR_EN_LISTAS = "BUSCAR_EN_INDICES";
	public static final String NL = System.getProperty("line.separator") + System.getProperty("line.separator");
	public static final DecimalFormat DECIMAL_FORMART = new DecimalFormat("#0.00");
	
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
				if (articulo != null) {
					analizarPalabraClave();
				}
				break;
			case A_CREAR_ARCHIVO:
				ventana.mostrarDialogoNuevoArticulo();
				break;
			case A_EXPORTAR_ARCHIVO:
				GestorArchivos.guardarArchivoArticulo(articulo, ventana);
				break;
			case A_CARGAR_ARCHIVO:
				cargarArticuloLocal();
				break;
			case A_CARGAR_ARCHIVO_WEB:
				cargarArticuloWeb();
				break;
			case A_EDITAR_PALABRAS_VACIAS:
				mostrarDialogoEditarPalabrasVacias();
				break;
			case A_BUSCAR_EN_LISTAS:
				buscarEnIndices();
				break;
		}
	}
	
	public void buscarEnIndices() {
		String palabra = articulo.getListaPalabrasClaveIngles()
				.get(ventana.getPanelResultados().obtenerIndicePalabraSelecionada());
		ventana.getPanelResultados().modificarPanelTerminosAparecen(
				VerificadorTerminos.verificarTermino(palabra, ConstantesGUI.LISTA_TERMINOS_IEEE),
				VerificadorTerminos.verificarTermino(palabra, ConstantesGUI.LISTA_TERMINOS_IFAC));
	}
	
	public void mostrarDialogoEditarPalabrasVacias(){
		ventana.mostrarDialogoEditarPalabrasVacias();
	}

	public void cargarArticuloWeb() {
		String url = JOptionPane.showInputDialog(ventana, "Ingrese la URL corta del articulo", "Cargar Articulo Web",
				JOptionPane.QUESTION_MESSAGE);
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
		articulo = GestorArchivos.cargarArchivoArticulo(ventana);
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
		ventana.agregarTexto(ConstantesGUI.T_REVISTA + articulo.getRevista()
		+ "\t" + ConstantesGUI.T_VOLUMEN + articulo.getVolumen() + "\t"
		+ ConstantesGUI.T_NUMERO + articulo.getNumero() + NL,
		VentanaPrincipal.ESTILO_NORMAL);
		ventana.agregarTexto(articulo.getTitulo() + NL, VentanaPrincipal.ESTILO_TITULO);
		for (String autor : articulo.getListaAutores()) {
			ventana.agregarTexto(autor + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventana.agregarTexto(
				NL + ConstantesGUI.T_FECHA_RECEPCION
				+ articulo.getFechaRecepcion() + "\t"
				+ ConstantesGUI.T_FECHA_RECEPCION
				+ articulo.getFechaAprobacion() + NL,
				VentanaPrincipal.ESTILO_NORMAL);
		ventana.agregarTexto(
				ConstantesGUI.T_RESUMEN + "\n" + articulo.getResumen() + NL,
				VentanaPrincipal.ESTILO_NORMAL);
		ventana.agregarTexto(ConstantesGUI.T_PALABRAS_CLAVE + "\n",
				VentanaPrincipal.ESTILO_NORMAL);
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
		ventana.agregarTexto("\n" + ConstantesGUI.T_REFERENCIAS + "\n",
				VentanaPrincipal.ESTILO_TITULO_CAPITULO);
		ventana.agregarTexto(articulo.getListaReferencias(), VentanaPrincipal.ESTILO_NORMAL);
		ventana.getBarraEstado().setNombreArticulo(articulo.getTitulo());
		cargarPalabrasClave();
		ventana.mostrarInicioArticulo();
		ventana.ocultarDialogoCargando();
		ventana.getPanelResultados().limiparTabla();
		ventana.getPanelResultados().limpiarResultadosIndices();
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
					DECIMAL_FORMART.format(parteArticulo.getValorElemento()),
					DECIMAL_FORMART.format(parteArticulo
							.getTotalElementos()),
							DECIMAL_FORMART.format(parteArticulo
									.getNumeroElementosAnalizables()),
									DECIMAL_FORMART.format(parteArticulo.obtenerPorcentaje()));
		}
		ventana.getBarraEstado().setPalabraClave(palabra);
		ventana.getBarraEstado().setEstadisticas("%");
	}
	
	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciar();
	}
}