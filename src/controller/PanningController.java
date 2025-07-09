package controller;

import View.MainView;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.callbacks.MouseCallBacks;
import model.V2models.ViewStateModel;

import javax.swing.*;
import java.awt.*;

public class PanningController implements MouseCallBacks {

    MainView view;

    private boolean middleButtonPressed;
    private int prevX, prevY;
    private int offsetX, offsetY;

    private ViewStateModel viewStateModel;

    public  PanningController(MainView view, ViewStateModel viewStateModel){
        this.view=view;
        this.viewStateModel=viewStateModel;

        WorkspaceMouseListener workspaceMouseListener = new WorkspaceMouseListener(this);
        WorkspaceMouseMotionListener mouseMotionListener = new WorkspaceMouseMotionListener(this);
        JPanel workspace=view.getWorkspacePanel();

        workspace.addMouseListener(workspaceMouseListener);

        workspace.addMouseMotionListener(mouseMotionListener);


    }

    @Override
    public void click(Point p) {

    }

    @Override
    public void pressedMidBtn(Point location) {

        middleButtonPressed = true;
        prevX = location.x;
        prevY = location.y;
        offsetX =viewStateModel.getMaterialLocation().x - prevX;
        offsetY = viewStateModel.getMaterialLocation().y- prevY;

    }

    @Override
    public void dragged(Point where) {

        if (middleButtonPressed) {
            int currX = where.x;
            int currY = where.y;
            int newPosX = offsetX + currX;
            int newPosY = offsetY + currY;

            viewStateModel.setMaterialLocation(new Point(newPosX, newPosY));
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

    }



}
