package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import vistas.ConstantesGUI;

public class GestorSemantico {

	public static final String CONSULTA_SINONIMOS_INICIO = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
			+ " SELECT DISTINCT  ?altLabel "
			+ " WHERE {?concept skos:altLabel ?altLabel . ?concept skos:prefLabel ?prefLabel . ?concept skos:altLabel ?x.  "
			+ " FILTER (regex(?prefLabel, \"";
	public static final String CONSULTA_SINONIMOS_MITAD = "\", \"i\") || regex(?x, \"";

	public static final String CONSULTA_SINONIMOS_FIN = "\", \"i\")) FILTER(lang(?altLabel) =\"es\")}";

	public ArrayList<String> buscarSinonimos(String termino) {
		ArrayList<String> listaSinonimos = new ArrayList<>();
		InputStream in = null;
		in = getClass().getResourceAsStream(ConstantesGUI.RUTA_TESAURO_UNESCO);
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null);
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Query query = QueryFactory.create(
				CONSULTA_SINONIMOS_INICIO + termino + CONSULTA_SINONIMOS_MITAD + termino + CONSULTA_SINONIMOS_FIN);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution row = results.nextSolution();
			if (!row.getLiteral("altLabel").getString().equalsIgnoreCase(termino)) {
				listaSinonimos.add(row.getLiteral("altLabel").getString());
			}
		}
		qe.close();
		return listaSinonimos;
	}
}