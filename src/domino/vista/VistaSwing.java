package domino.vista;

import domino.control.ControlText;
import domino.model.Joc;
import domino.model.PanelFondo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.IconUIResource;

public class VistaSwing extends JFrame implements ActionListener {
    
    Joc joc;
    
    int uiTorn = 1;

    private JMenuBar menuBar;
    private JMenu menuImatge, menuColor;
    private JMenuItem menuItemJugar, menuItemColorDefecte, menuItemColorBlau, menuItemColorRustic;

    private JLabel   labelNorte, labelEste, labelOeste,labeltab,usuario;
    private JPanel  jugNorte, jugSur, jugEste, jugOeste,tablero;
    private PanelFondo panelColor;

    private BufferedImage img = null;
    
    
    
    public VistaSwing() throws HeadlessException {

        configuracio();
        this.pack();
        this.setVisible(true);
    }

    private void configuracio() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(0, 0);
        this.setPreferredSize(new Dimension(1460, 1000));
        
        //this.setLocationRelativeTo(null);

        menuItemJugar = new JMenuItem("Jugar");
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

        this.labelNorte = new JLabel("jugNorte");
        //this.labelSur = new JLabel("jugSur");
        this.labelEste = new JLabel("jugEste");
        this.labelOeste = new JLabel("jugOeste");
        this.labeltab = new JLabel("tablerito");
        //this.labelColor = new JLabel("Color", JLabel.CENTER);
        //this.labelColor.setOpaque(true);

        panelColor = new PanelFondo();
        jugNorte = new JPanel();
        jugSur = new JPanel();
        jugEste = new JPanel();
        jugEste.setLayout(new BoxLayout(jugEste, BoxLayout.Y_AXIS));
        jugOeste = new JPanel();
        jugOeste.setLayout(new BoxLayout(jugOeste, BoxLayout.Y_AXIS));
        tablero = new JPanel();

        
        this.getContentPane().add(this.panelColor, BorderLayout.CENTER);
        //this.getContentPane().add(this.panelJuego, BorderLayout.SOUTH);
        
        this.getContentPane().add(this.jugSur, BorderLayout.SOUTH);
        jugSur.setPreferredSize(new Dimension(150,80));
        //jugSur.add(labelSur);
        
        
        
        this.getContentPane().add(this.jugNorte, BorderLayout.NORTH);
        jugNorte.add(labelNorte);
        
        this.getContentPane().add(this.jugEste, BorderLayout.EAST);
        jugEste.add(labelEste);
        
        this.getContentPane().add(this.jugOeste, BorderLayout.WEST);
        jugOeste.add(labelOeste);
        
        panelColor.add(this.tablero, BorderLayout.CENTER);
        tablero.add(labeltab);
        
        canviarFons("fichasDomino/tapete.png");
        
        
    }

    //JFIleChoose para escojer el tipico archivo que te pone
    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();
        switch (actionCommand) {

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

    public void canviarFons(String ruta) {

        panelColor.carregarImatge(ruta, 1280, 900);

    }

    public void jugar() {

        ControlText gj = new ControlText(this);
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
    
    public String pedirNombre(int i){
        return JOptionPane.showInputDialog(null, "Pon el nombre del jugador", "Nombre Jugador", 1);

    }

    public void mostrarJugador(int torn) throws IOException {
        
        this.jugSur.add(new JLabel("Torn " + (uiTorn) + "\tJugador: " + joc.getJugadors()[torn].getNom()),LEFT_ALIGNMENT);
        
        for (int i = 0; i < joc.getJugadors()[torn].getFitxes().size(); i++) {
            
            
            Image img = ImageIO.read(convertirFicha(i,torn));
            
            this.jugSur.add(new JButton(new ImageIcon(img)));
        }
        
        this.setVisible(true);
        uiTorn++;
    
        
        ArrayList<Integer> turno = new ArrayList<>();

        turno.add(torn);
        turno.add(mostrarAltresJugadors(turno,jugOeste));
        turno.add(mostrarAltresJugadors(turno,jugNorte));
        turno.add(mostrarAltresJugadors(turno,jugEste));
        
    }

    public Joc getJoc() {
        return joc;
    }

    public void setJoc(Joc joc) {
        this.joc = joc;
    }

    private File convertirFicha(int i,int torn) {
    
        StringBuilder sb = new StringBuilder();
        String ruta;
        
        sb.append("fichasDomino/");
        sb.append(joc.getJugadors()[torn].getFitxes().get(i).getPrimerValor());
        sb.append(joc.getJugadors()[torn].getFitxes().get(i).getSegonValor());
        sb.append(".png");
        ruta = sb.toString();

        
        return new File(ruta);
    }

    private int mostrarAltresJugadors(ArrayList<Integer> turno, JPanel panel) throws IOException {
    
       int devuelve = 0;
        
        for (int i = 0; i < joc.getJugadors().length; i++) {
            
            if(!turno.contains(i)){
            
            panel.add(new JLabel("Jugador: " + joc.getJugadors()[i].getNom()));
            
            Image img = ImageIO.read(new File("fichasDomino/cruz.png"));
            
            panel.add(new JButton(new ImageIcon(img)));
            devuelve = i;
            
            break;
            }
        }
        
        this.setVisible(true);
        System.out.println(devuelve);
        return devuelve;
    }

    
    

}
