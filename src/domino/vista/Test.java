package configpanels;

import java.awt.*;
import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Test t = new Test();
                t.createUI();
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    public void createUI() {
        JFrame frame = new JFrame("Panel Test");
        PanelAmbImatge panel = new PanelAmbImatge();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        frame.setPreferredSize(new Dimension(400,500));
        panel.carregarImatge("daus.jpeg", 400, 500);
        JButton boto=new JButton(" test");
        panel.add(boto );
        
        frame.add(panel, BorderLayout.CENTER);
//        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setVisible(true);

    }
}
