package View;

import ViewParts.*;
import helpers.TextureHelper;
import helpers.enums.FileOptionsE;
import helpers.enums.ToolNamesE;
import helpers.enums.WrkSpcNavPanBtnsE;
import helpers.enums.menuItemsE;
import helpers.helperModels.Span;
import model.FileData;
import model.LayerDrawingsModel;
import model.Layers;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainView {

    JButton hideConnectPoints, showConnectPoints, workspaceHomeLoc,workspaceScaleDefault;
    JPanel bottomPanel;
    ShownWindow mainFrame;
    JLayeredPane layeredPane;
    JPanel programPanel;
    JPanel workspacePanel;
    JPanel leftPanel;
    JPanel toolPanel;
    JPanel toolOptions;
    JPanel workspaceNavigationPanel;

    JButton addLayerBtn;
    JButton deleteLayer;

    JScrollPane leftScrollPane;

    JPanel layersGroupPanel;
    JPanel layersControlPanel;
    JPanel layersPanel;
    JPanel vectorLayer;
    LabeledInput zStep;
    ArrayList<JButton> toolBtns;
    JPanel rightSidePanel;
    JPanel upperPanel;
    JMenuBar menuBar;
    ArrayList<JMenu> menuItems;
    ArrayList<JMenuItem> menuOptions;
    LayerDrawingsModel drawingsModel;
    DrawingBoard board;
    ScalablePanel material;
    Dimension materialOriginalDim;

    JLabel scaleLbl;
    double scale = 1;


    JButton deleteLayerBtn;

    ScalableLayeredPane materialLayers;


    ArrayList<ScalablePanel> scalablePanels;

    public MainView() {

        initView();
    }

    public ArrayList<JButton> getToolBtns() {
        return toolBtns;
    }

    private void initView() {
        int layerPanelWidth = 90;

        materialLayers = new ScalableLayeredPane();
        scalablePanels = new ArrayList<>();
        mainFrame = new ShownWindow();


        layeredPane = new JLayeredPane();

        layersGroupPanel = new JPanel(new BorderLayout());
        layersGroupPanel.setPreferredSize(new Dimension(layerPanelWidth, mainFrame.getHeight()));

        layersControlPanel = new JPanel();
        layersControlPanel.setPreferredSize(new Dimension(90, 70));

        addLayerBtn = new JButton("+");
        addLayerBtn.setPreferredSize(new Dimension(100, 50));
        deleteLayer = new JButton("-");

        layersPanel = new JPanel();
        layersPanel.setLayout(new GridLayout(64, 1)); // Use BoxLayout to allow vertical expansion


        JScrollPane scrollPane = new JScrollPane(layersPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        layersGroupPanel.add(addLayerBtn, BorderLayout.NORTH);
        layersGroupPanel.add(scrollPane, BorderLayout.CENTER);


        drawingsModel = new LayerDrawingsModel();
        board = new DrawingBoard(drawingsModel);


        workspacePanel = new JPanel();
        workspacePanel.setBackground(new Color(107, 107, 107));
        workspacePanel.setLayout(null);

        leftPanel = new JPanel();
        rightSidePanel = new JPanel();
        upperPanel = new JPanel();
        programPanel = new JPanel();
        menuBar = new JMenuBar();
        menuItems = new ArrayList<>();
        menuOptions = new ArrayList<>();
        toolBtns = new ArrayList<>();
        toolPanel = new JPanel();
        toolOptions = new JPanel();
        leftScrollPane = new JScrollPane();

        vectorLayer = new JPanel();
        vectorLayer.setLayout(null);

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
        mainFrame.setTitle("2D SPG-22 CAM");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setJMenuBar(menuBar);

        menuBar.setPreferredSize(new Dimension(mainFrame.getWidth(), 50));

        programPanel.setLayout(new BorderLayout());


        leftPanel.setBackground(Color.cyan);
        leftPanel.setPreferredSize(new Dimension(200, frameSize.height));
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(toolOptions, BorderLayout.SOUTH);
        leftPanel.add(toolPanel, BorderLayout.WEST);
        leftPanel.add(layersGroupPanel, BorderLayout.EAST);


        rightSidePanel.setBackground(Color.ORANGE);
        rightSidePanel.setPreferredSize(new Dimension(100, frameSize.height));

        upperPanel.setBackground(Color.blue);
        upperPanel.setPreferredSize(new Dimension(frameSize.width, 50));
        upperPanel.setLayout(new GridLayout(1,20));

        workspaceNavigationPanel =new JPanel();
        workspaceNavigationPanel.setLayout(new GridLayout(1,3));
        workspaceNavigationPanel.setBackground(Color.cyan);
;

        int wkspcNavBtnsSIze=60;

        workspaceHomeLoc = new JButton();
        workspaceHomeLoc.setIcon(TextureHelper.getControlBtnTexture(WrkSpcNavPanBtnsE.locZZ,wkspcNavBtnsSIze));

        showConnectPoints=new JButton();
        showConnectPoints.setIcon(TextureHelper.getControlBtnTexture(WrkSpcNavPanBtnsE.showConnectDots,wkspcNavBtnsSIze));

         hideConnectPoints=new JButton();
         hideConnectPoints.addActionListener(e->{board.removeAll(); mainFrame.refresh();});


        workspaceScaleDefault=new JButton();
        workspaceScaleDefault.setIcon(TextureHelper.getControlBtnTexture(WrkSpcNavPanBtnsE.scaleDefault,wkspcNavBtnsSIze));

        workspaceScaleDefault.addActionListener(e->{setScale(1);});
        workspaceHomeLoc.addActionListener(e->{material.setLocation(0,0);});

        workspaceNavigationPanel.add(hideConnectPoints);
        workspaceNavigationPanel.add(showConnectPoints);
        workspaceNavigationPanel.add(workspaceHomeLoc);
        workspaceNavigationPanel.add(workspaceScaleDefault);




       upperPanel.add(new JPanel());
        upperPanel.add(workspaceNavigationPanel);
        upperPanel.add(new JPanel());






        bottomPanel.add(scaleLbl,BorderLayout.EAST);

        programPanel.add(leftPanel, BorderLayout.WEST);
        programPanel.add(rightSidePanel, BorderLayout.EAST);
        programPanel.add(upperPanel, BorderLayout.NORTH);
        programPanel.add(workspacePanel, BorderLayout.CENTER);
        programPanel.add(bottomPanel, BorderLayout.SOUTH);


        layeredPane.add(programPanel, JLayeredPane.DEFAULT_LAYER);


        mainFrame.add(programPanel);
        mainFrame.setVisible(true);
    }
    public void addMaterial(FileData data) {
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

    public Span getMaterialSpan() {
        Point matP = material.getLocation();
        Point matE = new Point(matP.x + material.getWidth(), matP.y + material.getHeight());

        return new Span(matP, matE);
    }

    public Point getMaterialPos() {
        return material.getLocation();
    }

    public boolean isPointOnMaterial(Point p) {
        return getMaterialSpan().isPointInSpan(p);
    }

    public void setMaterialLoc(Point p) {
        material.setLocation(p);
        mainFrame.refresh();
    }

    public ArrayList<JMenuItem> getMenuOptions() {
        return menuOptions;
    }
    public void boardSetDrawingsModel(LayerDrawingsModel model){
        board.setModel(model);
        this.refreshWindow();
    }

    public void refreshWindow() {
        board.getModel().resetBtnsLocation();
        mainFrame.refresh();
    }


    public Point recalcWorkspaceToMaterial(Point p) {
        Point matLoc = material.getLocation();
        return new Point((p.x - (matLoc.x)), (p.y - (matLoc.y)));

    }

    public DrawingBoard getBoard() {
        return board;
    }

    public JButton getAddLayerBtn() {
        return addLayerBtn;
    }

    public void addLayer(LayerPanel panel) {

        layersPanel.add(panel);
        mainFrame.refresh();
    }
    public void removeLayers(){
        layersPanel.removeAll();
  }
    public JButton getWorkspaceShowPointsBtn(){
        return  showConnectPoints;
    }


}
