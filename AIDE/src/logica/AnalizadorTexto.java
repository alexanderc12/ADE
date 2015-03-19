package logica;

import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AnalizadorTexto {
	
	private ArticuloCientifico articulo;
	private String texto;
	private Document documento;
	
	public AnalizadorTexto(String archivo) {
		articulo = new ArticuloCientifico();
		texto = LectorWebArticulo.leerArticulo(archivo);
	}
	
	public void extraerTextoArticulo(String textoPorConvertir){
		documento = Jsoup.parse(texto);
	}
	
	public void extraerMetadatosCabacera(){
		
		int indexInicioMetadatos = texto.indexOf("<meta xmlns=\"\" name=\"citation_title\"");
		int indexFinMetadatos = texto.indexOf("<meta xmlns=\"\" name=\"citation_firstpage\"");
		String textoMetadatos = texto.substring(indexInicioMetadatos, indexFinMetadatos);
		extraerTextoArticulo(textoMetadatos);
		articulo.setTitulo(documento.getElementsByAttributeValue("name", "citation_title").attr("content"));
		articulo.setVolumen(Integer.parseInt(documento.getElementsByAttributeValue("name", "citation_volume").attr("content")));
		articulo.setNumero(Integer.parseInt(documento.getElementsByAttributeValue("name", "citation_issue").attr("content")));
		Elements listaAutores = documento.getElementsByAttributeValue("name", "citation_author");
		for (Element autor : listaAutores) {
			articulo.getListaAutores().add(autor.attr("content"));
		}
	}
	
	public void extraerMetadatosTexto(){
		int indexInicioMetadatos = texto.indexOf("id=\"doi\"");
		int indexFinMetadatos = texto.indexOf("<div class=\"footer\">");
		String textoMetadatos = texto.substring(indexInicioMetadatos, indexFinMetadatos);
		extraerTextoArticulo(textoMetadatos);
		String fechas = documento.getElementsContainingOwnText("Fecha de ").first().text();
		articulo.setFechaRecepcion(LocalDate.parse(fechas.substring(20, 30), DateTimeFormatter.ISO_DATE));
		articulo.setFechaAprobacion(LocalDate.parse(fechas.substring(52, 62), DateTimeFormatter.ISO_DATE));
		Element resumen = documento.getElementsMatchingOwnText("Resumen").first().parent().nextElementSibling();
		articulo.setResumen(resumen.ownText());
		Element palabrasClaves = resumen.nextElementSibling();
		StringTokenizer stPalabras = new StringTokenizer(palabrasClaves.ownText(), ",");
		while (stPalabras.hasMoreTokens()) {
			String palabra = stPalabras.nextToken();
			if (palabra.charAt(0) == ' ') {
				palabra = palabra.substring(1);
			}
			if (palabra.charAt(palabra.length() - 1)=='.') {
				palabra = palabra.substring(0, palabra.length() - 2);
			}
			articulo.getPalabrasClave().add(palabra);
		}
		Elements contenidos = documento.getElementsByAttributeValue("size", "3");
		Elements data = documento.siblingElements();
		System.out.println(data.indexOf(contenidos.get(1)));
		System.out.println(contenidos.get(2).hashCode());
	}
	
	public static void main(String[] args) {
		AnalizadorTexto aide = new AnalizadorTexto("http://ref.scielo.org/j39xws");
		aide.extraerMetadatosTexto();
	}
}