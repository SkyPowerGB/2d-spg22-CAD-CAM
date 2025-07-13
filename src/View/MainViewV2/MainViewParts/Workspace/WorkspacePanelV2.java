package View.MainViewV2.MainViewParts.Workspace;

import View.MainViewV2.MainViewParts.Workspace.MaterialV4.DrawingsPrinter;
import View.MainViewV2.MainViewParts.Workspace.MaterialV4.MaterialLayers;
import View.MainViewV2.MainViewParts.Workspace.MaterialV4.MaterialPanel;
import controller.standard.Controller;
import model.FileDataModel;
import model.LayerDrawingsModel;
import model.V2models.ViewStateModel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorkspacePanelV2 extends JPanel {
    Controller workspaceController;
    Controller toolController;



    MaterialPanel materialPanel;
    MaterialLayers materialLayers;
    DrawingsPrinter printer;



    public WorkspacePanelV2(){
    this.setLayout(null);
    }

    public void setFileDataModel(FileDataModel fileDataModel) {


        materialPanel=new MaterialPanel();
        materialLayers=new MaterialLayers();
        printer=new DrawingsPrinter();

        materialPanel.setSize(fileDataModel.getMaterialViewDimension());
        materialLayers.setSize(fileDataModel.getMaterialViewDimension());
        printer.setSize(fileDataModel.getMaterialViewDimension());


        materialLayers.add(printer);

        materialPanel.add(materialLayers);


        this.add(materialPanel);
    }

    public void setViewStateModel(ViewStateModel viewStateModel) {

        if(materialPanel==null){  return;}
        materialPanel.setLocation(viewStateModel.getMaterialLocation());
    }

    public void setDrawingsModel(LayerDrawingsModel drawingsModel) {

        printer.setLayerDrawingsModel(drawingsModel);
    }


    public void setWorkspaceController(Controller workspaceController) {
        this.workspaceController = workspaceController;
    }

    public void setToolController(Controller toolController) {
        this.toolController = toolController;
    }
}
