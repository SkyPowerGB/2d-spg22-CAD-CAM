package View.ViewUIComponents;

import model.*;

import java.awt.*;

public class DrawingBoard extends ScalablePanel {


    LayerDrawingsModel drawingsModel;

    public DrawingBoard(LayerDrawingsModel drawingsModel) {
        this.drawingsModel = drawingsModel;
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        BasicStroke stroke = new BasicStroke((float) (1*(super.scale)));
        g2d.setStroke(stroke);

        for(LineModel model1: drawingsModel.getLineModels()){
            g2d.draw(model1.getLineScaled(super.scale));

        }


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



    }

    @Override
    public void setScale(double scale) {
        this.scale = scale;


        super.setScale(scale);
        repaint();


    }

    public LayerDrawingsModel getDrawingsModel() {
        return drawingsModel;
    }

    public void setDrawingsModel(LayerDrawingsModel drawingsModel) {
        this.drawingsModel = drawingsModel;
    }


    public void removeAllUComponents(){
        this.removeAll();
    }
}
