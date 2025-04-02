package controller;

import View.MainView;
import ViewParts.DrawingBoard;
import controller.AEclasses.PointAL;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.AEclasses.WorkspaceMouseWheelListener;
import controller.callbacks.MouseCallBacks;
import controller.callbacks.NewFileCallBack;
import controller.callbacks.PointPressedCallBack;
import helpers.*;
import helpers.enums.FileOptionsE;
import helpers.enums.ToolStatesE;
import helpers.helperModels.LineEqCalculator;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import helpers.enums.ToolNamesE;

import static helpers.improvedToolBox.*;

public class MainController implements NewFileCallBack, MouseCallBacks, PointPressedCallBack {
    MainView view;

    WorkspaceMouseWheelListener mouseWheelListener;
    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;

    PointAL pointAL;



    FileDataModel data;
    DrawingBoard board;

    LayerController layerController=new LayerController(view,data);
    int currLineIndex = -1;

    private boolean middleButtonPressed;
    private int prevX, prevY;
    private int offsetX, offsetY;

    public MainController() {

        // prep callbacks---------------------------------------------------------------------------------
        mouseWheelListener = new WorkspaceMouseWheelListener(this);
        workspaceMouseListener = new WorkspaceMouseListener(this);
        mouseMotionListener = new WorkspaceMouseMotionListener(this);
        pointAL = new PointAL(this);


        // create view
        view = new MainView();

        layerController.setMainView(view);


        // get  buttons and setup their listeners -------------------------------------------------
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

        // get workspace + add listeners ------------------------------------------------------------------------

        JPanel workspace = view.getWorkspacePanel();

        workspace.addMouseWheelListener(mouseWheelListener);

        workspace.addMouseListener(workspaceMouseListener);

        workspace.addMouseMotionListener(mouseMotionListener);


        // select layer btn
        view.getAddLayerBtn().addActionListener(e -> {
            layerController.addLayer();
        });

        // point connect helper tool
        view.getWorkspaceShowPointsBtn().addActionListener(e->{showConnectPointsBtn();});

      //--------------------------------------------------------------------------------

    }
    //-------  prepare layers map----------------------------------------------------------

    private void menuOptionClicked(String name) {


        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
            System.out.println("newFileCreated");
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

        LineEqCalculator ln;

        double moveX = view.getMaterial().getSize().getWidth() * (ammount / 2);

        int nextX;


        Point materialPos = view.getMaterialPos();

        if (relation == 3 || relation == 4) {
            ln = new LineEqCalculator(position, materialPos);
        } else {
            ln = new LineEqCalculator(materialPos, position);
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
    public void onFileCreate(FileDataModel data) {
        this.data = data;
        layerController.setFileData(data);
        view.addMaterial(data);
        view.refreshWindow();
        board = view.getBoard();

        layerController.setBoard(board);
        layerController.setMainView(view);
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

    }

    @Override
    public void moved(Point currPos) {
        if(true){
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
                lineBegin(p);
            }else if(improvedToolBox.getActiveTool()==ToolNamesE.selectPoint){
                movePointSelect(p);
            }
    }
    public void showConnectPointsBtn(){

        board=view.getBoard();
        board.removeAllUComponents();
        setALtoPoints();
        board.addPointBtns();
        view.refreshWindow();


    }

  //draw stuff---------------------------------------------

//main methods for starting operation with tool****------------
    boolean followMouse=false;
    private void clickOnMaterial(Point p) {
        switch (String.valueOf(getActiveTool())){
            case "txt":

                break;
            case "line":
                lineBegin(new PointModel(p));
                followMouse=true;
                break;

        }
        if(activeTool==ToolNamesE.selectPoint){
            movePointSelect(new PointModel(p));
        }


    }
    private void mouseMovedFollow(Point p){
        switch (String.valueOf(getActiveTool())){
            case "txt":
                break;
            case "line":
                mouseFollowLn(new PointModel(p));
                break;

        }
        if(activeTool== ToolNamesE.selectPoint){
         movePointMouse(new PointModel(p));
        }
    }
    //------------tool methods
    // ----------------------------------------------------

    public void lineBegin(PointModel point){
            System.out.println("line begin");
       LineModel.lineBeginImproved(point, layerController.getLayerDrawingsModel());
       view.refreshWindow();
    }
    public void mouseFollowLn(PointModel point){

      LineModel.lineMoving(point);
      view.refreshWindow();

    }

    PointModel pointToMove;
    public void movePointSelect(PointModel p){
        System.out.println("move point select");
         if(toolState==ToolStatesE.inactive){
             toolState=ToolStatesE.pointSelectMoving;
             pointToMove=p;
         }
else if(toolState==ToolStatesE.pointSelectMoving){
            pointToMove.setNewLocation(p);
            pointToMove=null;
            toolState=ToolStatesE.inactive;
            view.refreshWindow();
         }
else if(true){


         }



    }
    public void movePointMouse(PointModel p){
        if(toolState==ToolStatesE.pointSelectMoving){
            pointToMove.setNewLocation(p);
            view.refreshWindow();
        }
    }



    private void setALtoPoints(){

        for(LineModel ln: layerController.getLayerDrawingsModel().getLineModels()){
            ln.pointBtnB.removeActionListener(pointAL);
            ln.pointBtnA.removeActionListener(pointAL);
            ln.pointBtnB.addActionListener(pointAL);
            ln.pointBtnA.addActionListener(pointAL);
        }




    }
}







