package domino.model;

import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelConfig extends JInternalFrame {

    public PanelConfig() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout());
        this.pack();
		
    
    }

    public void mostrar() {
        this.setOpaque(true);
        System.out.println("Creado!");
        this.add (new JLabel("Una etiqueta"));
	this.add (new JTextField(10));
    }

    // Se construye el JInternalFrame
  
        
    
}
