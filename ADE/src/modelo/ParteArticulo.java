package modelo;

public class ParteArticulo {

	private String zona;
	private double numeroElementosAnalizables;
	private double totalElementos;
	private double valorElementos;
	private double numeroElementosLema;
	private double valorElementosLema;
	private double valorSinonimos;
	private String texto;

	public ParteArticulo(String nombreZona, String texto) {
		this.zona = nombreZona;
		this.texto = texto;
		this.totalElementos = Util.contarPalabras(texto);
	}

	/**
	 * @return con que frecuencia aparace el termino dependiendo de los
	 *         elementos analizables
	 */
	public double calcularPorcentajeFrecuenciaRegular() {
		return ((valorElementos * 100) / numeroElementosAnalizables);
	}

	public double calcularPorcentajeFrecuenciaLema() {
		return ((valorElementosLema * 100) / numeroElementosLema);
	}

	public double calcularPorcentajeFrecuenciaSinonimo() {
		return ((valorSinonimos * 100) / numeroElementosAnalizables);
	}

	/**
	 * Asigna 100 puntos si el valor del porcentaje es de 2,5, de lo contrario
	 * hace una regla de 3 para calcular cuantos puntos optiene
	 *
	 * @return
	 */
	public double calcularPuntosFrecuencia(double frecuencia) {
		double totalPuntos = 0;
		if (frecuencia <= 5) {
			if (frecuencia >= 2.5) {
				frecuencia = 5 - frecuencia;
			}
			totalPuntos = frecuencia * 40;
		}
		return totalPuntos;
	}

	public double generarPuntaje() {
		return calcularPuntosFrecuencia(calcularPorcentajeFrecuenciaRegular()) * 0.7
				+ calcularPuntosFrecuencia(calcularPorcentajeFrecuenciaLema()) * 0.25
				+ calcularPuntosFrecuencia(calcularPorcentajeFrecuenciaSinonimo()) * 0.5;
	}

	public double getNumeroElementosAnalizables() {
		return numeroElementosAnalizables;
	}

	public void setNumeroElementosAnalizables(double numeroElementosAnalizables) {
		this.numeroElementosAnalizables = numeroElementosAnalizables;
	}

	public double getValorElemento() {
		return valorElementos;
	}

	public void setValorElemento(double valorElemento) {
		this.valorElementos = valorElemento;
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
		return valorElementosLema;
	}

	public void setValorElementoLema(double valorElementoLema) {
		this.valorElementosLema = valorElementoLema;
	}

	public double getValorSinonimos() {
		return valorSinonimos;
	}

	public void setValorSinonimos(double valorSinonimos) {
		this.valorSinonimos = valorSinonimos;
	}

	public String getZona() {
		return zona;
	}
}