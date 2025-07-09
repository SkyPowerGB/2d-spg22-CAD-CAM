package View;

import View.MainViewV2.MainViewParts.BottomPanel.BottomPanelV2;
import View.MainViewV2.MainViewParts.LeftSidePanel.LeftSidePanelV2;
import View.MainViewV2.MainViewParts.MenuBar.MenuBarV2;
import View.MainViewV2.MainViewParts.RightSidePanel.RightSidePanel;
import View.MainViewV2.MainViewParts.TopPanel.TopPanelV2;
import View.ViewUIComponents.*;
import helpers.helperModels.RectangleSpanHelper;
import model.FileDataModel;
import model.LayerDrawingsModel;
import model.V2models.ViewStateModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainView {

   private JButton hideConnectPoints, showConnectPoints, workspaceHomeLoc,workspaceScaleDefault;
    private ShownWindow mainFrame;
    // main panel
    private JPanel programPanel;



     // layoutPanels

     MenuBarV2 menuBarV2;
    private RightSidePanel rightSidePanel;
    private BottomPanelV2 bottomPanel;
    private TopPanelV2 topPanel;
    private JPanel workspacePanel;




    // drawing panels

    ScalableLayeredPane materialLayers;
    private DrawingBoard drawingBoardPanel;
    private ScalablePanel materialPanel;



//---------------------------------------------------------------------------------------------------
    // models

    private ViewStateModel viewStateModel;
    private FileDataModel fileDataModel;
    private LayerDrawingsModel drawingsModel;



    public MainView() {

        initView();
    }


    private void initView() {









        mainFrame = new ShownWindow();

        //----------------------------------------












        rightSidePanel = new RightSidePanel();
        topPanel = new TopPanelV2();
        programPanel = new JPanel();


        bottomPanel = new BottomPanelV2();
        bottomPanel.setScaleTxt(1);




        // material is visual representation of real life material
        materialPanel = new ScalablePanel();
        materialPanel.setBackground(Color.white);
        materialPanel.setLayout(null);


        // material layers is extension of material for displaying multiple stuff on material
        materialLayers = new ScalableLayeredPane();

        // drawings model contains data that board must draw on the material
        drawingsModel = new LayerDrawingsModel();

        // drawing board displays drawingsModel
        drawingBoardPanel = new DrawingBoard(drawingsModel);
        drawingBoardPanel.setBackground(Color.ORANGE);
        drawingBoardPanel.setSize(500, 500);

        // setup material with new components
        materialLayers.add(drawingBoardPanel);
        materialLayers.setSize(500, 500);
        materialPanel.add(materialLayers);




        // menuBarSetup
        menuBarV2=new MenuBarV2();
        mainFrame.setJMenuBar(menuBarV2);

        // old code
        mainFrame.setTitle("2.5D SP-24 CAD/CAM");
        mainFrame.setLayout(new BorderLayout());


        programPanel.setLayout(new BorderLayout());

        initLeftPanel();
        workspacePanelInit();


        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(topPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);
        programPanel.add(bottomPanel, BorderLayout.SOUTH);


        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }

    // TEMPORARY SETUP METHODS...--------------------------------------------------------------------------------------------------


    private void workspacePanelInit(){

        workspacePanel = new JPanel();
        workspacePanel.setToolTipText("workspace");
        workspacePanel.setBackground(new Color(107, 107, 107));
        workspacePanel.setLayout(null);

    }



    // NEW VIEW SETUP  Improved------------------------------------------------------------------------------------------------------------------
    // new global vars
    private LeftSidePanelV2 leftSidePanelV2;

   private  void initLeftPanel(){
        leftSidePanelV2=new LeftSidePanelV2();
        programPanel.add(leftSidePanelV2,BorderLayout.WEST);
   }



   //new control methods and getters...   for setting up buttons with events
    public  LeftSidePanelV2 getLeftSidePanelV2(){
       return leftSidePanelV2;
    }

    public  TopPanelV2 getTopPanel(){return topPanel;}

    public MenuBarV2 getMenuBarV2() {return menuBarV2;}

    public ArrayList<JButton> getToolBtns(){

        return leftSidePanelV2.getToolPanel().getToolBtns();
    }

    public JPanel getWorkspacePanel() {

       return workspacePanel;
    }

    public ScalablePanel getMaterialPanel() {

       return materialPanel;
    }



    //---------------------------------------------------------------------------------------------------------------


    //public methods


    public void refreshWindow() {
        refreshViewStates();
        mainFrame.refresh();
    }







    // check if click is on material
    public boolean isPointOnMaterial(Point p) {
        return getMaterialSpan().isPointInRectangle(p);
    }


    // helper method, calculate click offset
    public Point recalcWorkspaceToMaterial(@NotNull Point p) {
        Point matLoc = materialPanel.getLocation();
        return new Point((p.x - (matLoc.x)), (p.y - (matLoc.y)));

    }



    // get currentBoardModel
    public LayerDrawingsModel getBoardsCurrDrawingModel(){

        return  getDrawingBoardPanel().getDrawingsModel();
    }


    //------helper methods ,private

    // get material rectangle

    private RectangleSpanHelper getMaterialSpan() {
        Point matP = materialPanel.getLocation();
        Point matE = new Point(matP.x + materialPanel.getWidth(), matP.y + materialPanel.getHeight());

        return new RectangleSpanHelper(matP, matE);
    }

    // get Board that draws graphics
    private DrawingBoard getDrawingBoardPanel() {
        return drawingBoardPanel;
    }




    // new model stuff---------------------------------------------------------------------------------------------------
    // decouple model for the controller

    /*
    * removes need for getting data from view just pass model. it auto sets all other data
    * */
    public void setViewStateModel(ViewStateModel model){
       this.viewStateModel =model;

       

       refreshWindow();
    }

  // load view state model data
    private void refreshViewStates(){
        materialPanel.setLocation(viewStateModel.getMaterialLocation());
        bottomPanel.setScaleTxt(viewStateModel.getScale());
        materialPanel.setScale(viewStateModel.getScale());
        materialLayers.setScale(viewStateModel.getScale());
        drawingBoardPanel.setScale(viewStateModel.getScale());

    }

    public void setFileDataModel(FileDataModel fileDataModel) {
        this.fileDataModel=fileDataModel;
        Dimension materialDim = fileDataModel.getMaterialViewDimension();



        materialLayers.setDefaultSize(materialDim);
        materialLayers.setSize(materialDim);
        materialLayers.setScale(1);


        drawingBoardPanel.setDefaultSize(materialDim);
        drawingBoardPanel.setSize(materialDim);


        drawingBoardPanel.setScale(1);


        materialPanel.setDefaultSize(materialDim.width, materialDim.height);
        materialPanel.setScale(viewStateModel.getScale());


        int Width = workspacePanel.getWidth() / 2 - (((int) fileDataModel.getMaterialDim().getWidth() * 10) / 2);
        if (Width < 0) {
            Width = 0;
        }

        materialPanel.setLocation(Width, 0);

        workspacePanel.add(materialPanel);
        mainFrame.setVisible(true);
    }






}
