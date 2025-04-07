package View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers.parts;

import Enums.ControllerActionEventNamesE;
import View.MainViewV2.MainViewParts.LeftSidePanel.VisualSettingsLeftSidePanel;
import controller.standard.Controller;
import model.V2models.LayerModelV2;

import javax.swing.*;
import java.awt.*;

public class LayerBtn extends JButton {
private LayerModelV2 model;

public LayerBtn(Controller controller, LayerModelV2 model){

    setController(controller);

    this.model=model;
    setupAppearance();
}

private void setupAppearance(){


    this.setPreferredSize(new Dimension(getMaximumSize().width,VisualSettingsLeftSidePanel.LayerBtnSize));
    this.setText(String.valueOf(model.getDepth()));

}

public  void setController(Controller controller){
    if(controller !=null){
    this.addActionListener(e-> controller.handleAction(e, ControllerActionEventNamesE.layerBtnClick));}else{
        System.out.println("Warning controller is null");
    }
}


    public LayerModelV2 getLayerV2Model() {
        return model;
    }

    public void setLayerV2Model(LayerModelV2 model) {
        this.model = model;
    }


}
