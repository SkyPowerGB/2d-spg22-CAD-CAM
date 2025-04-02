package helpers;

import helpers.enums.ToolNamesE;
import helpers.enums.WorkspaceToolBtnsE;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TextureHelper {
    static String resourcesFolder ="././Files/Textures";
    public static ImageIcon getToolBtnTexture(ToolNamesE tool, int size) {

        String imgPath=resourcesFolder+"/ControlBtns/"+tool+".png";
        if(improvedToolBox.getActiveTool()==tool){
            imgPath=resourcesFolder+"/ControlBtns/"+tool+"_on.png";
        }

        ImageIcon image = new ImageIcon(imgPath);
        File file= new File(imgPath);


        Image myImage = image.getImage();
        Image resized = myImage.getScaledInstance(size, size, Image.SCALE_FAST);

        return new ImageIcon(resized);
    }
    private static ImageIcon getScaled(ImageIcon iconIn,int w ,int h){
        Image myImage = iconIn.getImage();
        Image resized = myImage.getScaledInstance(w, h, Image.SCALE_FAST);
        return new ImageIcon(resized);
    }


    // workspace tool btns get texture
    public static ImageIcon getControlBtnTexture(WorkspaceToolBtnsE btn, int size){
        String folderPath=resourcesFolder+"/workspaceNavBtns/";
        ImageIcon icon=new ImageIcon(folderPath+btn.toString()+".png");
        return getScaled(icon,size,size);
    }




}
