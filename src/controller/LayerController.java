package controller;


import Enums.ControllerActionEventNamesE;
import View.MainView;
import View.ViewUIComponents.DrawingBoard;
import View.ViewUIComponents.LayerPanel;
import controller.standard.Controller;
import helpers.DepthColorMap;
import model.FileDataModel;
import model.LayerDrawingsModel;
import model.LayersDataStorageModel;
import model.V2models.LayerModelV2;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.TreeMap;

public class LayerController extends Controller  {
    private MainView view;
   private   FileDataModel fileData;
   private LayerDrawingsModel layerDrawingModel;
   private DrawingBoard board;
   private TreeMap<Double, LayerPanel> layers = new TreeMap<Double, LayerPanel>();

   public LayerController(MainView view){
       this.view=view;
       view.getLeftSidePanelV2().setLayersController(this);
   }



    public void setFileData(FileDataModel model){
       this.fileData=model;
    }


    @Override
    public void handleAction(ActionEvent e) {

    }

    @Override
    public void handleAction(ActionEvent e, ControllerActionEventNamesE action) {
         System.out.println(action);
         if(action==ControllerActionEventNamesE.addLayerBtnClick){
             addNewLayerV2();
         }
    }

    public void addNewLayerV2(){
        String input = JOptionPane.showInputDialog(null, "Enter layer depth in mm");

        try {
            Double out = Double.parseDouble(input);
            LayerModelV2 modelV2=new LayerModelV2();
            modelV2.setDepth(out);
            view.getLeftSidePanelV2().getLayersMainPanel().addNewLayer(modelV2);


        }catch (Exception e){


        }
    }




}
