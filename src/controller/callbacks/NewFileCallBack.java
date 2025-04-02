package controller.callbacks;

import model.FileDataModel;

public interface NewFileCallBack {
    void onFileCreate(FileDataModel data);
}
