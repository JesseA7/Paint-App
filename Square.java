import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The class to create a square shape
 */
public class Square extends GeometricObject {
    /**
     * Size of the square
     */
    private double size;

    /**
     * Constructor to create a new square object
     *
     * @param x         The x coordinate of the square
     * @param y         The y coordinate of the square
     * @param size      The size of the square
     * @param fillColor The color of the square
     */
    public Square(double x, double y, double size, Color fillColor) {
        super(x, y, fillColor);
        this.size = size;
    }

    /**
     * Get the size of the square
     *
     * @return The size of the square
     */
    public double getSize() {
        return size;
    }

    /**
     * Set the size of the square
     *
     * @param size The size of the square
     */
    public void setSize(double size) {
        if (size <= 0) {
            System.out.println("ERROR! Width must be greater than 0. Not changed.");
        } else {
            this.size = size;
        }
    }

    /**
     * Draws the square
     *
     * @param gc The square to draw
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillRect(getX() - size / 2.0, getY() - size / 2.0, size, size);
        gc.strokeRect(getX() - size / 2.0, getY() - size / 2.0, size, size);
    }

    /**
     * ToString method
     *
     * @return String representation of the square
     */
    @Override
    public String toString() {
        return "Square{" + "width=" + size / 2 + ", height=" + size / 2 + '}';
    }
}
