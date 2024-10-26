import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The class to create a circle shape
 */
public class Circle extends GeometricObject {
    /**
     * Radius of the circle
     */
    private double radius;

    /**
     * Constructor to create a new circle object
     *
     * @param x         The x coordinate of the circle
     * @param y         The y coordinate of the circle
     * @param radius    The radius of the circle
     * @param fillColor The color of the circle
     */
    public Circle(double x, double y, double radius, Color fillColor) {
        super(x, y, fillColor);
        this.radius = radius;
    }

    /**
     * Get the radius of the circle
     *
     * @return The radius of the circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set the radius of the circle
     *
     * @param radius The radius of the circle
     */
    public void setRadius(double radius) {
        if (radius <= 0) {
            System.out.println("ERROR! Radius must be greater than 0. Not changed.");
        } else {
            this.radius = radius;
        }
    }

    /**
     * Get the area of the circle
     *
     * @return The area of the circle
     */
    public double getArea() {
        return Math.PI * radius * radius;
    }

    /**
     * Draws the circle
     *
     * @param gc The circle to draw
     */
    public void draw(GraphicsContext gc) {
        gc.setFill(getFillColor());
        gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
        gc.strokeOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    /**
     * ToString method
     *
     * @return String representation of the circle
     */
    @Override
    public String toString() {
        return "Circle{" + "radius=" + radius + '}';
    }

}
