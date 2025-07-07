package View.MainViewV2.MainViewParts.Workspace.parts.materialComponents;

import View.MainViewV2.MainViewParts.Workspace.IScalablePanel;
import model.LayerDrawingsModel;
import model.V2models.MaterialModelV2;

import javax.swing.*;
import java.awt.*;

public class MaterialDrawingsBoardV2 extends JPanel   implements IScalablePanel {

  public MaterialModelV2 materialData;


    public  MaterialDrawingsBoardV2(MaterialModelV2 materialData){

        this.materialData=materialData;
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

    }

    @Override
    public void setDefaultDim(int x, int y) {

    }

    @Override
    public Dimension getScaledDim() {
        return null;
    }


}
