package model;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class LayerDrawingsModel {
    ArrayList<Line2D.Double> lines;

    public LayerDrawingsModel(){
        lines=new ArrayList<>();
    }


    public ArrayList<Line2D.Double> getLines() {
        return lines;
    }

    public int addLine(Line2D.Double line){
        lines.add(line);

        return lines.indexOf(line);
    }

    public void removeLineAtIndex(int i){
        lines.remove(i);
    }
}
