package model;

import ViewParts.PointBtn;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineModel {
    Line2D.Double lineScaled;

    PointModel pointA;
    PointModel pointB;
    BasicStroke stroke;

  public  PointBtn pointBtnA;
  public  PointBtn pointBtnB;

  public  static boolean started=false;
    public static boolean finished=false;
    static boolean deletePrevious=false;
    static Point tempPa;
    static LineModel tempLine;
    static LayerDrawingsModel modelTemp;
    public static void preparePointLine(Point p,LayerDrawingsModel model){
             if(started){
                 started=false;
                 finished=true;
                 deletePrevious=false;

                return;

             }

             tempPa=p;
             finished=false;
             modelTemp=model;
             started=true;
    }
    public static void drawTempLine(Point b){
                   if(!started){return;}
                   if(deletePrevious){
                       modelTemp.removeLineModel(tempLine);

                   }

               tempLine=new LineModel(new PointModel(tempPa),new PointModel(b));
               modelTemp.addLinesModel(tempLine);
               deletePrevious=true;
    }


    public LineModel(PointModel pointA, PointModel pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        pointBtnA =new PointBtn(pointA,25);
        pointBtnA.setBackground(null);
        pointBtnB =new PointBtn(pointB,25);
        pointBtnB.setBackground(null);

    }

    public LineModel(PointModel pointA, PointModel pointB, BasicStroke stroke) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.stroke = stroke;

    }

    public Line2D.Double getLineScaled(double scale) {


        return new Line2D.Double(
                pointA.x*scale,pointA.y*scale,pointB.x*scale,pointB.y*scale);

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
