package controladores;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import modelo.ArticuloCientifico;
import modelo.GeneradorReporte;
import modelo.GestorSemantico;
import modelo.ParteArticulo;
import modelo.VerificadorPalabrasClave;
import modelo.ZonaArticulo;
import persistencia.GestorArchivos;
import vistas.ConstantesGUI;
import vistas.DialogoAcercaDe;
import vistas.DialogoCargando;
import vistas.DialogoListaSinonimos;
import vistas.DialogoNuevoArticulo;
import vistas.DialogoPalabrasVacias;
import vistas.DialogoPonderados;
import vistas.DialogoReporte;
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
	private DialogoListaSinonimos dialogoListaSinonimos;
	private DialogoReporte dialogoReporte;
	private DialogoPonderados dialogoPonderados;
	private PanelResultados panelResultados;
	private VerificadorPalabrasClave verificadorPalabrasClave;
	private ArticuloCientifico articulo;
	private GeneradorReporte generadorReporte;

	public static final String A_VERIFICAR_PALABRAS_CLAVE = "VERIFICAR_PALABRAS_CLAVE";
	public static final String A_CREAR_ARCHIVO = "CREAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO = "CARGAR_ARCHIVO";
	public static final String A_EXPORTAR_ARCHIVO = "EXPORTAR_ARCHIVO";
	public static final String A_CARGAR_ARCHIVO_WEB = "A_CARGAR_ARCHIVO_WEB";
	public static final String A_EDITAR_PALABRAS_VACIAS = "A_EDITAR_PALABRAS_VACIAS";
	public static final String A_BUSCAR_NUEVA_PALABRA = "BUSCAR_NUEVA_PALABRA";
	public static final String A_VER_TERMINOS_TOP = "VER_DIALOGO_TERMINOS_TOP";
	public static final String A_ACTUALIZAR_LISTA_TERMINOS_TOP = "ACTUALIZAR_LISTA_TERMINOS_TOP";
	public static final String A_CONSULTAR_SINONIMOS = "CONSULTAR_SINONIMOS";
	public static final String A_ACTUALIZAR_LISTA_SINONIMOS = "ACTUALIZAR_LISTA_SINONIMOS";
	public static final String A_VER_ACERCA_DE = "VER_ACERCA_DE";
	public static final String A_GENERAR_REPORTE = "GENERAR_REPORTE";
	public static final String NL = System.getProperty("line.separator") + System.getProperty("line.separator");
	public static final DecimalFormat DECIMAL_FORMART = new DecimalFormat("#0.00");
	public static final String A_EDITAR_PONDERADOS = "EDITAR_PONDERADOS";
	public static final String A_SALIR = "SALIR";

	public void iniciar() {
		try {
			ZonaArticulo.actualizarPonderados(Files.readAllLines(Paths.get(ConstantesGUI.RUTA_PONDERADOS)));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_PONDERADOS, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		this.ventana = new VentanaPrincipal();
		ventana.init(this);
		dialogoProgreso = new DialogoCargando(ventana);
		dialogoPalabrasVacias = new DialogoPalabrasVacias(ventana);
		dialogoNuevoArticulo = new DialogoNuevoArticulo(ventana);
		dialogoAcercaDe = new DialogoAcercaDe(ventana);
		dialogoTopTerminos = new DialogoTopTerminos(ventana, this);
		dialogoListaSinonimos = new DialogoListaSinonimos(ventana, this);
		dialogoReporte = new DialogoReporte(ventana, this);
		dialogoPonderados = new DialogoPonderados(ventana);
		panelResultados = ventana.getPanelResultados();
		generadorReporte = new GeneradorReporte();
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case A_SALIR:
				System.exit(0);
				break;
			case A_VERIFICAR_PALABRAS_CLAVE:
				analizarPalabraClaveLista();
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
			case A_BUSCAR_NUEVA_PALABRA:
				analizarNuevaPalabraClave();
				break;
			case A_VER_ACERCA_DE:
				mostrarDialogoAcercaDe();
				break;
			case A_VER_TERMINOS_TOP:
				mostrarDialogoTerminosTop();
				break;
			case A_ACTUALIZAR_LISTA_TERMINOS_TOP:
				actualizarListaTerminosTop();
				break;
			case A_CONSULTAR_SINONIMOS:
				mostrarDialogoSinonimos();
				break;
			case A_ACTUALIZAR_LISTA_SINONIMOS:
				actualizarListaSinonimos();
				break;
			case A_GENERAR_REPORTE:
				generarReporte();
				break;
			case A_EDITAR_PONDERADOS:
				mostrarDialogoPonderado();
				break;
		}
	}

	public void cargarArticuloWeb() {
		String url = JOptionPane.showInputDialog(ventana, ConstantesGUI.DIALOGO_IMPORTAR_TITULO_WEB,
				ConstantesGUI.TITULO_CARGAR_WEB, JOptionPane.QUESTION_MESSAGE);
		if (url != null) {
			mostrarDialogoCargandoArticulo();
			SwingWorker<Void, Void> bWorker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
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
			mostrarDialogoCargandoArticulo();
			SwingWorker<Void, Void> aWorker = new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
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

	public void mostrarAnalisisPalabraClave(String palabra, String palabraEnIngles) {
		mostrarDialogoCargandoResultados();
		SwingWorker<Void, Void> aWorker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() {
				analizarPalabraClave(palabra, palabraEnIngles);
				return null;
			}
			@Override
			protected void done() {
				actulizarPanelResultados(palabra, palabraEnIngles);
				ocultarDialogoCargando();
			}
		};
		aWorker.execute();
	}

	public void mostrarArticulo() {
		if (articulo != null) {
			ventana.limpiarPanelArticulo();
			ventana.agregarTexto(
					ConstantesGUI.T_REVISTA + articulo.getRevista() + "\t" + ConstantesGUI.T_VOLUMEN
					+ articulo.getVolumen() + "\t" + ConstantesGUI.T_NUMERO + articulo.getNumero() + NL,
					ConstantesGUI.ESTILO_NORMAL);
			ventana.agregarTexto(articulo.getTitulo() + NL, ConstantesGUI.ESTILO_TITULO);
			for (String autor : articulo.getListaAutores()) {
				ventana.agregarTexto(autor + "\t", ConstantesGUI.ESTILO_NORMAL);
			}
			ventana.agregarTexto(NL + ConstantesGUI.T_RESUMEN + "\n" + articulo.getResumen() + NL,
					ConstantesGUI.ESTILO_NORMAL);
			ventana.agregarTexto(ConstantesGUI.T_PALABRAS_CLAVE + "\n", ConstantesGUI.ESTILO_NORMAL);
			for (String palabra : articulo.getListaPalabrasClave()) {
				ventana.agregarTexto(palabra + "\t", ConstantesGUI.ESTILO_NORMAL);
			}
			ventana.agregarTexto(NL, ConstantesGUI.ESTILO_NORMAL);
			for (int i = 0; i < articulo.getListaTitulosCapitulos().size(); i++) {
				ventana.agregarTexto(articulo.getListaTitulosCapitulos().get(i) + NL,
						ConstantesGUI.ESTILO_TITULO_CAPITULO);
				ventana.agregarTexto(articulo.getListaContenidoCapitulos().get(i) + NL, ConstantesGUI.ESTILO_NORMAL);
			}
			ventana.agregarTexto("\n" + ConstantesGUI.T_REFERENCIAS + "\n", ConstantesGUI.ESTILO_TITULO_CAPITULO);
			ventana.agregarTexto(articulo.getListaReferencias(), ConstantesGUI.ESTILO_NORMAL);
			ventana.getBarraEstado().setNombreArticulo(articulo.getTitulo());
			panelResultados.limpiarInterfaz();
			cargarPalabrasClave();
			ventana.mostrarInicioArticulo();
			ocultarDialogoCargando();
		} else {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_ARTICULO, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cargarPalabrasClave() {
		for (String palabra : articulo.getListaPalabrasClave()) {
			panelResultados.agregarPalabraClave(palabra);
		}
		panelResultados.activarSeleccion();
	}

	private void actualizarListaTerminosTop() {
		if (articulo != null) {
			dialogoTopTerminos.actualizarTablaTop(verificadorPalabrasClave.obtenerTopPalabras(
					dialogoTopTerminos.obtenerParteSeleccionada(), dialogoTopTerminos.obtenerNumero()));
		} else {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_ARTICULO, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void analizarNuevaPalabraClave() {
		if (articulo != null) {
			String palabra = JOptionPane.showInputDialog(ventana, ConstantesGUI.DIALOGO_BUSCAR_PALABRA_ES,
					ConstantesGUI.TITULO_BUSCAR_PALABRA, JOptionPane.QUESTION_MESSAGE);
			String palabraEnIngles = JOptionPane.showInputDialog(ventana, ConstantesGUI.DIALOGO_BUSCAR_PALABRA_EN,
					ConstantesGUI.TITULO_BUSCAR_PALABRA, JOptionPane.QUESTION_MESSAGE);
			mostrarAnalisisPalabraClave(palabra, palabraEnIngles);
		}
	}

	public void analizarPalabraClaveLista() {
		if (articulo != null) {
			String palabra = panelResultados.obtenerPalabraSelecionada();
			String palabraEnIngles = articulo.getListaPalabrasClaveIngles()
					.get(panelResultados.obtenerIndicePalabraSelecionada());
			mostrarAnalisisPalabraClave(palabra, palabraEnIngles);
		}
	}

	public void analizarPalabraClave(String palabra, String palabraEnIngles) {
		if (articulo != null) {
			verificadorPalabrasClave.contarFrecuenciaPalabra(palabra);
			verificadorPalabrasClave.contarFrecuenciaPalabrasLema(palabra);
			verificadorPalabrasClave.contarFrecuenciaMejorSinonimo(palabra);
			verificadorPalabrasClave.verificarIndices(palabraEnIngles);
		} else {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_ARTICULO, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actulizarPanelResultados(String palabra, String palabraEnIngles) {
		panelResultados.limpiarTabla();
		for (ParteArticulo parteArticulo : verificadorPalabrasClave.getLista()) {
			panelResultados.agregarResultado(ZonaArticulo.toString(parteArticulo.getZona()),
					DECIMAL_FORMART.format(parteArticulo.getValorElemento()),
					DECIMAL_FORMART.format(parteArticulo.getTotalElementos()),
					DECIMAL_FORMART.format(parteArticulo.getNumeroElementosAnalizables()),
					DECIMAL_FORMART.format(parteArticulo.getNumeroElementosLema()),
					DECIMAL_FORMART.format(parteArticulo.getValorElementoLema()),
					DECIMAL_FORMART.format(parteArticulo.getValorSinonimos()),
					DECIMAL_FORMART.format(parteArticulo.calcularPorcentajeFrecuencia()));
		}
		panelResultados.modificarPanelTerminosAparecen(verificadorPalabrasClave.isAparaceIEEE(),
				verificadorPalabrasClave.isApareceIFAC());
		ventana.getBarraEstado().setPalabraClave(palabra);
		double porcentaje = verificadorPalabrasClave.calcularPuntajePalabra();
		ventana.getBarraEstado().setInformacionEstadistica(DECIMAL_FORMART.format(porcentaje) + "%");
		panelResultados.modificarNivelAfinidad(porcentaje);

	}

	public void actualizarListaSinonimos() {
		ArrayList<String> listaSinonimos = GestorSemantico.buscarSinonimos(dialogoListaSinonimos.obtenerTermino());
		for (String sinonimo : listaSinonimos) {
			dialogoListaSinonimos.actualizarListaSinonimos(sinonimo);
		}
	}

	private void mostrarDialogoTerminosTop() {
		dialogoTopTerminos.setVisible(true);
		dialogoTopTerminos.limpiarInterfaz();
	}

	public void mostrarDialogoAcercaDe() {
		dialogoAcercaDe.setVisible(true);
	}

	public void mostrarDialogoCargandoArticulo() {
		ventana.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		dialogoProgreso.configuarParaArticulo();
		dialogoProgreso.setVisible(true);
	}

	public void mostrarDialogoCargandoResultados() {
		ventana.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		dialogoProgreso.configuarParaResultados();
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

	public void mostrarDialogoSinonimos() {
		dialogoListaSinonimos.setVisible(true);
	}

	private void generarReporte() {
		if (articulo != null && verificadorPalabrasClave.getPalabra() != null) {
			dialogoReporte.setVisible(true);
			dialogoReporte.agregarTexto(generadorReporte.generarReporte(verificadorPalabrasClave));
		}
	}

	private void mostrarDialogoPonderado() {
		try {
			ZonaArticulo.actualizarPonderados(Files.readAllLines(Paths.get(ConstantesGUI.RUTA_PONDERADOS)));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_PONDERADOS, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		dialogoPonderados.actualizarPonderados();
		dialogoPonderados.setVisible(true);
	}

	public static void main(String[] args) {
		Controlador c = new Controlador();
		c.iniciar();
	}
}