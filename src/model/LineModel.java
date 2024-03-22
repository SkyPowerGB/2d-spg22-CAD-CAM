package model;
import helpers.helperModels.Line;

import java.awt.*;
public class LineModel {
    Point A;
    Point B;
    double angle;
    Line lineHelper;

    public LineModel(Point A , Point B){
        this.A=A;
        this.B=B;
        lineHelper=new Line(A,B);
        double deltaY = B.getY() - A.getY();
        double deltaX = B.getX() - A.getX();
        double angleRad = Math.atan2(deltaY, deltaX);
        angle= Math.toDegrees(angleRad);


        if (angle < 0) {
            angle += 360;
        }



    }

}
