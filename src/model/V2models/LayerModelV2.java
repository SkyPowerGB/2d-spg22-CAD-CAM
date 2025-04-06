package model.V2models;

import model.LayerDrawingsModel;

public class LayerModelV2 {
    private double depth;
    private LayerDrawingsModel layerDrawingsModel;

    public LayerDrawingsModel getLayerDrawingsModel() {
        return layerDrawingsModel;
    }

    public void setLayerDrawingsModel(LayerDrawingsModel layerDrawingsModel) {
        this.layerDrawingsModel = layerDrawingsModel;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
