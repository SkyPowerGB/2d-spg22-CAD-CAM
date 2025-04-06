package View.MainViewV2.MainViewParts.LeftSidePanel.parts.Tools;

import controller.standard.Controller;
import Enums.ToolNamesE;

import javax.swing.*;

public class ToolBtn extends JButton {
  private ToolNamesE name;

  // improved... za preko controller ->handle event
    public ToolBtn(Controller controller,ToolNamesE name){
        this.name=name;
        this.addActionListener(controller::handleAction);

    }


// outdated za preko callbakca
    public ToolBtn(){

    }

    public ToolNamesE getToolBtnName() {
        return name;
    }
}
