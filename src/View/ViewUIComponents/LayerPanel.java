package View.ViewUIComponents;

import javax.swing.*;

public class LayerPanel extends JPanel {
    double LayerZheight;
    JButton layerBtn;
    public LayerPanel(double layerDepth){
        this.LayerZheight =layerDepth;
    }

    public double getLayerZheight() {
        return LayerZheight;
    }
    public JButton getLayerBtn() {
        return layerBtn;
    }

    public void setLayerBtn(JButton layerBtn) {
        this.layerBtn = layerBtn;
    }


}
