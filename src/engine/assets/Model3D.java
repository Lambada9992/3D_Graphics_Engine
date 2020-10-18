package engine.assets;

import java.util.ArrayList;

public class Model3D {
    ArrayList<Face3D> faces;

    public Model3D(ArrayList<Face3D> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "Model3D{" +
                "faces=" + faces +
                '}';
    }
}
