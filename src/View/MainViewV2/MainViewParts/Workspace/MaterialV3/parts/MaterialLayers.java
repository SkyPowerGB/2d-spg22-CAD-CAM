package View.MainViewV2.MainViewParts.Workspace.MaterialV3.parts;

import javax.swing.*;
import java.util.ArrayList;

public class MaterialLayers extends JLayeredPane implements MaterialPartI {

     LayerDrawingsPrinter drawingsPrinter;
    public MaterialLayers(){
        drawingsPrinter=new LayerDrawingsPrinter();
    }
    @Override
    public void setScale(float scale) {
       drawingsPrinter.setScale(scale);
    }

    @Override
    public void setDefaultSize(int x, int y) {
     drawingsPrinter.setDefaultSize(x,y);
    }
}
