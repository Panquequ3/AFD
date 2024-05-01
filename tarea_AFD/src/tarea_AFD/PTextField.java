package tarea_AFD;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

//Esta clase extiende JTextField para añadir una nueva funcionalidad de "placeholder text"
public class PTextField extends JTextField {
	private boolean first_focusable = false;
	private boolean first_focus = true;

	//El String que recibe la clase funciona como placeholder, y va a desaparecer cuando se haga focus al componente
	//Adicionalmente se recibe un booleano en caso de que el componente reciba focus al comenzar el programa
	//Este se usa en conjunto a otro booleano para que el texto placeholder no desaparezca la primera vez que se le hace focus
	public PTextField(final String proptText, boolean is_first_focusable) {
        super(proptText);
        this.setPreferredSize(new Dimension(250,30));
        this.first_focusable = is_first_focusable;
        addFocusListener(new FocusListener() {

        	//Cuando el componente pierde focus y no tiene texto, este va a volver a mostrar el texto placeholder
            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()) {
                    setText(proptText);
                }
            }

            //Cuando el componente gana focus, y tiene el texto placeholder, va a cambiarlo por una palabra vacia
            //Si el componente recibe focus al iniciarse el programa no se va a cambiar el texto por el primer focus
            @Override
            public void focusGained(FocusEvent e) {
            	if (first_focusable) {
            		if (first_focus) {
            			first_focus = false;
            		}
            		else {
            			if(getText().equals(proptText) ) {
                            setText("");
                        }
            		}
            		
            	}
            	else {
            		if(getText().equals(proptText) ) {
                        setText("");
                    }
            	}
            }
        });

    }
	
}
