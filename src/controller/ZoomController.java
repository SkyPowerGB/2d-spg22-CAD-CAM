package controller;

import View.MainView;
import controller.AEclasses.WorkspaceMouseWheelListener;

import controller.callbacks.ZoomCallBack;
import helpers.helperModels.LineEqCalculator;

import javax.swing.*;
import java.awt.*;

public class ZoomController implements ZoomCallBack {

    MainView view;

    public ZoomController(MainView view){
        this.view=view;

        WorkspaceMouseWheelListener mouseWheelListener=new WorkspaceMouseWheelListener(this);
        JPanel workspace=view.getWorkspacePanel();
        workspace.addMouseWheelListener(mouseWheelListener);


    }

    @Override
    public void zoomIn(Point p) {
        zoom_in_out(1, p, true);
    }

    @Override
    public void zoomOut(Point p) {

        zoom_in_out(1, p, false);
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
}
