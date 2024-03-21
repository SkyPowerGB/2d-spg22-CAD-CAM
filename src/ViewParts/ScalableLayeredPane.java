package ViewParts;

import javax.swing.*;
import java.awt.*;

public class ScalableLayeredPane extends JLayeredPane {
    double scale=1;
    int originalWidth;
    int originalHeight;

    public ScalableLayeredPane(){}

    public void setDefaultSize(Dimension dimension){
        super.setSize(dimension);
        this.originalWidth=dimension.width;
        this.originalHeight=dimension.height;
    }

    public void setDefaultPreferredSize(Dimension dimension){
        super.setPreferredSize(dimension);
        this.originalWidth=dimension.width;
        this.originalHeight=dimension.height;
    }
    public void setScale(double scale) {
        this.scale = scale;
        this.setPreferredSize(new Dimension((int) (this.getWidth()*scale), (int) (this.getHeight()*scale)));
    }
    public void setScalePreferred(double scale){
        this.scale = scale;
        super.setPreferredSize(new Dimension((int) (originalWidth*scale), (int) (originalHeight*scale)));
    }


}
