package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArticuloCientifico {
	
	private ArrayList<String> listaAutores;
	private LocalDate fechaRecepcion;
	private LocalDate fechaAprobacion;
	private int volumen;
	private int numero;
	private String titulo;
	private String resumen;
	private ArrayList<String> listaReferencias;
	private ArrayList<String> palabrasClave;
	private ArrayList<String> contenidoCapitulos;
	
	public ArticuloCientifico() {
		listaAutores = new ArrayList<String>();
		listaReferencias = new ArrayList<String>();
		palabrasClave = new ArrayList<String>();
		contenidoCapitulos = new ArrayList<String>();
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
		return palabrasClave;
	}
	public void setPalabrasClave(ArrayList<String> palabrasClave) {
		this.palabrasClave = palabrasClave;
	}
	public ArrayList<String> getContenidoCapitulos() {
		return contenidoCapitulos;
	}
	public void setContenidoCapitulos(ArrayList<String> contenidoCapitulos) {
		this.contenidoCapitulos = contenidoCapitulos;
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
}