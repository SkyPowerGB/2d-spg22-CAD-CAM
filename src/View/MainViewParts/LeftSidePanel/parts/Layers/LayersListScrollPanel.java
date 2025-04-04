package View.MainViewParts.LeftSidePanel.parts.Layers;

import javax.swing.*;

public class LayersListScrollPanel extends JScrollPane {
    public LayersListScrollPanel() {

        LayerBtnGrpPanelV2 layerBtnGrpPanelV2 =new LayerBtnGrpPanelV2();
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(layerBtnGrpPanelV2);
    }
}


