package ViewParts;

import model.StuffToDraw;

import javax.swing.JPanel;
import java.awt.*;

public class TextBoard extends JPanel {
    int index;
    int fontSize;

    public TextBoard(int i){
        index=i;
        this.setLocation(StuffToDraw.textLoc.get(i));
        this.fontSize=StuffToDraw.fonts.get(index).getSize();
    }
    public void scale(double factor){
        if(factor==1){
            this.setLocation(StuffToDraw.textLoc.get(index));
            this.fontSize=StuffToDraw.fonts.get(index).getSize();
            return;
        }
        Point loc=this.getLocation();
        int locNewX= (int) (loc.x*factor);
        int locNewY= (int) (loc.y*factor);
        this.setLocation(locNewX,locNewY);
        this.fontSize= (int) (fontSize*factor);
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D g2d= (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font=StuffToDraw.fonts.get(index);
        Font font2=new Font(font.getFontName(),font.getStyle(),this.fontSize);
        g2d.drawString(StuffToDraw.text.get(this.index),0,0);
        super.paintComponents(g);
    }
}
