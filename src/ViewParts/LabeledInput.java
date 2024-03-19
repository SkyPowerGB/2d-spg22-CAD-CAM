package ViewParts;

import javax.swing.*;
import java.awt.*;

public class LabeledInput extends JPanel {
    public JLabel label;
    public JTextField input;
    public void setVertical(){ this.setLayout(new GridLayout(2,1));}
    public void setHorizontal(){this.setLayout(new GridLayout(1,2));}

  public  LabeledInput(){
        this.add(label);
        this.add(input);
  }



}
