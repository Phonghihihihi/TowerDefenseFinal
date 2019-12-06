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
import javafx.stage.Stage;
import towerdefense.component.*;
import towerdefense.component.enemy.NormalEnemy;
import towerdefense.component.tower.MachineGunTower;
import towerdefense.component.tower.NormalTower;
import towerdefense.component.tower.Tower;

public class TowerDefense extends Application {


    private double canvasMouseY;
    private double canvasMouseX;
    public static Group root = new Group();

    @Override
    public void start(Stage stage) throws Exception {
        // Set up stage and main BorderPane

        Canvas canvas = new Canvas(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene theScene = new Scene(root);

        stage.setTitle(GameConfig.GAME_NAME);
        stage.setScene(theScene);

        GameStage gameStage = new GameStage();
        GameField gameField = new GameField();

        Button next_wave = new Button("Next Wave");
        next_wave.relocate(1200,400);

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

        Button upgrade = new Button ("Upgrade");
        upgrade.relocate(1200,350);
        upgrade.setVisible(false);
        upgrade.setDisable(true);
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameField.getUpgradingTower() != null)
                {
                    gameField.getUpgradingTower().upgrade();
                }
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int tileX =  (int)(mouseEvent.getX() / GameConfig.TILE_SIZE) ;
                int tileY =  (int)(mouseEvent.getY() / GameConfig.TILE_SIZE) ;
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;
                if (!gameField.getTowers().isEmpty())
                {
                    for (Tower tower : gameField.getTowers())
                    {
                        if ((tileX > tower.getTileX() && tileX < tower.getTileX() + 1) && (tileY > tower.getTileY() && tileY < tower.getTileY() +1))
                        {
                            gameField.setHasTower(true);
                            System.out.println(mouseX + tileY);
                        }
                        else
                        {
                            gameField.setHasTower(false);
                        }
                    }
                }
            }
        });
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                int tileX = (int)(mouseEvent.getX() / GameConfig.TILE_SIZE) ;
                int tileY = (int)(mouseEvent.getY() / GameConfig.TILE_SIZE) ;
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;
                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isHasTower())
                    {
                        for (Tower tower : gameField.getTowers())
                        {
                            //if (tower.distanceTo(mouseEvent.getX(),mouseEvent.getY()) < 32)
                            //if ((mouseEvent.getX() > mouseX && mouseEvent.getX() < mouseX + GameConfig.TILE_SIZE) && (mouseEvent.getY() > mouseY && mouseEvent.getY() < mouseY + GameConfig.TILE_SIZE))
                            if ((tileX > tower.getTileX() && tileX < tower.getTileX() + 1) && (tileY > tower.getTileY() && tileY < tower.getTileY() +1))
                            {
                                gameField.setUpgradingTower(tower);
                                upgrade.setDisable(false);
                                upgrade.setVisible(true);
                            }
                        }
                    }
                    else {
                        if (gameField.isPlacingNormalTower()) {
                            NormalTower t1 = new NormalTower(mouseX, mouseY, 50, 50);
                            t1.render(gc);
                            gameField.getTowers().add(t1);
                            gameField.setPlacingNormalTower(false);
                        } else if (gameField.isPlacingMachinGunTower()) {
                            MachineGunTower t2 = new MachineGunTower(mouseX,mouseY,50, 50);
                            t2.render(gc);
                            gameField.getTowers().add(t2);
                            gameField.setPlacingMachinGunTower(false);
                        }
                    }
                }

            }
        });

        root.getChildren().addAll(next_wave, buy_normal_tower, buy_machine_gun_tower, upgrade);
        next_wave.setVisible(false);
        TileMap.drawMap(gc);

        new AnimationTimer()
        {
            public void handle(long currentTimeNs)
            {
                gameField.getReinforcements().update();
                gameField.getReinforcements().render(gc);

                if (!gameField.getEnemies().isEmpty())
                {
                    for (Tower tower : gameField.getTowers())
                    {
                        for (int i = 0; i<gameField.getEnemies().size(); i++)
                        {
                            if (tower.checkEnemyInRange(gameField.getEnemies().get(i)))
                            {
                                if (tower.getTarget() == null)
                                {
                                    tower.setTarget(gameField.getEnemies().get(i));
                                }
                                else {
                                    tower.rotateTower();
                                }
                            }
                        }
                    }
                }
                if(!gameStage.isWaveOver())
                {
                    gameField.spawnEnemies();
                }
                for (int i=0; i<gameField.getEnemies().size(); i++)
                {
                    gameField.getEnemies().get(i).update();
                    gameField.getEnemies().get(i).render(gc);
                    if (gameField.getEnemies().get(i).getPosX() >= (GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0) -30)
                    {
                        root.getChildren().remove(gameField.getEnemies().get(i).getImageV());
                        gameField.getEnemies().remove(i);
                    }
                }

                if (!gameField.isSpawning() && gameField.getEnemies().isEmpty())
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
        }.start();
        stage.show();

    }

    public static void main(String[] args) {
        System.out.println(GameConfig.CANVAS_WIDTH + "x" + GameConfig.CANVAS_HEIGHT);
        launch(args);
    }
}
