package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LectorWebArticulo {
	
	public static final String ERROR_URL = "Error al leer la URL del articulo.";
	public static final String ERROR_CARGAR = "Error al cargar el articulo.";
	public static final String ERROR_LECTURA = "Error al leer el articulo.";
	public static final String ERROR_CERRAR = "Error al cerrar el canal de datos.";
	
	public static String leerArticulo(String urlArticulo){
		URL url;
		try {
			url = new URL(urlArticulo);
		} catch (MalformedURLException e) {
			return ERROR_URL;
		}
		BufferedReader entrada;
		try {
			entrada = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			return ERROR_CARGAR;
		}
		String linea = "";
		String texto = "";
		try {
			while ((linea = entrada.readLine()) != null)
				texto += linea + "\n";
		} catch (IOException e) {
			return ERROR_LECTURA;
		}
		try {
			entrada.close();
		} catch (IOException e) {
			return ERROR_CERRAR;
		}
		return texto;
	}
}
