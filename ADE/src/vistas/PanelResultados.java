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
	private JList<String> listaPalabrasClave;
	private DefaultListModel<String> modeloPalabrasClave;
	private JTable tablaResultados;
	private DefaultTableModel modeloTablaResultados;

	public PanelResultados() {
		setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_RESULTADOS));
		modeloPalabrasClave = new DefaultListModel<String>();
		listaPalabrasClave = new JList<String>(modeloPalabrasClave);
		listaPalabrasClave.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_LISTA_PALABRAS));
		add(listaPalabrasClave);
		
		modeloTablaResultados = new DefaultTableModel(ConstantesGUI.T_COLUMNAS_TABLA_RESULTADOS, 0);
		tablaResultados = new JTable(modeloTablaResultados);
		tablaResultados.setBackground(Color.WHITE);
		JScrollPane panelTabla = new JScrollPane(tablaResultados);
		panelTabla.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_TABLA_RESULTADOS));
		add(panelTabla);
	}

	public void agregarPalabraClave(String palabra) {
		modeloPalabrasClave.addElement(palabra);
	}

	public String obtenerPalabraSelecionada() {
		return listaPalabrasClave.getSelectedValue();
	}

	public void agregarResultado(String parteArticulo,
			String numeroIncidencias, String numeroElementos,
			String numeroElementosAnalizables,
			String porcentajeIncidencias) {
		modeloTablaResultados.addRow(new String[] { parteArticulo,
				numeroIncidencias, numeroElementos, numeroElementosAnalizables,
				porcentajeIncidencias });
	}

	public void limiparTabla() {
		modeloTablaResultados.setRowCount(0);
	}
	
	public void limpiarLista() {
		modeloPalabrasClave.clear();
	}
	
	public void activarSeleccion() {
		listaPalabrasClave.setSelectedIndex(0);
	}
}