package View.MainViewV2.MainViewParts.Workspace.MaterialV4;

import model.FileDataModel;
import model.LayerDrawingsModel;
import model.V2models.ViewStateModel;

import javax.swing.*;

public class DrawingsPrinter extends JPanel {
    ViewStateModel viewStateModel;
    FileDataModel fileDataModel;

    LayerDrawingsModel layerDrawingsModel;


    public void setViewStateModel(ViewStateModel viewStateModel) {
        this.viewStateModel = viewStateModel;
    }

    public void setFileDataModel(FileDataModel fileDataModel) {
        this.fileDataModel = fileDataModel;
    }

    public void setLayerDrawingsModel(LayerDrawingsModel layerDrawingsModel) {
        this.layerDrawingsModel = layerDrawingsModel;
    }



}
