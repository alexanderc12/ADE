package modelo;

public enum ZonaArticulo {

	TITULO(0.1429), RESUMEN(0.1429), INTRODUCCION(0.1429), TITULOS_CAPITULOS(0.1429), CONTENIDOS(0.1429), CONCLUSIONES(
			0.1429), REFERENCIAS(0.1429);

	private final double ponderado;

	ZonaArticulo(double ponderado) {
		this.ponderado = ponderado;
	}

	public double getPonderado() {
		return ponderado;
	}

	@Override
	public String toString() {
		return Util.pasarEnumAString(name());
	}
}
