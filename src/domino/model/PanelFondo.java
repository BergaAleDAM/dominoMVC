package domino.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class PanelFondo extends JPanel{

    
    
    private BufferedImage img = null;

    int amplada, altura;

    public PanelFondo() {
        this.setOpaque(false);
    }

    public void carregarImatge(String ruta, int amplada, int altura) {
        this.amplada = amplada;
        this.altura = altura;
        try {
            img = ImageIO.read(new File(ruta));
            
            this.repaint();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, amplada, altura, null);
        super.paintComponent(g);
    }
}
