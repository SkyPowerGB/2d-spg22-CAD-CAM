package View;

import View.MainViewV2.MainViewParts.BottomPanel.BottomPanelV2;
import View.MainViewV2.MainViewParts.LeftSidePanel.LeftSidePanelV2;
import View.MainViewV2.MainViewParts.MenuBar.MenuBarV2;
import View.MainViewV2.MainViewParts.RightSidePanel.RightSidePanel;
import View.MainViewV2.MainViewParts.TopPanel.TopPanelV2;
import View.ViewUIComponents.*;
import helpers.TextureHelper;
import Enums.FileOptionsE;
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

    private BottomPanelV2 bottomPanel;
    private ShownWindow mainFrame;

    private JPanel programPanel;
    private JPanel workspacePanel;







    private  ArrayList<JButton> toolBtns;
    private   RightSidePanel rightSidePanel;
    private TopPanelV2 topPanel;


    private LayerDrawingsModel drawingsModel;
    private  DrawingBoard board;
    private  ScalablePanel material;


    private   double scale = 1;


     MenuBarV2 menuBarV2;


    ScalableLayeredPane materialLayers;


    ArrayList<ScalablePanel> scalablePanels;

    public MainView() {

        initView();
    }




    private void initView() {





        scalablePanels = new ArrayList<>();




        mainFrame = new ShownWindow();

        //----------------------------------------


        drawingsModel = new LayerDrawingsModel();
        board = new DrawingBoard(drawingsModel);


        workspacePanelInit();







        rightSidePanel = new RightSidePanel();
        topPanel = new TopPanelV2();
        programPanel = new JPanel();









        bottomPanel = new BottomPanelV2();

          bottomPanel.setScaleTxt(scale);




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

         menuBarV2=new MenuBarV2();
        mainFrame.setJMenuBar(menuBarV2);

        // old code

        mainFrame.setTitle("2.5D SP-24 CAD/CAM");
        mainFrame.setLayout(new BorderLayout());






        programPanel.setLayout(new BorderLayout());










        int wrkSpcToolsBtnSize =60;

        workspaceHomeLoc = new JButton();
        workspaceHomeLoc.setIcon(TextureHelper.getControlBtnTexture(WorkspaceToolBtnsE.locZZ, wrkSpcToolsBtnSize));



        workspaceScaleDefault=new JButton();
        workspaceScaleDefault.setIcon(TextureHelper.getControlBtnTexture(WorkspaceToolBtnsE.scaleDefault, wrkSpcToolsBtnSize));







        initNewLeftPanel();


        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(topPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);
        programPanel.add(bottomPanel, BorderLayout.SOUTH);





        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }

    // TEMPORARY SETUP METHODS...--------------------------------------------------------------------------------------------------

    // visual------------------------------------


    private void  menuBarSetup(){


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

    public  TopPanelV2 getTopPanel(){return topPanel;}

    public MenuBarV2 getMenuBarV2() {return menuBarV2;}

    // workspace tool control btn s   temporary? functions
    public void SetHomeLoc(){
        material.setLocation(0,0);
    }
    public void ResetScale(){
       this.setScale(1);
    }


    //---------------------------------------------------------------------------------------------------------------

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
        bottomPanel.setScaleTxt(scale);

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





}
