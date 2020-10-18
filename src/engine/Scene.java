package engine;

import engine.assets.Model3D;

import java.util.ArrayList;

public class Scene {
    ArrayList<Model3D> models = new ArrayList<>();

    public void addModel(Model3D model){
        synchronized (model) {
            models.add(model);
        }
    }
}
