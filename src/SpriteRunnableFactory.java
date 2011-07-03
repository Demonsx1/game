import java.awt.Dimension;
import java.awt.Point;


public interface SpriteRunnableFactory {
    public SpriteRunnable getSprite();
    public SpriteRunnable getSprite(Point p);
    public Dimension getImageSize();
}
