package model;

import Enums.ToolStatesE;
import helpers.ToolStateTracker;

import java.awt.geom.Line2D;

public class LineModel {

    PointModel pointA;
    PointModel pointB;



    static PointModel tempPa;
    static LineModel tempLine;
    static LayerDrawingsModel modelTemp;


    public static void lineBeginImproved(PointModel point,LayerDrawingsModel model){
             modelTemp=model;
             if(ToolStateTracker.toolState==ToolStatesE.lineTracking){


                 ToolStateTracker.toolState=ToolStatesE.inactive;

                 finishLine(point);

             }else{
                 System.out.println("ln begin model");
                  ToolStateTracker.toolState=ToolStatesE.lineTracking;
                  tempPa=point;
             }
    }
    public static void lineMoving(PointModel point){
        if(ToolStateTracker.toolState!=ToolStatesE.lineTracking){
            if(ToolStateTracker.toolState==ToolStatesE.inactive){
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

        ToolStateTracker.toolState=ToolStatesE.inactive;
        System.out.println("finish line");

        modelTemp.addLinesModel(ln);
        modelTemp.removeLineModel(tempLine);
        tempLine=null;

    }

    public LineModel(PointModel pointA, PointModel pointB) {
        this.pointA = pointA;
        this.pointB = pointB;


    }

    public Line2D.Double getLineScaled(double scale) {


        return new Line2D.Double(
                pointA.x*scale,pointA.y*scale,pointB.x*scale,pointB.y*scale);

    }


}
