package View;

import model.TextModel;

import javax.swing.*;
import java.awt.*;

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

    public JTextField getFontSizeTextField() {
        return fontSizeTextField;
    }

    public JPanel getAddTextPanel() {
        return addTextPanel;
    }

    public JComboBox getFontTf() {
        return fontTf;
    }

    public JTextField getTextTf() {
        return textTf;
    }

    public JButton getCLOSEButton() {
        return CLOSEButton;
    }

    public JButton getADDButton() {
        return ADDButton;
    }


    public void closeWindow(){
        this.dispose();

    }

    public TextModel storeAndGetData(){
        String text=textTf.getText();
        String font= (String) fontTf.getSelectedItem();
        int fontSize=12;
        try{
            fontSize= Integer.parseInt(fontSizeTextField.getText());
        }catch (Exception e){

        }

        Font font1=new Font(font, Font.PLAIN,fontSize);
       return TextModel.drawText(text,font1);

    }



}
