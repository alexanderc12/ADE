package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controladores.Controlador;

public class DialogoListaSinonimos extends JDialog {

	private static final long serialVersionUID = 1L;

	private JLabel lbTermino;
	private JTextField txTermino;
	private JList<String> listaSinonimos;
	private DefaultListModel<String> modeloListaSinonimos;
	private JButton btnActualizarListaSinonimos;

	public DialogoListaSinonimos(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		super(ventanaPrincipal);
		setLayout(new BorderLayout());
		setTitle(ConstantesGUI.T_TITULO_DIALOGO_LISTA_SINONIMOS);
		setSize(ConstantesGUI.DIALOGO_SINONIMOS_ANCHO, ConstantesGUI.DIALOGO_SINONIMOS_ALTO);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);

		modeloListaSinonimos = new DefaultListModel<String>();
		listaSinonimos = new JList<String>(modeloListaSinonimos);
		JScrollPane panelLista = new JScrollPane(listaSinonimos);
		add(panelLista, BorderLayout.CENTER);

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new FlowLayout());

		lbTermino = new JLabel(ConstantesGUI.T_PALABRAS_CLAVE);
		panelFormulario.add(lbTermino);

		txTermino = new JTextField(20);
		panelFormulario.add(txTermino);

		btnActualizarListaSinonimos = new JButton(ConstantesGUI.T_ACTUALIZAR,
				createImageIcon(ConstantesGUI.ICONO_ACTUALIZAR));
		btnActualizarListaSinonimos.setFocusable(false);
		btnActualizarListaSinonimos.setActionCommand(Controlador.A_ACTUALIZAR_LISTA_SINONIMOS);
		btnActualizarListaSinonimos.addActionListener(controlador);
		panelFormulario.add(btnActualizarListaSinonimos);

		add(panelFormulario, BorderLayout.PAGE_END);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				limpiarInterfaz();
				txTermino.setText("");
				super.windowClosing(e);
			}
		});
	}

	private void limpiarInterfaz() {
		modeloListaSinonimos.clear();
	}

	public String obtenerTermino() {
		return txTermino.getText();
	}

	public void actualizarListaSinonimos(String sinonimo) {
		modeloListaSinonimos.addElement(sinonimo);
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