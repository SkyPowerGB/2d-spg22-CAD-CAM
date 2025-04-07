package View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools.parts;

import Enums.ControllerActionEventNamesE;
import Enums.WorkspaceToolBtnsE;
import View.MainViewV2.MainViewParts.TopPanel.TopPanelAppearance;
import controller.standard.Controller;

import javax.swing.*;

public class WorkspaceToolBtn extends JButton {
    private final WorkspaceToolBtnsE toolName;

   public WorkspaceToolBtn(Controller controller,ControllerActionEventNamesE eventName,WorkspaceToolBtnsE toolName){
       this.toolName=toolName;
       this.addActionListener(e->controller.handleAction(e,eventName));
       this.setSize(TopPanelAppearance.workToolBtnDim);

   }


   public WorkspaceToolBtnsE getToolName(){
       return toolName;
   }


}
