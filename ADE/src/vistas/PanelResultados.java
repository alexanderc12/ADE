package vistas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class PanelResultados extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final String T_PANEL_RESULTADOS = "Resultados";
	private static final String T_LISTA_PALABRAS = "Lista palabras clave:";
	private JList<String> listaPalabrasClave;
	private DefaultListModel<String> modeloPalabrasClave;
	
	public PanelResultados() {
		setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(T_PANEL_RESULTADOS));
		modeloPalabrasClave = new DefaultListModel<String>();
		listaPalabrasClave = new JList<String>(modeloPalabrasClave);
		listaPalabrasClave.setBorder(BorderFactory
				.createTitledBorder(T_LISTA_PALABRAS));
		add(listaPalabrasClave);
	}
	
	public void agregarPalabraClave(String palabra) {
		modeloPalabrasClave.addElement(palabra);
	}

}
