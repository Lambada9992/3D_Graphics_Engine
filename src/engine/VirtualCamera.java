package engine;

import engine.assets.Matrix;

public class VirtualCamera {
    Scene scene;
    Matrix matrix = new Matrix(Matrix.identityMatrix());

    public VirtualCamera(Scene scene) {
        this.scene = scene;
    }
}
