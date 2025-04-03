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


    public void setNewLocation(PointModel p){

        this.point=p.point;
        this.x= point.x;
        this.y= point.y;

    }






}
