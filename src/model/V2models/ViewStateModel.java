package model.V2models;

import java.awt.*;

public class ViewStateModel {
    private double scale=1;
    private Point materialLocation= new Point(0,0);
    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Point getMaterialLocation() {
        return materialLocation;
    }

    public void setMaterialLocation(Point p) {
        this.materialLocation = p;
    }
}
