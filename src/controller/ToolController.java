package controller;

import View.MainView;
import View.ViewUIComponents.DrawingBoard;
import controller.AEclasses.PointAL;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.callbacks.MouseCallBacks;
import controller.callbacks.PointPressedCallBack;
import helpers.ActiveBtns;
import helpers.PointScaler;
import helpers.TextureHelper;
import helpers.enums.ToolNamesE;
import helpers.enums.ToolStatesE;
import helpers.ToolTracker;
import model.LineModel;
import model.PointModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static helpers.ToolTracker.*;
import static helpers.ToolTracker.toolState;

public class ToolController implements MouseCallBacks, PointPressedCallBack {
    MainView view;
    LayerController layerController;

    PointAL pointAL;
    public  ToolController(MainView view,LayerController layerController) {
        this.view=view;
        this.layerController=layerController;
        view.getWorkspaceShowPointsBtn().addActionListener(e->{showConnectPointsBtn();});
        JPanel workspacePanel=   view.getWorkspacePanel();

        WorkspaceMouseListener ms=new WorkspaceMouseListener(this);
        WorkspaceMouseMotionListener mM=new WorkspaceMouseMotionListener(this);
         workspacePanel.addMouseListener(ms);
         workspacePanel.addMouseMotionListener(mM);

        ArrayList<JButton> toolBtns = view.getToolBtns();
        for (JButton btn : toolBtns) {
            btn.setBackground(null);
            btn.setBorder(null);
            btn.addActionListener(e -> {
                toolBtnPressed((JButton) e.getSource());
            });
        }

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
            click= PointScaler.getDefaultPoint(click,view.getScale());
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
        LineModel.lineBeginImproved(point, view.getBoardsCurrDrawingModel());
        view.refreshWindow();
    }
    public void mouseFollowLn(PointModel point){

        LineModel.lineMoving(point);
        view.refreshWindow();

    }

    PointModel pointToMove;
    public void movePointSelect(PointModel p){
        System.out.println("move point select");
        if(toolState== ToolStatesE.inactive){
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
    public void showConnectPointsBtn(){

        DrawingBoard board = view.getBoard();
        board.removeAllUComponents();
        setALtoPoints();
        board.addPointBtns();
        view.refreshWindow();


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
}
