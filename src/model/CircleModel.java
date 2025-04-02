package model;
import java.awt.*;

public class CircleModel {
    PointModel center;
    PointModel endPoint;

    boolean fill=false;




    double radius;
    Point startPoint;

    public CircleModel(PointModel center, PointModel endPoint){
        this.center=center;
        this.endPoint=endPoint;
        calculateDiameterAndStartPoint();
    }
    private  void  calculateDiameterAndStartPoint(){
        radius=Math.sqrt(Math.pow(endPoint.x-center.x,2)+Math.pow(endPoint.y-center.y,2));
        startPoint=new Point((int) (center.x-radius), (int) (center.y-radius));
    }

    public boolean isFill() {
        return fill;
    }

    public double getRadius() {
        return radius;
    }

    public Point getStartPoint() {
        return startPoint;
    }
}
