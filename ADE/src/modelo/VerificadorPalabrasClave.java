package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.misc.HighFreqTerms;
import org.apache.lucene.misc.TermStats;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.tartarus.snowball.ext.SpanishStemmer;

import vistas.ConstantesGUI;

public class VerificadorPalabrasClave {
	
	private static final int NUMERO_PARTES = 7;
	private ParteArticulo[] lista;
	private Directory directoryNormal;
	private Directory directoryLema;
	private ArticuloCientifico articulo;
	private Document documento;
	private CharArraySet listaPalabrasVacias = new CharArraySet(0, true);
	private SpanishStemmer stemmer = new SpanishStemmer();
	private static final String ERROR_CREAR_INDEX_WRITER = "Error la configuració para el indexado del articulo.";
	private static final String ERROR_AGREGAR_DOCUMENTO_INDICE = "Error al agregar al articulo al indice.";
	private static final String ERROR_CERRAR_INDICE = "Error al cerrar la configuración de indexado del articulo.";
	private static final String ERROR_ABRIR_CONFIGURACION_LECTURA = "Error en la configuración de lectura del articulo.";
	private static final String ERROR_CONTEO = "Error al contar las palabras clave en el articulo.";
	private static final int INDEX_NORMAL = 0;
	private static final int INDEX_LEMA = 1;
	
	/**
	 * Se carga el articulo y luego sus partes son pasadas a texto plano, se
	 * crea un documento que permita su indexado.
	 *
	 * @param ruta
	 */
	public VerificadorPalabrasClave(String ruta) {
		ConversorTextoArticulo conversorTextoArticulo = new ConversorTextoArticulo(ruta);
		articulo = conversorTextoArticulo.getArticulo();
		init();
	}
	
	public VerificadorPalabrasClave(ArticuloCientifico articulo) {
		this.articulo = articulo;
		init();
		
	}
	
	private void init() {
		lista = new ParteArticulo[NUMERO_PARTES];
		directoryNormal = new RAMDirectory();
		directoryLema = new RAMDirectory();
		cargarArticulo(articulo);
		try {
			for (String palabra : Files.readAllLines(Paths.get(ConstantesGUI.RUTA_PALABRAS_VACIAS))) {
				listaPalabrasVacias.add(palabra);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_LEER_PALABRAS_VACIAS, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		crearDocumento(INDEX_NORMAL);
		crearDocumento(INDEX_LEMA);
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
		tipo.setStoreTermVectors(true);
		return tipo;
	}
	
	/**
	 * Se crea un documento y se indexa el articulo a este, para ello se
	 * construye un indice, omitiendo las palabras vacias de la lista
	 */
	public void crearDocumento(int tipoIndexado) {
		IndexWriterConfig config = null;
		if (tipoIndexado == INDEX_NORMAL) {
			StandardAnalyzer analyzer = new StandardAnalyzer(listaPalabrasVacias);
			config = new IndexWriterConfig(analyzer);
		} else if (tipoIndexado == INDEX_LEMA) {
			Analizador analyzer = new Analizador();
			config = new IndexWriterConfig(analyzer);
		}
		
		IndexWriter iwriter = null;
		try {
			if (tipoIndexado == INDEX_NORMAL) {
				iwriter = new IndexWriter(directoryNormal, config);
			} else if (tipoIndexado == INDEX_LEMA) {
				iwriter = new IndexWriter(directoryLema, config);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CREAR_INDEX_WRITER, ConstantesGUI.TITULO_ERROR,
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
			JOptionPane.showMessageDialog(null, ERROR_AGREGAR_DOCUMENTO_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		try {
			iwriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public DirectoryReader abrirIndice(Directory directory) {
		try {
			return DirectoryReader.open(directory);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_ABRIR_CONFIGURACION_LECTURA, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	/**
	 * Cuenta la palabra clave en las partes del articulo
	 *
	 * @param palabra
	 */
	public void contarPalabras(String termino) {
		DirectoryReader ireader = abrirIndice(directoryNormal);
		String listaPalabras[] = termino.split(" ");
		for (ParteArticulo parteArticulo : lista) {
			long numeroPalabras = 0;
			for (String palabra : listaPalabras) {
				Term term = new Term(parteArticulo.getZonaArticulo().name(), palabra.toLowerCase());
				try {
					numeroPalabras += ireader.totalTermFreq(term);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, ERROR_CONTEO, ConstantesGUI.TITULO_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
			parteArticulo.setValorElemento((double) numeroPalabras / listaPalabras.length);
			try {
				Terms listaTerminos = ireader.getTermVector(0, parteArticulo.getZonaArticulo().name());
				parteArticulo.setNumeroElementosAnalizables(listaTerminos
						.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ireader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Cuenta la palabra clave en las partes del articulo
	 *
	 * @param palabra
	 */
	public void contarPalabrasLema(String termino) {
		DirectoryReader ireader = abrirIndice(directoryLema);
		String listaPalabras[] = termino.split(" ");
		for (ParteArticulo parteArticulo : lista) {
			long numeroPalabras = 0;
			for (String palabra : listaPalabras) {
				stemmer.setCurrent(palabra.toLowerCase());
				stemmer.stem();
				Term term = new Term(parteArticulo.getZonaArticulo().name(), stemmer.getCurrent());
				System.out.println("--->" + stemmer.getCurrent());
				try {
					numeroPalabras += ireader.totalTermFreq(term);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, ERROR_CONTEO, ConstantesGUI.TITULO_ERROR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
			parteArticulo.setValorElementoLema((double) numeroPalabras / listaPalabras.length);
			try {
				Terms listaTerminos = ireader.getTermVector(0, parteArticulo.getZonaArticulo().name());
				TermsEnum termsEnum = listaTerminos.iterator(null);
				BytesRef text;
				System.out.println(parteArticulo.getZonaArticulo().name());
				while ((text = termsEnum.next()) != null) {
					System.out.println(text.utf8ToString());
				}
				parteArticulo.setNumeroElementosLema(listaTerminos.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			ireader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ArrayList<String> obtenerTopPalabras(ZonaArticulo zonaArticulo, int n) {
		DirectoryReader ireader = null;
		ArrayList<String> listaTerminosTop = new ArrayList<>();
		try {
			ireader = DirectoryReader.open(directoryNormal);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_ABRIR_CONFIGURACION_LECTURA, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		try {
			TermStats[] stats = HighFreqTerms.getHighFreqTerms(ireader, n, zonaArticulo.name(),
					new HighFreqTerms.TotalTermFreqComparator());
			for (TermStats stat : stats) {
				listaTerminosTop.add((stat.termtext.utf8ToString() + " " + stat.totalTermFreq));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			ireader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ERROR_CERRAR_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		return listaTerminosTop;
	}
	
	public double calcularPuntajePalabra() {
		double totalPuntos = 0;
		for (ParteArticulo parteArticulo : lista) {
			totalPuntos += parteArticulo.calcularPuntos() * parteArticulo.getZonaArticulo().getPonderado();
		}
		return totalPuntos;
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