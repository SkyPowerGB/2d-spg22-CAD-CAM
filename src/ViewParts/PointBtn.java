package ViewParts;

import controller.AEclasses.PointAL;
import controller.callbacks.PointPressedCallBack;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PointBtn extends JButton {
    Point point;
    double scale;
    Point defaultLoc;
    public PointBtn(Point p, int size){
        this.point=p;
          this.setSize(new Dimension(size,size));

    }
    public void setScale(double scale){
        this.scale=scale;
        this.setSize((int) (defaultLoc.x*scale), (int) (defaultLoc.y*scale));
    }
    @Override
    public void setLocation(@NotNull Point p) {
        p=new Point(p.x-(getWidth()/2),p.y-(getHeight()/2));
        defaultLoc=p;
        super.setLocation(p);
    }
}
