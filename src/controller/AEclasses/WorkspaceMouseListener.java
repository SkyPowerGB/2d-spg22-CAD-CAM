package controller.AEclasses;

import controller.callbacks.MouseCallBacks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorkspaceMouseListener implements MouseListener {
    MouseCallBacks mouseCallBacks;

    public WorkspaceMouseListener(MouseCallBacks mouseCallBacks) {
        this.mouseCallBacks = mouseCallBacks;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          mouseCallBacks.click(new Point(e.getX(),e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (SwingUtilities.isMiddleMouseButton(e)) {

            int prevX = e.getX();
            int prevY = e.getY();

            mouseCallBacks.pressedMidBtn(new Point(prevX, prevY));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isMiddleMouseButton(e)) {


            mouseCallBacks.releasedMidBtn();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
           mouseCallBacks.entered();

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
