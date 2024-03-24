package model;


import java.awt.*;
public class TextModel {
    Point location;
    Font font;

    double scale;


    public TextModel(Point location,Font font){
        this.location=location;
        this.font=font;

    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Font getScaledFont(){
        return new Font(font.getFontName(),font.getStyle(), (int) (font.getSize()*scale));

    }

    public Point getScaledLocation(){
        return new Point((int) (location.x*scale), (int) (location.y*scale));
    }



}
