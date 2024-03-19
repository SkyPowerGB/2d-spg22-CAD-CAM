package View;

import ViewParts.*;
import helpers.TextureHelper;
import helpers.enums.FileOptionsE;
import helpers.enums.ToolNamesE;
import helpers.enums.menuItemsE;
import model.FileData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainView {
    ShownWindow mainFrame;
    JLayeredPane layeredPane;
    JPanel programPanel;
    JPanel workspacePanel;
    JPanel   leftPanel;
    JPanel toolPanel;
    JPanel toolOptions;
    JPanel settingsPanel;
    JScrollPane workspaceScroll;
    JScrollPane leftScrollPane;
    JScrollPane rightScrollPane;
    JScrollPane workspaceScrollPane;


    LabeledInput speed;
    LabeledInput acceleration;
    LabeledInput zStep;

    ArrayList<JButton> toolBtns;
    JPanel rightSidePanel;
    JPanel upperPanel;
    JMenuBar menuBar;
    ArrayList<JMenu> menuItems ;
    ArrayList<JMenuItem> menuOptions;

    JPanel material ;


    public MainView() {

        mainFrame = new ShownWindow();
        layeredPane = new JLayeredPane();
        workspacePanel = new JPanel();
        workspacePanel.setBackground(new Color(107, 107, 107));
        leftPanel = new JPanel();
        rightSidePanel = new JPanel();
        upperPanel = new JPanel();
        programPanel = new JPanel();
        menuBar=new JMenuBar();
        menuItems=new ArrayList<>();
        menuOptions=new ArrayList<>();
        toolBtns =new ArrayList<>();
        toolPanel =new JPanel();
        toolOptions = new JPanel();
        leftScrollPane = new JScrollPane();

         material = new JPanel();
         material.setBackground(Color.white);

        toolPanel.setLayout(new GridLayout(5,3,2,2));
        toolPanel.setBackground(null);
        for (ToolNamesE tool: ToolNamesE.values()) {

              JButton toolBtn= new JButton();
             toolBtn.setPreferredSize(new Dimension(50,50));
             toolBtn.setIcon(TextureHelper.getToolBtnTexture(tool,50));
             toolBtn.setName(tool.toString());
             toolPanel.add(toolBtn);
             toolBtns.add(toolBtn);
        }
        for (menuItemsE item:menuItemsE.values()) {
            JMenu menu= new JMenu(item.toString());
            menuItems.add(menu);
            menuBar.add(menu);
        }
        for(FileOptionsE item:FileOptionsE.values()){
            JMenuItem menuItem=new JMenuItem(item.toString());
            menuItem.setName(item.toString());
            menuOptions.add(menuItem);
            menuItems.get(0).add(menuItem);
        }


        Dimension frameSize=mainFrame.getSize();
        mainFrame.setTitle("2D SPG-22 CAM");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setJMenuBar(menuBar);

        menuBar.setPreferredSize(new Dimension(mainFrame.getWidth(),50));

        programPanel.setLayout(new BorderLayout());



        leftPanel.setBackground(Color.cyan);
        leftPanel.setPreferredSize(new Dimension(200, frameSize.height));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(toolOptions,BorderLayout.SOUTH);
        leftPanel.add(toolPanel,BorderLayout.NORTH);


        rightSidePanel.setBackground(Color.ORANGE);
        rightSidePanel.setPreferredSize(new Dimension(100, frameSize.height));

        upperPanel.setBackground(Color.blue);
        upperPanel.setPreferredSize(new Dimension(frameSize.width ,50));



        programPanel.add(leftPanel, BorderLayout.WEST);
        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(upperPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);

        layeredPane.add(programPanel, JLayeredPane.DEFAULT_LAYER);


        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }

    public ArrayList<JMenuItem> getMenuOptions() {
        return menuOptions;
    }

    public void loadMaterial(FileData data){
        material.setPreferredSize(new Dimension(((int)data.materialDim.getWidth()*10),
                ((int)data.materialDim.getHeight()*10)));
      workspacePanel.add(material);
      mainFrame.setVisible(true);
    }
}
