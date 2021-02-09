import engine.ModelsLoader;
import engine.Scene;
import engine.VirtualCamera;
import gui.ViewPanel;
import gui.Window;
import controller.Controller;

public class Main {
    static final int Height = 400;
    static final int Width = 400;
    static final int Scale = 1;
    static final String Title = "Program";

    public static void main(String[] args) {

        Scene scene = new Scene();
        ModelsLoader.loadModels(scene,"files_to_load.txt");

        VirtualCamera virtualCamera = new VirtualCamera(scene,Width,Height,Scale);

        Controller controller = new Controller(virtualCamera, 10.0d,Math.PI/32.0d);

        Window window = new Window(Title,Width*Scale,Height*Scale);
        ViewPanel viewPanel = new ViewPanel(virtualCamera.getBufferedImage());
        window.add(viewPanel);
        window.addKeyListener(controller);
        //window.show();


        long start = System.currentTimeMillis();
        int fps_counter=0;
        while (true){
            virtualCamera.update();
            viewPanel.repaint();
            if(System.currentTimeMillis()-start<1000){
                fps_counter++;
            }else{
                System.out.println(fps_counter + " fps");
                start = System.currentTimeMillis();
                fps_counter = 0;
            }
        }

    }
}
