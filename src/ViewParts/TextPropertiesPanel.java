package ViewParts;

import model.Storage;

import javax.swing.*;

public class TextPropertiesPanel extends JPanel {

    public  TextPropertiesPanel(){

        this.add(TextProperties);
    }
    public TextPropertiesPanel(TextBoard board){
        this.board=board;

        this.add(TextProperties);
    }
    private TextBoard board;

    private JComboBox FontsTF;
    private JPanel TextProperties;
    private JTextField TextTF;
    private JButton addToMaterialBtn;
    private JButton MoveBtn;
    private JButton updateButton;
    private JLabel PositionIndicator;
    private JComboBox fontStyle;
    private JTextField size;


    public JButton getMoveBtn() {
        return MoveBtn;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getAddToMaterialBtn() {
        return addToMaterialBtn;
    }

    public void updateProp(){
        this.PositionIndicator.setText("Position: "+ Storage.tempTextBoard.getLocation().x +"/"+
                Storage.tempTextBoard.getLocation().y);
    }
}
