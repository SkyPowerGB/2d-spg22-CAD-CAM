package controller.callbacks;

import model.FileData;

public interface NewFileCallBack {
    void onFileCreate(FileData data);
}
