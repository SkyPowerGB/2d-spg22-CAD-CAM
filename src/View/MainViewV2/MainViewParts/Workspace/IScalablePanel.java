package View.MainViewV2.MainViewParts.Workspace;

import java.awt.*;

// TO BE USED

public interface IScalablePanel {



    public void setScale(double scale);
    public Double getScale();
    public void setDefaultDim(Dimension d);
    public void setDefaultDim(int x,int y);
    public Dimension getScaledDim();


}
