package towerdefense.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import towerdefense.component.*;



public class TowerDefense extends Application {
    //    public static Group root = new Group();
    public Enum status = GameConfig.STATUS.START;
    public static StartGame startGame = new StartGame();
    public static Game game;
    public static EndGame endGame;

    @Override
    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane
        stage = startGame.createContent();
        stage.show();

    }

    public static void main(String[] args) {
        System.out.println(GameConfig.CANVAS_WIDTH + "x" + GameConfig.CANVAS_HEIGHT);
        launch(args);
    }
}
