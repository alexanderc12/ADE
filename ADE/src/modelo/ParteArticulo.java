package modelo;

public class ParteArticulo {
	
	private ZonaArticulo zonaArticulo;
	private int maximoElementos;
	private int valorElemento;
	private String texto;
	
	public ParteArticulo(ZonaArticulo zonaArticulo, String texto) {
		this.zonaArticulo = zonaArticulo;
		this.texto = texto;
	}
	
	public ZonaArticulo getZonaArticulo() {
		return zonaArticulo;
	}
	
	public void setZonaArticulo(ZonaArticulo zonaArticulo) {
		this.zonaArticulo = zonaArticulo;
	}
	
	public int getMaximoElementos() {
		return maximoElementos;
	}
	
	public void setMaximoElementos(int maximoElementos) {
		this.maximoElementos = maximoElementos;
	}
	
	public int getValorElemento() {
		return valorElemento;
	}
	
	public void setValorElemento(int valorElemento) {
		this.valorElemento = valorElemento;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
}