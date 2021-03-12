package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ViewPanel extends JPanel {
    BufferedImage graphic;

    public ViewPanel(BufferedImage graphic) {
        this.graphic = graphic;
        this.setPreferredSize(new Dimension(graphic.getWidth(),graphic.getHeight()));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLUE);
        g.drawImage(graphic,0,0,this);
    }

}
