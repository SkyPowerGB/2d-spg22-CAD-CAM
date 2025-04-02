package model;

import ViewParts.PointBtn;
import helpers.enums.ToolNamesE;
import helpers.enums.ToolStatesE;
import helpers.improvedToolBox;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineModel {

    PointModel pointA;
    PointModel pointB;


  public  PointBtn pointBtnA;
  public  PointBtn pointBtnB;


    static PointModel tempPa;
    static LineModel tempLine;
    static LayerDrawingsModel modelTemp;





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





    public LineModel(PointModel pointA, PointModel pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        pointBtnA =new PointBtn(pointA,25);
        pointBtnA.setBackground(null);
        pointBtnB =new PointBtn(pointB,25);
        pointBtnB.setBackground(null);

    }



    public Line2D.Double getLineScaled(double scale) {


        return new Line2D.Double(
                pointA.x*scale,pointA.y*scale,pointB.x*scale,pointB.y*scale);

    }





    public void resetPointBtnsLocation(){
        pointBtnA.resetLocation();
        pointBtnB.resetLocation();
    }

}
