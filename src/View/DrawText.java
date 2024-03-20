package View;

import javax.swing.*;

public class DrawText extends JFrame{

    public DrawText(){
this.add(addTextPanel);
        this.setSize(700,700);
        this.setVisible(true);

    }
    private JTextField fontSizeTextField;
    private JPanel addTextPanel;
    private JComboBox fontTf;
    private JTextField textTf;
    private JButton CLOSEButton;
    private JButton ADDButton;
}
