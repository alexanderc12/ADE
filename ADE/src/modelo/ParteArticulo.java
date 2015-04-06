package modelo;

public class ParteArticulo {

	private ZonaArticulo zonaArticulo;
	private double numeroElementosAnalizables;
	private double totalElementos;
	private double valorElemento;
	private String texto;

	public ParteArticulo(ZonaArticulo zonaArticulo, String texto) {
		this.zonaArticulo = zonaArticulo;
		this.texto = texto;
		this.totalElementos = Util.contarPalabras(texto);
	}

	public ZonaArticulo getZonaArticulo() {
		return zonaArticulo;
	}

	public void setZonaArticulo(ZonaArticulo zonaArticulo) {
		this.zonaArticulo = zonaArticulo;
	}

	public double getNumeroElementosAnalizables() {
		return numeroElementosAnalizables;
	}

	public void setNumeroElementosAnalizables(double numeroElementosAnalizables) {
		this.numeroElementosAnalizables = numeroElementosAnalizables;
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
		return ((valorElemento * 100) / numeroElementosAnalizables);
	}
	
	public double getTotalElementos() {
		return totalElementos;
	}
	
	public void setTotalElementos(double totalElementos) {
		this.totalElementos = totalElementos;
	}
}