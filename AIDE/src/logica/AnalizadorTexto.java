package logica;

public class AnalizadorTexto {
	
	private ArticuloCientifico articulo;
	private String texto;
	
	public AnalizadorTexto(String archivo) {
		articulo = new ArticuloCientifico();
		texto = LectorWebArticulo.leerArticulo(archivo);
		System.out.println(texto);
	}
	
	public void extraerTextoArticulo(){
		int indexInicio =  texto.indexOf("Fac. Ing.");
		int indexFin =  texto.indexOf("<div class=\"footer\">");
		String nuevoTexto = texto.substring(indexInicio, indexFin);
		System.out.println(nuevoTexto);
	}
	
	public static void main(String[] args) {
		AnalizadorTexto aide = new AnalizadorTexto("http://ref.scielo.org/j39xws");
		aide.extraerTextoArticulo();
	}
}