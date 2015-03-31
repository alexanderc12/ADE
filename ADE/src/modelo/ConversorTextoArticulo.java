package modelo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import persistencia.LectorWeb;

/**
 * Usando la liberia jsoup y algunos metodos de la clase String, analiza el
 * texto para extraer los metadatos y la informacion de un articulo cientifico
 * indexado en Scielo, aplica para paginas en español y el algortimo esta
 * diseñado para la pagina de la revista de Ingenieria de la UPTC pero puede ser
 * adaptado e incluso funcionar con otras revistas.
 *
 * @author Alexander Castro
 *
 */
public class ConversorTextoArticulo {
	
	private ArticuloCientifico articulo;
	private String texto;
	private Document documento;
	private static final String ATRIBUTO_NOMBRE = "name";
	private static final String ATRIBUTO_CONTENIDO = "content";
	private static final String VALOR_TITULO = "citation_title";
	private static final String VALOR_VOLUMEN = "citation_volume";
	private static final String VALOR_NUMERO = "citation_issue";
	private static final String VALOR_AUTOR = "citation_author";
	private static final String VALOR_REVISTA = "citation_journal_title";
	private static final String INICIO_CABECERA = "<meta xmlns=\"\" name=\"citation_journal_title\"";
	private static final String FIN_CABECERA = "<meta xmlns=\"\" name=\"citation_firstpage\"";
	private static final String INICIO_CONTENIDO = "id=\"doi\"";
	private static final String FIN_CONTENIDO = "<div class=\"footer\">";
	private static final String INDICE_FECHAS = "Fecha de ";
	private static final String INDICE_RESUMEN = "Resumen";
	private static final String ATRIBUTO_TITULO_CAPITULO = "size";
	private static final String VALOR_TITULO_CAPITULO = "3";
	private static final String INDICE_FIN_CAPITULOS = "<b>Referencias</b>";
	private static final String PARRAFO = "p";
	private static final String INICIO_REFERENCIA = "[";
	private static final String FIN_REFERENCIA = " [ Links ]";
	private static final String EXP_REG_NO_HTML = "\\<[^>]*>";
	private static final String EXP_REG_NO_ESPACIOS = "\\s+";
	
	
	public ConversorTextoArticulo(String archivo) {
		articulo = new ArticuloCientifico(archivo);
		texto = LectorWeb.leerArticulo(archivo);
		extraerMetadatosCabacera();
		extraerMetadatosTexto();
		extraerListaContenidos();
		extraerContenidoCapitulos();
		extraerReferencias();
	}
	
	private void extraerTextoArticulo(String textoInicio, String textoFin) {
		int indexInicioMetadatos = texto.indexOf(textoInicio);
		int indexFinMetadatos = texto.indexOf(textoFin);
		documento = Jsoup.parse(texto.substring(indexInicioMetadatos, indexFinMetadatos));
	}
	
	/**
	 * Extrae los metadatos de la cabecera de la magina Web teniendo en cuenta
	 * que estan guardados en una etiqueta meta, asi que se buscan por su valor
	 * y se extrae su contenido, los datos recuperados son el titulo, volumen,
	 * numero y los nombres de los autores
	 */
	private void extraerMetadatosCabacera() {
		extraerTextoArticulo(INICIO_CABECERA, FIN_CABECERA);
		articulo.setRevista(
				documento.getElementsByAttributeValue(ATRIBUTO_NOMBRE, VALOR_REVISTA).attr(ATRIBUTO_CONTENIDO));
		articulo.setTitulo(
				documento.getElementsByAttributeValue(ATRIBUTO_NOMBRE, VALOR_TITULO).attr(ATRIBUTO_CONTENIDO));
		articulo.setVolumen(Integer.parseInt(
				documento.getElementsByAttributeValue(ATRIBUTO_NOMBRE, VALOR_VOLUMEN).attr(ATRIBUTO_CONTENIDO)));
		articulo.setNumero(Integer.parseInt(
				documento.getElementsByAttributeValue(ATRIBUTO_NOMBRE, VALOR_NUMERO).attr(ATRIBUTO_CONTENIDO)));
		Elements listaAutores = documento.getElementsByAttributeValue(ATRIBUTO_NOMBRE, VALOR_AUTOR);
		for (Element autor : listaAutores) {
			articulo.agregarAutor(autor.attr(ATRIBUTO_CONTENIDO));
		}
	}
	
	/**
	 * Extrae los metadatos del contenido del articulo, las fechas,el resumen y
	 * las palabras clave(a estas se les da un formato quitando el espacio al
	 * incio de cada expresión y el punto al final)
	 */
	private void extraerMetadatosTexto() {
		extraerTextoArticulo(INICIO_CONTENIDO, FIN_CONTENIDO);
		String textofechas = documento.getElementsContainingOwnText(INDICE_FECHAS).first().text();
		articulo.setFechaRecepcion(
				textofechas.substring(20, textofechas.indexOf("Fecha de Aprobación: ") - 1).replace(".", ""));
		articulo.setFechaAprobacion(textofechas.substring(textofechas.indexOf("Fecha de Aprobación: ") + 21));
		Element resumen = articulo.getNumero() == 36
				? documento.getElementsMatchingOwnText(INDICE_RESUMEN).first().parent().parent().nextElementSibling()
						: documento.getElementsMatchingOwnText(INDICE_RESUMEN).first().parent().nextElementSibling();
				articulo.setResumen(resumen.ownText());
				Element palabrasClaves = resumen.nextElementSibling();
				String[] listaPalabras = palabrasClaves.ownText().split(",");
				articulo.agregarPalabraClave(listaPalabras[0]);
				for (int i = 1; i < listaPalabras.length - 1; i++) {
					articulo.agregarPalabraClave(listaPalabras[i].substring(1));
				}
				articulo.agregarPalabraClave(listaPalabras[listaPalabras.length - 1].replace(".", "").substring(1));
	}
	
	/**
	 * Extrae los titulos de los capitulos
	 */
	private void extraerListaContenidos() {
		int inicio = articulo.getNumero() > 35 ? 2 : 1;
		Elements contenidos = documento.getElementsByAttributeValue(ATRIBUTO_TITULO_CAPITULO, VALOR_TITULO_CAPITULO);
		for (int i = inicio; i < contenidos.size() - 1; i++) {
			articulo.agregarTituloCapitulo(contenidos.get(i).text());
		}
	}
	
	/**
	 * Extrae el texto de los capitulos
	 */
	private void extraerContenidoCapitulos() {
		int inicioCapitulo = 0;
		int finCapitulo = 0;
		for (int i = 0; i < articulo.getNumeroCapitulos(); i++) {
			inicioCapitulo = texto.indexOf(articulo.getListaTitulosCapitulos().get(i))
					+ articulo.getListaTitulosCapitulos().get(i).length();
			if (i == articulo.getNumeroCapitulos() - 1) {
				finCapitulo = texto.indexOf(INDICE_FIN_CAPITULOS);
			} else {
				finCapitulo = texto.indexOf(articulo.getListaTitulosCapitulos().get(i + 1));
			}
			articulo.agregarContenidoCapitulo(texto.substring(inicioCapitulo, finCapitulo)
					.replaceAll(EXP_REG_NO_HTML, "")
					.replaceAll(EXP_REG_NO_ESPACIOS, " ")
					.substring(1));
		}
	}
	
	/**
	 * Extrae las referencias del articulo
	 */
	private void extraerReferencias() {
		Elements referencias = documento.select(PARRAFO);
		for (Element referencia : referencias) {
			String texto = referencia.text();
			if (texto.startsWith(INICIO_REFERENCIA)
					&& (texto.endsWith(FIN_REFERENCIA) || texto.endsWith(FIN_REFERENCIA + "."))) {
				articulo.agregarReferencia(texto.replace(FIN_REFERENCIA, ""));
			}
		}
	}
	
	public ArticuloCientifico getArticulo() {
		return articulo;
	}
}