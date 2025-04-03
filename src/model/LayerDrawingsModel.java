package model;

import java.util.ArrayList;

public class LayerDrawingsModel {

    ArrayList<LineModel> lineModels;



    public LayerDrawingsModel(){

        lineModels=new ArrayList<>();


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


    public void resetBtnsLocation(){
        for (LineModel ln:lineModels) {
            ln.resetPointBtnsLocation();
        }

    }


}
