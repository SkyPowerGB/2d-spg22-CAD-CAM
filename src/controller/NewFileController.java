package controller;

import View.NewFile;
import controller.callbacks.NewFileCallBack;
import model.FileDataModel;

public class NewFileController {
    private NewFileCallBack callBack;
    NewFile view2;

    public NewFileController(NewFileCallBack callBack) {
        view2= new NewFile();
        this.callBack = callBack;
view2.getCreateBtn().addActionListener(e-> {onCreateNewFileClick();});
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
        callBack.onFileCreate(fileDataModel);
    }
}
