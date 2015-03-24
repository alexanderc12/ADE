package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.AnalizadorTexto;
import modelo.ArticuloCientifico;
import vistas.VentanaPrincipal;

public class ControladorEventos implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;
	private AnalizadorTexto analizadorTexto;
	private ArticuloCientifico articulo;

	public ControladorEventos() {
		analizadorTexto = new AnalizadorTexto("http://ref.scielo.org/j39xws");
		articulo = analizadorTexto.getArticulo();
	}

	public void iniciar() {
		this.ventanaPrincipal = new VentanaPrincipal(this);
		cargarArticulo();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case VentanaPrincipal.E_CARGAR_WEB:

			break;
		}
	}

	public void cargarArticulo() {
		ventanaPrincipal.agregarTexto("Revista:" + articulo.getRevista() + "\t" + "Volumen: " + articulo.getVolumen()
				+ "\t"
				+ "Numero: " + articulo.getNumero() + "\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto(articulo.getTitulo(), VentanaPrincipal.ESTILO_TITULO);
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
		for (String autor : articulo.getListaAutores()) {
			ventanaPrincipal.agregarTexto(autor + "\t", VentanaPrincipal.ESTILO_NORMAL);
		}
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Fecha de recepción:" + articulo.getFechaRecepcion() + "\t"
				+ "Fecha de aprobación" + articulo.getFechaAprobacion() + "\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Resumen: \n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto(articulo.getResumen(), VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
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
		ventanaPrincipal.agregarTexto("\n\n", VentanaPrincipal.ESTILO_NORMAL);
		ventanaPrincipal.agregarTexto("Referencias: \n", VentanaPrincipal.ESTILO_NORMAL);
		for (String referencia : articulo.getListaReferencias()) {
			ventanaPrincipal.agregarTexto(referencia + "\n", VentanaPrincipal.ESTILO_NORMAL);
		}
	}

	public static void main(String[] args) {
		ControladorEventos c = new ControladorEventos();
		c.iniciar();
	}
}