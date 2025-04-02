package helpers.helperModels;

import java.awt.*;

public class Span {
    Point A;
    Point B;
    public Span(Point A , Point B){
        this.A=A;
        this.B=B;
    }
    public boolean isPointInSpan(Point p){
        if(p.x>=A.x&&p.x<=B.x){
            if(p.y>=A.y&&p.y<=B.y){
                return true;
            }
            return false;
        }
        return false;
    }



}
