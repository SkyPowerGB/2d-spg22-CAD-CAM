package ViewParts;

import javax.swing.*;
import java.awt.*;

public class Material extends JPanel {
    double scale=1;
    public Material(){

    }
    public void setScale(double factor){
        this.setPreferredSize(new Dimension((int)(this.getWidth()*factor),(int)(this.getHeight()*factor)));
    }






}
