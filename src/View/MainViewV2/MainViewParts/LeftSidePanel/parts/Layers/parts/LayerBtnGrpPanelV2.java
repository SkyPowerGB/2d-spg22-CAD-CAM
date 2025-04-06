package View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers.parts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class LayerBtnGrpPanelV2 extends JPanel {



    public LayerBtnGrpPanelV2() {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(200,5000);

        this.setBackground(Color.BLUE);
    }

    private ArrayList<LayerBtn> layerButtons = new ArrayList<>();

    public void addLayerBtnAtTop(LayerBtn btn) {

        layerButtons.add(btn);

        System.out.println("Add new layer");
        refreshSorted();
    }

    public void removeLayerBtn(LayerBtn btn) {
        layerButtons.remove(btn);
        refreshSorted();
    }

    public ArrayList<LayerBtn> getLayerButtons() {
        return new ArrayList<>(layerButtons);
    }


    private void refreshSorted() {

        layerButtons.sort(Comparator.comparingDouble(
                b -> b.getLayerV2Model().getDepth()
        ));


        this.removeAll();
        for (LayerBtn b : layerButtons) {
            this.add(b);
        }
        System.out.println("refreshing layers");

    }

}
