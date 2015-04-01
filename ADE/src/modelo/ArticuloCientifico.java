package modelo;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import persistencia.LectorWeb;

/**
 * Representa la estructura de un articulo cientifico
 *
 * @author Alexander Castro
 *
 */
public class ArticuloCientifico implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Estos atributos son metadatos del articulo pero que no tienen que ver con
	 * su contenido
	 */
	private URL url;
	private String revista;
	private String fechaRecepcion;
	private String fechaAprobacion;
	private int volumen;
	private int numero;
	private ArrayList<String> listaAutores;
	/**
	 * La lista de palabras claves es el atributo que contenie las palabras con
	 * las que fue indexado el articulo
	 */
	private ArrayList<String> listaPalabrasClave;
	/**
	 * Estos son los atributos que tienen que ver directamente con el contenido
	 * el articulo y seran analizados
	 */
	private String titulo;
	private String resumen;
	private String listaReferencias;
	/**
	 * De estas listas se supone que el primer elemento es la introducción y el
	 * ultimo las conclusiones
	 */
	private ArrayList<String> listaTitulosCapitulos;
	private ArrayList<String> listaContenidoCapitulos;
	
	public ArticuloCientifico(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, LectorWeb.ERROR_URL, ConstantesTexto.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		listaReferencias = "";
		listaAutores = new ArrayList<String>();
		listaPalabrasClave = new ArrayList<String>();
		listaTitulosCapitulos = new ArrayList<String>();
		listaContenidoCapitulos = new ArrayList<String>();
	}
	
	/**
	 * Metodos para agregar datos al articulo
	 */
	public void agregarAutor(String autor) {
		listaAutores.add(autor);
	}

	public void agregarPalabraClave(String palabra) {
		listaPalabrasClave.add(palabra);
	}

	public void agregarTituloCapitulo(String tituloCapitulo) {
		listaTitulosCapitulos.add(tituloCapitulo);
	}

	public void agregarContenidoCapitulo(String contenidoCapitulo) {
		listaContenidoCapitulos.add(contenidoCapitulo);
	}
	
	public void agregarReferencia(String referencia) {
		this.listaReferencias += referencia + "\n";
	}

	/**
	 * Metodos para obtener el numero de capitulos del articulo
	 */
	public int getNumeroCapitulos() {
		return listaTitulosCapitulos.size();
	}

	/**
	 * Metodos Set y Get
	 */
	public URL getUrl() {
		return url;
	}
	
	public void setUrl(URL url) {
		this.url = url;
	}
	
	public String getRevista() {
		return revista;
	}
	
	public void setRevista(String revista) {
		this.revista = revista;
	}
	
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}
	
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	
	public String getFechaAprobacion() {
		return fechaAprobacion;
	}
	
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
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
	
	public ArrayList<String> getListaAutores() {
		return listaAutores;
	}
	
	public void setListaAutores(ArrayList<String> listaAutores) {
		this.listaAutores = listaAutores;
	}
	
	public ArrayList<String> getListaPalabrasClave() {
		return listaPalabrasClave;
	}
	
	public void setListaPalabrasClave(ArrayList<String> listaPalabrasClave) {
		this.listaPalabrasClave = listaPalabrasClave;
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
	
	public String getListaReferencias() {
		return listaReferencias;
	}
	
	public void setListaReferencias(String listaReferencias) {
		this.listaReferencias = listaReferencias;
	}
	
	public ArrayList<String> getListaContenidoCapitulos() {
		return listaContenidoCapitulos;
	}
	
	public void setListaContenidoCapitulos(ArrayList<String> listaContenidoCapitulos) {
		this.listaContenidoCapitulos = listaContenidoCapitulos;
	}
	
	public ArrayList<String> getListaTitulosCapitulos() {
		return listaTitulosCapitulos;
	}
	
	public void setListaTitulosCapitulos(ArrayList<String> listaTitulosCapitulos) {
		this.listaTitulosCapitulos = listaTitulosCapitulos;
	}
	
	@Override
	public String toString() {
		StringBuilder articulo = new StringBuilder();
		articulo.append("Revista").append(revista).append("Titulo: ").append(titulo).append("\nURL: ").append(url)
		.append("\nAutores: ")
		.append(listaAutores).append("\nResumen: ").append(resumen).append("\nPalabras clave:")
		.append(listaPalabrasClave).append("\nVolumen: ").append(volumen).append("\nNumero: ").append(numero)
		.append("\nFecha recepción: ").append(fechaRecepcion).append("\nFecha aprobación: ")
		.append("\nLista capitulos: ").append(listaTitulosCapitulos).append("\nLista contenidos: ")
		.append(listaContenidoCapitulos).append("\nLista referencias: ").append(listaReferencias);
		return articulo.toString();
	}
}