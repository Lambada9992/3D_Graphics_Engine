package engine.assets;

public class Point3D {
    double x,y,z;
    double w;

    public Point3D(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 1;
    }

    public Point3D(Point3D point){
        this.x = point.x;
        this.y = point.y;
        this.z = point.z;
        this.w = point.w;
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public void normalize(){
        x /=w;
        y /=w;
        z /=w;
        w /=w;
    }

    public double[][] getMatrix(){
        double[][] matrix = {
                {x},
                {y},
                {z},
                {w}
        };
        return matrix;
    }

    public void setFromMatrix(double[][] matrix){
        this.x = matrix[0][0];
        this.y = matrix[1][0];
        this.z = matrix[2][0];
        this.w = matrix[3][0];
        if(w!=1)normalize();
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
