import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public abstract class SpriteRunnable implements Runnable, KeyListener {

	BufferedImage[] frames;
	BufferedImage img;
	Graphics2D graphicsCtx;
	int width;
	int height;
	long animDelay;
	boolean keepRunning;

	void setGrphx(Graphics2D g) {
		this.graphicsCtx = g;
	}

	void setImg(BufferedImage i) {
		this.img = i;
	}

	void setDelay(long millis) {
		this.animDelay = millis;
	}

	void setW(int width) {
		this.width = width;
	}

	void setH(int height) {
		this.height = height;
	}

	boolean keepRunning() {
		return keepRunning;
	}

	void setRunning(boolean flag) {
		this.keepRunning = flag;
	}

	static BufferedImage[] splitImage(BufferedImage img, int cols, int rows) {
		int w = (int) img.getWidth() / cols;
		int h = (int) img.getHeight() / rows;

		int num = 0;

		BufferedImage[] imgs = new BufferedImage[cols * rows];
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				imgs[num] = new BufferedImage(w, h, img.getType());
				Graphics2D g = imgs[num].createGraphics();
				g.drawImage(img, 0, 0, w, h, w * x, h * y, w * x + w, h * y + h, null);
				g.dispose();
				num++;
			}
		}
		return imgs;
	}
}
