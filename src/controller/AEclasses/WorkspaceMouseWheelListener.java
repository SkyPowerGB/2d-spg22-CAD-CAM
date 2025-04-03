package controller.AEclasses;

import controller.callbacks.MouseCallBacks;
import controller.callbacks.ZoomCallBack;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WorkspaceMouseWheelListener implements MouseWheelListener {



    ZoomCallBack zoomCallBack;
    public  WorkspaceMouseWheelListener(ZoomCallBack zoomCallBack){
        this.zoomCallBack=zoomCallBack;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point loc = new Point(x, y);
        if (e.getWheelRotation() > 0) {



            if(zoomCallBack!=null){

              zoomCallBack.zoomOut(loc);
            }


        } else {

            if(zoomCallBack!=null){
                 zoomCallBack.zoomIn(loc);

            }
        }




    }


}
