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

    public ArrayList<Model3D> copyModels(){
        ArrayList<Model3D> result = new ArrayList<>();

        synchronized (models) {
            for (Model3D model : models) {
                result.add(new Model3D(model));
            }
        }
        return result;
    }
}
