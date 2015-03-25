package modelo;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Terms;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class VerificadorPalabrasClave {
	
	public void contarPalabras() throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer();
		Directory directory = new RAMDirectory();

		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);

		Document doc = new Document();
		
		String titulo = "Bases de datos NoSQL para datos no estructurados en datos.";
		doc.add(new Field("titulo", titulo, TextField.TYPE_STORED));
		
		String texto = "Reflexiona sobre las bases de datos NoSQL, describiéndolas "
				+ "y analizando el porqué de su importancia y actualidad; además recopila "
				+ "y define algunas características de este tipo de base de datos, para revisar "
				+ "las taxonomías más importantes y analizar el uso conjunto de tecnologías NoSQL y "
				+ "relacionales, con el fin de proporcionar un punto de partida para los trabajos "
				+ "en esta área por parte de investigadores.";
		doc.add(new Field("texto", texto, TextField.TYPE_STORED));

		iwriter.addDocument(doc);
		iwriter.close();
		
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		
		Terms i = ireader.getTermVector(0, "titulo");
		System.out.println(i);

		ireader.close();
		directory.close();
	}
	
	public static void main(String[] args) {
		VerificadorPalabrasClave verificador = new VerificadorPalabrasClave();
		try {
			verificador.contarPalabras();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
