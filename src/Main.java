import engine.ModelsLoader;
import engine.Scene;
import engine.VirtualCamera;
import gui.ViewPanel;
import gui.Window;
import controller.Controller;

import java.awt.*;

public class Main {
    static final int Height = 800;
    static final int Width = 800;
    static final String Title = "Program";

    public static void main(String[] args) {

        Scene scene = new Scene();
        ModelsLoader.loadModels(scene,"files_to_load.txt");


        VirtualCamera virtualCamera = new VirtualCamera(scene,Height,Width);

        Controller controller = new Controller(virtualCamera, 10.0d,Math.PI/32);
        //


        Window window = new Window(Title);
        ViewPanel viewPanel = new ViewPanel(virtualCamera.getBufferedImage());
        window.add(viewPanel);
        window.addKeyListener(controller);
        //window.pack();
        //window.setSize(Width+50,Height+50);
        window.show();


        while (true){
            long start = System.currentTimeMillis();

            virtualCamera.update();
            viewPanel.repaint();

            System.out.println("Time: "+(System.currentTimeMillis()-start)+ " ms");
        }

    }
}
