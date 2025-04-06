package View.MainViewV2.MainViewParts.LeftSidePanel;

import View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers.LayersMainPanel;
import View.MainViewV2.MainViewParts.LeftSidePanel.parts.Tools.ToolPanel;
import controller.standard.Controller;

import javax.swing.*;
import java.awt.*;

public class LeftSidePanelV2 extends JPanel {

    private ToolPanel toolPanel;
    private    LayersMainPanel layersMainPanel;
    public LeftSidePanelV2(){
        this.setBackground(Color.cyan);
        this.setPreferredSize(new Dimension(200, getMaximumSize().height));
        this.setLayout(new BorderLayout());
         toolPanel=new ToolPanel();
         layersMainPanel=new LayersMainPanel();
        this.add(toolPanel, BorderLayout.WEST);
        this.add(layersMainPanel, BorderLayout.EAST);

    }

    public ToolPanel getToolPanel() {
        return toolPanel;
    }

    public LayersMainPanel getLayersMainPanel() {
        return layersMainPanel;
    }


    public void setLayersController(Controller controller){
        layersMainPanel.setController(controller);
    }


}


