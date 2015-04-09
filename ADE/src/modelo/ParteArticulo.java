package modelo;

public class ParteArticulo {
	
	private ZonaArticulo zonaArticulo;
	private double numeroElementosAnalizables;
	private double totalElementos;
	private double valorElemento;
	private double numeroElementosLema;
	private double valorElementoLema;
	private String texto;
	
	public ParteArticulo(ZonaArticulo zonaArticulo, String texto) {
		this.zonaArticulo = zonaArticulo;
		this.texto = texto;
		this.totalElementos = Util.contarPalabras(texto);
	}
	
	/**
	 * @return con que frecuencia aparace el termino dependiendo de los
	 *         elementos analizables
	 */
	public double calcularPorcentajeFrecuencia() {
		return ((valorElemento * 100) / numeroElementosAnalizables);
	}
	
	/**
	 * Asigna 100 puntos si el valor del porcentaje es de 2,5, de lo contrario
	 * hace una regla de 3 para calcular cuantos puntos optiene
	 *
	 * @return
	 */
	public double calcularPuntos() {
		double totalPuntos = 0;
		double porcentajeFrecuencia = calcularPorcentajeFrecuencia();
		if (porcentajeFrecuencia <= 5) {
			if (porcentajeFrecuencia >= 2.5) {
				porcentajeFrecuencia = 5 - porcentajeFrecuencia;
			}
			totalPuntos = porcentajeFrecuencia * 40;
		}
		return totalPuntos;
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
	
	public double getTotalElementos() {
		return totalElementos;
	}
	
	public void setTotalElementos(double totalElementos) {
		this.totalElementos = totalElementos;
	}
	
	public double getNumeroElementosLema() {
		return numeroElementosLema;
	}
	
	public void setNumeroElementosLema(double numeroElementosLema) {
		this.numeroElementosLema = numeroElementosLema;
	}
	
	public double getValorElementoLema() {
		return valorElementoLema;
	}
	
	public void setValorElementoLema(double valorElementoLema) {
		this.valorElementoLema = valorElementoLema;
	}
}