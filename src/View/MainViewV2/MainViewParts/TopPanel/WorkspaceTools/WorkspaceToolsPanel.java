package View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools;

import Enums.ControllerActionEventNamesE;
import Enums.WorkspaceToolBtnsE;
import View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools.parts.WorkspaceToolBtn;
import controller.standard.Controller;

import javax.swing.*;
import java.awt.*;

public class WorkspaceToolsPanel extends JPanel {
    private final Controller workspaceToolBtnController;
    public WorkspaceToolsPanel(Controller workspaceToolBtnController){
        this.workspaceToolBtnController=workspaceToolBtnController;
        this.setLayout(new GridLayout(1,30));
        this.setBackground(Color.cyan);

    }
    public void addNewWToolBtn(WorkspaceToolBtnsE wToolBtnNm){
       this.add(new WorkspaceToolBtn(workspaceToolBtnController, ControllerActionEventNamesE.wToolBtn,wToolBtnNm));
    }


}
