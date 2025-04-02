package helpers;

import java.awt.*;

public class PointScaler {

    public static Point getDefaultPoint(Point p,double scale){

            int xp= (int) (p.x/scale);
            int yp= (int) (p.y/scale);
            return new Point(xp,yp);

    }

}
