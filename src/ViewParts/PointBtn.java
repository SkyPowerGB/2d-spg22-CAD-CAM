package ViewParts;

import controller.AEclasses.PointAL;
import controller.callbacks.PointPressedCallBack;
import model.PointModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PointBtn extends JButton {
    Point point;

    PointModel modelPoint;
    double scale;
    Point defaultLoc;
    Dimension defaultDim;


    public PointBtn(PointModel modelPoint,int size){
        this.modelPoint=modelPoint;
        defaultDim=new Dimension(size,size);
        this.setSize(defaultDim);
        point=modelPoint.point;
        this.setLocation(point);
    }
    public void setScale(double scale){
        this.scale=scale;
         Point scaledLoc=new Point((int) (point.x*scale), (int) (point.y*scale));
        this.setLocation(scaledLoc);
        this.setSize(new Dimension((int) (defaultDim.width*scale), (int) (defaultDim.height*scale)));
    }
    @Override
    public void setLocation(@NotNull Point p) {
        p=new Point(p.x-(getWidth()/2),p.y-(getHeight()/2));


        super.setLocation(p);
    }

    public PointModel getModelPoint(){
        return modelPoint;
    }

    public boolean isSamePoint(PointModel p){
        if(this.modelPoint==p){
            return true;
        }
        return false;
    }


}
