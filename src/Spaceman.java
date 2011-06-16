import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Spaceman extends SpriteRunnable {



    @Override
    public void run() {
        animate();
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void init(BufferedImage image) {
        frames = splitImage(image, 10, 8);
        setFrameX(frames[0].getWidth());
        setFrameY(frames[0].getHeight());
    }

    public void animate() {
        setRunning(true);
        if (graphicsCtx != null && frames != null){
            while (keepRunning()) {
                int j = 0;
                graphicsCtx.setBackground(Color.BLACK);
                graphicsCtx.clearRect(getxLoc(), getyLoc(), getFrameX(), getFrameY());
                graphicsCtx.drawImage(frames[0], null, getxLoc(), getyLoc());
                for (j = 0; j < frames.length && keepRunning(); j = (j + 1) % frames.length) {
                    graphicsCtx.clearRect(getxLoc(), getyLoc(), getFrameX(), getFrameY());
                    graphicsCtx.drawImage(frames[j], null, getxLoc(), getyLoc());
                    try {
                        Thread.sleep(animDelay);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
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

}