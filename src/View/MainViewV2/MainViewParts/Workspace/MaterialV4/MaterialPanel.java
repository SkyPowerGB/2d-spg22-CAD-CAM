package View.MainViewV2.MainViewParts.Workspace.MaterialV4;

import model.FileDataModel;
import model.V2models.ViewStateModel;

import javax.swing.*;

public class MaterialPanel extends JPanel {
    ViewStateModel viewStateModel;
    FileDataModel fileDataModel;

    public void setFileDataModel(FileDataModel fileDataModel) {
        this.fileDataModel = fileDataModel;
    }

    public void setViewStateModel(ViewStateModel viewStateModel) {
        this.viewStateModel = viewStateModel;
    }
}
