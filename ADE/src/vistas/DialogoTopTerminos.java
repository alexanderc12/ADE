package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.Controlador;
import modelo.ZonaArticulo;

public class DialogoTopTerminos extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JComboBox<ZonaArticulo> bxPartesArticulo;
	private JTextField txNumeroTerminos;
	private JList<String> listaTerminosTop;
	private DefaultListModel<String> modeloTerminosTop;
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
		
		modeloTerminosTop = new DefaultListModel<String>();
		listaTerminosTop = new JList<String>(modeloTerminosTop);
		listaTerminosTop.setBorder(BorderFactory.createTitledBorder(ConstantesGUI.T_LISTA_TOP_TERMINOS));
		add(listaTerminosTop, BorderLayout.CENTER);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.X_AXIS));
		
		lbPartesArticulo = new JLabel(ConstantesGUI.T_LISTA_PARTES_ARTICULO);
		panelFormulario.add(lbPartesArticulo);
		
		bxPartesArticulo = new JComboBox<>(ZonaArticulo.values());
		panelFormulario.add(bxPartesArticulo);

		lbNumeroTerminos = new JLabel(ConstantesGUI.T_NUMERO_TERMINOS);
		panelFormulario.add(lbNumeroTerminos);
		
		txNumeroTerminos = new JTextField(2);
		panelFormulario.add(txNumeroTerminos);

		btnActualizarLista = new JButton(ConstantesGUI.T_ACTUALIZAR_LISTA_TOP_TERMINOS,
				createImageIcon(ConstantesGUI.ICONO_ACTUALIZAR_LISTA_TOP_TERMINOS));
		btnActualizarLista.setFocusable(false);
		btnActualizarLista.setActionCommand(Controlador.A_ACTUALIZAR_LISTA_TERMINOS_TOP);
		btnActualizarLista.addActionListener(controlador);
		panelFormulario.add(btnActualizarLista);
		
		add(panelFormulario, BorderLayout.PAGE_END);
	}
	
	protected ImageIcon createImageIcon(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, "");
		} else {
			return null;
		}
	}

	public void agregarLista(ArrayList<String> listaTopTerminos) {
		modeloTerminosTop.clear();
		for (String termino : listaTopTerminos) {
			modeloTerminosTop.addElement(termino);
		}
	}

	public ZonaArticulo obtenerParteSeleccionada() {
		return (ZonaArticulo) bxPartesArticulo.getSelectedItem();
	}

	public int obtenerNumero() {
		return Integer.parseInt(txNumeroTerminos.getText());
	}
	
	public void limpiarInterfaz() {
		modeloTerminosTop.clear();
		bxPartesArticulo.setSelectedIndex(0);
		txNumeroTerminos.setText("");
	}
}