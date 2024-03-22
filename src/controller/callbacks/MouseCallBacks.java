package controller.callbacks;

import java.awt.*;

public interface MouseCallBacks {


    void zoomIn(Point p);

    void zoomOut(Point p);

    void click(Point p);

    void pressedMidBtn(Point location);

    void dragged(Point where);

    void releasedMidBtn();

    void entered();

    void moved(Point currPos);
}
