package View.MainViewV2.MainViewParts.Workspace;

import View.MainViewV2.MainViewParts.Workspace.parts.MaterialLayersV2;
import model.V2models.MaterialModelV2;

import javax.swing.*;
import java.awt.*;

public class MaterialV2 extends JPanel implements IScalablePanel {
    private double scale=1;
    private Dimension defaultDimension;
    private MaterialLayersV2 materialLayersV2;

    private MaterialModelV2 model;

    public  MaterialV2(){
      materialLayersV2 =new MaterialLayersV2();
      this.add(materialLayersV2);
    }


    
    public void setModel(MaterialModelV2 model) {
        this.setSize(model.getDisplayDimension());
        this.model = model;
    }
    private MaterialModelV2 getModel(){

        return  model;
    }

    @Override
    public void setScale(double scale) {
        this.setSize((int) (getSize().width*scale), (int) (getSize().height*scale));

        this.scale=scale;
    }

    @Override
    public Double getScale() {
        return scale;
    }

    @Override
    public void setDefaultDim(Dimension d) {
          this.defaultDimension=d;
    }

    @Override
    public void setDefaultDim(int x, int y) {

    }

    @Override
    public Dimension getScaledDim() {
        return null;
    }
}
