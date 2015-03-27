package modelo;

public class ParteArticulo {
	
	private ZonaArticulo zonaArticulo;
	private long maximoElementos;
	private long valorElemento;
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
	public long getMaximoElementos() {
		return maximoElementos;
	}
	public void setMaximoElementos(long maximoElementos) {
		this.maximoElementos = maximoElementos;
	}
	public long getValorElemento() {
		return valorElemento;
	}
	public void setValorElemento(long valorElemento) {
		this.valorElemento = valorElemento;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
}