package controller;

import View.MainView;
import controller.AEclasses.WorkspaceMouseListener;
import controller.AEclasses.WorkspaceMouseMotionListener;
import controller.callbacks.NewFileCallBack;
import model.*;
import model.V2models.ViewStateModel;

import javax.swing.*;

public class MainController implements NewFileCallBack {
    MainView view;




    WorkspaceMouseMotionListener mouseMotionListener;
    WorkspaceMouseListener workspaceMouseListener;


    FileDataModel fileDataModel;


    LayerController layerController;
    PanningController panningController;
    ZoomController zoomController;
    ToolController toolController;
    NewFileController newFileController;


    // view models new
    ViewStateModel viewStateModel;
    public MainController() {


        // CREATE MODELS


        viewStateModel =new ViewStateModel();
        fileDataModel =new FileDataModel();


        // create view
        view = new MainView();
        view.setViewStateModel(viewStateModel);
        view.setFileDataModel(fileDataModel);

            zoomController =new ZoomController(view,viewStateModel);
            panningController=new PanningController(view,viewStateModel);
            layerController=new LayerController(view);
            toolController=new ToolController(view,viewStateModel);
            newFileController=new NewFileController(view);









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
        this.fileDataModel = data;
        LayersDataStorageModel.setFileData(data);
        layerController.setFileData(LayersDataStorageModel.getFileData());

        view.setFileDataModel(LayersDataStorageModel.getFileData());
        view.refreshWindow();


    }








}







