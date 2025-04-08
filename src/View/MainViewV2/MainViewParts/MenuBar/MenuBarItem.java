package View.MainViewV2.MainViewParts.MenuBar;

import View.MainViewV2.MainViewParts.MenuBar.MenuBarSetup.MenuOptionsE;

import javax.swing.*;

public class MenuBarItem  extends JMenuItem {
  private final MenuOptionsE itemOptionName;
  public MenuBarItem(MenuOptionsE itemOptionName){
      this.itemOptionName = itemOptionName;
  }


    public MenuOptionsE getItemOptionName() {
        return itemOptionName;
    }


}
