package ViewParts;

import model.FileData;
import model.TextToDisplay;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.font.FontRenderContext;

public class TextBoard extends ScalablePanel {
    int index;
    int fontSize;

    Point defLoc;
    double scale=1;


    public TextBoard(int i) {
        index = i;

        this.fontSize = TextToDisplay.fonts.get(index).getSize();

        FontMetrics fm = getFontMetrics(TextToDisplay.fonts.get(index));
        int width = fm.stringWidth(TextToDisplay.text.get(i)) + 10;
        int height = fm.getHeight() + 10;
        setDefaultSize(new Dimension(width, height));
        this.setSize(width, height);



    }


    public void setDefaultLocation(Point p) {

        this.defLoc = p;

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font font = TextToDisplay.fonts.get(this.index);
        Font font2 = new Font(font.getFontName(), font.getStyle(), (int) (font.getSize() * scale));


        g2d.setFont(font2);
        g2d.drawString(TextToDisplay.text.get(this.index), 0, font2.getSize());


    }

    @Override
    public void setScale(double scale) {
        this.scale = scale;

        if (defLoc != null) {
            System.out.println("scaling text-setting position");
            this.setLocation((int) (defLoc.x * scale), (int) (defLoc.y * scale));
        }
      System.out.println("scaling text");
        super.setScale(scale);
        repaint();


    }


}
