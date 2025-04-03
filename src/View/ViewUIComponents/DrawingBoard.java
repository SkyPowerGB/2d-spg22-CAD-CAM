package View.ViewUIComponents;

import model.*;

import java.awt.*;

public class DrawingBoard extends ScalablePanel {


    LayerDrawingsModel model;

    public DrawingBoard(LayerDrawingsModel model) {
        this.model = model;
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        BasicStroke stroke = new BasicStroke((float) (1*(super.scale)));
        g2d.setStroke(stroke);

        for(LineModel model1:model.getLineModels()){
            g2d.draw(model1.getLineScaled(super.scale));
            model1.UIPointBtnA.setScale(super.scale);
            model1.UIPointBtnB.setScale(super.scale);
        }


        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



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


    public void addPointBtns(){
        this.removeAll();
        for (LineModel model:model.getLineModels()) {
            this.add(model.UIPointBtnA);
            this.add(model.UIPointBtnB);
        }

    }
    public void removeAllUComponents(){
        this.removeAll();
    }
}
