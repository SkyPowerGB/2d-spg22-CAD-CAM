package model;

import ViewParts.PointBtn;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class LayerDrawingsModel {

    ArrayList<LineModel> lineModels;
    ArrayList<TextModel> texts;

    ArrayList<CircleModel> circles;


    public LayerDrawingsModel(){

        lineModels=new ArrayList<>();
        texts=new ArrayList<>();
        circles=new ArrayList<>();

    }

    public ArrayList<TextModel> getTexts() {
        return texts;
    }
    public int addLinesModel(LineModel lineModel){
        lineModels.add(lineModel);
        return lineModels.indexOf(lineModel);
    }
    public LineModel getLineFromPointModel(PointModel pointModel){
            for (LineModel line:lineModels){
                       if(line.pointA==pointModel){
                           return line;
                       }else if(line.pointB==pointModel){
                           return line;
                       }
            }
            return null;
    }
    public ArrayList<LineModel> getLineModels() {
        return lineModels;
    }
    public void removeLineModelAtIndex(int i){lineModels.remove(i);}
    public void removeLineModel(LineModel lineModel){
        lineModels.remove(lineModel);
    }
    public void addTxt(TextModel modelTxt){
        texts.add(modelTxt);
    }

    public ArrayList<CircleModel> getCircles() {
        return circles;
    }

    public void removeCircle(CircleModel circle){ circles.remove(circle);}
    public void updateCircleEndpoint(CircleModel circle , PointModel point){
        for (CircleModel circ:circles) {
            if(circle==circ){
                circ.endPoint.setNewLocation(point);
            }
        }
    }
    public void resetBtnsLocation(){
        for (LineModel ln:lineModels) {
            ln.resetPointBtnsLocation();
        }

    }


}
