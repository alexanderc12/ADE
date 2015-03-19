package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArticuloCientifico {
	
	private ArrayList<String> listaAutores;
	private LocalDate fecha;
	private int volumen;
	private int numero;
	private String titulo;
	private String tituloIngles;
	private String resumen;
	private String resumenIngles;
	private ArrayList<String> listaReferencias;
	private ArrayList<String> palabrasClave;
	private ArrayList<String> palabrasClaveIngles;
	private ArrayList<String> contenidoCapitulos;
	
	public ArrayList<String> getListaAutores() {
		return listaAutores;
	}
	public void setListaAutores(ArrayList<String> listaAutores) {
		this.listaAutores = listaAutores;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
	public String getResumenIngles() {
		return resumenIngles;
	}
	public void setResumenIngles(String resumenIngles) {
		this.resumenIngles = resumenIngles;
	}
	public ArrayList<String> getListaReferencias() {
		return listaReferencias;
	}
	public void setListaReferencias(ArrayList<String> listaReferencias) {
		this.listaReferencias = listaReferencias;
	}
	public ArrayList<String> getPalabrasClave() {
		return palabrasClave;
	}
	public void setPalabrasClave(ArrayList<String> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
	public ArrayList<String> getPalabrasClaveIngles() {
		return palabrasClaveIngles;
	}
	public void setPalabrasClaveIngles(ArrayList<String> palabrasClaveIngles) {
		this.palabrasClaveIngles = palabrasClaveIngles;
	}
	public ArrayList<String> getContenidoCapitulos() {
		return contenidoCapitulos;
	}
	public void setContenidoCapitulos(ArrayList<String> contenidoCapitulos) {
		this.contenidoCapitulos = contenidoCapitulos;
	}
	public String getTituloIngles() {
		return tituloIngles;
	}
	public void setTituloIngles(String tituloIngles) {
		this.tituloIngles = tituloIngles;
	}
}