package controller.AEclasses;

import controller.callbacks.MouseCallBacks;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class WorkspaceMouseMotionListener implements MouseMotionListener {
    MouseCallBacks mouseCallBacks;
    public WorkspaceMouseMotionListener(MouseCallBacks mouseCallBacks){
        this.mouseCallBacks=mouseCallBacks;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        int currX = e.getX();
        int currY = e.getY();
        mouseCallBacks.dragged(new Point(currX,currY));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
           mouseCallBacks.moved(new Point(e.getX(),e.getY()));
    }
}
