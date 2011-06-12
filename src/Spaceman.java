import java.awt.Color;
import java.awt.event.KeyEvent;


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

	public void animate() {
		setRunning(true);
		if (graphicsCtx != null && img != null) {
			while (keepRunning()) {
				frames = splitImage(img, 10, 8);
				int x = frames[0].getWidth();
				int y = frames[0].getHeight();
				int j = 0;
				graphicsCtx.clearRect((width / 2) - x, (height / 2) - y, x, y);
				graphicsCtx.drawImage(frames[0], null, (width / 2) - x,
						(height / 2) - y);
				for (j = 0; j < frames.length && keepRunning(); j = (j + 1) % frames.length) {
					graphicsCtx.clearRect(width / 2 - x, height / 2 - y, x, y);
					graphicsCtx.drawImage(frames[j], null, (width / 2) - x,
							(height / 2) - y);
					graphicsCtx.setPaint(Color.BLACK);
					graphicsCtx.clearRect(width / 2 + 20, height / 2, 30, 20);
					graphicsCtx.drawString(Integer.toString(j), width / 2 + 20,
							height / 2 + 20);
					try {
						Thread.sleep(animDelay);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

}