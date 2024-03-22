package helpers.helperModels;

import java.awt.*;

public class Line {
    private double k = 0;
    private double l = 0;


    public Line(Point a, Point b) {
        calcKL(a, b);
    }

    public Line(int x, int y, int x2, int y2) {
        calcKL(new Point(x, y), new Point(x2, y2));
    }

    private void calcKL(Point a, Point b) {
        if ((b.x - a.x) == 0) {
            k = 0;
        } else {
            k = (double) (b.y - a.y) / (b.x - a.x);
        }

        if (k == 0) {
            l = a.y;
            return;
        }
        l = -(k * a.x - a.y);

    }

    public int calcY(int x) {
        return (int) ((k * x) + l);
    }



}
