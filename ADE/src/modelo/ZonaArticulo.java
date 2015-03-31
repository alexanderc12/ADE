package modelo;

public enum ZonaArticulo {

	TITULO(1), RESUMEN(1), INTRODUCCION(1), TITULOS_CAPITULOS(1), CONTENIDOS(1), CONCLUSIONES(1), REFERENCIAS(1);

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
