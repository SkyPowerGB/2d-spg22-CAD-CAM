package View.MainViewV2.MainViewParts.Workspace.parts;

import View.MainViewV2.MainViewParts.Workspace.IScalablePanel;

import javax.swing.*;
import java.awt.*;

public class MaterialLayersV2 extends JLayeredPane implements IScalablePanel {
    public MaterialLayersV2(){
        this.setSize(getMaximumSize());

    }


    @Override
    public void setScale(double scale) {

    }

    @Override
    public Double getScale() {
        return 0.0;
    }

    @Override
    public void setDefaultDim(Dimension d) {
     this.setSize(d);
    }

    @Override
    public void setDefaultDim(int x, int y) {
       setDefaultDim(new Dimension(x,y));
    }

    @Override
    public Dimension getScaledDim() {
        return null;
    }
}
