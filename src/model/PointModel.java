package model;

import java.awt.*;

public class PointModel {

    public Point point;
    public int x;
    public int y;

    public PointModel(Point p) {

        this.point =p;
        x=p.x;
        y=p.y;
    }

    public PointModel(int x, int y){
        this.point =new Point(x,y);
        this.x=x;
        this.y=y;
    }

    public Point getScaledLocation(double scale){
        return new Point((int) (x*scale), (int) (y*scale));
    }


}
