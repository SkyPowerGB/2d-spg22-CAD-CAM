package model;


import java.awt.*;
public class TextModel {
    PointModel location;
    Font font;
    String txt;


    static PointModel locTemp;
    static TextModel txtModelTmp;


    public static void prepTxtLoc(PointModel locationTemp){
        locTemp=locationTemp;
    }

    public static TextModel drawText(String text,Font fontIN){
        if(locTemp==null){return null;}
        return new TextModel(locTemp,text,fontIN);
    }


    public TextModel(PointModel location,String txt,Font font){
        this.location=location;
        this.font=font;
           this.txt=txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Point getLocation() {
        return location.point;
    }

    public void setLocation(PointModel location) {
        this.location = location;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getScaledFont(double scale){
        Font font2=  new Font(font.getFontName(),font.getStyle(), (int) (font.getSize()*scale));
        return font2;
    }


    public PointModel getScaledLoc(double scale){
        return  new PointModel(location.getScaledLocation(scale));
    }

}
