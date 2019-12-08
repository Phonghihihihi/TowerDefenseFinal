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
import towerdefense.component.bullet.Bullet;
import towerdefense.component.enemy.Enemy;
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
//        StartGame startGame = new StartGame();
//        startGame.createContent().show();


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
        upgrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (gameField.isUpgradingTower())
                {
                    gameField.setUpgradingTower(true);
                }
                gameField.setSellingTower(false);
            }
        });

        Button sell = new Button ("Sell");
        sell.relocate(1200,450);
        sell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameField.setSellingTower(true);
            }
        });


        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                int tileX = (int)(mouseEvent.getX() / GameConfig.TILE_SIZE) ;
                int tileY = (int)(mouseEvent.getY() / GameConfig.TILE_SIZE) ;
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;

                //mua thaps
                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isPlacingNormalTower()) {
                        NormalTower t1 = new NormalTower(mouseX, mouseY, 50, 50);

                        t1.render(gc);
                        gameField.getTowers().add(t1);
                        gameField.getBullets().add( new Bullet(GameConfig.NORMAL_BULLET_IMAGE_URL, t1.getPosX() , t1.getPosY(), 0,0, t1.getSpeed(), t1.getDamage()));
                        gameField.setPlacingNormalTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                    }
                    else if (gameField.isPlacingMachinGunTower()) {
                        MachineGunTower t2 = new MachineGunTower(mouseX,mouseY,50, 50);
                        t2.render(gc);
                        gameField.getTowers().add(t2);
                        gameField.getBullets().add( new Bullet(GameConfig.MACHINGUN_BULLET_IMAGE_URL, t2.getPosX() , t2.getPosY(),0,0, t2.getSpeed(), t2.getDamage()));
                        gameField.setPlacingMachinGunTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                    }
                }

                // ban thap
                else if (TileMap.MAP_PATH[tileY][tileX] == 1)
                {
                    for (int i = 0; i < gameField.getTowers().size(); i++) {
                        if (gameField.getTowers().get(i).getPosX() == mouseX && gameField.getTowers().get(i).getPosY() == mouseY) {
                            if (gameField.isSellingTower()) {
                                gameField.getTowers().get(i).delete();
                                gameField.getTowers().remove(i);
                                gameField.getBullets().get(i).delete();
                                gameField.getBullets().remove(i);
                                TileMap.MAP_PATH[tileY][tileX] = 0;
                                gameField.setSellingTower(false);
                            }
                             else if (gameField.isUpgradingTower())
                            {
                                gameField.getTowers().get(i).upgrade();
                            }
                        }
                    }
                }
            }
        });

        root.getChildren().addAll(next_wave, buy_normal_tower, buy_machine_gun_tower, upgrade, sell);
        next_wave.setVisible(false);


        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long currentTimeNs)
            {
                gameField.getReinforcements().update();
                gameField.getReinforcements().render(gc);
//
                //check va cham
                if(! gameField.getTowers().isEmpty() && !gameField.getBullets().isEmpty()) {
                    int j = -1;
                    for (Tower tower : gameField.getTowers()) {
                        j++;

                        for (Enemy enemy : gameField.getEnemies()) {
                            if (tower.checkEnemyInRange(enemy)) {
                                if (tower.getTarget() == null ) {
                                    tower.setTarget(enemy);
                                    gameField.getBullets().get(j).setBullet(tower.getPosX(), tower.getPosY() , enemy.getPosX() - tower.getPosX(),enemy.getPosY() - tower.getPosY());
                                    gameField.getBullets().get(j).update();
                                    break;
                                } else {
                                    tower.update();
                                    gameField.getBullets().get(j).update();
                                    gameField.getBullets().get(j).render(gc);
                                }
                            }
                            else gameField.getBullets().get(j).setIs_move(false);
                        }
                    }
                }

                for(int i= 0; i< gameField.getEnemies().size(); i++){
                    for(int j = 0; j< gameField.getBullets().size(); j++){
                        if(!gameField.getEnemies().isEmpty() && gameField.getBullets().get(j).distanceTo(gameField.getEnemies().get(i).getPosX(), gameField.getEnemies().get(i).getPosY())< 20){

                            gameField.getEnemies().get(i).setHealth();
                            gameField.getTowers().get(j).setTarget(null);

                            gameField.getBullets().get(j).setIs_move(false);

                            if (gameField.getEnemies().get(i).getHealth() <= 1 && !gameField.getEnemies().isEmpty()) {
                                gameField.getEnemies().get(i).delete();
                                root.getChildren().remove(gameField.getEnemies().get(i).getHealth_P_Rect());
                                root.getChildren().remove(gameField.getEnemies().get(i).getHealth_T_Rect());
                                gameField.getEnemies().remove(i);
                                //money += 1000;
                            }
                            break;

                        }
                    }

            }




                // neu het duong sinh dich tiep
                if(!gameStage.isWaveOver())
                {
                    gameField.spawnEnemies();
                }

                // neu con dich, update dich
                for (int i=0; i<gameField.getEnemies().size(); i++)
                {
                    gameField.getEnemies().get(i).update();
                    gameField.getEnemies().get(i).render(gc);

                    if (!gameField.getEnemies().isEmpty()&&gameField.getEnemies().get(i).getPosX() >= (GameConfig.GAME_WIDTH - GameConfig.TILE_SIZE/2.0) -30)
                    {
                        gameField.getEnemies().get(i).delete();
                        gameField.getEnemies().remove(i);
                       // root.getChildren().remove(gameField.getEnemies().get(i).getImageV());
                       // root.getChildren().remove(gameField.getEnemies().get(i).getHealth_P_Rect());
                       // root.getChildren().remove(gameField.getEnemies().get(i).getHealth_T_Rect());

                    }
                }

                //neu ko sinh quan dich va het dich thi duoc next van
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
        };

//        if (startGame.createContent(). == 1) {
            timer.start();
            stage.show();
//        }

    }

    public static void main(String[] args) {
        System.out.println(GameConfig.CANVAS_WIDTH + "x" + GameConfig.CANVAS_HEIGHT);
        launch(args);
    }
}
