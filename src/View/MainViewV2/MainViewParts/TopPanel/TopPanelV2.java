package View.MainViewV2.MainViewParts.TopPanel;

import Enums.WorkspaceToolBtnsE;
import View.MainViewV2.MainViewParts.TopPanel.WorkspaceTools.WorkspaceToolsPanel;
import controller.standard.Controller;

import javax.swing.*;
import java.awt.*;

public class TopPanelV2 extends JPanel {

    private Controller controller;
    private WorkspaceToolsPanel wToolsPanel;
public TopPanelV2(Controller controller){
    this.setBackground(TopPanelAppearance.topPanelBackgroundClr);
    this.setPreferredSize(new Dimension(getMaximumSize().width, TopPanelAppearance.topPanelHeight));
    this.setLayout(new GridLayout(1,20));
    this.setToolTipText("workspaceTools");

    this.controller=controller;
    wToolsPanel=new WorkspaceToolsPanel(controller);
    this.add(wToolsPanel);
}

public  WorkspaceToolsPanel getWToolsPanel(){
    return  wToolsPanel;
}
public void addWorkspaceToolBtn(WorkspaceToolBtnsE toolNm){
    wToolsPanel.addNewWToolBtn(toolNm);
}









}
