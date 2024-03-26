package model;

import ViewParts.LayerPanel;

import javax.swing.*;
import java.util.ArrayList;

public class Layers {
    private static ArrayList<LayerPanel> layers=new ArrayList<>();
    private static ArrayList<LayerDrawingsModel> models=new ArrayList<>();

    public static void enableAllBtns(){
        for (LayerPanel layer:layers) {
            layer.getLayerBtn().setEnabled(true);
        }
    }


    public static int addLayer(LayerPanel panel){
        LayerDrawingsModel model1 =new LayerDrawingsModel();
        models.add(model1);

        layers.add(panel);
        return layers.indexOf(panel);
    }

    public static void deleteLayer(int index){
        layers.remove(index);
        models.remove(index);
    }
    public static void deleteLayer(LayerPanel layer){
        deleteLayer(getPanelIndex(layer));

    }
    public static LayerPanel getPanel(int index) {
        return layers.get(index);
    }
    public static int getPanelIndex(LayerPanel panel){
        return layers.indexOf(panel);
    }

    public static int selectLayer(LayerPanel panel){
        activeLayer=getPanelIndex(panel);
        return activeLayer;
    }

    public static LayerDrawingsModel getLayerModel(){
        return models.get(activeLayer);
    }
    public static int activeLayer;

    public static boolean layerHeightExists(double h){
        for (LayerPanel layer:layers) {
            if(layer.getLayerZheight()==h){
                return true;
            }
        }
        return false;
    }






}
