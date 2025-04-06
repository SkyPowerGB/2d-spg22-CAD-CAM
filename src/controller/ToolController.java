package controller;

import View.MainView;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.callbacks.MouseCallBacks;
import helpers.PointScaler;
import helpers.TextureHelper;
import Enums.ToolNamesE;
import model.LineModel;
import model.PointModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static helpers.ToolStateTracker.getActiveTool;
import static helpers.ToolStateTracker.setActiveTool;

public class ToolController implements MouseCallBacks {

    MainView view;

    public ToolController(MainView view){
        this.view=view;
        ArrayList<JButton> toolBtns = view.getToolBtns();
        for (JButton btn : toolBtns) {
            btn.setBackground(null);
            btn.setBorder(null);
            btn.addActionListener(e -> {
                toolBtnPressed((JButton) e.getSource());
            });
        }

        WorkspaceMouseListener workspaceMouseListener = new WorkspaceMouseListener(this);
        WorkspaceMouseMotionListener mouseMotionListener = new WorkspaceMouseMotionListener(this);

        view.getWorkspacePanel().addMouseMotionListener(mouseMotionListener);
        view.getWorkspacePanel().addMouseListener(workspaceMouseListener);
    }

    private void toolBtnPressed(JButton btn) {


        setActiveTool(ToolNamesE.valueOf(btn.getName()));

        for(JButton tBtn:view.getToolBtns()){
            tBtn.setIcon(TextureHelper.getToolBtnTexture(ToolNamesE.valueOf(tBtn.getName()),50));
        }


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


    //main methods for starting operation with tool****------------
    boolean followMouse=false;
    private void clickOnMaterial(Point p) {
        switch (String.valueOf(getActiveTool())){

            case "line":
                lineBegin(new PointModel(p));
                followMouse=true;
                break;

        }



    }
    private void mouseMovedFollow(Point p){
        switch (String.valueOf(getActiveTool())){

            case "line":
                mouseFollowLn(new PointModel(p));
                break;

        }

    }

    //------------tool methods


    public void lineBegin(PointModel point){
        System.out.println("line begin");
        LineModel.lineBeginImproved(point, view.getBoardsCurrDrawingModel());
        view.refreshWindow();
    }
    public void mouseFollowLn(PointModel point){

        LineModel.lineMoving(point);
        view.refreshWindow();

    }

}
