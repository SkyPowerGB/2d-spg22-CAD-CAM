package controller;

import View.MainView;
import ViewParts.DrawingBoard;
import ViewParts.LayerPanel;
import ViewParts.TextBoard;
import ViewParts.TextPropertiesPanel;
import controller.AEclasses.PointAL;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.AEclasses.WorkspaceMouseWheelListener;
import controller.callbacks.AddTextCallback;
import controller.callbacks.MouseCallBacks;
import controller.callbacks.NewFileCallBack;
import controller.callbacks.PointPressedCallBack;
import helpers.*;
import helpers.enums.FileOptionsE;
import helpers.helperModels.Line;
import helpers.helperModels.Span;
import model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.BinaryOperator;
import helpers.enums.ToolNamesE;

public class MainController implements NewFileCallBack, AddTextCallback, MouseCallBacks, PointPressedCallBack {
    MainView view;
    TextPropertiesPanel textPropertiesPanel;
    WorkspaceMouseWheelListener mouseWheelListener;
    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;
    LayerDrawingsModel model;
    FileData data;
    DrawingBoard board;

    Point lineP1;
    int currLineIndex = -1;
    PointAL pointAL;
    private boolean middleButtonPressed;
    private int prevX, prevY;
    private int offsetX, offsetY;

    public MainController() {
        mouseWheelListener = new WorkspaceMouseWheelListener(this);
        workspaceMouseListener = new WorkspaceMouseListener(this);
        mouseMotionListener = new WorkspaceMouseMotionListener(this);
        pointAL = new PointAL(this);


        view = new MainView();

        ArrayList<JMenuItem> menuOptions = view.getMenuOptions();

        ArrayList<JButton> toolBtns = view.getToolBtns();

        for (JMenuItem item : menuOptions) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem option = (JMenuItem) e.getSource();
                    menuOptionClicked(option.getName());
                }
            });
        }

        for (JButton btn : toolBtns) {
            btn.addActionListener(e -> {
                toolBtnPressed((JButton) e.getSource());
            });
        }

        JPanel workspace = view.getWorkspacePanel();

        workspace.addMouseWheelListener(mouseWheelListener);


        workspace.addMouseListener(workspaceMouseListener);

        workspace.addMouseMotionListener(mouseMotionListener);


        view.getAddLayerBtn().addActionListener(e -> {
            addLayer();
        });


    }

    private void addLayer() {

        if (data == null) {
            JOptionPane.showMessageDialog(null, "Please create file");
            return;
        }
        String input = JOptionPane.showInputDialog(null, "Enter layer height in mm");
        double out;
        try {
            out = Double.parseDouble(input);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
        }
        if (out == 0) {
            JOptionPane.showMessageDialog(null, "Putting 0 depth?");
            return;
        }
        ZdepthMap map = new ZdepthMap(data.materialThickness, 0.1);
        if (Layers.layerHeightExists(out)) {
            JOptionPane.showMessageDialog(null, "Layer at that height already exists");
            return;
        }
        LayerPanel layer = new LayerPanel(out);
        layer.setPreferredSize(new Dimension(90, 50));
        layer.setBackground(Color.gray);
        layer.setLayout(null);
        layer.setBorder(new BevelBorder(BevelBorder.LOWERED));


        int index = Layers.addLayer(layer);
        JButton btn = new JButton();

        btn.setSize(layer.getPreferredSize());
        btn.setName(String.valueOf(index));
        btn.setBackground(null);
        btn.setBackground(map.getHeightColor(out));
        btn.setText(String.valueOf(out));
        layer.setLayerBtn(btn);
        btn.addActionListener(e -> {
            selectLayer((((JButton) e.getSource()).getName()));
        });
        layer.add(btn);
        Layers.selectLayer(layer);
        view.addLayer(layer);

    }
    private void selectLayer(String index) {
        int i = Integer.parseInt(index);
        Layers.activeLayer = i;
        Layers.enableAllBtns();
        Layers.getPanel(i).getLayerBtn().setEnabled(false);
        System.out.println("select layer: " + i);
          model= Layers.getLayerModel();
          view.boardSetDrawingsModel(model);
    }

    private void menuOptionClicked(String name) {


        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
        }
    }

    public void zoom_in_out(int factor, Point position, boolean dir) {
        double ammount = 0.1;
        byte relation = 0;
        Point workspacePos = view.getMaterialPos();
        if (workspacePos.x > position.x && workspacePos.y > position.y) {
            relation = 1;
        } else if (workspacePos.x > position.x && workspacePos.y < position.y) {
            relation = 2;
        } else if (workspacePos.x < position.x && workspacePos.y < position.y) {
            relation = 3;
        } else if (workspacePos.x < position.x && workspacePos.y > position.y) {
            relation = 4;
        }

        Line ln;

        double moveX = view.getMaterial().getSize().getWidth() * (ammount / 2);

        int nextX;


        Point materialPos = view.getMaterialPos();

        if (relation == 3 || relation == 4) {
            ln = new Line(position, materialPos);
        } else {
            ln = new Line(materialPos, position);
        }


        double scale = view.getScale();

        if (scale <= 0.2) {
            scale = 0.3;

            view.setScale(scale);


            view.refreshWindow();
            return;
        }

        if (dir) {
            nextX = (int) moveX + materialPos.x;
            scale = scale + ammount;
        } else {
            nextX = materialPos.x - (int) moveX;
            scale = scale - ammount;
        }


        int nexY = ln.calcY(nextX);

        Point newLoc = new Point(nextX, nexY);
        view.setMaterialLoc(newLoc);
        view.setScale(scale);

        view.refreshWindow();


    }

    public void OpenTextForm(Point p) {
        TextToDisplay.textLoc.add(p);
        DrawTextController drawText = new DrawTextController(this);

    }

    private void clickOnMaterial(Point p) {

        switch (ActiveTools.toolName) {
            case "txt":
                if (!ActiveTools.edit) {
                    OpenTextForm(p);
                } else {
                    if (ActiveTools.followMouse) {
                        ActiveTools.followMouse = false;
                    }
                }
                break;
            case "line":
                if (ActiveTools.followMouse) {
                    currLineIndex = -1;
                }
                ActiveTools.switchFollowMouse();

                lineP1 = p;

                System.out.println("added first point of line");


                break;

        }


    }


    @Override
    public void onFileCreate(FileData data) {
        this.data = data;
        view.addMaterial(data);
        view.refreshWindow();
        board = view.getBoard();
        model = board.getModel();
        currLineIndex = -1;
    }

    public void toolBtnPressed(JButton btn) {

        ActiveTools.toolName = btn.getName();

        switch (btn.getName()) {
            case "txt":

                if (ActiveBtns.text) {
                    ActiveBtns.text = false;
                } else {
                    ActiveBtns.text = true;
                }

                break;

            case "line":


                break;


        }

    }

    @Override
    public void AddText(int txtId) {
        TextBoard textBoard = new TextBoard(txtId);

        textBoard.setLocation(TextToDisplay.textLoc.get(txtId));
        textBoard.setDefaultLocation(TextToDisplay.textLoc.get(txtId));
        textBoard.setScale(view.getScale());
        textBoard.setBackground(Color.ORANGE);

        ActiveTools.edit = true;
        textPropertiesPanel = new TextPropertiesPanel(Storage.tempTextBoard);
        textPropertiesPanel.getMoveBtn().addActionListener(e -> {
            txtFollowMouse();
        });
        textPropertiesPanel.getUpdateButton().addActionListener(e -> {
            updateText();
        });
        textPropertiesPanel.getAddToMaterialBtn().addActionListener(e -> drawTxtToMaterialFinal());

        view.addUpperOptionsPanel(textPropertiesPanel);
        view.materialAddJPanel(textBoard);
        Storage.tempTextBoard = textBoard;
        textPropertiesPanel.updateProp();
        view.refreshWindow();

    }

    private void updateText() {


    }

    private void txtFollowMouse() {
        ActiveTools.switchFollowMouse();

    }

    private void drawTxtToMaterialFinal() {

    }

    @Override
    public void zoomIn(Point p) {

        zoom_in_out(1, p, true);
    }

    @Override
    public void zoomOut(Point p) {


        zoom_in_out(1, p, false);
    }

    @Override
    public void click(Point p) {
        if (view.isPointOnMaterial(p)) {
            Point click = view.recalcWorkspaceToMaterial(p);
            click=PointScaler.getDefaultPoint(click,view.getScale());
            clickOnMaterial(click);
        }

    }

    @Override
    public void pressedMidBtn(Point location) {
        middleButtonPressed = true;
        prevX = location.x;
        prevY = location.y;
        offsetX = view.getMaterialPos().x - prevX;
        offsetY = view.getMaterialPos().y - prevY;
    }

    @Override
    public void dragged(Point where) {

        if (middleButtonPressed) {
            int currX = where.x;
            int currY = where.y;
            int newPosX = offsetX + currX;
            int newPosY = offsetY + currY;

            view.setMaterialLoc(new Point(newPosX, newPosY));
            view.refreshWindow();
        }
    }

    @Override
    public void releasedMidBtn() {
        middleButtonPressed = false;
    }

    @Override
    public void entered() {
        if (ActiveBtns.text) {
            view.getMaterial().setCursor(new Cursor(Cursor.TEXT_CURSOR));
        } else {
            view.getMaterial().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void moved(Point currPos) {
        if (ActiveTools.followMouse) {
            if (ActiveTools.toolName == "line") {
                if (view.isPointOnMaterial(currPos)) {
                    currPos = view.recalcWorkspaceToMaterial(currPos);
                }
                Point scaledCurrent = PointScaler.getDefaultPoint(currPos, view.getScale());
                Line2D.Double line = new Line2D.Double(lineP1, scaledCurrent);


                if (currLineIndex != -1) {
                    model.removeLineAtIndex(currLineIndex);
                    System.out.println("brisem");
                }
                currLineIndex = model.addLine(line);
                view.refreshWindow();

            }
        }
        if (ActiveTools.toolName == "text") {
            if (view.isPointOnMaterial(currPos)) {
                textPropertiesPanel.updateProp();
                Storage.tempTextBoard.setDefaultLocation(view.recalcWorkspaceToMaterial(PointScaler.getDefaultPoint(currPos, view.getScale())));
                Storage.tempTextBoard.setLocation(view.recalcWorkspaceToMaterial(currPos));
            }
        }
    }

    @Override
    public void clickedPoint(Point p) {

    }



    public void lineBegin(Point p){
        System.out.println("line begin");
    }
    public void mouseFollowLn(Point p){}


    public void setTextPoint(Point p){
        System.out.println("line txt begin");
    }

    public void setCirclePoint(Point p){

    }
    public void mouseFollowCircle(Point p){}


    public void mouseFollowPoint(Point p){}


}







