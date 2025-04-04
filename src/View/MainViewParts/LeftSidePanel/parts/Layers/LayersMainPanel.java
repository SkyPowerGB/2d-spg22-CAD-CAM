package View.MainViewParts.LeftSidePanel.parts.Layers;

import javax.swing.*;
import java.awt.*;

public class LayersMainPanel extends JPanel {

    public LayersMainPanel(){
        this.setLayout(new BorderLayout());
         JButton addLayerBtn = new JButton("+");
        addLayerBtn.setPreferredSize(new Dimension(100, 50));
        LayersListScrollPanel layersListScrollPanel =new LayersListScrollPanel();
        this.add(layersListScrollPanel, BorderLayout.CENTER);
        this.add(addLayerBtn, BorderLayout.NORTH);


    }
}
