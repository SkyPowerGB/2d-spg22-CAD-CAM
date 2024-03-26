package model;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineModel {
    Line2D.Double line;

    PointModel pointA;
    PointModel pointB;
    BasicStroke stroke;

    public boolean PointsOfLine(PointModel Ap, PointModel Bp){
        if(Ap==pointA&&Bp==pointB){return true;}
        return false;
    }

    public PointModel getPointA() {
        return pointA;
    }



    public void setPointA(PointModel pointA) {
        this.pointA = pointA;
    }

    public PointModel getPointB() {
        return pointB;
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
