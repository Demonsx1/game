import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class SpacemanFactory implements SpriteRunnableFactory {
	private BufferedImage[] frames;
    private final int HEIGHT;
    private final int WIDTH;
    private final Graphics2D graphics;
    private final Rectangle frameBounds;

    SpacemanFactory(Component component, BufferedImage image, int windowX, int windowY, Graphics2D g) {
        this.WIDTH = windowX;    //the JFrame's x-axis size in px
        this.HEIGHT = windowY;
        this.graphics = g;
        frames = splitImage(image, 10, 8);
        //TODO probably have to tweak these values to account for menubar, buttons, etc.
		this.frameBounds = new Rectangle(component.getX(), component.getY(),
				component.getWidth(), component.getHeight());

    }

       
    private BufferedImage[] splitImage(BufferedImage img, int cols, int rows) {
        int w = (int) img.getWidth() / cols;
        int h = (int) img.getHeight() / rows;

        int num = 0;

        BufferedImage[] imgs = new BufferedImage[cols * rows];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                imgs[num] = new BufferedImage(w, h, img.getType());
                Graphics2D g = imgs[num].createGraphics();
                g.drawImage(img, 0, 0, w, h, (w * x) + 2, h * y, (w * x + w) + 2, h * y + h, null);
                g.dispose();
                num++;
            }
        }
        return imgs;
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
        s.setFrames(frames);
        s.setFrameX(frames[0].getWidth());
        s.setFrameY(frames[0].getHeight());
        Point ctr = findCenter(HEIGHT, WIDTH, s.getFrameX(), s.getFrameY());
        s.setDelay(83);
        s.setGrphx(graphics);
        s.setxLoc(ctr.x);
        s.setyLoc(ctr.y);
        s.setSpaceOccupied(new Rectangle(s.x, s.y, s.getFrameX(), s.getFrameY()));
        return s;
    }

    public SpriteRunnable getSprite(Point p) {
        Spaceman s = new Spaceman();
        s.setFrames(frames);
        s.setFrameX(frames[0].getWidth());
        s.setFrameY(frames[0].getHeight());
        s.setDelay(83);
        s.setGrphx(graphics);
        s.setxLoc(p.x);
        s.setyLoc(p.y);
        s.setSpaceOccupied(new Rectangle(s.x, s.y, s.getFrameX(), s.getFrameY()));
        return s;
    }

    @Override
	public Dimension getImageSize() {
    	if (null != frames && (frames.length > 0)) {
    		return new Dimension(frames[0].getWidth(), frames[0].getHeight());
    	} else {
    		return new Dimension(-1,-1);
    	}
    }
}
