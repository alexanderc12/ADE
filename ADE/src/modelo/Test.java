package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class Test {
	
	public static void main(String[] args) throws IOException {
		// Open the bloggers RDF graph from the filesystem
		InputStream in = new FileInputStream(new File("src/data/tesauro.rdf"));

		// Create an empty in-memory model and populate it from the graph
		Model model = ModelFactory.createMemModelMaker().createDefaultModel();
		model.read(in, null); // null base URI, since model URIs are absolute
		in.close();

		// Create a new query
		String queryString = "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
				+ " SELECT ?altLabel"
				+ " WHERE { ?concept skos:prefLabel \"Método de evaluación\".} ";

		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();

		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		// Important - free up resources used running the query
		qe.close();
		
	}
	
}
