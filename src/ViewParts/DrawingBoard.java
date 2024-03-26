package ViewParts;

import model.LayerDrawingsModel;

import java.awt.*;
import java.awt.geom.Line2D;

public class DrawingBoard extends ScalablePanel {


    LayerDrawingsModel model;

    public DrawingBoard(LayerDrawingsModel model) {
        this.model = model;
    }

    public DrawingBoard() {
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        BasicStroke stroke = new BasicStroke((float) (1*(super.scale)));
        g2d.setStroke(stroke);
        for (Line2D.Double line : model.getLines()) {
            g2d.drawLine((int) ((int) line.x1 * super.scale), (int) ((int) line.y1 * super.scale), (int) ((int) line.x2 * super.scale), (int) ((int) line.y2 * super.scale));
        }
    }

    @Override
    public void setScale(double scale) {
        this.scale = scale;

        super.setScale(scale);
        repaint();


    }

    public LayerDrawingsModel getModel() {
        return model;
    }

    public void setModel(LayerDrawingsModel model) {
        this.model = model;
    }
}
