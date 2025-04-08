package controller;

import View.MainView;
import View.defaults.View;
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
    NewFileController newFileController;


    public MainController() {

        // prep callbacks---------------------------------------------------------------------------------




        // create view
        view = new MainView();

            zoomController =new ZoomController(view);
            panningController=new PanningController(view);
            layerController=new LayerController(view);
            toolController=new ToolController(view);
            newFileController=new NewFileController(view);





        // get  buttons and setup their listeners -------------------------------------------------






        // get workspace + add listeners ------------------------------------------------------------------------

        JPanel workspace = view.getWorkspacePanel();



        workspace.addMouseListener(workspaceMouseListener);

        workspace.addMouseMotionListener(mouseMotionListener);


        // select layer btn



      //--------------------------------------------------------------------------------

    }
    //-------  prepare layers map----------------------------------------------------------


    // TO DO razdvojit main controller na: zooming,panning,tools ->(line,),file,


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







