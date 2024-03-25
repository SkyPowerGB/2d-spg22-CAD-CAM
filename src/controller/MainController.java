package controller;

import View.MainView;
import ViewParts.DrawingBoard;
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
import helpers.ActiveBtns;
import helpers.BackgroundActive;
import helpers.PointScaler;
import helpers.enums.FileOptionsE;
import helpers.helperModels.Line;
import helpers.helperModels.Span;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.function.BinaryOperator;

public class MainController implements NewFileCallBack, AddTextCallback , MouseCallBacks, PointPressedCallBack {
    MainView view;
    private boolean middleButtonPressed;
    private int prevX, prevY;
    private int offsetX, offsetY;
    TextPropertiesPanel textPropertiesPanel;
    WorkspaceMouseWheelListener mouseWheelListener;
    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;

    LayerDrawingsModel model;
    DrawingBoard board;

    Line2D.Double line;
    Point lineP1;
    int currLineIndex=-1;
 PointAL pointAL;
    public MainController() {
   mouseWheelListener=new WorkspaceMouseWheelListener(this);
   workspaceMouseListener=new WorkspaceMouseListener(this);
   mouseMotionListener=new WorkspaceMouseMotionListener(this);
    pointAL=new PointAL(this);



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
    public void drawLine(Point a){
           Span materialSpan=view.getMaterialSpan();
           if(materialSpan.isPointInSpan(a)){
                 if(BackgroundActive.switchOn()){

                 }
           }
    }
    private void clickOnMaterial(Point p){
              switch (ActiveTools.toolName){
                  case "txt": if(!ActiveTools.edit){   OpenTextForm(p);   }else{
                        if(ActiveTools.followMouse){
                            ActiveTools.followMouse=false;
                        }
                  }         break;
                  case "line":
                      if(ActiveTools.followMouse){currLineIndex=-1;}
                         ActiveTools.switchFollowMouse();

                        lineP1=PointScaler.getDefaultPoint(p,view.getScale());

                        System.out.println("added first point of line");



                      break;

              }



    }

    private void generatePointBtns(){


        for (Line2D.Double line:model.getLines()) {

        }





    }

    @Override
    public void onFileCreate(FileData data) {

        view.addMaterial(data);
        view.refreshWindow();
        board = view.getBoard();
        model = board.getModel();
        currLineIndex=-1;
    }
    public void toolBtnPressed(JButton btn) {

        ActiveTools.toolName=btn.getName();

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

        ActiveTools.edit=true;
        textPropertiesPanel =new TextPropertiesPanel(Storage.tempTextBoard);
        textPropertiesPanel.getMoveBtn().addActionListener(e->{txtFollowMouse();});
        textPropertiesPanel.getUpdateButton().addActionListener(e->{updateText();});
        textPropertiesPanel.getAddToMaterialBtn().addActionListener(e->drawTxtToMaterialFinal());

        view.addUpperOptionsPanel(textPropertiesPanel);
        view.materialAddJPanel(textBoard);
        Storage.tempTextBoard=textBoard;
        textPropertiesPanel.updateProp();
        view.refreshWindow();

    }
    private void updateText(){






    }
    private void txtFollowMouse(){
        ActiveTools.switchFollowMouse();

    }
    private void drawTxtToMaterialFinal(){

    }
    @Override
    public void zoomIn(Point p) {

        zoom_in_out(1, p, true);
    }
    @Override
    public void zoomOut(Point p) {


        zoom_in_out (1, p, false);
    }
    @Override
    public void click(Point p) {
        if(view.isPointOnMaterial(p)){
            Point click=  view.recalcWorkspaceToMaterial(p);
            clickOnMaterial(click);
        }

    }
    @Override
    public void pressedMidBtn(Point location) {
             middleButtonPressed=true;
        prevX = location.x;
        prevY = location.y;
        offsetX = view.getMaterialPos().x - prevX;
        offsetY = view.getMaterialPos().y - prevY;
    }
    @Override
    public void dragged(Point where) {

        if(middleButtonPressed) {
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
         middleButtonPressed=false;
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
       if(ActiveTools.followMouse){
           if(ActiveTools.toolName=="line") {
               if(view.isPointOnMaterial(currPos)) {
                   currPos = view.recalcWorkspaceToMaterial(currPos);
               }
                   Point scaledCurrent = PointScaler.getDefaultPoint(currPos,view.getScale());
                   Line2D.Double line = new Line2D.Double(lineP1, scaledCurrent);


                if(currLineIndex !=-1){
                 model.removeLineAtIndex(currLineIndex);
            System.out.println("brisem");
                }
             currLineIndex=  model.addLine(line);
                view.refreshWindow();

                }
           }
        if(ActiveTools.toolName=="text") {
            if(view.isPointOnMaterial(currPos)){
                       textPropertiesPanel.updateProp();
                Storage.tempTextBoard.setDefaultLocation(view.recalcWorkspaceToMaterial(PointScaler.getDefaultPoint(currPos,view.getScale())));
                Storage.tempTextBoard.setLocation(view.recalcWorkspaceToMaterial(currPos));
            }
        }
       }

    @Override
    public void clickedPoint(Point p) {

    }
}







