package modelo;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
	private ConversorTextoArticulo conversorTextoArticulo;

	public VerificadorPalabrasClave() {
		conversorTextoArticulo = new ConversorTextoArticulo(
				"http://ref.scielo.org/rnxd37");
		lista = new ParteArticulo[NUMERO_PARTES];
		directory = new RAMDirectory();
		cargarArticulo(conversorTextoArticulo.getArticulo());
		crearDocumento();
	}
	
	public void cargarArticulo(ArticuloCientifico articulo) {
		lista[0] = new ParteArticulo(ZonaArticulo.TITULO, articulo.getTitulo());
		lista[1] = new ParteArticulo(ZonaArticulo.RESUMEN,
				articulo.getResumen());
		lista[2] = new ParteArticulo(ZonaArticulo.INTRODUCCION, articulo
				.getContenidoCapitulos().get(0));
		lista[3] = new ParteArticulo(ZonaArticulo.TITULOS_CAPITULOS,
				Util.pasarListaToString(articulo.getListaCapitulos(), 1,
						articulo
						.getContenidoCapitulos().size() - 2));
		lista[4] = new ParteArticulo(ZonaArticulo.CONTENIDOS,
				Util.pasarListaToString(articulo.getContenidoCapitulos(), 1,
						articulo.getContenidoCapitulos().size() - 2));
		lista[5] = new ParteArticulo(ZonaArticulo.CONCLUSIONES, articulo
				.getContenidoCapitulos().get(
						articulo.getContenidoCapitulos().size() - 1));
		lista[6] = new ParteArticulo(ZonaArticulo.REFERENCIAS,
				Util.pasarListaToString(articulo.getListaReferencias()));
		Util.pasarListaAMinusculas(lista);
	}
	

	private FieldType crearTipoCampo() {
		FieldType tipo = new FieldType();
		tipo.setStoreTermVectors(true);
		tipo.setTokenized(true);
		tipo.setStoreTermVectorPositions(true);
		tipo.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		return tipo;
	}

	public void crearDocumento() {
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = null;
		try {
			iwriter = new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Document doc = new Document();
		FieldType tipo = crearTipoCampo();
		for (ParteArticulo parte : lista) {
			doc.add(new Field(parte.getZonaArticulo().name(), parte.getTexto(),
					tipo));
		}
		try {
			iwriter.addDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			iwriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void contarPalabras(String palabra) {
		
		DirectoryReader ireader = null;
		try {
			ireader = DirectoryReader.open(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long numeroPalabras = 0;
		for (ParteArticulo parteArticulo : lista) {
			Term term = new Term(parteArticulo.getZonaArticulo().name(),
					palabra.toLowerCase());
			try {
				numeroPalabras = ireader.totalTermFreq(term);
				parteArticulo.setValorElemento(numeroPalabras);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(numeroPalabras);
		}

		try {
			ireader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ConversorTextoArticulo getConversorTextoArticulo() {
		return conversorTextoArticulo;
	}
	
	public ParteArticulo[] getLista() {
		return lista;
	}
	
	public static void main(String[] args) {
		VerificadorPalabrasClave v = new VerificadorPalabrasClave();
		v.contarPalabras("sólidos");
	}
}