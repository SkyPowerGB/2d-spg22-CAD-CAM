package View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools;

import Enums.ControllerActionEventNamesE;
import Enums.WorkspaceToolBtnsE;
import View.MainViewV2.MainViewParts.TopPanel.TopPanelAppearance;
import View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools.parts.WorkspaceToolBtn;
import controller.standard.Controller;

import javax.swing.*;
import java.awt.*;

public class WorkspaceToolsPanel extends JPanel {
    private final Controller workspaceToolBtnController;
    public WorkspaceToolsPanel(Controller workspaceToolBtnController){
        this.workspaceToolBtnController=workspaceToolBtnController;
        this.setPreferredSize(new Dimension(getMaximumSize().width,TopPanelAppearance.topPanelHeight));
        this.setLayout(new FlowLayout());
        this.setBackground(null);

        for(WorkspaceToolBtnsE e:WorkspaceToolBtnsE.values()){

            addNewWToolBtn(e);
        }

    }


    public void addNewWToolBtn(WorkspaceToolBtnsE wToolBtnNm){
       this.add(new WorkspaceToolBtn(workspaceToolBtnController, ControllerActionEventNamesE.wToolBtn,wToolBtnNm));
    }


}
