package modelo;

import java.io.Serializable;
import java.util.ArrayList;

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
	private String url;
	private String revista;
	private int volumen;
	private int numero;
	private ArrayList<String> listaAutores;
	/**
	 * La lista de palabras claves es el atributo que contenie las palabras con
	 * las que fue indexado el articulo
	 */
	private ArrayList<String> listaPalabrasClave;
	private ArrayList<String> listaPalabrasClaveIngles;
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

		listaReferencias = "";
		listaAutores = new ArrayList<String>();
		listaPalabrasClave = new ArrayList<String>();
		listaPalabrasClaveIngles = new ArrayList<String>();
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
	
	public void agregarPalabraClaveIngles(String palabra) {
		listaPalabrasClaveIngles.add(palabra);
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
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRevista() {
		return revista;
	}

	public void setRevista(String revista) {
		this.revista = revista;
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
	
	public ArrayList<String> getListaPalabrasClaveIngles() {
		return listaPalabrasClaveIngles;
	}
	
	public void setListaPalabrasClaveIngles(
			ArrayList<String> listaPalabrasClaveIngles) {
		this.listaPalabrasClaveIngles = listaPalabrasClaveIngles;
	}
}