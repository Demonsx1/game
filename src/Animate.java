import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Animate extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private static final String spacemanFile = "img" + File.separatorChar + "spaceman.png";
    private boolean runAnimation;
    private SpriteRunnableFactory spacemanFactory;
    private LinkedList<SpriteRunnable> sprites =  new LinkedList<SpriteRunnable>();

    public static void main(String... args) {
        //loadImage(imageFile);
        new Animate();
    }

    /* Constructor */
    Animate() {
        runAnimation = false;

        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        JButton startToggle = new JButton("Start/Stop");
        startToggle.addActionListener(new StartStopListener());
        this.addMouseListener(new MouseTracker());
        setLayout(new BorderLayout());
        add(startToggle, BorderLayout.SOUTH);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        spacemanFactory = new SpacemanFactory(this, loadImage(spacemanFile),
                getContentPane().getWidth(), getContentPane().getHeight(),
                (Graphics2D) getGraphics());
        sprites.add(spacemanFactory.getSprite());
    }

    public static BufferedImage loadImage(String filename) {
        BufferedImage i = null;
        try {
            i = ImageIO.read(new File(filename));
        } catch (Exception e) {
            System.err.println("Error loading file: " + filename);
            e.printStackTrace();
        }
        return i;
    }
    
    public Point getEmptyPosition(Point p, Dimension d) {
    	Rectangle trial = new Rectangle(0,0, d.width, d.height);
    	Random rand = new Random(System.currentTimeMillis());
    	boolean empty = false;
    	while(!empty) {
    		boolean collision = false;
    		for(SpriteRunnable sprite : sprites) {
    			if(trial.intersects(sprite.spaceOccupied)) {
    				collision = true;
    			}
    		}
    		trial.setLocation(Math.abs(rand.nextInt()) % this.getContentPane().getWidth(),
    						  Math.abs(rand.nextInt()) % this.getContentPane().getHeight());
    		empty = !collision;
    	}
		return trial.getLocation();
    }

    private class StartStopListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            runAnimation = (!runAnimation);
            if (runAnimation) {
                startThreads();
            } else {
                stopThreads();
            }
        }

        private void startThreads() {
            for (SpriteRunnable sprt : sprites) {
                new Thread(sprt).start();
            }

        }

        private void stopThreads() {
            for (SpriteRunnable sprt: sprites) {
                sprt.setRunning(false);
            }
        }

    }

    private class MouseTracker implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent arg0) {
        	SpriteRunnable sprite = spacemanFactory.getSprite(getEmptyPosition(
					arg0.getPoint(), spacemanFactory.getImageSize()));
        	new Thread(sprite).start();
        	sprites.add(sprite);
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub

        }

    }

}

