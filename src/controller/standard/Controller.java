package controller.standard;

import Enums.ControllerActionEventNamesE;

import java.awt.event.ActionEvent;

public abstract class Controller {
    public abstract void handleAction(ActionEvent e) ;

    // improved handle action
    public abstract void handleAction(ActionEvent e, ControllerActionEventNamesE action);

}