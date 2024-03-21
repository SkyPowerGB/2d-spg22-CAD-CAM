package model;

import java.awt.*;
import java.util.ArrayList;

public class TextToDisplay {
    public static double scale;
    public static ArrayList<Point> textLoc=new ArrayList<Point>();
    public static ArrayList<String> text=new ArrayList<>();
    public static ArrayList<Font> fonts=new ArrayList<>();


    public static int addText(String txt,Font font){
        text.add(txt);
        fonts.add(font);
        return text.size()-1;
    }

public static int addTextLoc(Point p){
        textLoc.add(p);
        return textLoc.size()-1;
}

}
