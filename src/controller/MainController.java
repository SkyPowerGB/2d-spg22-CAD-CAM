package controller;

import View.MainView;
import controller.callbacks.NewFileCallBack;
import helpers.enums.FileOptionsE;
import model.FileData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainController implements NewFileCallBack {
    MainView view;
    public MainController(){

       view =new MainView();

        ArrayList<JMenuItem>  menuOptions= view.getMenuOptions();
        for (JMenuItem item:menuOptions) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  JMenuItem option = (JMenuItem) e.getSource();
                  menuOptionClicked(option.getName());
                }
            });
        }
    }


    private void menuOptionClicked(String name){
        System.out.println(name);

        if(name.contentEquals(FileOptionsE.new_file.toString())){
            NewFileController controllerB= new NewFileController(this);
        }
    }


    @Override
    public void onFileCreate(FileData data) {

        view.loadMaterial(data);
    }




}
