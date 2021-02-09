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
            case 'w': case 'W':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,0,-step),matrix.getMatrix()));
                }
                break;
            case 's': case 'S':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,0,step),matrix.getMatrix()));
                }
                break;
            case 'a': case 'A':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(step,0,0),matrix.getMatrix()));
                }
                break;
            case 'd': case 'D':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(-step,0,0),matrix.getMatrix()));
                }
                break;
            case 'q': case 'Q':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,-step,0),matrix.getMatrix()));
                }
                break;
            case 'e': case 'E':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.translationMatrix(0,step,0),matrix.getMatrix()));
                }
                break;
            case 'i': case 'I':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationXMatrix(-angle),matrix.getMatrix()));
                }
                break;
            case 'k': case 'K':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationXMatrix(angle),matrix.getMatrix()));
                }
                break;
            case 'j': case 'J':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationYMatrix(angle),matrix.getMatrix()));
                }
                break;
            case 'l': case 'L':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationYMatrix(-angle),matrix.getMatrix()));
                }
                break;
            case 'u': case 'U':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationZMatrix(angle),matrix.getMatrix()));
                }
                break;
            case 'o': case 'O':
                synchronized (matrix){
                    matrix.setMatrix(Matrix.multiply(Matrix.rotationZMatrix(-angle),matrix.getMatrix()));
                }
                break;
            case 't': case 'T':
                synchronized (virtualCamera.getDistance()) {
                    if (virtualCamera.getDistance() - step > 0) {
                        virtualCamera.setDistance(virtualCamera.getDistance()-step);
                    }
                }
                break;
            case 'g': case 'G':
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
