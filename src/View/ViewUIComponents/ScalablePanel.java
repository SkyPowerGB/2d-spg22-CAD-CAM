package View.ViewUIComponents;

import javax.swing.*;
import java.awt.*;

public class ScalablePanel extends JPanel {
    double scale=1;
    int originalWidth;
    int originalHeight;
    public ScalablePanel(){
        originalHeight=this.getHeight();
        originalWidth=this.getWidth();
    }
    public void setDefaultSize(int width,int height){
        this.setSize(width,height);
        this.originalWidth=width;
        this.originalHeight=height;
    }

    public void setDefaultSize(Dimension dimension){
        super.setSize(dimension);
        this.originalWidth=dimension.width;
        this.originalHeight=dimension.height;
    }


    public void setScale(double scale) {

        this.scale = scale;

        super.setSize(new Dimension((int) (originalWidth*scale), (int) (originalHeight*scale)));

    }

    public double getScale(){
            return scale;
    }





}
