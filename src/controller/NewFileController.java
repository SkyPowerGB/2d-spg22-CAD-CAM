package controller;

import Enums.ControllerActionEventNamesE;
import View.MainView;
import View.MainViewV2.MainViewParts.MenuBar.MenuBarItem;
import View.MainViewV2.MainViewParts.MenuBar.MenuBarSetup.MenuOptionsE;
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
    LayerController layerController;

    public NewFileController(MainView view,LayerController layerController){

        view2= new NewFile();
      System.out.println("creating file controller");
         view.getMenuBarV2().SetupFilesMenu(this);
         view.refreshWindow();
        for (int i = 0; i < view.getMenuBarV2().getMenuCount(); i++) {
            JMenu menu = view.getMenuBarV2().getMenu(i);
            System.out.println("Menu " + i + ": " + (menu != null ? menu.getText() : "null"));
        }
        this.view=view;

    }
   public void onCreateNewFileClick(){
        FileDataModel fileDataModel =new FileDataModel();

       fileDataModel.materialThickness=view2.getMaterialHeight();
       fileDataModel.fileName= view2.getFileName();
       fileDataModel.material= view2.getMaterialPreset();
       fileDataModel.fullSpace=view2.startAtHome();
      fileDataModel.materialDim=view2.getWorkspaceDim();



        if(view2.errorVar){return;}

        view2.dispose();

    }

    @Override
    public void handleAction(ActionEvent e) {

    }

    @Override
    public void handleAction(ActionEvent e, ControllerActionEventNamesE action) {
       if(action==ControllerActionEventNamesE.menuItemClick){

           view2= new NewFile();
           view2.getCreateBtn().addActionListener(event-> {onCreateNewFileClick();});
       }
    }

    @Override
    public void onFileCreate(FileDataModel data) {
        LayersDataStorageModel.setFileData(data);
        layerController.setFileData(LayersDataStorageModel.getFileData());

        view.addMaterial(LayersDataStorageModel.getFileData());
        view.refreshWindow();
    }
}
