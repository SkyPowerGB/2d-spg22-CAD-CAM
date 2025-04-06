package View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers.parts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LayersListScrollPanel extends JScrollPane {

    private LayerBtnGrpPanelV2 layerBtnGrpPanelV2;
    public LayersListScrollPanel() {
       this.setSize(200, 400);
        layerBtnGrpPanelV2 =new LayerBtnGrpPanelV2();
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(layerBtnGrpPanelV2);
    }

    public void addNewLayerBtn(LayerBtn btn){

        layerBtnGrpPanelV2.addLayerBtnAtTop(btn);
        layerBtnGrpPanelV2.revalidate();  // Ensure layout is recalculated
        layerBtnGrpPanelV2.repaint();
    }

    public ArrayList<LayerBtn> getLayerBtns(){
      return   layerBtnGrpPanelV2.getLayerButtons();
    }


}


