import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.scene.control.ToggleGroup;

/**
 * Simulates a drawing application
 * March 30, 2023
 *
 * @author Jesse Atkinson
 */
public class PaintApp extends Application {

    private ArrayList<GeometricObject> shapes = new ArrayList<>();

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(800, 600); // Set canvas size in pixels
        stage.setTitle("A8 - Drawing App"); // Set window title
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create UI elements
        Label locationLabel = new Label("Location:");
        locationLabel.setLayoutX(10);
        locationLabel.setLayoutY(canvas.getHeight() - 62);
        root.getChildren().add(locationLabel);

        TextField xInput = new TextField();
        xInput.setLayoutX(65);
        xInput.setLayoutY(canvas.getHeight() - 65);
        xInput.setPrefWidth(50);
        root.getChildren().add(xInput);

        TextField yInput = new TextField();
        yInput.setLayoutX(120);
        yInput.setLayoutY(canvas.getHeight() - 65);
        yInput.setPrefWidth(50);
        root.getChildren().add(yInput);

        Label sizeLabel = new Label("Size:");
        sizeLabel.setLayoutX(175);
        sizeLabel.setLayoutY(canvas.getHeight() - 62);
        root.getChildren().add(sizeLabel);

        TextField sizeInput = new TextField();
        sizeInput.setLayoutX(205);
        sizeInput.setLayoutY(canvas.getHeight() - 65);
        sizeInput.setPrefWidth(50);
        root.getChildren().add(sizeInput);

        Label rgbLabel = new Label("Color (RGB):");
        rgbLabel.setLayoutX(260);
        rgbLabel.setLayoutY(canvas.getHeight() - 62);
        root.getChildren().add(rgbLabel);

        TextField rInput = new TextField();
        rInput.setLayoutX(330);
        rInput.setLayoutY(canvas.getHeight() - 65);
        rInput.setPrefWidth(50);
        root.getChildren().add(rInput);

        TextField gInput = new TextField();
        gInput.setLayoutX(385);
        gInput.setLayoutY(canvas.getHeight() - 65);
        gInput.setPrefWidth(50);
        root.getChildren().add(gInput);

        TextField bInput = new TextField();
        bInput.setLayoutX(440);
        bInput.setLayoutY(canvas.getHeight() - 65);
        bInput.setPrefWidth(50);
        root.getChildren().add(bInput);

        RadioButton circleButton = new RadioButton("Circle");
        circleButton.setLayoutX(500);
        circleButton.setLayoutY(canvas.getHeight() - 65);
        circleButton.setSelected(true); // Set the initial selected button
        root.getChildren().add(circleButton);

        RadioButton squareButton = new RadioButton("Square");
        squareButton.setLayoutX(555);
        squareButton.setLayoutY(canvas.getHeight() - 65);
        root.getChildren().add(squareButton);

        // Group the radio buttons together
        ToggleGroup shapeToggleGroup = new ToggleGroup();
        circleButton.setToggleGroup(shapeToggleGroup);
        squareButton.setToggleGroup(shapeToggleGroup);

        Button drawButton = new Button("Draw");
        drawButton.setLayoutX(640);
        drawButton.setLayoutY(canvas.getHeight() - 65);
        root.getChildren().add(drawButton);

        Button undrawButton = new Button("Undraw");
        undrawButton.setLayoutX(695);
        undrawButton.setLayoutY(canvas.getHeight() - 65);
        root.getChildren().add(undrawButton);

        Label error = new Label("No Errors");
        error.setLayoutY(canvas.getHeight() - 32);
        error.setLayoutX(canvas.getWidth() / 2);
        root.getChildren().add(error);

        drawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    // Get the shape coordinates from the text fields
                    double x = Double.parseDouble(xInput.getText());
                    double y = Double.parseDouble(yInput.getText());
                    // Get the size from the text field
                    double size = Double.parseDouble(sizeInput.getText());
                    // Get the RGB colors from the text fields
                    int r = Integer.parseInt(rInput.getText());
                    int g = Integer.parseInt(gInput.getText());
                    int b = Integer.parseInt(bInput.getText());
                    // Error handling
                    if (x <= 0) {
                        throw new IllegalArgumentException("X coordinate must be greater than 0.");
                    }
                    if (y <= 0) {
                        throw new IllegalArgumentException("Y coordinate must be greater than 0.");
                    }
                    if (size <= 0) {
                        throw new IllegalArgumentException("Size must be greater than 0.");
                    }
                    if (r < 0 || r > 255) {
                        throw new IllegalArgumentException("Invalid red value. Must be between 0 and 255.");
                    } else if (g < 0 || g > 255) {
                        throw new IllegalArgumentException("Invalid green value. Must be between 0 and 255.");
                    } else if (b < 0 || b > 255) {
                        throw new IllegalArgumentException("Invalid blue value. Must be between 0 and 255.");
                    } else {
                        error.setText("No Errors");
                        error.setBackground(Background.fill(Color.WHITE));
                        error.setTextFill(Color.BLACK);
                    }
                    Color fillColor = Color.rgb(r, g, b);
                    // Create the geometric object with the specified properties and add it to the list of shapes
                    if (circleButton.isSelected()) {
                        Circle circle = new Circle(x, y, size, fillColor);
                        shapes.add(circle);
                        circle.draw(gc);
                    } else {
                        Square square = new Square(x, y, size, fillColor);
                        shapes.add(square);
                        square.draw(gc);
                    }
                } catch (NumberFormatException e) {
                    error.setText("Invalid input. Please enter a number.");
                } catch (IllegalArgumentException e) {
                    error.setText(e.getMessage());
                }
            }
        });

        undrawButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // Remove the last drawn shape from the list of shapes and clear the canvas
                if (!shapes.isEmpty()) {
                    shapes.remove(shapes.size() - 1);
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    // Redraw all remaining shapes
                    for (GeometricObject shape : shapes) {
                        shape.draw(gc);
                    }
                }
            }
        });

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    // Get the location of the mouse click
                    double x = event.getX();
                    double y = event.getY();
                    // Get the size from the text field
                    double size = Double.parseDouble(sizeInput.getText());
                    // Get the RGB colors from the text fields
                    int r = Integer.parseInt(rInput.getText());
                    int g = Integer.parseInt(gInput.getText());
                    int b = Integer.parseInt(bInput.getText());
                    // Error handling
                    if (size <= 0) {
                        throw new IllegalArgumentException("Size must be greater than 0.");
                    }
                    if (r < 0 || r > 255) {
                        throw new IllegalArgumentException("Invalid red value. Must be between 0 and 255.");
                    } else if (g < 0 || g > 255) {
                        throw new IllegalArgumentException("Invalid green value. Must be between 0 and 255.");
                    } else if (b < 0 || b > 255) {
                        throw new IllegalArgumentException("Invalid blue value. Must be between 0 and 255.");
                    } else {
                        error.setText("No Errors");
                        error.setBackground(Background.fill(Color.WHITE));
                        error.setTextFill(Color.BLACK);
                    }
                    Color fillColor = Color.rgb(r, g, b);
                    // Create the geometric object with the specified properties and add it to the list of shapes
                    if (circleButton.isSelected()) {
                        Circle circle = new Circle(x, y, size, fillColor);
                        shapes.add(circle);
                        circle.draw(gc);
                    } else {
                        Square square = new Square(x, y, size, fillColor);
                        shapes.add(square);
                        square.draw(gc);
                    }
                } catch (NumberFormatException e) {
                    error.setText("Invalid input. Please enter a number.");
                    error.setBackground(Background.fill(Color.RED));
                    error.setTextFill(Color.WHITE);
                    error.setLayoutX(canvas.getWidth() / 2);
                } catch (IllegalArgumentException e) {
                    error.setText(e.getMessage());
                    error.setBackground(Background.fill(Color.RED));
                    error.setTextFill(Color.WHITE);
                    error.setLayoutX(canvas.getWidth() / 2);
                }
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                // Get the location of the mouse click
                double x = event.getX();
                double y = event.getY();
                // Get the size from the text field
                double size = Double.parseDouble(sizeInput.getText());
                // Get the RGB colors from the text fields
                int r = Integer.parseInt(rInput.getText());
                int g = Integer.parseInt(gInput.getText());
                int b = Integer.parseInt(bInput.getText());
                Color fillColor = Color.rgb(r, g, b);
                // Create the geometric object with the specified properties and add it to the list of shapes
                if (circleButton.isSelected()) {
                    Circle circle = new Circle(x, y, size, fillColor);
                    shapes.add(circle);
                    circle.draw(gc);
                } else {
                    Square square = new Square(x, y, size, fillColor);
                    shapes.add(square);
                    square.draw(gc);
                }
            }
        });
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
