package model;


import java.awt.*;
public class TextModel {
    Point location;
    Font font;
    String style;
    String text;

    int fontSize;
    public TextModel(Point location,Font font,String text,int fontSize){
        this.location=location;
        this.font=font;
        this.text=text;
        this.fontSize =fontSize;
    }
    public TextModel(Point location,Font font,String text,String style,int fontSize){
        this.location=location;
        this.font=font;
        this.text=text;
        this.fontSize =fontSize;
        this.style=style;
    }





}
