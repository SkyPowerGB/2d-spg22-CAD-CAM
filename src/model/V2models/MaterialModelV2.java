package model.V2models;

import java.awt.*;

public class MaterialModelV2 {
    // px per mm
    private double resolution;

    // mm
    private Dimension dimension;

    public Dimension getDisplayDimension() {
        return new Dimension((int) (dimension.width*resolution), (int) (dimension.height*resolution));
    }

    public  MaterialModelV2 (double resolution,Dimension dimension){

    }

    public Dimension getDimension() {
            return  dimension;
    }
}
