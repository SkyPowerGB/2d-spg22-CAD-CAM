package View;

import View.MainViewV2.MainViewParts.LeftSidePanel.LeftSidePanelV2;
import View.ViewUIComponents.*;
import helpers.TextureHelper;
import Enums.FileOptionsE;
import Enums.ToolNamesE;
import Enums.WorkspaceToolBtnsE;
import Enums.menuItemsE;
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

    private  JPanel topPanelWorkspaceTools;

    private  JButton addLayerBtn;





    private  ArrayList<JButton> toolBtns;
    private   JPanel rightSidePanel;
    private JPanel workspaceToolsTopPanel;
    private JMenuBar menuBar;
    private ArrayList<JMenu> menuItems;
    private  ArrayList<JMenuItem> menuOptions;
    private LayerDrawingsModel drawingsModel;
    private  DrawingBoard board;
    private  ScalablePanel material;


    private  JLabel scaleLbl;
    private   double scale = 1;




    ScalableLayeredPane materialLayers;


    ArrayList<ScalablePanel> scalablePanels;

    public MainView() {

        initView();
    }




    private void initView() {





        scalablePanels = new ArrayList<>();



        menuItems = new ArrayList<>();

        menuOptions = new ArrayList<>();



        mainFrame = new ShownWindow();

        //----------------------------------------


        drawingsModel = new LayerDrawingsModel();
        board = new DrawingBoard(drawingsModel);


        workspacePanelInit();

        leftPanel = new JPanel();





        rightSidePanel = new JPanel();
        workspaceToolsTopPanel = new JPanel();
        programPanel = new JPanel();
        menuBar = new JMenuBar();





        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));

        scaleLbl = new JLabel();
        scaleLbl.setText(String.valueOf(scale));

        material = new ScalablePanel();
        material.setBackground(Color.white);
        material.setLayout(null);

        materialLayers = new ScalableLayeredPane();

        board.setBackground(Color.ORANGE);
        materialLayers.add(board);
        board.setSize(500, 500);
        materialLayers.setSize(500, 500);
        material.add(materialLayers);




        // menuBarSetup
        menuBarSetup();


        Dimension frameSize = mainFrame.getSize();
        // old code
            mainFrame.setTitle("2.5D SP-24 CAD/CAM");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setJMenuBar(menuBar);
        menuBar.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));
        programPanel.setLayout(new BorderLayout());





        rightSidePanel.setBackground(Color.ORANGE);
        rightSidePanel.setPreferredSize(new Dimension(100, frameSize.height));
        rightSidePanel.setToolTipText("RightSidePanel");

        workspaceToolsTopPanel.setBackground(Color.blue);
        workspaceToolsTopPanel.setPreferredSize(new Dimension(frameSize.width, 50));
        workspaceToolsTopPanel.setLayout(new GridLayout(1,20));
        workspaceToolsTopPanel.setToolTipText("workspaceTools");

        topPanelWorkspaceTools =new JPanel();
        topPanelWorkspaceTools.setLayout(new GridLayout(1,3));
        topPanelWorkspaceTools.setBackground(Color.cyan);
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

        topPanelWorkspaceTools.add(hideConnectPoints);
        topPanelWorkspaceTools.add(showConnectPoints);
        topPanelWorkspaceTools.add(workspaceHomeLoc);
        topPanelWorkspaceTools.add(workspaceScaleDefault);




       workspaceToolsTopPanel.add(new JPanel());
        workspaceToolsTopPanel.add(topPanelWorkspaceTools);
        workspaceToolsTopPanel.add(new JPanel());






        bottomPanel.add(scaleLbl,BorderLayout.EAST);

        initNewLeftPanel();


        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(workspaceToolsTopPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);
        programPanel.add(bottomPanel, BorderLayout.SOUTH);





        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }

    // TEMPORARY SETUP METHODS...--------------------------------------------------------------------------------------------------

    // visual------------------------------------

    // functional-------------------------------
    private void  toolBtnsSetup(){

        // tools setup
        for (ToolNamesE tool : ToolNamesE.values()) {

            JButton toolBtn = new JButton();
            toolBtn.setSize(new Dimension(50, 50));
            toolBtn.setIcon(TextureHelper.getToolBtnTexture(tool, 50));
            toolBtn.setName(tool.toString());
            toolPanel.add(toolBtn);
            toolBtns.add(toolBtn);
        }

    }
    private void  menuBarSetup(){
        // menuBarSetup
        for (menuItemsE item : menuItemsE.values()) {
            JMenu menu = new JMenu(item.toString());
            menuItems.add(menu);
            menuBar.add(menu);
        }
        // menubar file options...setup
        for (FileOptionsE item : FileOptionsE.values()) {
            JMenuItem menuItem = new JMenuItem(item.toString());
            menuItem.setName(item.toString());
            menuOptions.add(menuItem);
            menuItems.get(0).add(menuItem);
        }

    }

    //------------------------------------------
    private void workspacePanelInit(){

        workspacePanel = new JPanel();
        workspacePanel.setToolTipText("workspace");
        workspacePanel.setBackground(new Color(107, 107, 107));
        workspacePanel.setLayout(null);

    }



    // NEW VIEW SETUP  Improved------------------------------------------------------------------------------------------------------------------
    // new global vars
    private LeftSidePanelV2 leftSidePanelV2;

   private  void initNewLeftPanel(){
        leftSidePanelV2=new LeftSidePanelV2();
        programPanel.add(leftSidePanelV2,BorderLayout.WEST);
   }

   //new control methods and getters...
    public  LeftSidePanelV2 getLeftSidePanelV2(){
       return leftSidePanelV2;
    }



    public ArrayList<JButton> getToolBtns() {
        return leftSidePanelV2.getToolPanel().getToolBtns();
    }
    public void addMaterial(FileDataModel data) {
        Dimension materialDim = new Dimension(((int) data.materialDim.getWidth() * 10),
                ((int) data.materialDim.getHeight() * 10));



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



    public JButton getWorkspaceShowPointsBtn(){
        return  showConnectPoints;
    }


}
