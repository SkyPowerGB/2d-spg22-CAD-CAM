package View.MainViewV2.MainViewParts.LeftSidePanel.parts.Tools;

import controller.standard.Controller;
import helpers.TextureHelper;
import Enums.ToolNamesE;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToolPanel extends JPanel {


    // old tool btns
    private  ArrayList<JButton> toolBtns=new ArrayList<>();

    // new tool btns type
    private  ArrayList<ToolBtn> toolBtnList=new ArrayList<>();
    public  ToolPanel(){
        this.setLayout(new GridLayout(10, 1, 2, 2));
        this.setSize(100,getMaximumSize().height);
        this.setBackground(null);
        setupToolBtns();
    }

    //stari pristup------------------------------------------------------------
    public void setupToolBtns(){
        for (ToolNamesE tool : ToolNamesE.values()) {

            JButton toolBtn = new JButton();
            toolBtn.setSize(new Dimension(50, 50));
            toolBtn.setIcon(TextureHelper.getToolBtnTexture(tool, 50));
            toolBtn.setName(tool.toString());
            this.add(toolBtn);
            toolBtns.add(toolBtn);
        }
    }

    public ArrayList<JButton> getToolBtns() {
        return toolBtns;
    }

    // novi pristup ---------------------------------------------------------------
    public  void setToolController(Controller controller){
        for (ToolBtn toolBtn : toolBtnList) {
           toolBtn.addActionListener(controller::handleAction);


        }
    }


}
