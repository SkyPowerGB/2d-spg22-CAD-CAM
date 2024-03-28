package controller;

import View.MainView;
import ViewParts.DrawingBoard;
import ViewParts.LayerPanel;
import ViewParts.TextPropertiesPanel;
import controller.AEclasses.PointAL;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.AEclasses.WorkspaceMouseWheelListener;
import controller.callbacks.AddTextCallback;
import controller.callbacks.MouseCallBacks;
import controller.callbacks.NewFileCallBack;
import controller.callbacks.PointPressedCallBack;
import helpers.*;
import helpers.enums.FileOptionsE;
import helpers.enums.ToolStatesE;
import helpers.helperModels.Line;
import model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.VolatileImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import helpers.enums.ToolNamesE;

import static helpers.improvedToolBox.*;

public class MainController implements NewFileCallBack, AddTextCallback, MouseCallBacks, PointPressedCallBack {
    MainView view;
    TextPropertiesPanel textPropertiesPanel;
    WorkspaceMouseWheelListener mouseWheelListener;
    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;
    LayerDrawingsModel model;


    FileData data;
    DrawingBoard board;

    Point lineP1;
    int currLineIndex = -1;
    PointAL pointAL;
    private boolean middleButtonPressed;
    private int prevX, prevY;
    private int offsetX, offsetY;

    public MainController() {

        mouseWheelListener = new WorkspaceMouseWheelListener(this);
        workspaceMouseListener = new WorkspaceMouseListener(this);
        mouseMotionListener = new WorkspaceMouseMotionListener(this);

        pointAL = new PointAL(this);



        view = new MainView();

        ArrayList<JMenuItem> menuOptions = view.getMenuOptions();

        ArrayList<JButton> toolBtns = view.getToolBtns();

        for (JMenuItem item : menuOptions) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem option = (JMenuItem) e.getSource();
                    menuOptionClicked(option.getName());
                }
            });
        }
        for (JButton btn : toolBtns) {
            btn.setBackground(null);
            btn.setBorder(null);
            btn.addActionListener(e -> {
                toolBtnPressed((JButton) e.getSource());
            });
        }

        JPanel workspace = view.getWorkspacePanel();

        workspace.addMouseWheelListener(mouseWheelListener);

        workspace.addMouseListener(workspaceMouseListener);

        workspace.addMouseMotionListener(mouseMotionListener);

        view.getAddLayerBtn().addActionListener(e -> {
            addLayer();
        });

        view.getWorkspaceShowPointsBtn().addActionListener(e->{showConnectPointsBtn();});



    }
    TreeMap<Double, LayerPanel>    layers = new TreeMap<Double, LayerPanel>();
    private void addLayer() {

        if (data == null) {
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
        ZdepthMap map = new ZdepthMap(data.materialThickness, 0.1);
        if (Layers.layerHeightExists(out)) {
            JOptionPane.showMessageDialog(null, "Layer at that height already exists");
            return;
        }
        LayerPanel layer = new LayerPanel(out);
        layer.setPreferredSize(new Dimension(90, 50));
        layer.setBackground(Color.gray);
        layer.setLayout(null);
        layer.setBorder(new BevelBorder(BevelBorder.LOWERED));


        int index = Layers.addLayer(layer);
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
    private void selectLayer(String index) {
        int i = Integer.parseInt(index);
        board.removePointBtns();
        Layers.activeLayer = i;
        Layers.enableAllBtns();
        Layers.getPanel(i).getLayerBtn().setEnabled(false);
        System.out.println("select layer: " + i);
          model= Layers.getLayerModel();
          view.boardSetDrawingsModel(model);

    }

    private void menuOptionClicked(String name) {


        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
        }
    }

    public void zoom_in_out(int factor, Point position, boolean dir) {
        double ammount = 0.1;
        byte relation = 0;
        Point workspacePos = view.getMaterialPos();
        if (workspacePos.x > position.x && workspacePos.y > position.y) {
            relation = 1;
        } else if (workspacePos.x > position.x && workspacePos.y < position.y) {
            relation = 2;
        } else if (workspacePos.x < position.x && workspacePos.y < position.y) {
            relation = 3;
        } else if (workspacePos.x < position.x && workspacePos.y > position.y) {
            relation = 4;
        }

        Line ln;

        double moveX = view.getMaterial().getSize().getWidth() * (ammount / 2);

        int nextX;


        Point materialPos = view.getMaterialPos();

        if (relation == 3 || relation == 4) {
            ln = new Line(position, materialPos);
        } else {
            ln = new Line(materialPos, position);
        }


        double scale = view.getScale();

        if (scale <= 0.2) {
            scale = 0.3;

            view.setScale(scale);


            view.refreshWindow();
            return;
        }

        if (dir) {
            nextX = (int) moveX + materialPos.x;
            scale = scale + ammount;
        } else {
            nextX = materialPos.x - (int) moveX;
            scale = scale - ammount;
        }


        int nexY = ln.calcY(nextX);

        Point newLoc = new Point(nextX, nexY);
        view.setMaterialLoc(newLoc);
        view.setScale(scale);

        view.refreshWindow();


    }


    //callbacks
    @Override
    public void onFileCreate(FileData data) {
        this.data = data;
        view.addMaterial(data);
        view.refreshWindow();
        board = view.getBoard();
        model = board.getModel();
        currLineIndex = -1;
    }

    public void toolBtnPressed(JButton btn) {


       setActiveTool(ToolNamesE.valueOf(btn.getName()));

       for(JButton tBtn:view.getToolBtns()){
           tBtn.setIcon(TextureHelper.getToolBtnTexture(ToolNamesE.valueOf(tBtn.getName()),50));
       }

        switch (btn.getName()) {
            case "txt":

                if (ActiveBtns.text) {
                    ActiveBtns.text = false;
                } else {
                    ActiveBtns.text = true;
                }

                break;

            case "line":


                break;


        }

        view.refreshWindow();
    }

    @Override
    public void AddText(TextModel txtModel) {
           drawTxt(txtModel);

    }

    @Override
    public void zoomIn(Point p) {

        zoom_in_out(1, p, true);
    }

    @Override
    public void zoomOut(Point p) {


        zoom_in_out(1, p, false);
    }

    @Override
    public void click(Point p) {
        if (view.isPointOnMaterial(p)) {
            Point click = view.recalcWorkspaceToMaterial(p);
            click=PointScaler.getDefaultPoint(click,view.getScale());
            clickOnMaterial(click);
        }

    }

    @Override
    public void pressedMidBtn(Point location) {
        middleButtonPressed = true;
        prevX = location.x;
        prevY = location.y;
        offsetX = view.getMaterialPos().x - prevX;
        offsetY = view.getMaterialPos().y - prevY;
    }

    @Override
    public void dragged(Point where) {

        if (middleButtonPressed) {
            int currX = where.x;
            int currY = where.y;
            int newPosX = offsetX + currX;
            int newPosY = offsetY + currY;

            view.setMaterialLoc(new Point(newPosX, newPosY));
            view.refreshWindow();
        }
    }

    @Override
    public void releasedMidBtn() {
        middleButtonPressed = false;
    }

    @Override
    public void entered() {
        if (ActiveBtns.text) {
            view.getMaterial().setCursor(new Cursor(Cursor.TEXT_CURSOR));
        } else {
            view.getMaterial().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void moved(Point currPos) {
        if(followMouse){
            if(!view.isPointOnMaterial(currPos)){return;}
            currPos=view.recalcWorkspaceToMaterial(currPos);
            currPos=PointScaler.getDefaultPoint(currPos,view.getScale());
            mouseMovedFollow(currPos);
        }


    }

    @Override
    public void clickedPoint(PointModel p) {
            System.out.println("point Selected!");
            if(improvedToolBox.getActiveTool().toString()=="line"){
                lineBegin(p.point);
            }
    }
    public void showConnectPointsBtn(){
        board=view.getBoard();
        board.removePointBtns();
        board.addPointBtns();
        view.refreshWindow();


    }

  //draw stuff---------------------------------------------

//main methods for starting operation*****------------
    boolean followMouse=false;
    private void clickOnMaterial(Point p) {
        switch (String.valueOf(getActiveTool())){
            case "txt":
                setTextPoint(p);
                break;
            case "line":
                lineBegin(p);
                followMouse=true;
                break;

        }


    }

    private void mouseMovedFollow(Point p){
        switch (String.valueOf(getActiveTool())){
            case "txt":
                break;
            case "line":
                mouseFollowLn(p);
                break;

        }
    }

    //----------------------------------------------------------------


    public void lineClickPoint(PointModel p){
           
    }


    public void lineBegin(Point p){
       if(toolState!=ToolStatesE.lineClickDot){
          if(toolState==ToolStatesE.lineTracking){

          }
       }
       LineModel.preparePointLine(p,model);
       if(LineModel.finished){
           for (LineModel model: model.getLineModels()) {
               model.pointBtnA.addActionListener(pointAL);
               model.pointBtnB.addActionListener(pointAL);
           }
       }

    }
    public void mouseFollowLn(Point p){

        if(!LineModel.started){return;}
       LineModel.drawTempLine(p);
       view.refreshWindow();
    }



    public void setTextPoint(Point p){
        TextModel.prepTxtLoc(new PointModel(p));
             DrawTextController txtController = new DrawTextController(this);
    }
    public void drawTxt(TextModel textModel){
            model.addTxt(textModel);
            view.refreshWindow();
    }

    public void setCirclePoint(Point p){

    }
    public void mouseFollowCircle(Point p){}


    public void mouseFollowPoint(Point p){}


}







