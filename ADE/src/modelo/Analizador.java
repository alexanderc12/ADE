package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.tartarus.snowball.ext.SpanishStemmer;

import vistas.ConstantesGUI;

public class Analizador extends Analyzer {

	private CharArraySet listaPalabrasVacias = new CharArraySet(0, true);

	public Analizador() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(ConstantesGUI.RUTA_PALABRAS_VACIAS)));
		String texto = null;
		try {
			while ((texto = reader.readLine()) != null) {
				listaPalabrasVacias.add(texto);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_PALABRAS_VACIAS, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer source = new StandardTokenizer();
		TokenStream filter = new StandardFilter(source);
		filter = new LowerCaseFilter(filter);
		filter = new StopFilter(filter, listaPalabrasVacias);
		filter = new SnowballFilter(filter, new SpanishStemmer());
		return new TokenStreamComponents(source, filter);
	}

}
