package controladores;

import java.awt.Cursor;
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
import vistas.DialogoAcercaDe;
import vistas.DialogoCargando;
import vistas.DialogoNuevoArticulo;
import vistas.DialogoPalabrasVacias;
import vistas.DialogoTopTerminos;
import vistas.PanelResultados;
import vistas.VentanaPrincipal;

public class Controlador implements ActionListener {
	
	private VentanaPrincipal ventana;
	private DialogoCargando dialogoProgreso;
	private DialogoPalabrasVacias dialogoPalabrasVacias;
	private DialogoNuevoArticulo dialogoNuevoArticulo;
	private DialogoAcercaDe dialogoAcercaDe;
	private DialogoTopTerminos dialogoTopTerminos;
	private VerificadorPalabrasClave verificadorPalabrasClave;
	private PanelResultados panelResultados;
	private ArticuloCientifico articulo;
	
	public static final String A_VERIFICAR_PALABRAS_CLAVE = "VERIFICAR_PALABRAS_CLAVE";
	public static final String A_CREAR_ARCHIVO = "CREAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO = "CARGAR_ARCHIVO";
	public static final String A_EXPORTAR_ARCHIVO = "EXPORTAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO_WEB = "A_CARGAR_ARCHIVO_WEB";
	public static final String A_EDITAR_PALABRAS_VACIAS = "A_EDITAR_PALABRAS_VACIAS";
	public static final String A_BUSCAR_EN_LISTAS = "BUSCAR_EN_INDICES";
	public static final String A_VER_ACERCA_DE = "VER_ACERCA_DE";
	public static final String NL = System.getProperty("line.separator") + System.getProperty("line.separator");
	public static final DecimalFormat DECIMAL_FORMART = new DecimalFormat("#0.00");
	public static final String A_VER_TERMINOS_TOP = "VER_DIALOGO_TERMINOS_TOP";
	public static final String A_ACTUALIZAR_LISTA_TERMINOS_TOP = "ACTUALIZAR_LISTA_TERMINOS_TOP";
	
	public void iniciar() {
		this.ventana = new VentanaPrincipal();
		ventana.setControlador(this);
		ventana.init();
		dialogoProgreso = new DialogoCargando(ventana);
		dialogoPalabrasVacias = new DialogoPalabrasVacias(ventana);
		dialogoNuevoArticulo = new DialogoNuevoArticulo(ventana);
		dialogoAcercaDe = new DialogoAcercaDe(ventana);
		dialogoTopTerminos = new DialogoTopTerminos(ventana, this);
		panelResultados = ventana.getPanelResultados();
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
				mostrarDialogoNuevoArticulo();
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
			case A_VER_ACERCA_DE:
				mostrarDialogoAcercaDe();
				break;
			case A_VER_TERMINOS_TOP:
				mostrarDialogoTopTerminos();
				break;
			case A_ACTUALIZAR_LISTA_TERMINOS_TOP:
				actualizarListaTerminosTop();
				break;
		}
	}
	
	private void actualizarListaTerminosTop() {
		dialogoTopTerminos.agregarLista(verificadorPalabrasClave
				.obtenerTopPalabras(dialogoTopTerminos.obtenerParteSeleccionada(), dialogoTopTerminos.obtenerNumero()));
	}
	
	private void mostrarDialogoTopTerminos() {
		dialogoTopTerminos.setVisible(true);
		dialogoTopTerminos.limpiarInterfaz();
	}
	
	public void mostrarDialogoAcercaDe() {
		dialogoAcercaDe.setVisible(true);
	}
	
	public void buscarEnIndices() {
		String palabra = articulo.getListaPalabrasClaveIngles()
				.get(panelResultados.obtenerIndicePalabraSelecionada());
		panelResultados.modificarPanelTerminosAparecen(
				VerificadorTerminos.verificarTermino(palabra, ConstantesGUI.LISTA_TERMINOS_IEEE),
				VerificadorTerminos.verificarTermino(palabra, ConstantesGUI.LISTA_TERMINOS_IFAC));
	}
	
	public void cargarArticuloWeb() {
		String url = JOptionPane.showInputDialog(ventana, "Ingrese la URL corta del articulo", "Cargar Articulo Web",
				JOptionPane.QUESTION_MESSAGE);
		if (url != null) {
			mostrarDialogoCargando();
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
			mostrarDialogoCargando();
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
		panelResultados.limiparTabla();
		panelResultados.limpiarResultadosIndices();
		ocultarDialogoCargando();
	}
	
	public void cargarPalabrasClave() {
		panelResultados.limpiarLista();
		for (String palabra : articulo.getListaPalabrasClave()) {
			panelResultados.agregarPalabraClave(palabra);
		}
		panelResultados.activarSeleccion();
	}
	
	public void analizarPalabraClave(){
		String palabra = panelResultados.obtenerPalabraSelecionada();
		verificadorPalabrasClave.contarPalabras(palabra);
		verificadorPalabrasClave.contarPalabrasLema(palabra);
		panelResultados.limiparTabla();
		for (ParteArticulo parteArticulo : verificadorPalabrasClave.getLista()) {
			panelResultados.agregarResultado(
					parteArticulo.getZonaArticulo().toString(),
					DECIMAL_FORMART.format(parteArticulo.getValorElemento()),
					DECIMAL_FORMART.format(parteArticulo.getTotalElementos()),
					DECIMAL_FORMART.format(parteArticulo.getNumeroElementosAnalizables()),
					DECIMAL_FORMART.format(parteArticulo.getNumeroElementosLema()),
					DECIMAL_FORMART.format(parteArticulo.getValorElementoLema()),
					DECIMAL_FORMART.format(parteArticulo.calcularPorcentajeFrecuencia()));
		}
		ventana.getBarraEstado().setPalabraClave(palabra);
		double porcentaje = verificadorPalabrasClave.calcularPuntajePalabra();
		ventana.getBarraEstado().setEstadisticas(DECIMAL_FORMART.format(porcentaje) + "%");
		panelResultados.modificarNivelAfinidad(porcentaje);
	}
	
	public void mostrarDialogoCargando() {
		ventana.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		dialogoProgreso.setVisible(true);
	}
	
	public void ocultarDialogoCargando() {
		ventana.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		dialogoProgreso.setVisible(false);
	}
	
	public void mostrarDialogoEditarPalabrasVacias() {
		dialogoPalabrasVacias.setVisible(true);
	}
	
	public void mostrarDialogoNuevoArticulo() {
		dialogoNuevoArticulo.setVisible(true);
	}
	
	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciar();
	}
}