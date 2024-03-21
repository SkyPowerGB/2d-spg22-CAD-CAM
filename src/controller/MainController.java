package controller;

import View.MainView;
import controller.callbacks.NewFileCallBack;
import helpers.ActiveBtns;
import helpers.enums.FileOptionsE;
import helpers.helperModels.Line;
import model.FileData;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainController implements NewFileCallBack {
    MainView view;
    private boolean middleButtonPressed;
    private int prevX, prevY;
    private int offsetX,offsetY;

    public MainController() {

        view = new MainView();

        ArrayList<JMenuItem> menuOptions = view.getMenuOptions();

        ArrayList<JButton> toolBtns=view.getToolBtns();

        for (JMenuItem item : menuOptions) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem option = (JMenuItem) e.getSource();
                    menuOptionClicked(option.getName());
                }
            });
        }

        for (JButton btn : toolBtns){
            btn.addActionListener(e->{ toolBtnPressed((JButton) e.getSource()); });
        }



        JPanel workspace = view.getWorkspacePanel();

        workspace.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int x=e.getX();
                int y=e.getY();
                Point loc=new Point(x,y);
                if (e.getWheelRotation() > 0) {
                    System.out.println("zoomout");
                    scale(1, loc, false);
                } else {

                    System.out.println("zoomin");
                    scale(1,loc, true);
                }

            }

        });


        workspace.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isMiddleMouseButton(e)) {
                    middleButtonPressed = true;
                    prevX = e.getX();
                    prevY = e.getY();
                  offsetX= view.getMaterialPos().x-prevX;
                  offsetY=view.getMaterialPos().y-prevY;

                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isMiddleMouseButton(e)) {
                    middleButtonPressed = false;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        workspace.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println(e.getX());
                if(middleButtonPressed){
                  int currX=e.getX();
                  int currY=e.getY();
                  int newPosX=offsetX+currX;
                  int newPosY=offsetY+currY;

                  view.setMaterialLoc(new Point(newPosX,newPosY));
                  view.refreshWindow();

                }
                super.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }
        });



    }


    private void menuOptionClicked(String name) {
        System.out.println(name);

        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
        }
    }


    public void scale(int factor, Point position, boolean dir) {
        double ammount =0.1;
        byte relation=0;
        Point workspacePos=view.getMaterialPos();
        if(workspacePos.x>position.x && workspacePos.y>position.y){
            relation=1;
        }else if(workspacePos.x>position.x && workspacePos.y<position.y){
            relation=2;
        }else if(workspacePos.x<position.x && workspacePos.y<position.y){
            relation=3;
        }else if(workspacePos.x<position.x && workspacePos.y>position.y){
            relation=4;
        }

        Line ln;

        double moveX = view.getMaterial().getSize().getWidth() * (ammount/2);

        int nextX;



        Point materialPos = view.getMaterialPos();

   if(relation==3||relation==4) {
       ln = new Line(position, materialPos);
   }else{
       ln = new Line(materialPos, position);
   }



        double scale=view.getScale();

        if(scale<=0.2){
            scale=0.3;

            view.setScale(scale);
            view.scale();
            view.refreshWindow();
            return;
        }

        if (dir) {
            nextX=(int)moveX+materialPos.x;
             scale=scale+ammount;
        }else{
            nextX=materialPos.x-(int)moveX;
            scale=scale-ammount;
        }

        System.out.println(scale);

        int nexY=ln.calcY(nextX);

             Point newLoc=new Point(nextX,nexY);
view.setMaterialLoc(newLoc);
view.setScale(scale);
view.scale();
view.refreshWindow();


    }

    @Override
    public void onFileCreate(FileData data) {

        view.loadMaterial(data);
        view.refreshWindow();
    }


    public void toolBtnPressed(JButton btn){

            System.out.println(btn.getName());
             switch(btn.getName()){
                 case "txt":

                     ActiveBtns.text=true;

                     break;


             }

    }


}
