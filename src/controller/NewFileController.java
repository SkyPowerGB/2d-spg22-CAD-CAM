package controller;

import View.NewFile;
import controller.callbacks.NewFileCallBack;
import model.DimensionDouble;
import model.FileData;

public class NewFileController {
    private NewFileCallBack callBack;
    NewFile view2;

    public NewFileController(NewFileCallBack callBack) {
        view2= new NewFile();
        this.callBack = callBack;
view2.getCreateBtn().addActionListener(e-> {onCreateNewFileClick();});
    }
   public void onCreateNewFileClick(){
        FileData fileData =new FileData();

       fileData.materialThickness=view2.getMaterialHeight();
       fileData.fileName= view2.getFileName();
       fileData.material= view2.getMaterialPreset();
       fileData.fullSpace=view2.startAtHome();
      fileData.materialDim=view2.getWorkspaceDim();



        if(view2.errorVar){return;}

        view2.dispose();
        callBack.onFileCreate(fileData);
    }
}
