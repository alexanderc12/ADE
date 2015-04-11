package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controladores.Controlador;
import modelo.ZonaArticulo;

public class DialogoTopTerminos extends JDialog {

	private static final long serialVersionUID = 1L;
	private JComboBox<ZonaArticulo> bxPartesArticulo;
	private JTextField txNumeroTerminos;
	private JTable tablaTerminosTop;
	private DefaultTableModel modeloTablaTerminosTop;
	private JButton btnActualizarLista;
	private JLabel lbPartesArticulo;
	private JLabel lbNumeroTerminos;

	public DialogoTopTerminos(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		super(ventanaPrincipal);
		setLayout(new BorderLayout());
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_TOP_TERMINOS);
		setSize(ConstantesGUI.DIALOGO_TOP_TERMINOS_ANCHO, ConstantesGUI.DIALOGO_TOP_TERMINOS_ALTO);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);

		modeloTablaTerminosTop = new DefaultTableModel(ConstantesGUI.T_COLUMNAS_TABLA_TOP_TERMINOS, 0);
		tablaTerminosTop = new JTable(modeloTablaTerminosTop);
		JScrollPane panelTabla = new JScrollPane(tablaTerminosTop);
		add(panelTabla, BorderLayout.CENTER);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new FlowLayout());

		lbPartesArticulo = new JLabel(ConstantesGUI.T_LISTA_PARTES_ARTICULO);
		panelFormulario.add(lbPartesArticulo);

		bxPartesArticulo = new JComboBox<>(ZonaArticulo.values());
		panelFormulario.add(bxPartesArticulo);

		lbNumeroTerminos = new JLabel(ConstantesGUI.T_NUMERO_TERMINOS);
		panelFormulario.add(lbNumeroTerminos);

		txNumeroTerminos = new JTextField(2);
		panelFormulario.add(txNumeroTerminos);

		btnActualizarLista = new JButton(ConstantesGUI.T_ACTUALIZAR, createImageIcon(ConstantesGUI.ICONO_ACTUALIZAR));
		btnActualizarLista.setFocusable(false);
		btnActualizarLista.setActionCommand(Controlador.A_ACTUALIZAR_LISTA_TERMINOS_TOP);
		btnActualizarLista.addActionListener(controlador);
		panelFormulario.add(btnActualizarLista);

		add(panelFormulario, BorderLayout.PAGE_END);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				limpiarInterfaz();
				super.windowClosing(e);
			}
		});
	}

	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}

	public void actualizarTablaTop(ArrayList<String> listaTopTerminos) {
		modeloTablaTerminosTop.setRowCount(0);
		String termino = "";
		for (int i = 1; i <= listaTopTerminos.size(); i++) {
			termino = listaTopTerminos.get(i - 1);
			String[] listaComponentesTermino = termino.split(",");
			modeloTablaTerminosTop.addRow(
					new String[] { Integer.toString(i), listaComponentesTermino[0], listaComponentesTermino[1] });
		}
	}

	public ZonaArticulo obtenerParteSeleccionada() {
		return (ZonaArticulo) bxPartesArticulo.getSelectedItem();
	}

	public int obtenerNumero() {
		return Integer.parseInt(txNumeroTerminos.getText());
	}

	public void limpiarInterfaz() {
		modeloTablaTerminosTop.setRowCount(0);
		bxPartesArticulo.setSelectedIndex(0);
		txNumeroTerminos.setText("");
	}
}