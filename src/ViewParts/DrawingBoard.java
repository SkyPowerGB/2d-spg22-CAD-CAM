package ViewParts;

import model.*;

import java.awt.*;
import java.awt.geom.Line2D;

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
            model1.pointBtnA.setScale(super.scale);
            model1.pointBtnB.setScale(super.scale);
        }

        for (CircleModel circle:model.getCircles()){
            Point circlePos=circle.getStartPoint();
            if(circle.isFill()){

                g2d.fillOval(circlePos.x,circlePos.y, (int) circle.getRadius(), (int) circle.getRadius());
            }else{
                g2d.drawOval(circlePos.x,circlePos.y, (int) circle.getRadius(), (int) circle.getRadius());
            }
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for(TextModel txt: model.getTexts()){
            g2d.setFont(txt.getScaledFont(super.scale));
            PointModel txtLox=txt.getScaledLoc(super.scale);
            g2d.drawString(txt.getTxt(),txtLox.x,txtLox.y);
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

    public void addPointBtns(){
        this.removeAll();
        for (LineModel model:model.getLineModels()) {
            this.add(model.pointBtnA);
            this.add(model.pointBtnB);
        }

    }
    public void removePointBtns(){
        this.removeAll();
    }
}
