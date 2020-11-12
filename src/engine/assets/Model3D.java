package engine.assets;

import java.util.ArrayList;

public class Model3D {
    private ArrayList<Face3D> faces = new ArrayList<Face3D>();

    public Model3D(ArrayList<Face3D> faces) {
        this.faces.addAll(faces);
    }

    public Model3D(Model3D model3D){
        for (Face3D face: model3D.faces) {
            this.faces.add(new Face3D(face));
        }
    }

    public void multiplyByMatrix(Matrix leftMatrix,Matrix rightMatrix){
        for(Face3D face3D : faces){
            face3D.multiplyByMatrix(leftMatrix,rightMatrix);
        }
    }

    public ArrayList<Face3D> getFaces() {
        return faces;
    }

    public ArrayList<Face3D> copyFaces(){
        ArrayList<Face3D> result = new ArrayList<>();
        for(Face3D face : faces){
            result.add(new Face3D(face));
        }
        return result;

    }

    @Override
    public String toString() {
        return "Model3D{" +
                "faces=" + faces +
                '}';
    }
}
