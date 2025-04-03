package model;

import View.ViewUIComponents.LayerPanel;

import java.util.ArrayList;

public class
LayersDataStorageModel {



    private static ArrayList<LayerPanel> layers=new ArrayList<>();
    private static ArrayList<LayerDrawingsModel> models=new ArrayList<>();
    private static FileDataModel fileData;

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


    public static LayerPanel getPanel(int index) {
        return layers.get(index);
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


    public static FileDataModel getFileData() {
        return fileData;
    }

    public static void setFileData(FileDataModel fileData) {
        LayersDataStorageModel.fileData = fileData;
    }
}
