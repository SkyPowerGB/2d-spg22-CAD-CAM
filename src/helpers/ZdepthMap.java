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
        if (maxSteps < 4) {

        }
        double stepsPerColor = maxSteps / 4;

        int colorSpace = (int) Math.floor((h / maxD) * 100);
        int colorPoints = (int) ((colorSpace / 100) * 255);
        if (colorSpace < 25) {
            return new Color(255, colorPoints, 0);
        } else if (colorSpace > 25 && colorSpace <= 50) {
            return new Color(255 - colorPoints, 255, 0);
        } else if (colorSpace > 50 && colorSpace <= 75) {
            return new Color(0, 255, colorPoints);
        } else if (colorSpace > 75 && colorSpace < 100) {
            return new Color(0, 255 - colorPoints, 255);
        } else {
            return new Color(100, 0, 107);
        }
    }

}
