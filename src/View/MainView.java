package View;

import View.ViewUIComponents.*;
import helpers.TextureHelper;
import helpers.enums.FileOptionsE;
import helpers.enums.ToolNamesE;
import helpers.enums.WorkspaceToolBtnsE;
import helpers.enums.menuItemsE;
import helpers.helperModels.RectangleSpanHelper;
import model.FileDataModel;
import model.LayerDrawingsModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainView {

   private JButton hideConnectPoints, showConnectPoints, workspaceHomeLoc,workspaceScaleDefault;

    private JPanel bottomPanel;
    private ShownWindow mainFrame;

    private JPanel programPanel;
    private JPanel workspacePanel;
    private JPanel leftPanel;
    private  JPanel toolPanel;

    private  JPanel workspaceToolPanel;

    private  JButton addLayerBtn;




    private  JPanel layerSidebarPanel;

    private  JPanel layersPanel;

    private LabeledInput zStep;
    private  ArrayList<JButton> toolBtns;
    private   JPanel rightSidePanel;
    private JPanel workspaceToolsTopPanel;
    private JMenuBar menuBar;
    private ArrayList<JMenu> menuItems;
    private  ArrayList<JMenuItem> menuOptions;
    private LayerDrawingsModel drawingsModel;
    private  DrawingBoard board;
    private  ScalablePanel material;
    private  Dimension materialOriginalDim;

    private  JLabel scaleLbl;
    private   double scale = 1;




    ScalableLayeredPane materialLayers;


    ArrayList<ScalablePanel> scalablePanels;

    public MainView() {

        initView();
    }



    private void initView() {
        int layerPanelWidth = 90;

        materialLayers = new ScalableLayeredPane();
        scalablePanels = new ArrayList<>();
        mainFrame = new ShownWindow();

        //----------------------------------------
          // layers control panel---------------------------
        // main layerSidebar
        layerSidebarPanel = new JPanel(new BorderLayout());
        layerSidebarPanel.setPreferredSize(new Dimension(layerPanelWidth, mainFrame.getHeight()));

        //add layerBtn
        addLayerBtn = new JButton("+");
        addLayerBtn.setPreferredSize(new Dimension(100, 50));

        //layersPanel setup
        layersPanel = new JPanel();
        layersPanel.setLayout(new GridLayout(64, 1));

        //add vertical scroll to the layersPanel
        JScrollPane layerListVerticalScrollPane = new JScrollPane(layersPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // layer sidebar add layersPanels
        layerSidebarPanel.add(addLayerBtn, BorderLayout.NORTH);
        layerSidebarPanel.add(layerListVerticalScrollPane, BorderLayout.CENTER);
        //----------------------------------------

        drawingsModel = new LayerDrawingsModel();
        board = new DrawingBoard(drawingsModel);


        workspacePanel = new JPanel();
        workspacePanel.setToolTipText("workspace");
        workspacePanel.setBackground(new Color(107, 107, 107));
        workspacePanel.setLayout(null);

        leftPanel = new JPanel();
        rightSidePanel = new JPanel();
        workspaceToolsTopPanel = new JPanel();
        programPanel = new JPanel();
        menuBar = new JMenuBar();
        menuItems = new ArrayList<>();
        menuOptions = new ArrayList<>();
        toolBtns = new ArrayList<>();
        toolPanel = new JPanel();



        zStep = new LabeledInput();

        zStep.input.setText("0.1");
        zStep.label.setText("Z-resolution mm");


        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));

        scaleLbl = new JLabel();
        scaleLbl.setText(String.valueOf(scale));

        material = new ScalablePanel();
        material.setBackground(Color.white);
        material.setLayout(null);

        board.setBackground(Color.ORANGE);
        materialLayers.add(board);
        board.setSize(500, 500);
        materialLayers.setSize(500, 500);
        material.add(materialLayers);

        toolPanel.setLayout(new GridLayout(10, 2, 2, 2));
        toolPanel.setBackground(null);

        for (ToolNamesE tool : ToolNamesE.values()) {

            JButton toolBtn = new JButton();
            toolBtn.setSize(new Dimension(50, 50));
            toolBtn.setIcon(TextureHelper.getToolBtnTexture(tool, 50));
            toolBtn.setName(tool.toString());
            toolPanel.add(toolBtn);
            toolBtns.add(toolBtn);
        }
        for (menuItemsE item : menuItemsE.values()) {
            JMenu menu = new JMenu(item.toString());
            menuItems.add(menu);
            menuBar.add(menu);
        }
        for (FileOptionsE item : FileOptionsE.values()) {
            JMenuItem menuItem = new JMenuItem(item.toString());
            menuItem.setName(item.toString());
            menuOptions.add(menuItem);
            menuItems.get(0).add(menuItem);
        }


        Dimension frameSize = mainFrame.getSize();
        mainFrame.setTitle("2.5D SP-24 CAD/CAM");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setJMenuBar(menuBar);

        menuBar.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));

        programPanel.setLayout(new BorderLayout());


        leftPanel.setBackground(Color.cyan);
        leftPanel.setPreferredSize(new Dimension(200, frameSize.height));
        leftPanel.setLayout(new BorderLayout());

        leftPanel.add(toolPanel, BorderLayout.WEST);
        leftPanel.add(layerSidebarPanel, BorderLayout.EAST);



        rightSidePanel.setBackground(Color.ORANGE);
        rightSidePanel.setPreferredSize(new Dimension(100, frameSize.height));
        rightSidePanel.setToolTipText("RightSidePanel");

        workspaceToolsTopPanel.setBackground(Color.blue);
        workspaceToolsTopPanel.setPreferredSize(new Dimension(frameSize.width, 50));
        workspaceToolsTopPanel.setLayout(new GridLayout(1,20));
        workspaceToolsTopPanel.setToolTipText("workspaceTools");

        workspaceToolPanel =new JPanel();
        workspaceToolPanel.setLayout(new GridLayout(1,3));
        workspaceToolPanel.setBackground(Color.cyan);
;

        int wrkSpcToolsBtnSize =60;

        workspaceHomeLoc = new JButton();
        workspaceHomeLoc.setIcon(TextureHelper.getControlBtnTexture(WorkspaceToolBtnsE.locZZ, wrkSpcToolsBtnSize));

        showConnectPoints=new JButton();
        showConnectPoints.setIcon(TextureHelper.getControlBtnTexture(WorkspaceToolBtnsE.showConnectDots, wrkSpcToolsBtnSize));

         hideConnectPoints=new JButton();
         hideConnectPoints.addActionListener(e->{board.removeAll(); mainFrame.refresh();});


        workspaceScaleDefault=new JButton();
        workspaceScaleDefault.setIcon(TextureHelper.getControlBtnTexture(WorkspaceToolBtnsE.scaleDefault, wrkSpcToolsBtnSize));

        workspaceScaleDefault.addActionListener(e->{setScale(1);});
        workspaceHomeLoc.addActionListener(e->{material.setLocation(0,0);});

        workspaceToolPanel.add(hideConnectPoints);
        workspaceToolPanel.add(showConnectPoints);
        workspaceToolPanel.add(workspaceHomeLoc);
        workspaceToolPanel.add(workspaceScaleDefault);




       workspaceToolsTopPanel.add(new JPanel());
        workspaceToolsTopPanel.add(workspaceToolPanel);
        workspaceToolsTopPanel.add(new JPanel());






        bottomPanel.add(scaleLbl,BorderLayout.EAST);

        programPanel.add(leftPanel, BorderLayout.WEST);
        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(workspaceToolsTopPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);
        programPanel.add(bottomPanel, BorderLayout.SOUTH);





        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }









    public ArrayList<JButton> getToolBtns() {
        return toolBtns;
    }
    public void addMaterial(FileDataModel data) {
        Dimension materialDim = new Dimension(((int) data.materialDim.getWidth() * 10),
                ((int) data.materialDim.getHeight() * 10));
        materialOriginalDim = materialDim;


        materialLayers.setDefaultSize(materialDim);
        materialLayers.setSize(materialDim);
        materialLayers.setScale(1);


        board.setDefaultSize(materialDim);
        board.setSize(materialDim);
        scalablePanels.add(board);

        board.setScale(1);


        material.setDefaultSize(materialDim.width, materialDim.height);
        material.setScale(scale);


        int Width = workspacePanel.getWidth() / 2 - (((int) data.materialDim.getWidth() * 10) / 2);
        if (Width < 0) {
            Width = 0;
        }

        material.setLocation(Width, 0);

        workspacePanel.add(material);
        mainFrame.setVisible(true);
    }

    private void zoomInOut() {
        scaleLbl.setText(String.valueOf(scale));

        material.setScale(scale);
        materialLayers.setScale(scale);
        board.setScale(scale);

        mainFrame.setVisible(true);
        for (ScalablePanel comp : scalablePanels) {

            comp.setScale(scale);
        }
    }

    //public methods
    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        zoomInOut();
    }

    public JPanel getWorkspacePanel() {
        return workspacePanel;
    }

    public ScalablePanel getMaterial() {
        return material;
    }

    public RectangleSpanHelper getMaterialSpan() {
        Point matP = material.getLocation();
        Point matE = new Point(matP.x + material.getWidth(), matP.y + material.getHeight());

        return new RectangleSpanHelper(matP, matE);
    }

    public Point getMaterialPos() {
        return material.getLocation();
    }

    public boolean isPointOnMaterial(Point p) {
        return getMaterialSpan().isPointInRectangle(p);
    }

    public void setMaterialLoc(Point p) {
        material.setLocation(p);
        mainFrame.refresh();
    }

    public ArrayList<JMenuItem> getMenuOptions() {
        return menuOptions;
    }


    public void refreshWindow() {

        mainFrame.refresh();
    }


    public Point recalcWorkspaceToMaterial(@NotNull Point p) {
        Point matLoc = material.getLocation();
        return new Point((p.x - (matLoc.x)), (p.y - (matLoc.y)));

    }

    // get Board that draws graphics
    public DrawingBoard getBoard() {
        return board;
    }

    // set new model for board to draw
    public void boardSetDrawingsModel(LayerDrawingsModel model){
        board.setDrawingsModel(model);
        this.refreshWindow();
    }

    // get currentBoardModel
    public LayerDrawingsModel getBoardsCurrDrawingModel(){

        return  getBoard().getDrawingsModel();
    }

    // for action listner ->new layer
    public JButton getAddLayerBtn() {
        return addLayerBtn;
    }

    // add Layer Panel With Btn
    public void addLayer(LayerPanel panel) {

        layersPanel.add(panel);
        mainFrame.refresh();
    }

    // layers are in binary tree -> remove then re-add stuff
    public void removeLayers(){
        layersPanel.removeAll();
  }

    public JButton getWorkspaceShowPointsBtn(){
        return  showConnectPoints;
    }


}
