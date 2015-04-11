package vistas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladores.Controlador;

public class PanelResultados extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> listaPalabrasClave;
	private DefaultListModel<String> modeloPalabrasClave;
	private JTable tablaResultados;
	private DefaultTableModel modeloTablaResultados;
	private JLabel lbApareceIEEE;
	private JLabel lbApareceIFAC;
	private JProgressBar barraNivelAfinidad;

	public PanelResultados() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_PANEL_RESULTADOS));

		JPanel panelListaEIndices = new JPanel(new GridLayout(1, 2));

		modeloPalabrasClave = new DefaultListModel<String>();
		listaPalabrasClave = new JList<String>(modeloPalabrasClave);
		listaPalabrasClave.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_LISTA_PALABRAS));
		JScrollPane panelLista = new JScrollPane(listaPalabrasClave);
		panelListaEIndices.add(panelLista);

		JPanel panelApareceListaTerminos = new JPanel(new GridLayout(2, 1));
		panelApareceListaTerminos.setBackground(Color.WHITE);

		lbApareceIEEE = new JLabel(createImageIcon(ConstantesGUI.ICONO_SIN_ANALIZAR));
		lbApareceIEEE.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_INDICE_IEEE));
		panelApareceListaTerminos.add(lbApareceIEEE);

		lbApareceIFAC = new JLabel(createImageIcon(ConstantesGUI.ICONO_SIN_ANALIZAR));
		lbApareceIFAC.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_INDICE_IFAC));
		panelApareceListaTerminos.add(lbApareceIFAC);

		panelListaEIndices.add(panelApareceListaTerminos);

		add(panelListaEIndices);

		modeloTablaResultados = new DefaultTableModel(ConstantesGUI.T_COLUMNAS_TABLA_RESULTADOS, 0);
		tablaResultados = new JTable(modeloTablaResultados);
		tablaResultados.getTableHeader()
		.setPreferredSize(new Dimension(tablaResultados.getColumnModel().getTotalColumnWidth(), 70));
		JScrollPane panelTabla = new JScrollPane(tablaResultados);
		panelTabla.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_TABLA_RESULTADOS));
		add(panelTabla);

		JPanel panelBarraAfinidad = new JPanel(new GridLayout(1, 1));
		panelBarraAfinidad.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_BARRA_AFINIDAD));
		barraNivelAfinidad = new JProgressBar(0, 100);
		barraNivelAfinidad.setPreferredSize(new Dimension(getWidth(), 50));
		barraNivelAfinidad.setStringPainted(true);
		panelBarraAfinidad.add(barraNivelAfinidad);
		add(panelBarraAfinidad);
	}

	public void modificarNivelAfinidad(double nivel) {
		barraNivelAfinidad.setValue((int) nivel);
		barraNivelAfinidad.setString(Controlador.DECIMAL_FORMART.format(nivel) + "%");
	}

	public void modificarPanelTerminosAparecen(boolean apareceIEEE, boolean aparceIFAC) {
		if (apareceIEEE) {
			lbApareceIEEE.setIcon(createImageIcon(ConstantesGUI.ICONO_CORRECTO));
		} else {
			lbApareceIEEE.setIcon(createImageIcon(ConstantesGUI.ICONO_INCORRECTO));
		}
		if (aparceIFAC) {
			lbApareceIFAC.setIcon(createImageIcon(ConstantesGUI.ICONO_CORRECTO));
		} else {
			lbApareceIFAC.setIcon(createImageIcon(ConstantesGUI.ICONO_INCORRECTO));
		}
	}

	public void agregarPalabraClave(String palabra) {
		modeloPalabrasClave.addElement(palabra);
	}

	public String obtenerPalabraSelecionada() {
		return listaPalabrasClave.getSelectedValue();
	}

	public int obtenerIndicePalabraSelecionada() {
		return listaPalabrasClave.getSelectedIndex();
	}

	public void agregarResultado(String parteArticulo, String numeroIncidencias, String numeroElementos,
			String numeroElementosAnalizables, String numeroLemas, String numeroIncidenciasLemas,
			String numeroSinonimos, String porcentajeIncidencias) {
		modeloTablaResultados.addRow(new String[] { parteArticulo, numeroElementos, numeroElementosAnalizables,
				numeroIncidencias, numeroLemas, numeroIncidenciasLemas, numeroSinonimos, porcentajeIncidencias });
	}

	public void limpiarTabla() {
		modeloTablaResultados.setRowCount(0);
	}

	public void limpiarLista() {
		modeloPalabrasClave.clear();
	}

	public void activarSeleccion() {
		listaPalabrasClave.setSelectedIndex(0);
	}

	public void limpiarResultadosIndices() {
		lbApareceIEEE.setIcon(createImageIcon(ConstantesGUI.ICONO_SIN_ANALIZAR));
		lbApareceIFAC.setIcon(createImageIcon(ConstantesGUI.ICONO_SIN_ANALIZAR));
	}

	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}

	public void limpiarInterfaz() {
		limpiarTabla();
		limpiarLista();
		limpiarResultadosIndices();
	}
}