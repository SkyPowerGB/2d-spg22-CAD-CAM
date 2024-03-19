package helpers;

import helpers.enums.ToolNamesE;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class TextureHelper {
    static String resourcesFolder ="././Files/Textures";
    public static ImageIcon getToolBtnTexture(ToolNamesE tool, int size) {

        String imgPath=resourcesFolder+"/"+tool+".png";

        ImageIcon image = new ImageIcon(imgPath);
        File file= new File(imgPath);

        if(file.exists()){System.out.println(imgPath);}
        Image myImage = image.getImage();
        Image resized = myImage.getScaledInstance(size, size, Image.SCALE_FAST);

        return new ImageIcon(resized);
    }


}
