package gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    String title;

    public Window(String title,int width,int height) throws HeadlessException {
        super(title);
        this.title = title;
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
