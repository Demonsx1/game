import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Spaceman extends SpriteRunnable {

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

    public void animate() {
        setRunning(true);
        if (graphicsCtx != null && frames != null){
            while (keepRunning()) {
                int j = 0;
                graphicsCtx.setBackground(Color.BLACK);
                graphicsCtx.clearRect(getxLoc(), getyLoc(), getFrameX(), getFrameY());
                graphicsCtx.drawImage(frames.get(0), null, getxLoc(), getyLoc());
                for (j = 0; j < frames.size() && keepRunning(); j = (j + 1) % frames.size()) {
                    graphicsCtx.clearRect(getxLoc(), getyLoc(), getFrameX(), getFrameY());
                    graphicsCtx.drawImage(frames.get(j), null, getxLoc(), getyLoc());
                    try {
                        Thread.sleep(animDelay);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }



	@Override
	public void setSpaceOccupied(Rectangle rect) {
		spaceOccupied = rect;
	}

}