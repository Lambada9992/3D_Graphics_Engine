package com.company;

import engine.ModelsLoader;
import engine.Scene;
import engine.VirtualCamera;

public class Main {
    static final int Height = 800;
    static final int Width = 800;

    public static void main(String[] args) {

        Scene scene = new Scene();
        ModelsLoader.loadModels(scene,"files_to_load.txt");

        VirtualCamera virtualCamera = new VirtualCamera(scene);


    }
}
