package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.apache.lucene.misc.HighFreqTerms;
import org.apache.lucene.misc.TermStats;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.tartarus.snowball.ext.SpanishStemmer;

import vistas.ConstantesGUI;

public class VerificadorPalabrasClave {

	private static final int NUMERO_PARTES = 7;
	private ParteArticulo[] lista;
	private boolean aparaceIEEE;
	private boolean apareceIFAC;
	private Directory directoryNormal;
	private Directory directoryLema;
	private ArticuloCientifico articulo;
	private Document documento;
	private CharArraySet listaPalabrasVacias = new CharArraySet(0, true);
	private SpanishStemmer stemmer = new SpanishStemmer();
	private static final int INDEX_NORMAL = 0;
	private static final int INDEX_LEMA = 1;
	private String palabra;
	private String palabraEnIngles;
	private VerificadorTerminos verificadorTerminos;
	private GestorSemantico gestorSemantico;

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
		if (articulo != null) {
			lista = new ParteArticulo[NUMERO_PARTES];
			directoryNormal = new RAMDirectory();
			directoryLema = new RAMDirectory();
			cargarArticulo(articulo);
			cargarPalabrasVacias();
			crearDocumento(INDEX_NORMAL);
			crearDocumento(INDEX_LEMA);
			verificadorTerminos = new VerificadorTerminos();
			gestorSemantico = new GestorSemantico();
		}
	}

	public void cargarPalabrasVacias() {
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
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CREAR_INDEX_WRITER, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
		documento = new Document();
		FieldType tipo = crearTipoCampo();
		for (ParteArticulo parte : lista) {
			documento.add(new Field(parte.getZona(), parte.getTexto(), tipo));
		}
		try {
			iwriter.addDocument(documento);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_AGREGAR_DOCUMENTO_INDICE,
					ConstantesGUI.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
		}
		try {
			iwriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CERRAR_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private DirectoryReader abrirIndice(Directory directory) {
		try {
			return DirectoryReader.open(directory);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_ABRIR_CONFIGURACION_LECTURA,
					ConstantesGUI.TITULO_ERROR, JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	private void cerrarIndice(DirectoryReader ireader) {
		try {
			ireader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CERRAR_INDICE, ConstantesGUI.TITULO_ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 * Cuenta la palabra clave en las partes del articulo
	 *
	 * @param palabra
	 */
	public void contarFrecuenciaPalabra(String termino) {
		palabra = termino;
		DirectoryReader ireader = abrirIndice(directoryNormal);
		String listaPalabras[] = termino.split(" ");
		for (ParteArticulo parteArticulo : lista) {
			long numeroPalabras = 0;
			int numeroPalabrasAnalizadas = 0;
			for (String palabra : listaPalabras) {
				if (!listaPalabrasVacias.contains(palabra)) {
					Term term = new Term(parteArticulo.getZona(), palabra.toLowerCase());
					numeroPalabrasAnalizadas++;
					try {
						numeroPalabras += ireader.totalTermFreq(term);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CONTEO, ConstantesGUI.TITULO_ERROR,
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			parteArticulo.setValorElemento((double) numeroPalabras / numeroPalabrasAnalizadas);
			try {
				Terms listaTerminos = ireader.getTermVector(0, parteArticulo.getZona());
				parteArticulo.setNumeroElementosAnalizables(listaTerminos.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cerrarIndice(ireader);
	}

	public void verificarIndices(String palabraEnIngles){
		this.palabraEnIngles = palabraEnIngles;
		aparaceIEEE = verificadorTerminos.verificarTermino(palabraEnIngles, ConstantesGUI.RUTA_LISTA_TERMINOS_IEEE);
		apareceIFAC = verificadorTerminos.verificarTermino(palabraEnIngles, ConstantesGUI.RUTA_LISTA_TERMINOS_IFAC);
	}

	/**
	 * Cuenta la palabra clave en las partes del articulo
	 * @param palabra
	 */
	public void contarFrecuenciaPalabrasLema(String termino) {
		DirectoryReader ireader = abrirIndice(directoryLema);
		String listaPalabras[] = termino.split(" ");
		for (ParteArticulo parteArticulo : lista) {
			long numeroPalabras = 0;
			int numeroPalabrasAnalizadas = 0;
			for (String palabra : listaPalabras) {
				if (!listaPalabrasVacias.contains(palabra)) {
					stemmer.setCurrent(palabra.toLowerCase());
					stemmer.stem();
					Term term = new Term(parteArticulo.getZona(), stemmer.getCurrent());
					numeroPalabrasAnalizadas++;
					try {
						numeroPalabras += ireader.totalTermFreq(term);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CONTEO, ConstantesGUI.TITULO_ERROR,
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			parteArticulo.setValorElementoLema((double) numeroPalabras / numeroPalabrasAnalizadas);
			try {
				Terms listaTerminos = ireader.getTermVector(0, parteArticulo.getZona());
				parteArticulo.setNumeroElementosLema(listaTerminos.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		cerrarIndice(ireader);
	}

	public void contarFrecuenciaMejorSinonimo(String termino) {
		DirectoryReader ireader = abrirIndice(directoryNormal);
		ArrayList<String> listaSinonimos = gestorSemantico.buscarSinonimos(termino);
		long numeroMayorIncidencias = 0;
		for (String sinonimo : listaSinonimos) {
			String listaPalabras[] = sinonimo.split(" ");
			for (ParteArticulo parteArticulo : lista) {
				long numeroPalabras = 0;
				int numeroPalabrasAnalizadas = 0;
				for (String palabra : listaPalabras) {
					if (!listaPalabrasVacias.contains(palabra)) {
						Term term = new Term(parteArticulo.getZona(), palabra.toLowerCase());
						numeroPalabrasAnalizadas++;
						try {
							numeroPalabras += ireader.totalTermFreq(term);
						} catch (IOException e) {
							JOptionPane.showMessageDialog(null, ConstantesGUI.ERROR_CONTEO, ConstantesGUI.TITULO_ERROR,
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				if (numeroPalabras > numeroMayorIncidencias) {
					numeroMayorIncidencias = numeroPalabras;
					parteArticulo.setValorSinonimos(((double) numeroPalabras / numeroPalabrasAnalizadas));
				}
			}
		}
		cerrarIndice(ireader);
	}

	public ArrayList<String> obtenerTopPalabras(String zonaArticulo, int n) {
		ArrayList<String> listaTerminosTop = new ArrayList<>();
		DirectoryReader ireader = abrirIndice(directoryNormal);
		try {
			TermStats[] stats = HighFreqTerms.getHighFreqTerms(ireader, n, zonaArticulo,
					new HighFreqTerms.TotalTermFreqComparator());
			for (TermStats stat : stats) {
				listaTerminosTop.add((stat.termtext.utf8ToString() + "," + stat.totalTermFreq));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		cerrarIndice(ireader);
		return listaTerminosTop;
	}

	public double calcularPuntajePalabra() {
		double totalPuntos = 0;
		for (ParteArticulo parteArticulo : lista) {
			totalPuntos += parteArticulo.generarPuntaje()
					* ((double) ZonaArticulo.valueOf(parteArticulo.getZona()) / 100);
		}
		if (aparaceIEEE) {
			totalPuntos += 2;
		}
		if (apareceIFAC) {
			totalPuntos += 2;
		}
		return totalPuntos;
	}

	public ParteArticulo[] getLista() {
		return lista;
	}

	public ArticuloCientifico getArticulo() {
		return articulo;
	}

	public boolean isAparaceIEEE() {
		return aparaceIEEE;
	}

	public boolean isApareceIFAC() {
		return apareceIFAC;
	}

	public String getPalabra() {
		return palabra;
	}

	public String getPalabraEnIngles() {
		return palabraEnIngles;
	}

	public long calcularTotalElementos() {
		long totalelementos = 0;
		for (ParteArticulo parteArticulo : lista) {
			totalelementos += parteArticulo.getTotalElementos();
		}
		return totalelementos;
	}

	public long calcularTotalElementosAnalizables() {
		long totalelementos = 0;
		for (ParteArticulo parteArticulo : lista) {
			totalelementos += parteArticulo.getNumeroElementosAnalizables();
		}
		return totalelementos;
	}

	public GestorSemantico getGestorSemantico() {
		return gestorSemantico;
	}
}