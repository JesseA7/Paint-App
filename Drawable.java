import javafx.scene.canvas.GraphicsContext;

/**
 * The interface for creating a new shape
 */
public interface Drawable {
    /**
     * Draws the shape
     *
     * @param gc The shape to draw
     */
    public void draw(GraphicsContext gc);
}
