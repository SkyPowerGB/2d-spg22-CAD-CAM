package controller;

import Enums.ControllerActionEventNamesE;
import View.MainView;
import View.NewFile;
import controller.callbacks.NewFileCallBack;
import controller.standard.Controller;
import model.FileDataModel;
import model.LayersDataStorageModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewFileController extends Controller implements  NewFileCallBack{

    NewFile view2;
    MainView view;


    public NewFileController(MainView view){


      System.out.println("creating file controller");
         view.getMenuBarV2().SetupFilesMenu(this);

        this.view=view;

        view.refreshWindow();
    }

    private void OpenNewFileDialog(){
        view2= new NewFile();
        view2.getCreateBtn().addActionListener(event-> {onCreateNewFileClick();});
    }


   public void onCreateNewFileClick(){
        FileDataModel fileDataModel =new FileDataModel();

       fileDataModel.materialThickness=view2.getMaterialHeight();
       fileDataModel.fileName= view2.getFileName();
       fileDataModel.material= view2.getMaterialPreset();
       fileDataModel.fullSpace=view2.startAtHome();
      fileDataModel.materialDim=view2.getWorkspaceDim();


      view.addMaterial(fileDataModel);
      view.refreshWindow();
        if(view2.errorVar){return;}

        view2.dispose();

    }

    @Override
    public void handleAction(ActionEvent e) {

    }

    @Override
    public void handleAction(ActionEvent e, ControllerActionEventNamesE action) {
       if(action==ControllerActionEventNamesE.menuItemClick){
           OpenNewFileDialog();
       }

    }

    @Override
    public void onFileCreate(FileDataModel data) {
        LayersDataStorageModel.setFileData(data);


        view.addMaterial(LayersDataStorageModel.getFileData());
        view.refreshWindow();
    }
}
