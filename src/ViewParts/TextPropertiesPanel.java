package ViewParts;

import javax.swing.*;
import java.awt.*;

public class TextPropertiesPanel extends JPanel {

    public  TextPropertiesPanel(){

        this.add(TextProperties);
    }
    private JComboBox FontsTF;
    private JPanel TextProperties;
    private JTextField TextTF;
    private JButton addToMaterialBtn;
    private JButton MoveBtn;
    private JButton updateButton;
    private JLabel PositionIndicator;
}
