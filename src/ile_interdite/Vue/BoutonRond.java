package ile_interdite.Vue;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JButton;
 
public class BoutonRond extends JButton {
 
private Image img;
public BoutonRond(String aNameString){
super("");
                setContentAreaFilled(false);
                try {
                    img = ImageIO.read(new File(aNameString));
           } catch (IOException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
           }
}

@Override
protected void paintComponent(Graphics g) {

    Graphics2D g2d=(Graphics2D) g;

    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		    RenderingHints.VALUE_ANTIALIAS_ON);
    int w = getWidth();
		    int h = getHeight();
    RoundRectangle2D.Float r2d =new RoundRectangle2D.Float(0, 0, w-1 , h-1, 30, 30);
    g2d.clip(r2d);

	    GradientPaint gradient = new GradientPaint(0, 0, Color.white, 0, h, Color.gray, false);
    g2d.setPaint(gradient);
    g2d.fillRect(0,0,w,h);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		    RenderingHints.VALUE_ANTIALIAS_OFF);
    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    super.paintComponent(g);

    }
}