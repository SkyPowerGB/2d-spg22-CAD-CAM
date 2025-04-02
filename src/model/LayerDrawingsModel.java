package model;

import ViewParts.PointBtn;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class LayerDrawingsModel {

    ArrayList<LineModel> lineModels;

    ArrayList<CircleModel> circles;


    public LayerDrawingsModel(){

        lineModels=new ArrayList<>();

        circles=new ArrayList<>();

    }


    public int addLinesModel(LineModel lineModel){
        lineModels.add(lineModel);
        return lineModels.indexOf(lineModel);
    }

    public ArrayList<LineModel> getLineModels() {
        return lineModels;
    }

    public void removeLineModel(LineModel lineModel){
        lineModels.remove(lineModel);
    }


    public ArrayList<CircleModel> getCircles() {
        return circles;
    }


    public void resetBtnsLocation(){
        for (LineModel ln:lineModels) {
            ln.resetPointBtnsLocation();
        }

    }


}
