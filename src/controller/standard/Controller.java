package controller.standard;

import Enums.ControllerActionEventNamesE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;

public abstract class Controller  {
    public abstract void handleAction(ActionEvent e) ;

    // improved handle action
    public abstract void handleAction(ActionEvent e, ControllerActionEventNamesE action);

   public void click(Point p){

   }

   public void pressedMidBtn(Point location){}


    public void dragged(Point where){};

    public void releasedMidBtn(){};

    public void entered(){};

    public void moved(Point currPos){}


}