package modelo;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.lucene.analysis.shingle.ShingleAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class VerificadorPalabrasClave {

	private static final int NUMERO_PARTES = 7;
	private ParteArticulo[] lista;
	private Directory directory;
	private ArticuloCientifico articulo;
	private ShingleAnalyzerWrapper analyzer;
	private Document documento;
	private static final String ERROR_CREAR_INDEX_WRITER = "Error la configuració para el indexado del articulo.";
	private static final Object ERROR_AGREGAR_DOCUMENTO_INDICE = "Error al agregar al articulo al indice.";
	private static final Object ERROR_CERRAR_INDICE = "Error al cerrar la configuración de indexado del articulo.";
	private static final Object ERROR_ABRIR_CONFIGURACION_LECTURA = "Error en la configuración de lectura del articulo.";
	private static final Object ERROR_CONTEO = "Error al contar las palabras clave en el articulo.";

	/**
	 * Se carga el articulo y luego sus partes son pasadas a texto plano, se
	 * crea un documento que permita su indexado.
	 *
	 * @param ruta
	 */
	public VerificadorPalabrasClave(String ruta) {
		ConversorTextoArticulo conversorTextoArticulo = new ConversorTextoArticulo(ruta);
		lista = new ParteArticulo[NUMERO_PARTES];
		directory = new RAMDirectory();
		articulo = conversorTextoArticulo.getArticulo();
		cargarArticulo(articulo);
		crearDocumento();
	}

	public VerificadorPalabrasClave(ArticuloCientifico articulo) {
		lista = new ParteArticulo[NUMERO_PARTES];
		directory = new RAMDirectory();
		this.articulo = articulo;
		cargarArticulo(articulo);
		crearDocumento();
	}

	/**
	 * El articulo es convertido a una lista de elementos que contiene un
	 * indentificador y el texto de esa parte del articulo para su posterior
	 * analisis.
	 *
	 * @param articulo
	 */
	public void cargarArticulo(ArticuloCientifico articulo) {
		lista[0] = new ParteArticulo(ZonaArticulo.TITULO, articulo.getTitulo());
		lista[1] = new ParteArticulo(ZonaArticulo.RESUMEN, articulo.getResumen());
		lista[2] = new ParteArticulo(ZonaArticulo.INTRODUCCION, articulo.getListaContenidoCapitulos().get(0));
		lista[3] = new ParteArticulo(ZonaArticulo.TITULOS_CAPITULOS,
				Util.pasarListaAString(articulo.getListaTitulosCapitulos(), 1, articulo.getNumeroCapitulos() - 1));
		lista[4] = new ParteArticulo(ZonaArticulo.CONTENIDOS,
				Util.pasarListaAString(articulo.getListaContenidoCapitulos(), 1, articulo.getNumeroCapitulos() - 1));
		lista[5] = new ParteArticulo(ZonaArticulo.CONCLUSIONES,
				articulo.getListaContenidoCapitulos().get(articulo.getNumeroCapitulos() - 1));
		lista[6] = new ParteArticulo(ZonaArticulo.REFERENCIAS, articulo.getListaReferencias());
		Util.pasarListaAMinusculas(lista);
	}
	
	/**
	 * Se crea un tipo de campo especial que permita el conteo de palabras.
	 *
	 * @return
	 */
	private FieldType crearTipoCampo() {
		FieldType tipo = new FieldType();
		tipo.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
		return tipo;
	}

	/**
	 * Se crea un documento y se indexa el articulo a este, para ello se
	 * contsruye un indice con hasta 5 anagramas del contenido del articulo.
	 */
	public void crearDocumento() {
		analyzer = new ShingleAnalyzerWrapper(2, 5);
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = null;
		try {
			iwriter = new IndexWriter(directory, config);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CREAR_INDEX_WRITER, ConstantesTexto.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		documento = new Document();
		FieldType tipo = crearTipoCampo();
		for (ParteArticulo parte : lista) {
			documento.add(new Field(parte.getZonaArticulo().name(), parte.getTexto(), tipo));
		}
		try {
			iwriter.addDocument(documento);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_AGREGAR_DOCUMENTO_INDICE, ConstantesTexto.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		try {
			iwriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR_INDICE, ConstantesTexto.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Cuenta la palabra clave en las partes del articulo
	 *
	 * @param palabra
	 */
	public void contarPalabras(String palabra) {
		DirectoryReader ireader = null;
		try {
			ireader = DirectoryReader.open(directory);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_ABRIR_CONFIGURACION_LECTURA, ConstantesTexto.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		long numeroPalabras = 0;
		for (ParteArticulo parteArticulo : lista) {
			Term term = new Term(parteArticulo.getZonaArticulo().name(), palabra.toLowerCase());
			try {
				numeroPalabras = ireader.totalTermFreq(term);
				parteArticulo.setValorElemento(numeroPalabras);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, ERROR_CONTEO, ConstantesTexto.TITULO_ERROR,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		try {
			ireader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR_INDICE, ConstantesTexto.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public ParteArticulo[] getLista() {
		return lista;
	}

	public ArticuloCientifico getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloCientifico articulo) {
		this.articulo = articulo;
	}
}