package View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers;

import Enums.ControllerActionEventNamesE;
import View.MainView;
import View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers.parts.LayerBtn;
import View.MainViewV2.MainViewParts.LeftSidePanel.parts.Layers.parts.LayersListScrollPanel;
import controller.standard.Controller;
import model.V2models.LayerModelV2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LayersMainPanel extends JPanel {
   private LayersListScrollPanel layersListScrollPanel;
    private JButton addLayerBtn;
    private Controller controller;

    public LayersMainPanel(){
        this.setLayout(new BorderLayout());
   this.setBackground(Color.GREEN);
          addLayerBtn = new JButton("+");
        addLayerBtn.setPreferredSize(new Dimension(100, 50));

         layersListScrollPanel =new LayersListScrollPanel();
        this.add(layersListScrollPanel, BorderLayout.CENTER);
        this.add(addLayerBtn, BorderLayout.NORTH);


    }

    public void setController(Controller controller) {

        this.controller = controller;
        addLayerBtn.addActionListener(e->controller.handleAction(e, ControllerActionEventNamesE.addLayerBtnClick));
    }

    public  void addNewLayer(LayerModelV2 modelV2){
        if(controller==null){
            System.out.println("set layer controller for layerBtns first!");
            return;
        }
        LayerBtn layerBtn=new LayerBtn(controller,modelV2);
        layerBtn.setSize(50,50);
        layersListScrollPanel.addNewLayerBtn(layerBtn);
    }

    private ArrayList<LayerBtn> getLayerBtns(){
        return layersListScrollPanel.getLayerBtns();
    }

    public  ArrayList<LayerModelV2> getAllLayerModels(){
        ArrayList<LayerModelV2> models=new ArrayList<>();
        for(LayerBtn btn:getLayerBtns()){
            models.add(btn.getLayerV2Model());

        }
        return models;
    }

}
