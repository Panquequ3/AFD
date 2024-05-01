package tarea_AFD;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PTextField extends JTextField {
	private boolean first_focusable = false;
	private boolean first_focus = true;

	public PTextField(final String proptText, boolean is_first_focusable) {
        super(proptText);
        this.setPreferredSize(new Dimension(250,30));
        this.first_focusable = is_first_focusable;
        addFocusListener(new FocusListener() {

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()) {
                    setText(proptText);
                }
            }

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
