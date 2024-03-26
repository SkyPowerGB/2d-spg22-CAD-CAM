package helpers;

import java.awt.*;

public class ZdepthMap {
    double maxD;
    double maxSteps;
    double resolution;

    public ZdepthMap(double dim, double resolution) {
        maxD = dim;

        maxSteps = dim / resolution;
        maxSteps -= 2;
        this.resolution = resolution;

    }

    public Color getHeightColor(double h) {
        if (h == maxD) {
            return new Color(100, 0, 107);
        }
        if (h == 0) {
            return new Color(255, 255, 255);
        }

        double colorSpace = (h / maxD) * 100;


        int colorPoints = (int) ((colorSpace / 100) * 255);


        int red = 0, green = 0, blue = 0;
        if (colorSpace < 25) {
            red = 255;
            green = colorPoints;
        } else if (colorSpace >= 25 && colorSpace < 50) {
            red = 255 - colorPoints;
            green = 255;
        } else if (colorSpace >= 50 && colorSpace < 75) {
            green = 255;
            blue = colorPoints;
        } else if (colorSpace >= 75 && colorSpace < 100) {
            green = 255 - colorPoints;
            blue = 255;
        } else {
            red = 100;
            blue = 107;
        }

        return new Color(red, green, blue);
    }

}
