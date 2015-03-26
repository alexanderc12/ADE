package modelo;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Representa la estructura de un articulo cientifico
 *
 * @author Alexander Castro
 *
 */
public class ArticuloCientifico {

	private String revista;
	private LocalDate fechaRecepcion;
	private LocalDate fechaAprobacion;
	private int volumen;
	private int numero;
	private String titulo;
	private String resumen;
	private ArrayList<String> listaAutores;
	private ArrayList<String> listaReferencias;
	private ArrayList<String> listaPalabrasClave;
	private ArrayList<String> listaContenidoCapitulos;
	private ArrayList<String> listaCapitulos;
	private URL url;

	public ArticuloCientifico(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, LectorWebArticulo.ERROR_URL);
		}
		listaAutores = new ArrayList<String>();
		listaReferencias = new ArrayList<String>();
		listaPalabrasClave = new ArrayList<String>();
		listaContenidoCapitulos = new ArrayList<String>();
		listaCapitulos = new ArrayList<String>();
	}

	public void agregarAutor(String autor) {
		listaAutores.add(autor);
	}

	public void agregarReferencia(String referencia) {
		listaReferencias.add(referencia);
	}

	public void agregarPalabraClave(String palabra) {
		listaPalabrasClave.add(palabra);
	}

	public void agregarCapitulo(String capitulo) {
		listaCapitulos.add(capitulo);
	}

	public void agregarContenido(String contenido) {
		listaContenidoCapitulos.add(contenido);
	}

	public ArrayList<String> getListaAutores() {
		return listaAutores;
	}
	public void setListaAutores(ArrayList<String> listaAutores) {
		this.listaAutores = listaAutores;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public ArrayList<String> getListaReferencias() {
		return listaReferencias;
	}
	public void setListaReferencias(ArrayList<String> listaReferencias) {
		this.listaReferencias = listaReferencias;
	}
	public ArrayList<String> getPalabrasClave() {
		return listaPalabrasClave;
	}
	public void setPalabrasClave(ArrayList<String> palabrasClave) {
		this.listaPalabrasClave = palabrasClave;
	}
	public ArrayList<String> getContenidoCapitulos() {
		return listaContenidoCapitulos;
	}
	public void setContenidoCapitulos(ArrayList<String> contenidoCapitulos) {
		this.listaContenidoCapitulos = contenidoCapitulos;
	}
	public LocalDate getFechaRecepcion() {
		return fechaRecepcion;
	}
	public void setFechaRecepcion(LocalDate fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	public LocalDate getFechaAprobacion() {
		return fechaAprobacion;
	}
	public void setFechaAprobacion(LocalDate fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}
	public ArrayList<String> getListaCapitulos() {
		return listaCapitulos;
	}
	public void setListaCapitulos(ArrayList<String> listaCapitulos) {
		this.listaCapitulos = listaCapitulos;
	}
	public URL getUlr() {
		return url;
	}
	public void setUlr(URL ulr) {
		this.url = ulr;
	}
	
	public String getRevista() {
		return revista;
	}
	
	public void setRevista(String revista) {
		this.revista = revista;
	}

	@Override
	public String toString() {
		StringBuilder articulo = new StringBuilder();
		articulo.append("Revista").append(revista).append("Titulo: ").append(titulo).append("\nURL: ").append(url)
				.append("\nAutores: ")
		.append(listaAutores).append("\nResumen: ").append(resumen).append("\nPalabras clave:")
		.append(listaPalabrasClave).append("\nVolumen: ").append(volumen).append("\nNumero: ").append(numero)
		.append("\nFecha recepción: ").append(fechaRecepcion).append("\nFecha aprobación: ")
		.append("\nLista capitulos: ").append(listaCapitulos).append("\nLista contenidos: ")
		.append(listaContenidoCapitulos).append("\nLista referencias: ").append(listaReferencias);
		return articulo.toString();
	}


}