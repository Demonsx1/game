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

    private static final String spriteImageFilename =
        "img" + File.separatorChar + "spaceman.png";
    private boolean runAnimation;
    private SpriteRunnableFactory spriteFactory;
    private LinkedList<SpriteRunnable> sprites =
        new LinkedList<SpriteRunnable>();

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
        setLayout(new BorderLayout());
        add(startToggle, BorderLayout.SOUTH);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        spriteFactory = new SpacemanFactory(spriteImageFilename,
                getContentPane().getWidth(), getContentPane().getHeight(),
                (Graphics2D) getGraphics());
        sprites.add(spriteFactory.getSprite());
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

}

