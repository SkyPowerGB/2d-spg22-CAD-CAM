package controller;


import View.MainView;
import ViewParts.DrawingBoard;
import ViewParts.LayerPanel;
import helpers.DepthColorMap;
import model.FileDataModel;
import model.LayerDrawingsModel;
import model.LayersModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.TreeMap;

public class LayerController {
    private MainView view;
   private   FileDataModel fileData;
   private LayerDrawingsModel layerDrawingModel;
   private DrawingBoard board;
   private TreeMap<Double, LayerPanel> layers = new TreeMap<Double, LayerPanel>();
   public LayerController(){}
   public LayerController(MainView view,FileDataModel fileData){
        this.view=view;
        this.fileData = fileData;
    }

    public void addLayer() {

        if (fileData == null) {
            JOptionPane.showMessageDialog(null, "Please create file");
            return;
        }
        String input = JOptionPane.showInputDialog(null, "Enter layer height in mm");
        double out;
        try {
            out = Double.parseDouble(input);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
        }
        if (out == 0) {
            JOptionPane.showMessageDialog(null, "Putting 0 depth?");
            return;
        }
        DepthColorMap map = new DepthColorMap(fileData.materialThickness, 0.1);
        if (LayersModel.layerHeightExists(out)) {
            JOptionPane.showMessageDialog(null, "Layer at that height already exists");
            return;
        }
        LayerPanel layer = new LayerPanel(out);
        layer.setPreferredSize(new Dimension(90, 50));
        layer.setBackground(Color.gray);
        layer.setLayout(null);
        layer.setBorder(new BevelBorder(BevelBorder.LOWERED));


        int index = LayersModel.addLayer(layer);
        JButton btn = new JButton();

        btn.setSize(layer.getPreferredSize());
        btn.setName(String.valueOf(index));
        btn.setBackground(null);
        btn.setBackground(map.getHeightColor(out));
        btn.setText(String.valueOf(out));
        layer.setLayerBtn(btn);
        btn.addActionListener(e -> {
            selectLayer((((JButton) e.getSource()).getName()));
        });



        layers.put(out, layer);

        view.removeLayers();
        layer.add(btn);
        layers.forEach((k,l)-> view.addLayer(l));

    }

    public void selectLayer(String index) {
        int i = Integer.parseInt(index);
        board.removeAllUComponents();
        LayersModel.activeLayer = i;
        LayersModel.enableAllBtns();
        LayersModel.getPanel(i).getLayerBtn().setEnabled(false);
        System.out.println("select layer: " + i);
        layerDrawingModel = LayersModel.getLayerModel();
        view.boardSetDrawingsModel(layerDrawingModel);

    }

    public void setMainView(MainView view){
       this.view=view;

    }

    public  LayerDrawingsModel getLayerDrawingsModel(){
       return layerDrawingModel;
    }

    public void setFileData(FileDataModel model){
       this.fileData=model;
    }

    public void setBoard(DrawingBoard board){
       this.board=board;
    }

    public  void setLayerDrawingModel(LayerDrawingsModel model){
       this.layerDrawingModel=model;
    }
}
