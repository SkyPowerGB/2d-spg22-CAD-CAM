package model;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineModel {
    Line2D.Double line;

    PointModel pointA;
    PointModel pointB;
    BasicStroke stroke;

    public Line2D.Double getLine() {
        return line;
    }

    public LineModel(PointModel pointA, PointModel pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        line=new Line2D.Double(pointA.x,pointA.y,pointB.x,pointB.y);
    }

    public LineModel(PointModel pointA, PointModel pointB, BasicStroke stroke) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.stroke = stroke;
        line=new Line2D.Double(pointA.x,pointA.y,pointB.x,pointB.y);
    }

    public boolean PointsOfLine(PointModel Ap, PointModel Bp){
        if(Ap==pointA&&Bp==pointB){return true;}
        return false;
    }

    public PointModel getPointA() {
        return pointA;
    }
    public PointModel getPointB() {
        return pointB;
    }



    public void setPointA(PointModel pointA) {
        this.pointA = pointA;
    }
    public void setPointB(PointModel pointB) {
        this.pointB = pointB;
    }


    public BasicStroke getStroke() {
        return stroke;
    }
    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }
}
