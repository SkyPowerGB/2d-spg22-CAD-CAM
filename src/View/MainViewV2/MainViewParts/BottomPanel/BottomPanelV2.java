package View.MainViewV2.MainViewParts.BottomPanel;

import View.MainViewV2.MainViewParts.BottomPanel.parts.ScaleDisplayLbl;

import javax.swing.*;
import java.awt.*;

public class BottomPanelV2  extends JPanel {
    private ScaleDisplayLbl scaleDisplayLbl;
    public  BottomPanelV2(){

        this.setPreferredSize(new Dimension(getMaximumSize().width, 50));
        scaleDisplayLbl=new ScaleDisplayLbl();
        this.add(scaleDisplayLbl,BorderLayout.EAST);

    }

    public void setScaleTxt(double scale){
        int zoom=(int)(scale*100);
        scaleDisplayLbl.setText("Scale:"+zoom+"%");
    }

}
