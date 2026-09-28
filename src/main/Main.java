package main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private static boolean theme = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatDarkLaf.setup();
					theme = false;
					boolean tema = Preferences.userNodeForPackage(Main.class).getBoolean("tema", false);
					if (tema) {
						FlatLightLaf.setup();
						theme = true;
					}
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static String traducirBinario(String binario) {
		String resultado = "";
		for (int i=0; i<binario.length();i+=4) {
			String grupo = binario.substring(i,i+4);
			switch (grupo) {
	        case "0000": resultado += "0"; break;
	        case "0001": resultado += "1"; break;
	        case "0010": resultado += "2"; break;
	        case "0011": resultado += "3"; break;
	        case "0100": resultado += "4"; break;
	        case "0101": resultado += "5"; break;
	        case "0110": resultado += "6"; break;
	        case "0111": resultado += "7"; break;
	        case "1000": resultado += "8"; break;
	        case "1001": resultado += "9"; break;
	        case "1010": resultado += "A"; break;
	        case "1011": resultado += "B"; break;
	        case "1100": resultado += "C"; break;
	        case "1101": resultado += "D"; break;
	        case "1110": resultado += "E"; break;
	        case "1111": resultado += "F"; break;
			}
		}
		return resultado;
	}
	
	private static String traducirHexadecimal(String hexadecimal) {
		String resultado = "";
		for (int i=0; i<hexadecimal.length();i++) {
			char hexa = Character.toUpperCase(hexadecimal.charAt(i));
			switch (hexa) {
	        case '0': resultado += "0000"; break;
	        case '1': resultado += "0001"; break;
	        case '2': resultado += "0010"; break;
	        case '3': resultado += "0011"; break;
	        case '4': resultado += "0100"; break;
	        case '5': resultado += "0101"; break;
	        case '6': resultado += "0110"; break;
	        case '7': resultado += "0111"; break;
	        case '8': resultado += "1000"; break;
	        case '9': resultado += "1001"; break;
	        case 'A': resultado += "1010"; break;
	        case 'B': resultado += "1011"; break;
	        case 'C': resultado += "1100"; break;
	        case 'D': resultado += "1101"; break;
	        case 'E': resultado += "1110"; break;
	        case 'F': resultado += "1111"; break;
			}
		}
		return resultado;
	}
	
	private static void asignarIconoFijo(JButton boton, String rutaRecurso, int lado) {
		FlatSVGIcon icono = new FlatSVGIcon(Main.class.getResource(rutaRecurso));
		icono = icono.derive(lado - 10, lado - 10);
		icono.setColorFilter(new FlatSVGIcon.ColorFilter(color -> boton.getForeground()));
		boton.setIcon(icono);
	}
	
	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Traductor Binario a Hexadecimal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(400, 250));
		panelPrincipal = new JPanel();
		panelPrincipal.setBounds(new Rectangle(100, 100, 450, 330));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		JPanel panelArriba = new JPanel();
		panelArriba.setBorder(new EmptyBorder(5, 0, 5, 0));
		panelPrincipal.add(panelArriba);
		panelArriba.setLayout(new GridLayout(1, 2, 100, 0));
		
		JLabel lblIntroducir = new JLabel("Binario");
		lblIntroducir.setHorizontalAlignment(SwingConstants.CENTER);
		panelArriba.add(lblIntroducir);
		String temaGuardado = Preferences.userNodeForPackage(Main.class).get("tema", "Oscuro");
		
		JLabel lblSalida = new JLabel("Hexadecimal");
		lblSalida.setHorizontalAlignment(SwingConstants.CENTER);
		panelArriba.add(lblSalida);
		
		JPanel panelAbajo = new JPanel();
		panelAbajo.setMinimumSize(new Dimension(10, 150));
		panelPrincipal.add(panelAbajo);
		panelAbajo.setLayout(new BoxLayout(panelAbajo, BoxLayout.X_AXIS));
		
		JPanel panelIzquierda = new JPanel();
		panelAbajo.add(panelIzquierda);
		panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
		
		JPanel panelCentro = new JPanel();
		panelCentro.setPreferredSize(new Dimension(50, 50));
		panelCentro.setMinimumSize(new Dimension(35, 130));
		panelAbajo.add(panelCentro);
		
		JPanel panelDerecha = new JPanel();
		panelAbajo.add(panelDerecha);
		panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.Y_AXIS));
		
		JComboBox Traducido = new JComboBox();
		Traducido.setModel(new DefaultComboBoxModel(new String[] {"Binario", "Hexadecimal"}));
		panelIzquierda.add(Traducido);
		
		JComboBox Traducir = new JComboBox();
		Traducir.setModel(new DefaultComboBoxModel(new String[] {"Hexadecimal", "Binario"}));
		panelDerecha.add(Traducir);
		
		JTextArea textSalida = new JTextArea();
		textSalida.setMargin(new Insets(5, 5, 5, 5));
		textSalida.setLineWrap(true);
		textSalida.setEditable(false);
		panelDerecha.add(textSalida);
		
		JTextArea textIntroducir = new JTextArea();
		textIntroducir.setMargin(new Insets(5, 5, 5, 5));
		textIntroducir.setLineWrap(true);
		panelIzquierda.add(textIntroducir);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		// Cambiar los valores de las comboBox y de los labels dinámicamente
		Traducido.addActionListener(e -> actualizarFormatosComboBox(Traducido, Traducir, lblIntroducir, lblSalida, textIntroducir, textSalida));
		Traducir.addActionListener(e -> actualizarFormatosComboBox(Traducido, Traducir, lblIntroducir, lblSalida, textIntroducir, textSalida));
		
		textIntroducir.setMinimumSize(new Dimension(100, 60));
		textSalida.setMinimumSize(new Dimension(100, 60));
		Traducido.setMinimumSize(new Dimension(80, 25));
		Traducir.setMinimumSize(new Dimension(80, 25));
		
		JPanel panelBotones = new JPanel();
		panelBotones.setMinimumSize(new Dimension(50, 130));
		panelCentro.add(panelBotones, BorderLayout.NORTH);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		
		JButton btnCambiar = new JButton("");
		btnCambiar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(btnCambiar);
		panelBotones.add(Box.createVerticalStrut(10));
		btnCambiar.setPreferredSize(new Dimension(33, 33));
		btnCambiar.setMinimumSize(new Dimension(11, 11));
		btnCambiar.setMaximumSize(new Dimension(33, 33));
		asignarIconoFijo(btnCambiar, "/img/exchange.svg", 33);
		btnCambiar.addActionListener(e -> actualizarFormatos(Traducido, Traducir, lblIntroducir, lblSalida, textIntroducir, textSalida));
		
		JButton btnBorrar = new JButton("");
		btnBorrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(btnBorrar);
		panelBotones.add(Box.createVerticalStrut(10));
		btnBorrar.setPreferredSize(new Dimension(33, 33));
		btnBorrar.setMinimumSize(new Dimension(33, 33));
		btnBorrar.setMaximumSize(new Dimension(33, 33));
		asignarIconoFijo(btnBorrar, "/img/trash.svg", 33);
		
		btnBorrar.addActionListener(e -> {
			textIntroducir.setText("");
			textSalida.setText("");
		});
		
		JButton btnTraducir = new JButton("");
		btnTraducir.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(btnTraducir);
		btnTraducir.setMaximumSize(new Dimension(33, 33));
		btnTraducir.setMinimumSize(new Dimension(33, 33));
		btnTraducir.setPreferredSize(new Dimension(33, 33));
		asignarIconoFijo(btnTraducir, "/img/translation.svg", 33);
		btnTraducir.addActionListener(e -> {
		    if (Traducido.getSelectedItem().equals("Binario")) {
		        if (textIntroducir.getText().length() % 4 != 0) {
		            textSalida.setText("El binario debe tener una longitud múltiplo de 4.");
		            return;
		        }
		    	textSalida.setText(traducirBinario(textIntroducir.getText()));
		    } else {
		        textSalida.setText(traducirHexadecimal(textIntroducir.getText()));
		    }
		});
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panelBotones.add(verticalStrut);
		
		JButton btnTema = new JButton("");
		btnTema.setPreferredSize(new Dimension(33, 33));
		btnTema.setMinimumSize(new Dimension(33, 33));
		btnTema.setMaximumSize(new Dimension(33, 33));
		btnTema.setAlignmentX(0.5f);
		panelBotones.add(btnTema);
		asignarIconoFijo(btnTema, "/img/theme.svg", 33);
		btnTema.addActionListener(e -> cambiarTema(btnCambiar, btnTraducir, btnBorrar, btnTema));

	}

	// Método para cambiar los valores de las comboBox y labels dinámicamente
	private void actualizarFormatos(JComboBox Traducido, JComboBox Traducir, JLabel lblIntroducir, JLabel lblSalida, JTextArea textIntroducir, JTextArea textSalida) {
		String entrada = textIntroducir.getText(); 
		String salida =  textSalida.getText();
	    if (Traducido.getSelectedItem().equals("Binario")) {
	        Traducido.setSelectedItem("Hexadecimal");
	        Traducir.setSelectedItem("Binario");
	        lblIntroducir.setText("Hexadecimal");
	        lblSalida.setText("Binario");
	        
	    } else if (Traducido.getSelectedItem().equals("Hexadecimal")){
	        Traducido.setSelectedItem("Binario");
	        Traducir.setSelectedItem("Hexadecimal");
	        lblIntroducir.setText("Binario");
	        lblSalida.setText("Hexadecimal");
	    }
        textIntroducir.setText(salida);
        textSalida.setText(entrada);
	}
	
	private void actualizarFormatosComboBox(JComboBox Traducido, JComboBox Traducir, JLabel lblIntroducir, JLabel lblSalida, JTextArea textIntroducir, JTextArea textSalida) {
		String entrada = textIntroducir.getText(); 
		String salida =  textSalida.getText();
	    if (Traducido.getSelectedItem().equals("Binario")) {
	        Traducido.setSelectedItem("Binario");
	        Traducir.setSelectedItem("Hexadecimal");
	        lblIntroducir.setText("Binario");
	        lblSalida.setText("Hexadecimal");
	    } else if (Traducido.getSelectedItem().equals("Hexadecimal")){
	        Traducido.setSelectedItem("Hexadecimal");
	        Traducir.setSelectedItem("Binario");
	        lblIntroducir.setText("Hexadecimal");
	        lblSalida.setText("Binario");
	    }
        textIntroducir.setText(salida);
        textSalida.setText(entrada);
	}
	
	private void cambiarTema(JButton cambiar, JButton traducir, JButton borrar, JButton tema) {
	    try {
	    	if (!theme) {
	    		FlatLightLaf.setup();
	    		theme = true;
	    	} else {
	    		FlatDarkLaf.setup();
	    		theme = false;
	    	}
	        SwingUtilities.updateComponentTreeUI(this);
	        Preferences prefs = Preferences.userNodeForPackage(Main.class);
	        prefs.putBoolean("tema", theme);
	        asignarIconoFijo(cambiar, "/img/exchange.svg", 33);
	        asignarIconoFijo(borrar, "/img/trash.svg", 33);
	        asignarIconoFijo(traducir, "/img/translation.svg", 33);
	        asignarIconoFijo(tema, "/img/theme.svg", 33);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
