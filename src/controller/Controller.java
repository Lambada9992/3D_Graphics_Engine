package controller;

import engine.VirtualCamera;
import engine.assets.Matrix;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    VirtualCamera virtualCamera;
    Matrix matrix;
    double step;
    double angle;

    public Controller(VirtualCamera virtualCamera,double step,double angle) {
        this.virtualCamera = virtualCamera;
        matrix = virtualCamera.getMatrix();
        this.step = step;
        this.angle = angle;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'w','W':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,0,-step),matrix.getMatrix()));
                }
                break;
            case 's','S':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,0,step),matrix.getMatrix()));
                }
                break;
            case 'a','A':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(step,0,0),matrix.getMatrix()));
                }
                break;
            case 'd','D':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(-step,0,0),matrix.getMatrix()));
                }
                break;
            case 'q','Q':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,-step,0),matrix.getMatrix()));
                }
                break;
            case 'e','E':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,step,0),matrix.getMatrix()));
                }
                break;
            case 'i','I':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationXMatrix(-angle),matrix.getMatrix()));
                }
                break;
            case 'k','K':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationXMatrix(angle),matrix.getMatrix()));
                }
                break;
            case 'j','J':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationYMatrix(angle),matrix.getMatrix()));
                }
                break;
            case 'l','L':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationYMatrix(-angle),matrix.getMatrix()));
                }
                break;
            case 'u','U':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationZMatrix(angle),matrix.getMatrix()));
                }
                break;
            case 'o','O':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationZMatrix(-angle),matrix.getMatrix()));
                }
                break;
            case 't','T':
                synchronized (virtualCamera.getDistance()) {
                    if (virtualCamera.getDistance() - step > 0) {
                        virtualCamera.setDistance(virtualCamera.getDistance()-step);
                    }
                }
                break;
            case 'g','G':
                synchronized (virtualCamera.getDistance()) {
                    virtualCamera.setDistance(virtualCamera.getDistance()+step);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
