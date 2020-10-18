package gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    String title;

    public Window(String title) throws HeadlessException {
        super(title);
        this.title = title;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
