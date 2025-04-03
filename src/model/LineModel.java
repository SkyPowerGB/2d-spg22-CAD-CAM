package model;

import View.ViewUIComponents.UIPointBtn;
import helpers.enums.ToolStatesE;
import helpers.ToolTracker;

import java.awt.geom.Line2D;

public class LineModel {

    PointModel pointA;
    PointModel pointB;


  public UIPointBtn UIPointBtnA;
  public UIPointBtn UIPointBtnB;


    static PointModel tempPa;
    static LineModel tempLine;
    static LayerDrawingsModel modelTemp;





    public static void lineBeginImproved(PointModel point,LayerDrawingsModel model){
             modelTemp=model;
             if(ToolTracker.toolState==ToolStatesE.lineTracking){


                 ToolTracker.toolState=ToolStatesE.inactive;

                 finishLine(point);

             }else{
                 System.out.println("ln begin model");
                  ToolTracker.toolState=ToolStatesE.lineTracking;
                  tempPa=point;
             }
    }
    public static void lineMoving(PointModel point){
        if(ToolTracker.toolState!=ToolStatesE.lineTracking){
            if(ToolTracker.toolState==ToolStatesE.inactive){
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

           ToolTracker.toolState=ToolStatesE.inactive;
        System.out.println("finish line");

        modelTemp.addLinesModel(ln);
        modelTemp.removeLineModel(tempLine);
        tempLine=null;

    }

    public LineModel(PointModel pointA, PointModel pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
        UIPointBtnA =new UIPointBtn(pointA,25);
        UIPointBtnA.setBackground(null);
        UIPointBtnB =new UIPointBtn(pointB,25);
        UIPointBtnB.setBackground(null);

    }

    public Line2D.Double getLineScaled(double scale) {


        return new Line2D.Double(
                pointA.x*scale,pointA.y*scale,pointB.x*scale,pointB.y*scale);

    }

    public void resetPointBtnsLocation(){
        UIPointBtnA.resetLocation();
        UIPointBtnB.resetLocation();
    }

}
