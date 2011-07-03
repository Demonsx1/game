import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.List;

import com.google.common.collect.ImmutableList;

public abstract class SpriteRunnable implements Runnable, KeyListener {

    List<BufferedImage> frames;
    Rectangle spaceOccupied;
    Graphics2D graphicsCtx;
    int x;
    int y;
    long animDelay;
    boolean keepRunning;
    private int frameX;
    private int frameY;
    private int xLoc;
    private int yLoc;

    void setGrphx(Graphics2D g) {
        this.graphicsCtx = g;
    }

    void setDelay(long millis) {
        this.animDelay = millis;
    }

    boolean keepRunning() {
        return keepRunning;
    }

    void setRunning(boolean flag) {
        this.keepRunning = flag;
    }


    public int getFrameX() {
        return frameX;
    }

    public void setFrameX(int frameX) {
        this.frameX = frameX;
    }

    public int getFrameY() {
        return frameY;
    }

    public void setFrameY(int frameY) {
        this.frameY = frameY;
    }

    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }
    
    public void setSpaceOccupied(Rectangle rect) {
    	this.spaceOccupied = rect;
    }
    
    public void setSpaceOccupied(Point p) {
    	spaceOccupied.setLocation(p);
    }
    
    public Rectangle getSpaceOccupied() {
    	return spaceOccupied;
    }
    
    public void setFrames(BufferedImage[] frames) {
    	this.frames = ImmutableList.copyOf(frames);
    }
    
   public Dimension getImageSize() {
    	if (null != frames && (frames.size() > 0)) {
    		return new Dimension(frames.get(0).getWidth(), frames.get(0).getHeight());
    	} else {
    		return new Dimension(-1,-1);
    	}
    }
    
   
	public void run() {
		animate();
	}
	
    public abstract void animate();

}
