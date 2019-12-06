package towerdefense.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import towerdefense.component.*;
import towerdefense.component.enemy.NormalEnemy;
import towerdefense.component.tower.MachineGunTower;
import towerdefense.component.tower.NormalTower;

public class TowerDefense extends Application {


    private double canvasMouseY;
    private double canvasMouseX;
    public static int status = 0;
    public static Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane
        StartGame startGame = new StartGame();
        startGame.createContent().show();


        Canvas canvas = new Canvas(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene theScene = new Scene(root);

        stage.setTitle(GameConfig.GAME_NAME);
        stage.setScene(theScene);
        TileMap.drawUI(gc);
        TileMap.drawMap(gc);
        GameStage gameStage = new GameStage();
        GameField gameField = new GameField();

        Button next_wave = new Button("Next Wave");
        next_wave.relocate(GameConfig.GAME_WIDTH + GameConfig.UI_HORIZONTAL/2.0,400);

        Button buy_normal_tower = new Button ("", new ImageView(new Image(GameConfig.NORMAL_TOWER_IMAGE_URL)));
        buy_normal_tower.relocate(1200,250);
        buy_normal_tower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameField.setPlacingNormalTower(true);
            }
        });

        Button buy_machine_gun_tower = new Button ("", new ImageView(new Image(GameConfig.MACHINE_GUN_TOWER_IMAGE_URL)));
        buy_machine_gun_tower.relocate(1200,150);
        buy_machine_gun_tower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameField.setPlacingMachinGunTower(true);
            }
        });

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                int tileX = (int)(mouseEvent.getX() / GameConfig.TILE_SIZE);
                int tileY = (int)(mouseEvent.getY() / GameConfig.TILE_SIZE);
                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isPlacingNormalTower()) {
                        NormalTower t1 = new NormalTower(tileX * GameConfig.TILE_SIZE, tileY * GameConfig.TILE_SIZE, 1, 1);
                        t1.render(gc);
                        gameField.getTowers().add(t1);
                        gameField.setPlacingNormalTower(false);
                    }
                    else if (gameField.isPlacingMachinGunTower())
                    {
                        MachineGunTower t2 = new MachineGunTower(tileX * GameConfig.TILE_SIZE, tileY * GameConfig.TILE_SIZE, 1, 1);
                        t2.render(gc);
                        gameField.getTowers().add(t2);
                        gameField.setPlacingMachinGunTower(false);
                    }
                }

            }
        });


        root.getChildren().addAll(next_wave, buy_normal_tower, buy_machine_gun_tower);
        next_wave.setVisible(false);


        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long currentTimeNs)
            {
                gameField.getReinforcements().update();
                gameField.getReinforcements().render(gc);
//                System.out.println(gameField.getReinforcements().getPosX());


                if(!gameStage.isWaveOver())
                {
                    gameField.spawnEnemies();
                }
                if (gameField.getReinforcements().getPosX() >(GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0)){
                    gameField.getReinforcements().destroyReinforcements();
                }
                for (int i=0; i<gameField.getGameEntities().size(); i++)
                {
                    gameField.getGameEntities().get(i).update();
                    gameField.getGameEntities().get(i).render(gc);
                    if (gameField.getGameEntities().get(i).getPosX() >= (GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0) -30)
                    {
                        root.getChildren().remove(gameField.getGameEntities().get(i).getImageV());
                        gameField.getGameEntities().remove(i);
                    }
                }

                if (!gameField.isSpawning() && gameField.getGameEntities().isEmpty())
                {
                    gameStage.setWaveOver(true);
                    next_wave.setVisible(true);
                    next_wave.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            gameStage.setWaveOver(false);
                            gameField.setSpawning(true);
                            next_wave.setVisible(false);

                        }
                    });
                }
            }
        };

        if (status == 1) {
            timer.start();
            stage.show();
        }

    }

    public static void main(String[] args) {
        System.out.println(GameConfig.CANVAS_WIDTH + "x" + GameConfig.CANVAS_HEIGHT);
        launch(args);
    }
}
