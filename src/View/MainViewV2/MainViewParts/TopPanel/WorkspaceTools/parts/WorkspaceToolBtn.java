package View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools.parts;

import Enums.ControllerActionEventNamesE;
import Enums.WorkspaceToolBtnsE;
import View.MainViewV2.MainViewParts.TopPanel.TopPanelAppearance;
import controller.standard.Controller;
import helpers.TextureHelper;

import javax.swing.*;

public class WorkspaceToolBtn extends JButton {
    private final WorkspaceToolBtnsE toolName;

   public WorkspaceToolBtn(Controller controller,ControllerActionEventNamesE eventName,WorkspaceToolBtnsE toolName){
       this.toolName=toolName;
       this.addActionListener(e->controller.handleAction(e,eventName));
       this.setPreferredSize(TopPanelAppearance.workToolBtnDim);
       this.setSize(TopPanelAppearance.workToolBtnDim);
       this.setIcon(TextureHelper.getControlBtnTexture(toolName,TopPanelAppearance.workspaceToolBtnHeight));


   }


   public WorkspaceToolBtnsE getToolName(){
       return toolName;
   }


}
