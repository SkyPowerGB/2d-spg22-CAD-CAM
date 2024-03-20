package controller;

import View.MainView;
import controller.callbacks.NewFileCallBack;
import helpers.enums.FileOptionsE;
import helpers.helperModels.Line;
import model.FileData;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class MainController implements NewFileCallBack {
    MainView view;

    public MainController() {

        view = new MainView();

        ArrayList<JMenuItem> menuOptions = view.getMenuOptions();


        for (JMenuItem item : menuOptions) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem option = (JMenuItem) e.getSource();
                    menuOptionClicked(option.getName());
                }
            });
        }

        JPanel workspace = view.getWorkspacePanel();
        workspace.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {


            }
        });


    }


    private void menuOptionClicked(String name) {
        System.out.println(name);

        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
        }
    }


    public void scale(int factor , Point position){

        Point materialPos= view.getMaterialPos();
        Line ln=new Line(position,materialPos);


    }

    @Override
    public void onFileCreate(FileData data) {

        view.loadMaterial(data);
        view.refreshWindow();
    }



}
