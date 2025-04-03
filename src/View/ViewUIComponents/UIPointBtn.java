package View.ViewUIComponents;

import model.PointModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class UIPointBtn extends JButton {
    Point point;

    PointModel modelPoint;
    double scale;
    Point defaultLoc;
    Dimension defaultDim;


    public UIPointBtn(PointModel modelPoint, int size){
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

    //reset ui position to match point pos
    public void resetLocation(){

         point=modelPoint.point;
         setLocation(point);
    }
    @Override
    public void setLocation(@NotNull Point p) {
        //adjust point so that button is in center
        p=new Point(p.x-(getWidth()/2),p.y-(getHeight()/2));


        super.setLocation(p);
    }

    public PointModel getModelPoint(){
        return modelPoint;
    }

    public void setModelPoint(PointModel modelPoint){
        this.modelPoint=modelPoint;
        resetLocation();
    }

    public boolean isSamePoint(PointModel referencePoint){
        if(this.modelPoint==referencePoint){
            return true;
        }
        if( (this.modelPoint.x==referencePoint.x)&&this.modelPoint.y== referencePoint.y ){
            return true;
        }
        return false;
    }


}
