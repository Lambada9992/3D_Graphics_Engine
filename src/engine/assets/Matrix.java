package engine.assets;

public class Matrix {
    double[][] matrix;

    public double[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(double[][] matrix){
        this.matrix = matrix;
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(Matrix object){
        matrix = object.getMatrix();
    }

    public static double[][] scaleMatrix(double scale){
        double[][] matrix ={
                {scale,0,0,0},
                {0,scale,0,0},
                {0,0,scale,0},
                {0,0,0,1}
        };

        return matrix;
    }

    public static double[][] identityMatrix(){
        double[][] matrix ={
                {1,0,0,0},
                {0,1,0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        return matrix;
    }
    public static double[][] translationMatrix(double x,double y,double z){
        double[][] matrix ={
                {1,0,0,x},
                {0,1,0,y},
                {0,0,1,z},
                {0,0,0,1}
        };
        return matrix;
    }
    public static double[][] rotationXMatrix(double angle){
        double[][] matrix ={
                {1,0,0,0},
                {0,Math.cos(angle),-Math.sin(angle),0},
                {0,Math.sin(angle),Math.cos(angle),0},
                {0,0,0,1}
        };
        return matrix;
    }
    public static double[][] rotationYMatrix(double angle){
        double[][] matrix ={
                {Math.cos(angle),0,Math.sin(angle),0},
                {0,1,0,0},
                {-Math.sin(angle),0,Math.cos(angle),0},
                {0,0,0,1}
        };
        return matrix;
    }
    public static double[][] rotationZMatrix(double angle){
        double[][] matrix ={
                {Math.cos(angle),-Math.sin(angle),0,0},
                {Math.sin(angle),Math.cos(angle),0,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        return matrix;
    }

    public static double[][] perspectiveProjectionMatrix(double z){
        double[][] matrix ={
                {1,0,0,0},
                {0,1,0,0},
                {0,0,0,0},
                {0,0,1/z,1}
        };
        return matrix;
    }

    public static double[][] multiply(double[][] matrix1,double[][] matrix2){
        double[][] resultMatrix = new double[matrix1.length][matrix2[0].length];

        for(int i = 0;i<matrix1.length;i++){
            for(int j = 0;j<matrix2[0].length;j++){
                double value = 0;
                for(int k=0;k<matrix1[0].length;k++){
                    value += matrix1[i][k] * matrix2[k][j];
                }
                resultMatrix[i][j] = value;
            }
        }

        return resultMatrix;
    }
}
