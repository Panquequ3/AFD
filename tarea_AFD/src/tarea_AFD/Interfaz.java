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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
	
	//Elementos de la parte de ingresar el AFD
	
	private JLabel bodyTitle;
	private JTextField bodyEstadoInicialBox;
	private JTextField bodyEstadosFinalesBox; 
	private JTextField bodyTransicionesBox;
	private JPanel botonesTransiciones;
	private JButton botonIngresar;
	private JButton botonResetear;
	private JButton comfirmarAFD;
	
	//Elementos de la parte post ingresar el AFD
	
	private JLabel footerTitle;
	private JTextField palabraBox;
	private JButton comfirmar;
	
	//Lugar donde se va a guardar toda la info
	
	private String stringEstadoInicial;
	private String stringEstadosFinales;
	private String stringTransiciones;
	private String palabra;
	
	//AFD
	
	private AFD afd;
	
	
	/*Constructor de la clase, usa singleton, crea el JFrame principal y llama
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
	
	/*
	 Funcion que crea la parte visual de la interfaz 
	 */
	
	private void makeMenu() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10,0,10,20);
		
		//Creacion del Titulo de la interfaz
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		headerTitle = new JLabel("Titulo");
		headerTitle.setHorizontalAlignment(JLabel.CENTER);
		main.add(headerTitle, gbc);
		
		//SubTitulo de la interfaz donde el usuario ingresara el AFD
		
		bodyTitle = new JLabel("Datos AFD");
		bodyTitle.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 1;
		main.add(bodyTitle, gbc);
		
		//Creacion del input para el estado inicial con su
		//ejemplo correspondiente
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		main.add(new JLabel("Estado inicial:"), gbc);
		bodyEstadoInicialBox = new PTextField("ej: q0", true);
		gbc.gridx = 1;
		gbc.gridy = 2;
		main.add(bodyEstadoInicialBox, gbc);	
		
		//Creacion del input para los estados finales con
		//su ejemplo correspondiente
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		main.add(new JLabel("Estados finales:"), gbc);
		bodyEstadosFinalesBox = new PTextField("ej: q1, q5, q7", false);
		gbc.gridx = 1;
		gbc.gridy = 3;
		main.add(bodyEstadosFinalesBox, gbc);
		
		//Creacion del input para las tranciciones
		//donde el primer elemento es el estado inicial(Donde comienza)
		//El segundo es el simbolo y el ultimo es el
		//estado final(Donde LLega)
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		main.add(new JLabel("Transiciones: "), gbc);
		bodyTransicionesBox = new PTextField("ej: (q0,a,q1)-(q0,b,q2)", false);
		gbc.gridx = 1;
		gbc.gridy = 4;
		main.add(bodyTransicionesBox, gbc);	
		
		//Boton para crear el AFD con los datos almacenados
		
		comfirmarAFD = new JButton("Comfirmar AFD");
		comfirmarAFD.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		main.add(comfirmarAFD, gbc);
		
		//Parte del ingreso de las palabras dentro del AFD
		
		footerTitle = new JLabel("Ingreso Palabras");
		footerTitle.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 7;
		main.add(footerTitle, gbc);
		
		//Celda donde se ingresara la palabra a comprobar
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 8;
		main.add(new JLabel("Palabra:"), gbc);
		palabraBox = new PTextField("ej: abbcbabacbcc", false);
		gbc.gridx = 1;
		gbc.gridy = 8;
		main.add(palabraBox, gbc);
		
		//Boton para probar la palabra dentro del AFD ingresado
		
		comfirmar = new JButton("Comfirmar");
		comfirmar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		main.add(comfirmar, gbc);
	}
	
	public void ventanilla() {
		
	}
		
	//Funcion donde se iniciara la interfaz
	
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}
		Interfaz v = new Interfaz();
	}
	
	//Funcion donde se crean las funciones de los botones

	@Override
	public void actionPerformed(ActionEvent e) {		
		
		//Si se presiona el boton comfirmarAFD
		if (e.getSource() == comfirmarAFD) {
			
			//Toma el texto actualmente en los JTextField
			stringEstadoInicial = bodyEstadoInicialBox.getText();
			stringEstadosFinales = bodyEstadosFinalesBox.getText();
			stringTransiciones = bodyTransicionesBox.getText();
			
			
			//Crea el afd
			afd = new AFD(stringEstadoInicial, stringEstadosFinales, stringTransiciones);
			
		}
		
		//Si se presiona el boton comfirmar
		if (e.getSource() == comfirmar) {
			palabra = palabraBox.getText();
			if (afd.lee_palabra(palabra)) {
				headerTitle.setText("se acepto la palabra");
			}
			else {
				headerTitle.setText("no se acepto la palabra");
			}
		}
	}
}

