package engine;

import engine.assets.Algorithms;
import engine.assets.Face3D;
import engine.assets.Matrix;
import engine.assets.Model3D;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class VirtualCamera {
    Scene scene;
    Matrix matrix = new Matrix(Matrix.identityMatrix());
    Double distance = new Double(400);
    int width,height;

    BufferedImage bufferedImage;
    Graphics2D graphicTool;

    public VirtualCamera(Scene scene,int width,int height) {
        this.scene = scene;
        this.width = width;
        this.height = height;
        bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
        graphicTool = (Graphics2D) bufferedImage.getGraphics();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void update(){
        ArrayList<Model3D> models = scene.copyModels();
        ArrayList<Face3D> faces = new ArrayList<>();
        ArrayList<Face3D> facesAfterPerspectiveProjection = new ArrayList<>();
        Matrix matrixCopy;
        Double distanceCopy;

        synchronized (matrix){
            matrixCopy = new Matrix(matrix);
        }
        synchronized (distance){
            distanceCopy = new Double(distance);
        }




        for(Model3D model : models){
            model.multiplyByMatrix(matrixCopy,null);
            Algorithms.facesNormalizationAndDeletion(model);
            faces.addAll(model.copyFaces());
            model.multiplyByMatrix(new Matrix(Matrix.perspectiveProjectionMatrix(distanceCopy)),null);
            facesAfterPerspectiveProjection.addAll(model.copyFaces());
        }


        //usuwanie z poza ekranu
        Algorithms.facesOnView(width,height,faces,facesAfterPerspectiveProjection);


        //rysowanie
        long start = System.currentTimeMillis();
        Algorithms.drawImage(width,height,distanceCopy,graphicTool,faces,facesAfterPerspectiveProjection);
        System.out.println('\t' + "Image drawing time: " +(System.currentTimeMillis()-start));

        //TODO to delete rotation of model
//        matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,0,-2000),matrix.getMatrix()));
//        matrix.setMatrix(Matrix.multiply(Matrix.rotationYMatrix(Math.PI/16),matrix.getMatrix()));
//        matrix.setMatrix(Matrix.multiply(Matrix.rotationXMatrix(Math.PI/16),matrix.getMatrix()));
//        matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,0,2000),matrix.getMatrix()));

    }

    public Matrix getMatrix(){
        return matrix;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }
}
