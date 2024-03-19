package ViewParts;

import javax.swing.*;

public class ShownWindow extends JFrame {
  public  ShownWindow(){

        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void refresh(){
        this.repaint();
        this.setVisible(true);
    }

}
