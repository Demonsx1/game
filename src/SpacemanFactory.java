import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class SpacemanFactory implements SpriteRunnableFactory {
    private final BufferedImage image;
    private final int HEIGHT;
    private final int WIDTH;
    private final Graphics2D graphics;

    SpacemanFactory(String fname, int windowX, int windowY, Graphics2D g) {
        this.image = Animate.loadImage(fname);
        this.WIDTH = windowX;    //the JFrame's x-axis size in px
        this.HEIGHT = windowY;
        this.graphics = g;

    }

    /**
     * Find the point (top left corner) of a centered rect, based on the window
     * x, y size values, and the size of the frames to be displayed
     * @param height  = pane height
     * @param width	= pane width
     * @return
     */
    public Point findCenter(int height, int width, int frameX, int frameY) {
        //System.out.println("height: " + height + " width: " + width + " framex: " + frameX + " framey: " + frameY);
        Point p = new Point();
        int x = (width/2) - (frameX/2);
        int y = (height/2) - (frameY/2);
        p.setLocation(x, y);
        return p;
    }

    @Override
    public SpriteRunnable getSprite() {
        Spaceman s = new Spaceman();
        s.init(image);
        Point ctr = findCenter(HEIGHT, WIDTH, s.getFrameX(), s.getFrameY());
        s.setDelay(83);
        s.setGrphx(graphics);
        s.setxLoc(ctr.x);
        s.setyLoc(ctr.y);
        return s;
    }

}
