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

    JPanel bottomPanel;
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

    JPanel drawnHeightLayer;
    JPanel vectorLayer;
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
    Dimension materialOriginalDim;

    JLabel scaleLbl;
    double scale=1;

    private Dimension recalcMaterialSize(){
        return new Dimension((int)(materialOriginalDim.width*scale), (int)(materialOriginalDim.height*scale));
    }

    public ArrayList<JButton> getToolBtns() {
        return toolBtns;
    }

    public MainView() {

        mainFrame = new ShownWindow();
        layeredPane = new JLayeredPane();

        workspacePanel = new JPanel();
        workspacePanel.setBackground(new Color(107, 107, 107));
        workspacePanel.setLayout(null);

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

        vectorLayer=new JPanel();
        vectorLayer.setLayout(null);

        zStep=new LabeledInput();

        zStep.input.setText("0.1");
        zStep.label.setText("Z-resolution mm");


        bottomPanel=new JPanel();
        bottomPanel.setPreferredSize(new Dimension(mainFrame.getWidth(),50));

        scaleLbl=new JLabel();
        scaleLbl.setText(String.valueOf(scale));

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


        bottomPanel.add(scaleLbl,BorderLayout.EAST);


        programPanel.add(leftPanel, BorderLayout.WEST);
        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(upperPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);
        programPanel.add(bottomPanel,BorderLayout.SOUTH);


        layeredPane.add(programPanel, JLayeredPane.DEFAULT_LAYER);


        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }

    public double getScale() {
        return scale;
    }

    public JPanel getWorkspacePanel() {
        return workspacePanel;
    }

    public JPanel getMaterial() {
        return material;
    }


    public void setScale(double scale) {
        this.scale = scale;
    }
    public void setMaterialLoc(Point p){
        material.setLocation(p);
        mainFrame.refresh();
    }

    public ArrayList<JMenuItem> getMenuOptions() {
        return menuOptions;
    }

    public void loadMaterial(FileData data){
        Dimension materialDim=new Dimension(((int)data.materialDim.getWidth()*10), ((int)data.materialDim.getHeight()*10));
        materialOriginalDim=materialDim;
        materialDim=this.recalcMaterialSize();
        material.setSize(materialDim);

      int Width=  workspacePanel.getWidth()/2-(((int)data.materialDim.getWidth()*10)/2);
        if(Width<0){
            Width=0;
        }

        material.setLocation(Width,0);

      workspacePanel.add(material);
      mainFrame.setVisible(true);
    }

    public void scale(){
        scaleLbl.setText(String.valueOf(scale));
        Dimension dim=this.recalcMaterialSize();
        material.setSize(dim);
        mainFrame.setVisible(true);
    }

    public void refreshWindow(){
        mainFrame.refresh();
    }

    public Point getMaterialPos(){return material.getLocation();}
}
