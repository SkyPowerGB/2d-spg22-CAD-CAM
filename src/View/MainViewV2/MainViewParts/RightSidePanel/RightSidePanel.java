package View.MainViewV2.MainViewParts.RightSidePanel;

import javax.swing.*;
import java.awt.*;

public class RightSidePanel extends JPanel {
    public RightSidePanel(){
        this.setBackground(Color.ORANGE);
        this.setPreferredSize(new Dimension(100, getMaximumSize().height));
        this.setToolTipText("RightSidePanel");

    }
}
