package controller;

import View.MainView;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.callbacks.NewFileCallBack;
import Enums.FileOptionsE;
import model.*;

import javax.swing.*;
import java.awt.event.*;

public class MainController implements NewFileCallBack {
    MainView view;


    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;


    FileDataModel data;


    LayerController layerController;
    PanningController panningController;
    ZoomController zoomController;
    ToolController toolController;


    public MainController() {

        // prep callbacks---------------------------------------------------------------------------------




        // create view
        view = new MainView();

            zoomController =new ZoomController(view);
            panningController=new PanningController(view);
            layerController=new LayerController(view);
            toolController=new ToolController(view);





        // get  buttons and setup their listeners -------------------------------------------------



        for (JMenuItem item : view.getMenuOptions()) {
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem option = (JMenuItem) e.getSource();
                    menuOptionClicked(option.getName());
                }
            });
        }


        // get workspace + add listeners ------------------------------------------------------------------------

        JPanel workspace = view.getWorkspacePanel();



        workspace.addMouseListener(workspaceMouseListener);

        workspace.addMouseMotionListener(mouseMotionListener);


        // select layer btn



      //--------------------------------------------------------------------------------

    }
    //-------  prepare layers map----------------------------------------------------------


    // TO DO razdvojit main controller na: zooming,panning,tools ->(line,),file,

    private void menuOptionClicked(String name) {


        if (name.contentEquals(FileOptionsE.new_file.toString())) {
            NewFileController controllerB = new NewFileController(this);
            System.out.println("newFileCreated");
        }
    }

    //callbacks
    @Override
    public void onFileCreate(FileDataModel data) {
        this.data = data;
        LayersDataStorageModel.setFileData(data);
        layerController.setFileData(LayersDataStorageModel.getFileData());

        view.addMaterial(LayersDataStorageModel.getFileData());
        view.refreshWindow();


    }








}







