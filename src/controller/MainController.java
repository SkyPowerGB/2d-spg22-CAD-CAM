package controller;

import View.MainView;
import View.ViewUIComponents.DrawingBoard;
import controller.AEclasses.PointAL;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.callbacks.MouseCallBacks;
import controller.callbacks.NewFileCallBack;
import controller.callbacks.PointPressedCallBack;
import helpers.*;
import helpers.enums.FileOptionsE;
import helpers.enums.ToolStatesE;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import helpers.enums.ToolNamesE;

import static helpers.ToolTracker.*;

public class MainController implements NewFileCallBack, MouseCallBacks, PointPressedCallBack {
    MainView view;


    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;

    PointAL pointAL;



    FileDataModel data;
    DrawingBoard board;

    LayerController layerController=new LayerController(view,data);
    PanningController panningController;
    ZoomController zoomController;
    ToolController toolController;



    public MainController() {

        // prep callbacks---------------------------------------------------------------------------------

        workspaceMouseListener = new WorkspaceMouseListener(this);
        mouseMotionListener = new WorkspaceMouseMotionListener(this);
        pointAL = new PointAL(this);


        // create view
        view = new MainView();

            zoomController =new ZoomController(view);
            panningController=new PanningController(view);


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


    // TO DO razdvojit main controller na: zooming,panning,tools ->(line,),file,

    private void menuOptionClicked(String name) {


        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
            System.out.println("newFileCreated");
        }
    }

    //callbacks
    @Override
    public void onFileCreate(FileDataModel data) {
        this.data = data;
        LayersDataStorageModel.setFileData(data);
        layerController.setFileData(LayersDataStorageModel.getFileData());

        view.addMaterial(LayersDataStorageModel.getFileData());
        view.refreshWindow();


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
    public void click(Point p) {
        if (view.isPointOnMaterial(p)) {
            Point click = view.recalcWorkspaceToMaterial(p);
            click=PointScaler.getDefaultPoint(click,view.getScale());
            clickOnMaterial(click);
        }

    }

    @Override
    public void pressedMidBtn(Point location) {

    }

    @Override
    public void dragged(Point where) {


    }

    @Override
    public void releasedMidBtn() {

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
            if(ToolTracker.getActiveTool().toString()=="line"){
                lineBegin(p);
            }else if(ToolTracker.getActiveTool()==ToolNamesE.selectPoint){
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
            ln.UIPointBtnB.removeActionListener(pointAL);
            ln.UIPointBtnA.removeActionListener(pointAL);
            ln.UIPointBtnB.addActionListener(pointAL);
            ln.UIPointBtnA.addActionListener(pointAL);
        }




    }
}







