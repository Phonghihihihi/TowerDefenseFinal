package towerdefense.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TowerDefense extends Application {
    private final double NS_IN_SEC = 1000000000.0;
    private final int CANVAS_WIDTH = 800;
    private final int CANVAS_HEIGHT = 600;
    private final int TILE_SIZE = 20;
    private final double SHOT_FADE_TIME =1.;

    private double canvasMouseY;
    private double canvasMouseX;

    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane
        stage.setTitle("Tower Defense");
        Group root = new Group();
        Scene theScene = new Scene(root);
        stage.setScene(theScene);

        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
