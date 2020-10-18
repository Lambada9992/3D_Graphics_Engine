package engine.assets;

import java.util.ArrayList;

public class Face3D {
    ArrayList<Point3D> points = new ArrayList<>();

    public void addPoint(Point3D point){
        points.add(point);
    }

    @Override
    public String toString() {
        return '\n' + "Face3D{" +
                "points=" + points +
                '}';
    }
}
