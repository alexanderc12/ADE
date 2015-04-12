package modelo;

import java.text.DecimalFormat;

public class GeneradorReporte {

	public static final DecimalFormat DECIMAL_FORMART = new DecimalFormat("#0.00");

	public String generarReporte(VerificadorPalabrasClave verificador) {
		ArticuloCientifico articulo = verificador.getArticulo();
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html><body>");
		htmlBuilder.append("<h5>REPORTE ANALISIS DE TERMINO CLAVE: " + verificador.getPalabra().toUpperCase() + " - "
				+ verificador.getPalabraEnIngles().toUpperCase() + " EN EL ARTICULO : "
				+ articulo.getTitulo().toUpperCase() + " </h5>");
		htmlBuilder.append("<h6>Resumen:</h6>");
		htmlBuilder.append("<small> Luego de analizar <strong>" + verificador.calcularTotalElementos()
		+ "</strong> palabras de las cuales <strong>" + verificador.calcularTotalElementosAnalizables()
		+ "</strong> son valiosas semanticamente y <strong>"
		+ (verificador.calcularTotalElementos() - verificador.calcularTotalElementosAnalizables())
		+ "</strong> son palabaras vacias (como conectores y pronombres), se determina que la palabra clave analizada obtiene "
		+ "una puntación(en la escala de 0 a 100) de: </small> </br>");
		double puntajeFinal = verificador.calcularPuntajePalabra();
		if (puntajeFinal > 0 && puntajeFinal <= 20) {
			htmlBuilder.append("<span style=\"color: #ffffff; background-color: #D9534F;\">"
					+ DECIMAL_FORMART.format(puntajeFinal) + "% </span>");
		} else if (puntajeFinal > 21 && puntajeFinal <= 40) {
			htmlBuilder.append("<span style=\"color: #ffffff; background-color: #F0AD4E;\">"
					+ DECIMAL_FORMART.format(puntajeFinal) + "% </span>");
		} else if (puntajeFinal > 40 && puntajeFinal <= 60) {
			htmlBuilder.append("<span style=\"color: #ffffff; background-color: #5BC0DE;\">"
					+ DECIMAL_FORMART.format(puntajeFinal) + "% </span>");
		} else if (puntajeFinal > 60 && puntajeFinal <= 80) {
			htmlBuilder.append("<span style=\"color: #ffffff; background-color: #337AB7;\">"
					+ DECIMAL_FORMART.format(puntajeFinal) + "% </span>");
		} else if (puntajeFinal > 80 && puntajeFinal <= 100) {
			htmlBuilder.append("<span style=\"color: #ffffff; background-color: #5CB85C;\">"
					+ DECIMAL_FORMART.format(puntajeFinal) + "% </span>");
		}

		htmlBuilder.append("<h6>Detalles: </h6>");

		htmlBuilder.append("<ul class=\"list-group\">");
		if (verificador.isAparaceIEEE()) {
			htmlBuilder
			.append("<li class=\"list-group-item\"> <p class=\"bg-success\">La palabra clave aparece en el indice de "
					+ "terminos de la IEEE :), esto permite una mejor indexación.</li> </p>");
		}else{
			htmlBuilder.append(
					"<li class=\"list-group-item\"> <p class=\"bg-danger\">La palabra clave NO aparece en el indice de "
							+ "terminos de la IEEE :(, esto no permite que se indexe correctamente.</li> </p>");
		}
		if (verificador.isApareceIFAC()) {
			htmlBuilder.append(
					"<li class=\"list-group-item\"> <p class=\"bg-success\">La palabra clave aparece en el indice de "
							+ "terminos de la IFAC :), esto permite una mejor indexación.</li> </p>");
		}else{
			htmlBuilder.append(
					"<li class=\"list-group-item\"> <p class=\"bg-danger\"> La palabra clave NO aparece en el indice de"
							+ " terminos de la IFAC :(, esto no permite que se indexe correctamente.</li></p>");
		}
		htmlBuilder.append("</ul>");

		htmlBuilder.append(
				"<p><small>Esta es el puntaje obtenido por la frecuencia con la que aparace el termino en el articulo "
						+ "analizando sus partes, este dato es uno de los más importantes para determinar el puntaje final "
						+ "del articulo (lo idel es una frecuencia de 2,5%, si es mayor a 5% se considera  keyword stuffing.) </small></p>");

		for (ParteArticulo parte : verificador.getLista()) {
			htmlBuilder.append("<div class=\"panel panel-default\">  <small>" + ZonaArticulo.toString(parte.getZona())
			+ "</small>");
			double puntaje = parte.calcularPuntosFrecuencia(parte.calcularPorcentajeFrecuenciaRegular());
			htmlBuilder
			.append("<div class=\"progress\" style =\"border-style: solid; border-color:#dddddd\"> "
					+ "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\""
					+ puntaje
					+ "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"background-color: #5cb85c; width: "
					+ (int) puntaje + "%;\">"
					+ DECIMAL_FORMART.format(puntaje) + "% </div> </div>");
			htmlBuilder.append("</div>");
		}

		htmlBuilder.append("<p><small>Esta es la puntuación recibida en cada una de las partes(teniendo en cuenta "
				+ "terminos regulares, lemas, sinonimos e indices), recuerde que este valor se pondera con el valor "
				+ "asignado a la parte del articulo para dar el puntaje total. </small></p>");
		for (ParteArticulo parte : verificador.getLista()) {
			htmlBuilder.append("<div class=\"panel panel-default\">  <small>"
					+ ZonaArticulo.toString(parte.getZona())
					+ " - Ponderado: " + DECIMAL_FORMART.format(ZonaArticulo.valueOf(parte.getZona())) + "</small>");
			double puntaje = parte.generarPuntaje();
			htmlBuilder
			.append("<div class=\"progress\" style =\" border-style: solid; border-color:#dddddd\"> "
					+ "<div class=\"progress-bar\" role=\"progressbar\" aria-valuenow=\"" + puntaje
					+ "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: " + (int) puntaje
					+ "%;\"> " + DECIMAL_FORMART.format(puntaje) + "% </div> </div>");
			htmlBuilder.append("</div>");
		}

		htmlBuilder.append("<h6>Recomendaciones: </h6>");

		htmlBuilder.append("<ul class=\"list-group\">");

		if (puntajeFinal > 0 && puntajeFinal <= 20) {
			htmlBuilder
			.append("<li class=\"list-group-item\" style=\"color:#A94442; background-color: #F2DEDE;\">Se suguire cambiar el termino"
					+ " clave :( ,  para ello se puede revisar los terminos más usados en el articulo :).</li>");
		} else if (puntajeFinal > 20 && puntajeFinal <= 40) {
			htmlBuilder
			.append("<li class=\"list-group-item\" style=\"color:#8A6D3B; background-color: #FCF8E3;\">Se suguire mejorar el termino clave :/, "
					+ "para ello se pueden revisar las listas de palabras clave del area, tal vez sobre o falte alguna palabra.</li>");
		} else if (puntajeFinal > 40 && puntajeFinal <= 70) {
			htmlBuilder
			.append("<li class=\"list-group-item\" style=\"color:31708F; background-color: #D9EDF7;\">Se suguire mejorar un poco el "
					+ "termino clave :|, para ello se pueden revisar los tesauros del area, tal vez sea mejor usar un sinonimo más popular.</li>");
		} else if (puntajeFinal > 70 && puntajeFinal <= 100) {
			htmlBuilder
			.append("<li class=\"list-group-item\" style=\"color:3C763D; background-color: #DFF0D8;\">Siempre es posible mejorar, "
					+ "intete repasar el uso del temrino en determinadas partes del articulo :).</li>");
		}
		htmlBuilder.append("</ul>");


		// for (ParteArticulo parte : verificador.getLista()) {
		// double puntosParte = parte.calcularPorcentajeFrecuencia();
		// if (puntosParte > 0 && puntosParte <= 20) {
		// if (parte.get) {
		//
		// }
		// } else if (puntosParte > 21 && puntosParte <= 40) {
		//
		// } else if (puntosParte > 40 && puntosParte <= 60) {
		//
		// } else if (puntosParte > 60 && puntosParte <= 80) {
		//
		// } else if (puntosParte > 80 && puntosParte <= 100) {
		//
		// }
		// }

		htmlBuilder.append("</body></html>");
		return htmlBuilder.toString();
	}
}