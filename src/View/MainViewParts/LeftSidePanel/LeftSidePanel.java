package View.MainViewParts.LeftSidePanel;

import View.MainViewParts.LeftSidePanel.parts.Layers.LayersMainPanel;
import View.MainViewParts.LeftSidePanel.parts.Tools.ToolPanel;

import javax.swing.*;
import java.awt.*;

public class LeftSidePanel extends JPanel {
    public LeftSidePanel(){
        this.setBackground(Color.cyan);
        this.setPreferredSize(new Dimension(200, getMaximumSize().height));
        this.setLayout(new BorderLayout());
        ToolPanel toolPanel=new ToolPanel();
        LayersMainPanel layersMainPanel=new LayersMainPanel();
        this.add(toolPanel, BorderLayout.WEST);
        this.add(layersMainPanel, BorderLayout.EAST);

    }
}
