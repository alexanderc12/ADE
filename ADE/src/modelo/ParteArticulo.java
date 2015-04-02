package modelo;

public class ParteArticulo {

	private ZonaArticulo zonaArticulo;
	private double maximoElementos;
	private double valorElemento;
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

	public double getMaximoElementos() {
		return maximoElementos;
	}

	public void setMaximoElementos(double maximoElementos) {
		this.maximoElementos = maximoElementos;
	}

	public double getValorElemento() {
		return valorElemento;
	}

	public void setValorElemento(double valorElemento) {
		this.valorElemento = valorElemento;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public double obtenerPorcentaje() {
		return ((valorElemento * 100) / maximoElementos);
	}
}