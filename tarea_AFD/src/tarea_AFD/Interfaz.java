package tarea_AFD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;




public class Interfaz extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private static Interfaz instance;
	private JFrame main;
	
	private JLabel headerTitle;
	
	private JLabel bodyTitle;
	private JTextField bodyEstadoInicialBox;
	private JTextField bodyEstadosFinalesBox; 
	private JTextField bodyTransicionesBox;
	private JPanel botonesTransiciones;
	private JButton botonIngresar;
	private JButton botonResetear;
	private JButton comfirmarAFD;
	
	private JLabel footerTitle;
	private JTextField palabraBox;
	private JButton comfirmar;
	
	private String stringEstadoInicial;
	private String stringEstadosFinales;
	private String stringTransiciones;
	
	
	/*constructor de la clase, usa singleton, crea el JFrame principal y llama
	las funciones que crean los JPanel*/
	private Interfaz() {
		this.main = new JFrame("Nombre");
		main.setBounds(300, 150, 450, 500); 

		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		main.setLayout(new GridBagLayout());
		
		stringEstadoInicial = "";
		stringEstadosFinales = "";
		stringTransiciones = "";
		
		makeMenu();
		

		main.setVisible(true);
	}
	
	private void makeMenu() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10,0,10,20);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		headerTitle = new JLabel("Titulo");
		headerTitle.setHorizontalAlignment(JLabel.CENTER);
		main.add(headerTitle, gbc);
		
		
		
		bodyTitle = new JLabel("Datos AFD");
		bodyTitle.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 1;
		main.add(bodyTitle, gbc);

		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		main.add(new JLabel("Estado inicial:"), gbc);
		bodyEstadoInicialBox = new JTextField("ej: q0");
		gbc.gridx = 1;
		gbc.gridy = 2;
		main.add(bodyEstadoInicialBox, gbc);	
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		main.add(new JLabel("Estados finales:"), gbc);
		bodyEstadosFinalesBox = new JTextField("ej: q4, q5, q7");
		gbc.gridx = 1;
		gbc.gridy = 3;
		main.add(bodyEstadosFinalesBox, gbc);
			
		gbc.gridx = 0;
		gbc.gridy = 4;
		main.add(new JLabel("Transiciones: "), gbc);
		bodyTransicionesBox = new JTextField("ej: (q0,a,q1), (q0,b,q2)");
		gbc.gridx = 1;
		gbc.gridy = 4;
		main.add(bodyTransicionesBox, gbc);
		
		botonesTransiciones = new JPanel();
		botonesTransiciones.setLayout(new GridBagLayout());
		botonIngresar = new JButton("Ingresar transiciones");
		botonIngresar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		botonesTransiciones.add(botonIngresar, gbc);
		botonResetear = new JButton("Resetear transiciones");
		botonResetear.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 0;
		botonesTransiciones.add(botonResetear, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 5;
		main.add(botonesTransiciones, gbc);
		
		comfirmarAFD = new JButton("Comfirmar AFD");
		comfirmarAFD.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		main.add(comfirmarAFD, gbc);
		
		
		
		footerTitle = new JLabel("Ingreso Palabras");
		footerTitle.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 7;
		main.add(footerTitle, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 8;
		main.add(new JLabel("Palabra:"), gbc);
		palabraBox = new JTextField("ej: abbcbabacbcc");
		gbc.gridx = 1;
		gbc.gridy = 8;
		main.add(palabraBox, gbc);
		
		comfirmar = new JButton("Comfirmar");
		comfirmar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		main.add(comfirmar, gbc);
		
		
		

		
		
		
		
	
	}
	
	
	
	

	


	
	

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		Interfaz v = new Interfaz();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == botonIngresar) {
			stringTransiciones = stringTransiciones + bodyTransicionesBox.getText();
			if (stringTransiciones.charAt(stringTransiciones.length() - 1) != ',') {
				stringTransiciones = stringTransiciones + ",";
			}
		}
		
		if (e.getSource() == botonResetear) {
			stringTransiciones = "";
		}
		
		

		if (e.getSource() == comfirmarAFD) {
			headerTitle.setText("comfirmarAFD");
		}
		
		
		if (e.getSource() == comfirmar) {
			headerTitle.setText("comfirmar");
		}
	}
}

