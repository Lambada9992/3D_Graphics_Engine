package engine.assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Face3D {
    Double xMax=null,xMin=null,yMax=null,yMin=null;
    Color color = null;
    ArrayList<Point3D> points = new ArrayList<>();

    public Color getColor(){
        if(color==null){
            Random rand = new Random();
            color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
        }
        return color;
    }

    public void addPoint(Point3D point){
        points.add(point);
        setMaxMin();
    }

    private void setMaxMin(){
        for(Point3D point : points){
            if(xMax == null || xMin==null || yMax == null || yMin==null){
                xMax = point.x;
                xMin = point.x;
                yMax = point.y;
                yMin = point.y;
            }
            if(xMax<point.x){xMax = point.x;}
            if(xMin>point.x){xMin = point.x;}
            if(yMax<point.y){yMax = point.y;}
            if(yMin>point.y){xMin = point.y;}
        }
    }

    public Face3D(){

    }

    public ArrayList<Point3D> getPoints() {
        return points;
    }

    public Face3D(Face3D face3D){
        for(Point3D point3D : face3D.points){
            this.points.add(new Point3D(point3D));
        }
        this.xMin = face3D.xMin;
        this.xMax = face3D.xMax;
        this.yMin = face3D.yMin;
        this.yMax = face3D.yMax;
        this.color = face3D.color;
    }

    public void multiplyByMatrix(Matrix leftMatrix,Matrix rightMatrix){
        if(leftMatrix != null && rightMatrix == null){
            for(Point3D point : points){
                point.setFromMatrix(Matrix.multiply(leftMatrix.getMatrix(),point.getMatrix()));
            }
        }else if(leftMatrix == null && rightMatrix != null){
            for(Point3D point : points){
                point.setFromMatrix(Matrix.multiply(point.getMatrix(),rightMatrix.getMatrix()));
            }
        }
        this.setMaxMin();
    }

    public Double getxMax() {
        return xMax;
    }

    public Double getxMin() {
        return xMin;
    }

    public Double getyMax() {
        return yMax;
    }

    public Double getyMin() {
        return yMin;
    }

    @Override
    public String toString() {
        return '\n' + "Face3D{" +
                "points=" + points +
                '}';
    }
}
