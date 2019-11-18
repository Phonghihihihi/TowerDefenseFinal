package towerdefense.ui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import towerdefense.component.CommonFunc;
import towerdefense.component.GameController;
import towerdefense.component.TileMap;

public class TowerDefense extends Application {


    private double canvasMouseY;
    private double canvasMouseX;
    public static Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane

        Canvas canvas = new Canvas(CommonFunc.CANVAS_WIDTH, CommonFunc.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GameController gameController = new GameController(gc);

        canvas.setOnMouseClicked(gameController::mouseHandler);

        //Group root = new Group();
        root.getChildren().add(canvas);
        Scene theScene = new Scene(root);

        stage.setTitle(CommonFunc.GAME_NAME);
        stage.setScene(theScene);

        TileMap map = new TileMap();
        map.drawMap(gc);
        gameController.start();
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
