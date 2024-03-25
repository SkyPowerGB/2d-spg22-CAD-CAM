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

    Font fontT;
    String txt;


    public TextBoard(int i) {
        index = i;
        fontT=TextToDisplay.fonts.get(index);
        txt=TextToDisplay.text.get(this.index);

        this.fontSize = fontT.getSize();

        FontMetrics fm = getFontMetrics(fontT);
        int width = fm.stringWidth(txt) + 20;
        int height = fm.getHeight() + 20;
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


        Font font2 = new Font(fontT.getFontName(), fontT.getStyle(), (int) (fontT.getSize() * scale));


        g2d.setFont(font2);
        g2d.drawString(txt, 0, font2.getSize());


    }

    @Override
    public void setScale(double scale) {
        this.scale = scale;

        if (defLoc != null) {

            this.setLocation((int) (defLoc.x * scale), (int) (defLoc.y * scale));
        }

        super.setScale(scale);
        repaint();


    }


}
