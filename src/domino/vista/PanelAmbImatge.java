package configpanels;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class PanelAmbImatge extends JPanel {

    private BufferedImage img = null;

    int amplada, altura;

    public PanelAmbImatge() {
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
