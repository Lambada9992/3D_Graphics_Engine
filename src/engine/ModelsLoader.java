package engine;

import engine.assets.Face3D;
import engine.assets.Model3D;
import engine.assets.Point3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModelsLoader{

    static void objInterpreter(ArrayList<Point3D> vertices,ArrayList<Face3D> faces,String lineToInterprete){
        String[] stringArray = lineToInterprete.split(" ");
        switch (stringArray[0]){
            case "v":
                vertices.add(new Point3D(Double.parseDouble(stringArray[1]),Double.parseDouble(stringArray[2]),Double.parseDouble(stringArray[3])));
                break;
            case "f":
                Face3D face = new Face3D();
                for(int i = 1;i<stringArray.length;i++){
                    face.addPoint(vertices.get(Integer.parseInt(stringArray[i].split("/")[0])-1));
                }
                faces.add(face);
                break;
            default:
                break;
        }
    }

    static void loadModel(Scene scene,String filename){
        File file = new File(filename);
        if(!file.exists())return;

        ArrayList<Point3D> vertices = new ArrayList<>();
        ArrayList<Face3D> faces = new ArrayList<>();


        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()){
                objInterpreter(vertices,faces,reader.nextLine());
            }
            Model3D model = new Model3D(faces);
            scene.addModel(model);
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public static void loadModels(Scene scene,String filesToLoad_File){
        ArrayList<String> listOfFiles= new ArrayList<String>();
        ArrayList<Thread> threads = new ArrayList<Thread>();

        File file = new File(filesToLoad_File);
        if(!file.exists())return;
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNext()){
                String filename = reader.nextLine();
                Thread thread = new Thread(()->{
                    loadModel(scene,filename);
                });

                thread.start();
                threads.add(thread);
            }
            for(Thread thread : threads){
                try {
                    thread.join();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }
}
