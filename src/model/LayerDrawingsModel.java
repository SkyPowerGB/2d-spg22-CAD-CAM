package model;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class LayerDrawingsModel {
    ArrayList<Line2D.Double> lines;
    ArrayList<LineModel> lineModels;
    ArrayList<TextModel> texts;


    public LayerDrawingsModel(){
        lines=new ArrayList<>();
        lineModels=new ArrayList<>();
    }

    public ArrayList<Line2D.Double> getLines() {
        return lines;
    }



    public int addLine(Line2D.Double line){
        lines.add(line);

        return lines.indexOf(line);
    }
    public int addLinesModel(LineModel lineModel){
        lineModels.add(lineModel);
        return lineModels.indexOf(lineModel);
    }

    public ArrayList<LineModel> getLineModels() {
        return lineModels;
    }

    public void removeLineAtIndex(int i){
        lines.remove(i);
    }

    public void removeLineModelAtIndex(int i){lineModels.remove(i);}


}
