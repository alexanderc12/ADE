package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class GestorSemantico {

	public ArrayList<String> buscarSinonimos(String termino) {
		ArrayList<String> listaSinonimos = new ArrayList<>();
		InputStream in = null;
		try {
			in = new FileInputStream(new File("src/data/tesauro.rdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null);
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
				+ " SELECT ?altLabel "
				+ " WHERE {?concept skos:altLabel ?altLabel . ?concept skos:prefLabel ?prefLabel ."
				+ " FILTER regex(?prefLabel, \""
				+ termino + "\", \"i\") FILTER(lang(?altLabel) =\"es\")}";

		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		while (results.hasNext()) {
			QuerySolution row = results.nextSolution();
			listaSinonimos.add(row.getLiteral("altLabel").getString());
		}
		qe.close();
		return listaSinonimos;
	}

	/**
	 * narrower
	 */
	public void buscarAreasRelacionadas() {
		InputStream in = null;
		try {
			in = new FileInputStream(new File("src/data/tesauro.rdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null);
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> PREFIX text: <http://jena.apache.org/text#>" + " SELECT ?x "
				+ " WHERE {?concept skos:altLabel ?altLabel . ?concept skos:prefLabel ?a .  ?concept skos:narrower ?narrower . ?narrower skos:prefLabel ?x ."
				+ " FILTER CONTAINS(str(?a), \"Industria de edi\") FILTER(lang(?x) =\"es\")}";

		Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		ResultSetFormatter.out(System.out, results, query);

		qe.close();
	}

	/**
	 * broader
	 */
	public void buscarSubAreasRelacionadas() {
		InputStream in = null;
		try {
			in = new FileInputStream(new File("src/data/tesauro.rdf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null);
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" + " SELECT ?altLabel "
				+ " WHERE {?concept skos:altLabel ?altLabel . ?concept skos:prefLabel ?a ."
				+ " FILTER regex(?a, \"seguridad\", \"i\") FILTER(lang(?altLabel) =\"es\")}";

		Query query = QueryFactory.create(queryString);

		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		ResultSetFormatter.out(System.out, results, query);

		qe.close();
	}


	public static void main(String[] args) throws IOException {
		GestorSemantico t = new GestorSemantico();
		t.buscarAreasRelacionadas();
	}

}
