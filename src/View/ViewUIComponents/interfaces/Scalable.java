package View.ViewUIComponents.interfaces;

import java.awt.*;

// TO BE USED

public interface Scalable {
    double scale=1;
    Dimension DefaultDim = null;
    Point defaultLoc =null;
    public void setScale(double scale);
    public Double getScale();
    public void setDefaultDim(Dimension d);
    public void setDefaultDim(int x,int y);
    public void setDefaultLoc(Point p);

    public Dimension getScaledDim();
    public Point getScaledPoint();

}
