package engine.assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Algorithms {
    private static final int nThreads = 4;
    private static final Color bg_color = Color.WHITE;
    private static final int numberOfThreads = 8;

    public static void facesNormalizationAndDeletion(Model3D model){
        ArrayList<Face3D> facesToAdd = new ArrayList<>();
        ArrayList<Face3D> facesToRemove = new ArrayList<>();

        for (Face3D face : model.getFaces()){
            ArrayList<Point3D> pointsBehindeScene = new ArrayList<Point3D>();
            ArrayList<Point3D> frontPoints = new ArrayList<Point3D>();
            for(Point3D point : face.points){
                if(point.z<0){
                    pointsBehindeScene.add(point);
                }else{
                    frontPoints.add(point);
                }
            }
            double x1,y1,z1,x2,y2,z2;
            double t1,t2;
            switch (pointsBehindeScene.size()){
                case 1:
                    x1 = frontPoints.get(0).x - pointsBehindeScene.get(0).x;
                    y1 = frontPoints.get(0).y - pointsBehindeScene.get(0).y;
                    z1 = frontPoints.get(0).z - pointsBehindeScene.get(0).z;

                    x2 = frontPoints.get(1).x - pointsBehindeScene.get(0).x;
                    y2 = frontPoints.get(1).y - pointsBehindeScene.get(0).y;
                    z2 = frontPoints.get(1).z - pointsBehindeScene.get(0).z;

                    t1 = pointsBehindeScene.get(0).z/z1;
                    t2 = pointsBehindeScene.get(0).z/z2;

                    Point3D p1 = new Point3D(),p2 = new Point3D(),middlePoint = new Point3D();
                    p1.x = pointsBehindeScene.get(0).x - x1*t1;
                    p1.y = pointsBehindeScene.get(0).y - y1*t1;
                    p1.z = pointsBehindeScene.get(0).z - z1*t1;

                    p2.x = pointsBehindeScene.get(0).x - x2*t2;
                    p2.y = pointsBehindeScene.get(0).y - y2*t2;
                    p2.z = pointsBehindeScene.get(0).z - z2*t2;

                    middlePoint.x = (frontPoints.get(0).x + frontPoints.get(1).x)/2.0;
                    middlePoint.y = (frontPoints.get(0).y + frontPoints.get(1).y)/2.0;
                    middlePoint.z = (frontPoints.get(0).z + frontPoints.get(1).z)/2.0;


                    Face3D face1 = new Face3D();
                    face1.color = face.getColor();
                    face1.addPoint(frontPoints.get(0));
                    face1.addPoint(p1);
                    face1.addPoint(middlePoint);
                    facesToAdd.add(face1);

                    Face3D face2 = new Face3D();
                    face2.color = face.getColor();
                    face2.addPoint(frontPoints.get(1));
                    face2.addPoint(p2);
                    face2.addPoint(middlePoint);
                    facesToAdd.add(face2);

                    Face3D face3 = new Face3D();
                    face3.color = face.getColor();
                    face3.addPoint(p2);
                    face3.addPoint(p1);
                    face3.addPoint(middlePoint);
                    facesToAdd.add(face3);

                    facesToRemove.add(face);

                    break;
                case 2:
                    x1 = frontPoints.get(0).x - pointsBehindeScene.get(0).x;
                    y1 = frontPoints.get(0).y - pointsBehindeScene.get(0).y;
                    z1 = frontPoints.get(0).z - pointsBehindeScene.get(0).z;

                    x2 = frontPoints.get(0).x - pointsBehindeScene.get(1).x;
                    y2 = frontPoints.get(0).y - pointsBehindeScene.get(1).y;
                    z2 = frontPoints.get(0).z - pointsBehindeScene.get(1).z;

                    t1 = pointsBehindeScene.get(0).z/z1;
                    t2 = pointsBehindeScene.get(1).z/z2;

                    pointsBehindeScene.get(0).x = pointsBehindeScene.get(0).x - x1*t1;
                    pointsBehindeScene.get(0).y = pointsBehindeScene.get(0).y - y1*t1;
                    pointsBehindeScene.get(0).z = pointsBehindeScene.get(0).z - z1*t1;

                    pointsBehindeScene.get(1).x = pointsBehindeScene.get(1).x - x2*t2;
                    pointsBehindeScene.get(1).y = pointsBehindeScene.get(1).y - y2*t2;
                    pointsBehindeScene.get(1).z = pointsBehindeScene.get(1).z - z2*t2;
                    break;
                case 3:
                    facesToRemove.add(face);
                    break;
                default:
                    continue;
            }
        }
        model.getFaces().addAll(facesToAdd);
        model.getFaces().removeAll(facesToRemove);
    }
    public static void facesOnView(int width,int height,ArrayList<Face3D> faces,ArrayList<Face3D> facesAfterPerspectiveProjection){
        if(faces.size()!=facesAfterPerspectiveProjection.size())return;

        ArrayList<Face3D> facesToDelete = new ArrayList<>();

        for(int i = 0;i<facesAfterPerspectiveProjection.size();i++){
            Face3D face = facesAfterPerspectiveProjection.get(i);

            if(!(face.getxMax()>=-width/2.0 || face.getxMin()<=width/2.0 &&
            face.getyMax()>=-height/2.0 || face.getyMin()<=height/2.0)){
                facesToDelete.add(face);
                facesToDelete.add(faces.get(i));
            }
        }

        faces.removeAll(facesToDelete);
        facesAfterPerspectiveProjection.removeAll(facesToDelete);

    }
    public static void drawImage(int width,int height,double distane,Graphics2D graphicsTool,
                                 ArrayList<Face3D> faces,ArrayList<Face3D> projectedFaces){


        int image[][] = new int[height][width];

//        for(int i = -height/2 ; i < height/2 ; i++){
//            for(int j=-width/2;j<width/2;j++){
//
//                ArrayList<Integer> facesOnPixel = new ArrayList<Integer>();
//
//                for(int iterator = 0;iterator<projectedFaces.size();iterator++ ){
//                    if(isPointInsideFace(projectedFaces.get(iterator),j,i)){
//                        facesOnPixel.add(iterator);
//                    }
//                }
//
//
//                if(facesOnPixel.size()==0){
//                    image[i+height/2][j+width/2] = -1;
//                }else if(facesOnPixel.size() == 1){
//                    image[i+height/2][j+width/2] = facesOnPixel.get(0);
//                }else{
//                    image[i+height/2][j+width/2] = choosingClosestFace(j,i,distane,facesOnPixel,faces,projectedFaces);
//                }
//            }
//
//        }

        //////////////////////
        ArrayList<Thread> threads = new ArrayList<>();
        AtomicReference<Integer> index = new AtomicReference<>(0);
        int sqrtThreadAmount = ((int) Math.sqrt(numberOfThreads));//number that can by sqrt

        for(int threadIterator = 0;threadIterator<sqrtThreadAmount*sqrtThreadAmount;threadIterator++){
            threads.add(new Thread(()->{
                Integer threadIndex;
                synchronized (index){
                    threadIndex = index.get();
                    index.set(index.get()+1);
                }
                //////

                int y = threadIndex/sqrtThreadAmount;
                int x = threadIndex - y*sqrtThreadAmount;

                int istart = y*(height/sqrtThreadAmount);
                int iend = ((y+1)*(height/sqrtThreadAmount))-1;
                int jstart = x*(width/sqrtThreadAmount);
                int jend = ((x+1)*(width/sqrtThreadAmount))-1;

                for(int i = istart-height/2 ; i <= iend - height/2 ; i++){
                    for(int j=jstart - width/2;j<= jend - width/2;j++){

                        ArrayList<Integer> facesOnPixel = new ArrayList<Integer>();

                        for(int iterator = 0;iterator<projectedFaces.size();iterator++ ){
                            if(isPointInsideFace(projectedFaces.get(iterator),j,i)){
                                facesOnPixel.add(iterator);
                            }
                        }


                        if(facesOnPixel.size()==0){
                            image[i+height/2][j+width/2] = -1;
                        }else if(facesOnPixel.size() == 1){
                            image[i+height/2][j+width/2] = facesOnPixel.get(0);
                        }else{
                            image[i+height/2][j+width/2] = choosingClosestFace(j,i,distane,facesOnPixel,faces,projectedFaces);
                        }
                    }

                }

            }));
            threads.get(threadIterator).start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //////////////////////
        for(int i = 0;i<image.length;i++){
            graphicsTool.setColor(bg_color);
            int previousJIndex = 0;
            for(int j = 0;j<image[0].length;j++){
                Color courentColor;
                if(image[i][j] == -1){
                    courentColor = bg_color;
                }else{
                    courentColor = faces.get(image[i][j]).getColor();
                }

                if(j == 0){
                    graphicsTool.setColor(courentColor);
                }else if(j==image[0].length-1){
                    graphicsTool.drawLine(previousJIndex,i,j,i);
                } else {
                    if(courentColor!=graphicsTool.getColor()){
                        graphicsTool.drawLine(previousJIndex,i,j-1,i);
                        previousJIndex = j;
                        graphicsTool.setColor(courentColor);
                    }
                }

            }
        }

    }
    private static double area(double x1,double y1,double x2,double y2,double x3,double y3){
        return 0.5 * Math.abs((x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2)));
    }

    private static double sign (Point3D p1, Point3D p2, Point3D p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }


    private static boolean isPointInsideFace(Face3D face,int x,int y){
//        double T = area(face.getPoints().get(0).x, face.getPoints().get(0).y, face.getPoints().get(1).x, face.getPoints().get(1).y, face.getPoints().get(2).x, face.getPoints().get(2).y);
//        double T1 = area(x, y, face.getPoints().get(1).x, face.getPoints().get(1).y, face.getPoints().get(2).x, face.getPoints().get(2).y);
//        double T2 = area(face.getPoints().get(0).x, face.getPoints().get(0).y, x, y, face.getPoints().get(2).x, face.getPoints().get(2).y);
//        double T3 = area(face.getPoints().get(0).x, face.getPoints().get(0).y, face.getPoints().get(1).x, face.getPoints().get(1).y, x, y);
//        return (T >= T1 + T2 + T3);

        //https://mathworld.wolfram.com/TriangleInterior.html

        //Point3D p0 = face.getPoints().get(0),p1= face.getPoints().get(1),p2= face.getPoints().get(2);



//        double a = ((x*p2.y - y*p2.x)-(p0.x*p2.y-p0.y*p2.x))
//                /(p1.x*p2.y - p2.x*p1.y);
//
//        double b = -1*(((x*p1.y - y*p1.x)-(p0.x*p1.y-p0.y*p1.x))
//                /(p1.x*p2.y - p2.x*p1.y));
//        return (a>0 && b>0 && a+b<1);

        double d1, d2, d3;
        boolean has_neg, has_pos;
        Point3D pt = new Point3D(x,y,0);

        d1 = sign(pt, face.getPoints().get(0), face.getPoints().get(1));
        d2 = sign(pt, face.getPoints().get(1), face.getPoints().get(2));
        d3 = sign(pt, face.getPoints().get(2), face.getPoints().get(0));

        has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
        has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

        return !(has_neg && has_pos);

    }

    private static int choosingClosestFace(int x,int y,double z,ArrayList<Integer> facesOnPixel,ArrayList<Face3D> faces,ArrayList<Face3D> projectedFaces){
        Double distance = Double.valueOf(-1);
        int result =-1 ;

        for(int i = 0;i<facesOnPixel.size();i++){
            int faceIndex = facesOnPixel.get(i);
            //normal vector of suface
            double A = ((faces.get(faceIndex).points.get(1).y-faces.get(faceIndex).points.get(0).y)*(faces.get(faceIndex).points.get(2).z-faces.get(faceIndex).points.get(0).z))
                    -((faces.get(faceIndex).points.get(1).z-faces.get(faceIndex).points.get(0).z)*(faces.get(faceIndex).points.get(2).y-faces.get(faceIndex).points.get(0).y));
            double B =-1*(((faces.get(faceIndex).points.get(1).x-faces.get(faceIndex).points.get(0).x)*(faces.get(faceIndex).points.get(2).z-faces.get(faceIndex).points.get(0).z))
                    -((faces.get(faceIndex).points.get(1).z-faces.get(faceIndex).points.get(0).z)*(faces.get(faceIndex).points.get(2).x-faces.get(faceIndex).points.get(0).x)));
            double C = ((faces.get(faceIndex).points.get(1).x-faces.get(faceIndex).points.get(0).x)*(faces.get(faceIndex).points.get(2).y-faces.get(faceIndex).points.get(0).y))
                    -((faces.get(faceIndex).points.get(1).y-faces.get(faceIndex).points.get(0).y)*(faces.get(faceIndex).points.get(2).x-faces.get(faceIndex).points.get(0).x));

            double T = (A*faces.get(faceIndex).points.get(0).x + B*faces.get(faceIndex).points.get(0).y + C*faces.get(faceIndex).points.get(0).z + C*z)
                    /(A*x + B*y + C*z);

             double xCoordinate = x * T;
             double yCoordinate = y * T;
             double zCoordinate = -z + T*z;

             //double distanceOfPoint = Math.pow(xCoordinate,2) + Math.pow(yCoordinate,2) + Math.pow(zCoordinate+z,2);
            double distanceOfPoint = xCoordinate*xCoordinate + yCoordinate*yCoordinate + (T)*(T);


             if(result==-1){
                 distance = distanceOfPoint;
                 result = faceIndex;
             }else if(distanceOfPoint < distance){
                 distance = distanceOfPoint;
                 result = faceIndex;
             }



        }


        return result;

    }

}
