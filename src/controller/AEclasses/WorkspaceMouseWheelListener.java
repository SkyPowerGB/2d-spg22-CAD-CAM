package controller.AEclasses;

import controller.callbacks.MouseCallBacks;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WorkspaceMouseWheelListener implements MouseWheelListener {

    MouseCallBacks mouseCallBacks;
    public WorkspaceMouseWheelListener(MouseCallBacks mouseCallBacks){
        this.mouseCallBacks = mouseCallBacks;

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point loc = new Point(x, y);
        if (e.getWheelRotation() > 0) {

            mouseCallBacks.zoomOut(loc);
        } else {
               mouseCallBacks.zoomIn(loc);
        }



    }


}
