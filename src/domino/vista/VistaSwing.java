package domino.vista;

import domino.control.ControlText;import domino.model.PanelFondo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VistaSwing extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu menuImatge, menuColor;
    private JMenuItem menuItemJugar, menuItemColorDefecte,menuItemColorBlau,menuItemColorRustic;

    
    private JLabel labelJuego, labelColor;
    private JPanel panelJuego;
    private PanelFondo panelColor;

    private BufferedImage img = null;
    
    public VistaSwing() throws HeadlessException {

        configuracio();
        this.pack();
        this.setVisible(true);
    }

    private void configuracio() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(700, 400));
        this.setLocationRelativeTo(null);
    
        menuItemJugar  = new JMenuItem("Jugar");
        menuItemJugar.addActionListener(this);
        
        menuItemColorDefecte = new JMenuItem("Per defecte");
        menuItemColorDefecte.addActionListener(this);
        
        menuItemColorBlau = new JMenuItem("Blau Professional");
        menuItemColorBlau.addActionListener(this);
        
        menuItemColorRustic = new JMenuItem("Rústic");
        menuItemColorRustic.addActionListener(this);
        
    
        menuImatge = new JMenu("Inicia Joc");
        menuImatge.add(menuItemJugar);
        
        menuColor = new JMenu("Canviar Color");
        menuColor.add(menuItemColorDefecte);
        menuColor.add(menuItemColorBlau);
        menuColor.add(menuItemColorRustic);
        
        
        menuBar = new JMenuBar();
        menuBar.add(menuColor);
        menuBar.add(menuImatge);
        
        this.setJMenuBar(menuBar);
        
        
        
        this.labelJuego = new JLabel();
        this.labelColor = new JLabel("Color", JLabel.CENTER);
        this.labelColor.setOpaque(true);
        
        panelColor = new PanelFondo();
        
        
        panelJuego = new JPanel();
        
        panelJuego.setOpaque(false);
        
        //panelColor.add(labelColor);
        panelJuego.add(labelJuego);

        this.getContentPane().add(this.panelColor, BorderLayout.CENTER);
        this.getContentPane().add(this.panelJuego, BorderLayout.SOUTH);
        
        
                

}

    
   //JFIleChoose para escojer el tipico archivo que te pone
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

       String actionCommand = e.getActionCommand();
       switch (actionCommand){
           
           case "Jugar":
                jugar();
               break;
           
           case "Per defecte":
               canviarFons("fichasDomino/tapete.png");
               break;
               
            case "Rústic":
               canviarFons("fichasDomino/tapeteMesaDePueblo.png");
               break;

            case "Blau Professional":
               canviarFons("fichasDomino/tapeteBlue.png");
               break;


       }
        
    }
    
    
    public void canviarFons(String ruta){
        
        panelColor.carregarImatge(ruta, 700, 400);
       
         
    }
    
    
    
    public void jugar(){
        
        ControlText gj = new ControlText();
        gj.iniciarPartida();
      
    }
    
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaSwing();
            }
        });
    } 
    

}
