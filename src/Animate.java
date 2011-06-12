import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;


import javax.imageio.ImageIO;
import javax.swing.*;

public class Animate extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final int ANIM_DELAY = 83;
	private static final String imageFile = "spaceman.png";
	private boolean runAnimation;
	private LinkedList<SpriteRunnable> sprites = new LinkedList<SpriteRunnable>();

	public static void main(String... args) {
		loadImage(imageFile);
		new Animate();
	}

	/* Constructor */
	Animate() {
		runAnimation = false;
		BufferedImage image = loadImage(imageFile);

		this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			}
		);

		JButton startToggle = new JButton("Start/Stop");
		startToggle.addActionListener(new StartStopListener());
		setLayout(new BorderLayout());
		add(startToggle, BorderLayout.SOUTH);
		setSize(WIDTH, HEIGHT);
		setVisible(true);

		Spaceman s = new Spaceman();
		s.setImg(image);
		s.setDelay(83);
		s.setGrphx((Graphics2D) getGraphics());
		s.setH(HEIGHT);
		s.setW(WIDTH);

		sprites.add(s);
	}

	public static BufferedImage loadImage(String filename) {
		BufferedImage i = null;
		try {
			i = ImageIO.read(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	private class StartStopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			runAnimation = (!runAnimation);
			if(runAnimation) {
				startThreads();
			} else {
				stopThreads();
			}
		}

		private void startThreads() {
			for(SpriteRunnable sprt : sprites) {
				new Thread(sprt).start();
			}

		}

		private void stopThreads() {
			for(SpriteRunnable sprt: sprites) {
				sprt.setRunning(false);
			}
		}

	}


	public static int getAnimDelay() {
		return ANIM_DELAY;
	}

	public static int getW() {
		return WIDTH;
	}


}