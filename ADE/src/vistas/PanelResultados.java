package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	private JLabel lbApareceIEEE;
	private JLabel lbApareceIFAC;

	public PanelResultados() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_RESULTADOS));
		modeloPalabrasClave = new DefaultListModel<String>();
		listaPalabrasClave = new JList<String>(modeloPalabrasClave);
		listaPalabrasClave.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_LISTA_PALABRAS));
		add(listaPalabrasClave);
		
		JPanel panelApareceListaTerminos = new JPanel();
		panelApareceListaTerminos.setBackground(Color.WHITE);
		
		lbApareceIEEE = new JLabel();
		lbApareceIEEE.setPreferredSize(new Dimension(150, 50));
		lbApareceIEEE.setBorder(BorderFactory
				.createTitledBorder("Aparece en el indice IEEE"));
		panelApareceListaTerminos.add(lbApareceIEEE);
		
		lbApareceIFAC = new JLabel();
		lbApareceIFAC.setPreferredSize(new Dimension(150, 50));
		lbApareceIFAC.setBorder(BorderFactory
				.createTitledBorder("Aparece en el indice IFAC"));
		panelApareceListaTerminos.add(lbApareceIFAC);
		
		add(panelApareceListaTerminos);
		
		modeloTablaResultados = new DefaultTableModel(ConstantesGUI.T_COLUMNAS_TABLA_RESULTADOS, 0);
		tablaResultados = new JTable(modeloTablaResultados);
		tablaResultados.setBackground(Color.WHITE);
		JScrollPane panelTabla = new JScrollPane(tablaResultados);
		panelTabla.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_TABLA_RESULTADOS));
		add(panelTabla);
	}
	
	public void modificarPanelTerminosAparecen(boolean apareceIEEE,
			boolean aparceIFAC) {
		if (apareceIEEE) {
			lbApareceIEEE
					.setIcon(createImageIcon(ConstantesGUI.ICONO_CORRECTO));
		} else {
			lbApareceIEEE
					.setIcon(createImageIcon(ConstantesGUI.ICONO_INCORRECTO));
		}
		if (aparceIFAC) {
			lbApareceIFAC
					.setIcon(createImageIcon(ConstantesGUI.ICONO_CORRECTO));
		} else {
			lbApareceIFAC
					.setIcon(createImageIcon(ConstantesGUI.ICONO_INCORRECTO));
		}
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
	
	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}
}