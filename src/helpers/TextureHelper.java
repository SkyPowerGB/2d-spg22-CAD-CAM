package helpers;

import helpers.enums.ToolNamesE;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class TextureHelper {
    static String resourcesFolder ="././Files/Textures";
    public static ImageIcon getToolBtnTexture(ToolNamesE tool, int size) {

        String imgPath=resourcesFolder+"/"+tool+".png";
        if(improvedToolBox.getActiveTool()==tool){
            imgPath=resourcesFolder+"/"+tool+"_on.png";
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
    public static ImageIcon getToolBtnTxState(ToolNamesE tool,int size,boolean state){
        String path=resourcesFolder+"/"+tool;
        if(state){
            path+="_on.png";
        }else{
            path+=".png";
        }

        ImageIcon img=new ImageIcon(path);
        return getScaled(img,size,size);
    }





}
