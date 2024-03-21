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

    public static Point getOtherPoint(Point location,Dimension size){
        return new Point(location.x+ size.width,location.y+size.width);
    }

    public static Point offsetPoint(Point fromPoint , Point toPoint){
        return new Point((fromPoint.x-toPoint.x),(fromPoint.y-toPoint.y));
    }

}
