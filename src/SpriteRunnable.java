import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public abstract class SpriteRunnable implements Runnable, KeyListener {

    BufferedImage[] frames;
    BufferedImage img;
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

    void setImg(BufferedImage i) {
        this.img = i;
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


}
