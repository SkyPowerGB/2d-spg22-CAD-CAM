package View.MainViewV2.MainViewParts.MenuBar;

import View.MainViewV2.MainViewParts.MenuBar.MenuBarSetup.File.FilesMenu;
import controller.standard.Controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuBarV2  extends JMenuBar {

public  MenuBarV2(){
 this.setPreferredSize(new Dimension(getMaximumSize().width, 25));
}

public void SetupFilesMenu(Controller controller){
  System.out.println("Setting up FilesMenu...");
  FilesMenu menu = new FilesMenu(controller);
  System.out.println("Created FilesMenu: " + menu);
  this.add(menu);
  System.out.println("Menu count after add: " + this.getMenuCount());

}

}
