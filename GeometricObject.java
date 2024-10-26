import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The class that defines the parameters of the shape to draw
 */
public class GeometricObject implements Drawable {
    /**
     * The x and y coordinates of the shape
     */
    private double x, y;
    /**
     * The color of the shape
     */
    private Color fillColor;

    /**
     * Constructor to create a new shape
     *
     * @param x         The x coordinate of the shape
     * @param y         The y coordinate of the shape
     * @param fillColor The color of the shape
     */
    public GeometricObject(double x, double y, Color fillColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
    }

    /**
     * Get the x coordinate of the shape
     *
     * @return The x coordinate of the shape
     */
    public double getX() {
        return x;
    }

    /**
     * Set the x coordinate of the shape
     *
     * @param x The x coordinate of the shape
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Get the y coordinate of the shape
     *
     * @return The y coordinate of the shape
     */
    public double getY() {
        return y;
    }

    /**
     * Set the x coordinate of the shape
     *
     * @param y The y coordinate of the shape
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Get the color of the shape
     *
     * @return The color of the shape
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Set the color of the shape
     *
     * @param fillColor The color of the shape
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * ToString method for the shape
     *
     * @return The string representation of the shape
     */
    @Override
    public String toString() {
        return "GeometricObject{" + "x=" + x + ", y=" + y + ", strokeColor=" + ", fillColor=" + fillColor + '}';
    }

    /**
     * Draw the shape
     *
     * @param gc The shape to draw
     */
    @Override
    public void draw(GraphicsContext gc) {
    }
}
