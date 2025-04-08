package View.MainViewV2.MainViewParts.MenuBar.MenuBarSetup.File;

import Enums.ControllerActionEventNamesE;
import View.MainViewV2.MainViewParts.MenuBar.MenuBarItem;
import View.MainViewV2.MainViewParts.MenuBar.MenuBarSetup.MenuOptionsE;
import controller.standard.Controller;

import javax.swing.*;

public class FilesMenu extends JMenu {
 private    Controller controller;
public FilesMenu(Controller controller){
    MenuBarItem newFile=new MenuBarItem(MenuOptionsE.new_file);

    this.setText("File");
    newFile.addActionListener(e->controller.handleAction(e, ControllerActionEventNamesE.menuItemClick));
    newFile.setText("New File");
    this.add(newFile);
}

}
