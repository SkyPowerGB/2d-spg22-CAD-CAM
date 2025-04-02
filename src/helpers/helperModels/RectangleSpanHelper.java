package helpers.helperModels;

import java.awt.*;

public class RectangleSpanHelper {
    Point A;
    Point B;
    public RectangleSpanHelper(Point A , Point B){
        this.A=A;
        this.B=B;
    }
    public boolean isPointInRectangle(Point p){
        int minX = Math.min(A.x, B.x);
        int maxX = Math.max(A.x, B.x);
        int minY = Math.min(A.y, B.y);
        int maxY = Math.max(A.y, B.y);


        return (p.x >= minX && p.x <= maxX) && (p.y >= minY && p.y <= maxY);
    }



}
