package model;

import ViewParts.PointBtn;
import helpers.enums.ToolNamesE;
import helpers.enums.ToolStatesE;
import helpers.improvedToolBox;

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
    static PointModel tempPa;
    static LineModel tempLine;
    static LayerDrawingsModel modelTemp;


    private static boolean pointExits(LayerDrawingsModel layerDrawingsModel,PointModel p){
        for (LineModel ln:layerDrawingsModel.lineModels) {
                  if(ln.pointBtnExits(p)){
                      return true;
                  }
        }
        return false;
    }



    public static void lineBeginImproved(PointModel point,LayerDrawingsModel model){
             modelTemp=model;
             if(improvedToolBox.toolState==ToolStatesE.lineTracking){


                 improvedToolBox.toolState=ToolStatesE.inactive;

                 finishLine(point);

             }else{
                 System.out.println("ln begin model");
                  improvedToolBox.toolState=ToolStatesE.lineTracking;
                  tempPa=point;
             }
    }
    public static void lineMoving(PointModel point){
        if(improvedToolBox.toolState!=ToolStatesE.lineTracking){
            if(improvedToolBox.toolState==ToolStatesE.inactive){
                if(tempLine!=null){
                    if(modelTemp==null){return;}
                    modelTemp.removeLineModel(tempLine);
                }

            }
            return;
        }
        if(tempLine!=null){
             modelTemp.removeLineModel(tempLine);
        }
        tempLine=new LineModel(tempPa,point);
        modelTemp.addLinesModel(tempLine);
    }

    public static void finishLine(PointModel pb){
        LineModel ln=new LineModel(tempPa,pb);

           improvedToolBox.toolState=ToolStatesE.inactive;
        System.out.println("finish line");

        modelTemp.addLinesModel(ln);
        modelTemp.removeLineModel(tempLine);
        tempLine=null;

    }

    static int pointIndex=0;
    static PointModel[] lnTemp= new PointModel[2];
    public static void lnAddPoint(PointModel p){
        if(improvedToolBox.toolState== ToolStatesE.lineBegin||improvedToolBox.toolState==ToolStatesE.lineClickDot){

        }
       else if(improvedToolBox.toolState==ToolStatesE.lineTracking){

        }
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

    public boolean pointBtnExits(PointModel point){
           if(pointBtnA.isSamePoint(point)){
               return true;
           }else if(pointBtnB.isSamePoint(point)){
               return true;
           }else{return false;}

    }

    public void resetPointBtnsLocation(){
        pointBtnA.resetLocation();
        pointBtnB.resetLocation();
    }

}
