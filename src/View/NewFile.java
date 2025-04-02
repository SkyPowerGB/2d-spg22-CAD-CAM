package View;

import model.DimensionDoubleModel;

import javax.swing.*;
import java.util.Objects;

public class NewFile extends JFrame{
    public NewFile(){

        this.setSize(800,300);
        this.setContentPane(content);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
    private JTextField fileNameTf;
    private JTextField materialHeightTf;
    private JComboBox materialPresetCb;
    private JRadioButton homeRadBtn;
    private JRadioButton materialRadBtn;
    private JTextField materialXTf;
    private JTextField materialYTf;
    private JButton cancelBtn;
    private JButton createBtn;
    private JPanel content;
    private JTextField textField1;

    public boolean errorVar=false;


    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public String getFileName(){
        return fileNameTf.getText();
    }

    public String getMaterialPreset(){
        return  Objects.requireNonNull(materialPresetCb.getSelectedItem()).toString();
    }

    public  boolean startAtHome(){
        return  homeRadBtn.isSelected();
    }


    public double getMaterialHeight(){
        String matH=materialHeightTf.getText();

        try{
            return Double.parseDouble(matH);
        }catch(Exception e){
            showError("invalid height number!");
            errorVar=true;
            return 0;
        }
    }
// TU BU TRELO ZA CONFIGURACIJU SLOZITI velicina masine
    public DimensionDoubleModel getWorkspaceDim(){
        if(startAtHome()){
            return  new DimensionDoubleModel(100,100);
        }
        try{
            double x=Double.parseDouble(materialXTf.getText());
            double y=Double.parseDouble(materialYTf.getText());
            return new DimensionDoubleModel(x,y);

        }catch(Exception e){
            showError("DimensionXY inputs not valid format");
            errorVar=true;
            return  new DimensionDoubleModel(0,0);
        }
    }


    public void showError(String msg){
        JOptionPane.showMessageDialog(null,msg);
    }
}
