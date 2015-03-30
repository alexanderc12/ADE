package vistas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelResultados extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String T_PANEL_RESULTADOS = "Resultados";
	private static final String T_LISTA_PALABRAS = "Lista palabras clave:";
	private JList<String> listaPalabrasClave;
	private DefaultListModel<String> modeloPalabrasClave;
	private JTable tablaResultados;
	private DefaultTableModel modeloTablaResultados;

	public PanelResultados() {
		setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(T_PANEL_RESULTADOS));
		modeloPalabrasClave = new DefaultListModel<String>();
		listaPalabrasClave = new JList<String>(modeloPalabrasClave);
		listaPalabrasClave.setBorder(BorderFactory
				.createTitledBorder(T_LISTA_PALABRAS));
		add(listaPalabrasClave);

		String[] columnNames = { "Parte del articulo", "Numero de incidencias",
				"Numero elementos", "% Incidencias" };
		modeloTablaResultados = new DefaultTableModel(columnNames, 0);
		tablaResultados = new JTable(modeloTablaResultados);
		add(new JScrollPane(tablaResultados));
	}

	public void agregarPalabraClave(String palabra) {
		modeloPalabrasClave.addElement(palabra);
	}

	public String obtenerPalabraSelecionada() {
		return listaPalabrasClave.getSelectedValue();
	}

	public void agregarResultado(String parteArticulo,
			String numeroIncidencias, String numeroElementos,
			String porcentajeIncidencias) {
		modeloTablaResultados.addRow(new String[] { parteArticulo,
				numeroIncidencias, numeroElementos, porcentajeIncidencias });
	}

	public void limiparTabla() {
		modeloTablaResultados.setRowCount(0);
	}
}