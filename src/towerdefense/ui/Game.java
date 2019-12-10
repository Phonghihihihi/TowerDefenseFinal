package towerdefense.ui;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import towerdefense.component.*;
import towerdefense.component.bullet.Bullet;
import towerdefense.component.enemy.Enemy;
import towerdefense.component.tower.MachineGunTower;
import towerdefense.component.tower.NormalTower;
import towerdefense.component.tower.SniperTower;
import towerdefense.component.tower.Tower;

import java.io.IOException;

public class Game {
    public static Group root = new Group();
    private Stage stage = new Stage();
    private AnimationTimer timer;
    public Stage getStage() {
        return stage;
    }
    private GameStage gameStage;
    private GameField gameField;
    boolean isResetGame = false;

    public void startGame(){
        timer.start();
        stage.show();
    }

    public Game(){
        Canvas canvas = new Canvas(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene theScene = new Scene(root);

        stage.setTitle(GameConfig.GAME_NAME);
        stage.setScene(theScene);
        TileMap.drawUI(gc);
        TileMap.drawMap(gc);
        gameStage = new GameStage();
        gameField = new GameField();

        Button next_wave = new Button("Next Wave");
        next_wave.relocate(1200,400);

        Button call_reinforcements = new Button("Send Help!");
        call_reinforcements.relocate(1200, 600);

        ImageView normal_preplace = new ImageView(new Image("file:src/Assets/Tower/Normal_preplace.png"));
        ImageView machine_gun_preplace = new ImageView(new Image("file:src/Assets/Tower/250_preplace.png"));
        ImageView sniper_preplace = new ImageView(new Image("file:src/Assets/Tower/Sniper_preplace.png"));
        Circle circle_preplace_1 = new Circle(GameConfig.NORMAL_TOWER_RANGE,Color.TRANSPARENT);
        circle_preplace_1.setStroke(Color.BLACK);

        Circle circle_preplace_2 = new Circle(GameConfig.MACHINE_GUN_TOWER_RANGE,Color.TRANSPARENT);
        circle_preplace_2.setStroke(Color.BLACK);

        Circle circle_preplace_3 = new Circle(GameConfig.SNIPER_TOWER_RANGE, Color.TRANSPARENT);
        circle_preplace_3.setStroke(Color.BLACK);

        Text normal_price = new Text( "$" + GameConfig.NORMAL_TOWER_PRICE);
        Text machine_gun_price = new Text("$" + GameConfig.MACHINE_GUN_TOWER_PRICE);
        Text sniper_price = new Text("$" + GameConfig.SNIPER_TOWER_PRICE);

        normal_price.setFill(Color.LEMONCHIFFON);
        machine_gun_price.setFill(Color.LEMONCHIFFON);
        sniper_price.setFill(Color.LEMONCHIFFON);

        normal_price.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 32));
        machine_gun_price.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 32));
        sniper_price.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 32));

        normal_price.relocate(1260, 150);
        machine_gun_price.relocate(1260, 200);
        sniper_price.relocate(1260, 250);

        root.getChildren().addAll(normal_price, machine_gun_price, sniper_price);


        Button buy_normal_tower = new Button ("", new ImageView(new Image("file:src/Assets/Tower/Normal Button.png")));
        buy_normal_tower.relocate(1200,150);
        buy_normal_tower.setBackground(null);
        buy_normal_tower.setOnAction(actionEvent -> {
            gameField.setPlacingNormalTower(true);
            gameField.setPlacingMachineGunTower(false);
            gameField.setPlacingSniperTower(false);
            if (!root.getChildren().contains(normal_preplace))  {
                root.getChildren().addAll(normal_preplace, circle_preplace_1);
            }
            if (root.getChildren().contains(machine_gun_preplace)) {
                root.getChildren().removeAll(machine_gun_preplace, circle_preplace_2);
            }
            else if (root.getChildren().contains(sniper_preplace))
            {
                root.getChildren().removeAll(sniper_preplace, circle_preplace_3);
            }
        });

        Button buy_machine_gun_tower = new Button ("", new ImageView(new Image("file:src/Assets/Tower/Machine Gun Button.png")));
        buy_machine_gun_tower.relocate(1200,200);
        buy_machine_gun_tower.setBackground(null);
        buy_machine_gun_tower.setOnAction(actionEvent -> {
            gameField.setPlacingMachineGunTower(true);
            gameField.setPlacingNormalTower(false);
            gameField.setPlacingSniperTower(false);
            if(!root.getChildren().contains(machine_gun_preplace)) {
                root.getChildren().addAll(machine_gun_preplace, circle_preplace_2);
            }
            if (root.getChildren().contains(normal_preplace)) {
                root.getChildren().removeAll(normal_preplace, circle_preplace_1);
            }
            else if (root.getChildren().contains(sniper_preplace))
            {
                root.getChildren().removeAll(sniper_preplace, circle_preplace_3);
            }
        });

        Button buy_sniper_tower = new Button ("", new ImageView(new Image("file:src/Assets/Tower/Sniper Button.png")));
        buy_sniper_tower.relocate(1200,250);
        buy_sniper_tower.setBackground(null);
        buy_sniper_tower.setOnAction(actionEvent -> {
            gameField.setPlacingSniperTower(true);
            gameField.setPlacingMachineGunTower(false);
            gameField.setPlacingNormalTower(false);
            if (!root.getChildren().contains(sniper_preplace))
            {
                root.getChildren().addAll(sniper_preplace, circle_preplace_3);
            }
            if (root.getChildren().contains(machine_gun_preplace)) {
                root.getChildren().removeAll(machine_gun_preplace, circle_preplace_2);
            }
            else if (root.getChildren().contains(normal_preplace))
            {
                root.getChildren().removeAll(normal_preplace, circle_preplace_1);
            }
        });

        Button upgrade = new Button ("Upgrade");
        upgrade.relocate(1200,350);
        upgrade.setDisable(true);
        upgrade.setVisible(false);
        upgrade.setOnAction(actionEvent -> gameField.getUpgradingTower().upgrade());

        Button sell = new Button ("Sell");
        sell.relocate(1200,450);
        sell.setVisible(false);
        sell.setDisable(true);
        sell.setOnAction(actionEvent -> {
            gameStage.setMoney(gameStage.getMoney() + gameField.getSellingTower().getPrice() / 2);
            gameStage.update();
            TileMap.MAP_PATH[gameField.getSellingTower().getTileY()][gameField.getSellingTower().getTileX()] = 0;
            gameField.getSellingTower().delete();
            gameField.getTowers().remove(gameField.getSellingTower());
            gameField.setSellingTower(null);
        });


        theScene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getX() <= GameConfig.GAME_WIDTH) {

                int tileX = (int) (mouseEvent.getX() / GameConfig.TILE_SIZE);
                int tileY = (int) (mouseEvent.getY() / GameConfig.TILE_SIZE);
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;

                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isPlacingNormalTower() && gameStage.getMoney() >= GameConfig.NORMAL_TOWER_PRICE) {
                        NormalTower t1 = new NormalTower(mouseX, mouseY, 50, 50);
                        t1.render(gc);
                        gameField.getTowers().add(t1);


                        gameField.setPlacingNormalTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                        root.getChildren().removeAll(normal_preplace, circle_preplace_1);
                        gameStage.setMoney(gameStage.getMoney() - GameConfig.NORMAL_TOWER_PRICE);
                        gameStage.update();
                    } else if (gameField.isPlacingMachineGunTower() && gameStage.getMoney()>= GameConfig.MACHINE_GUN_TOWER_PRICE) {
                        MachineGunTower t2 = new MachineGunTower(mouseX, mouseY, 50, 50);
                        t2.render(gc);
                        gameField.getTowers().add(t2);

                        gameField.setPlacingMachineGunTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                        root.getChildren().removeAll(machine_gun_preplace, circle_preplace_2);
                        gameStage.setMoney(gameStage.getMoney() - GameConfig.MACHINE_GUN_TOWER_PRICE);
                        gameStage.update();
                    }
                    else if (gameField.isPlacingSniperTower() && gameStage.getMoney()>= GameConfig.SNIPER_TOWER_PRICE) {
                        SniperTower t3 = new SniperTower(mouseX, mouseY, 50, 50);
                        t3.render(gc);
                        gameField.getTowers().add(t3);
                        gameField.setPlacingSniperTower(false);
                        TileMap.MAP_PATH[tileY][tileX] = 1;
                        root.getChildren().removeAll(sniper_preplace, circle_preplace_3);
                        gameStage.setMoney(gameStage.getMoney() - GameConfig.SNIPER_TOWER_PRICE);
                        gameStage.update();
                    }
                    gameField.setSellingTower(null);
                    upgrade.setDisable(true);
                    upgrade.setVisible(false);
                    sell.setDisable(true);
                    sell.setVisible(false);
                    root.getChildren().removeAll(normal_preplace, machine_gun_preplace);
                } else if (TileMap.MAP_PATH[tileY][tileX] == 1) {
                    for (Tower tower : gameField.getTowers())
                    if (tower.getPosX() == mouseX && tower.getPosY() == mouseY)
                    {
                        gameField.setSellingTower(tower);
                        gameField.setUpgradingTower(tower);
                        upgrade.setDisable(false);
                        upgrade.setVisible(true);
                        sell.setDisable(false);
                        sell.setVisible(true);
                        root.getChildren().removeAll(normal_preplace, machine_gun_preplace);
                    }
                }
            }
        });

        theScene.setOnMouseMoved(mouseEvent -> {
            if (mouseEvent.getX() < GameConfig.GAME_WIDTH) {
                int tileX = (int) (mouseEvent.getX() / GameConfig.TILE_SIZE);
                int tileY = (int) (mouseEvent.getY() / GameConfig.TILE_SIZE);
                int mouseX = tileX * GameConfig.TILE_SIZE;
                int mouseY = tileY * GameConfig.TILE_SIZE;

                if (TileMap.MAP_PATH[tileY][tileX] == 0) {
                    if (gameField.isPlacingNormalTower()) {
                        normal_preplace.relocate(mouseX, mouseY);
                        circle_preplace_1.setCenterX(mouseX + 39);
                        circle_preplace_1.setCenterY(mouseY + 39);

                    } else if (gameField.isPlacingMachineGunTower()) {
                        machine_gun_preplace.relocate(mouseX, mouseY);
                        circle_preplace_2.setCenterX(mouseX + 39);
                        circle_preplace_2.setCenterY(mouseY + 39);

                    }
                    else if (gameField.isPlacingSniperTower())
                    {
                        sniper_preplace.relocate(mouseX, mouseY);
                        circle_preplace_3.setCenterX(mouseX + 32);
                        circle_preplace_3.setCenterY(mouseY + 32);
                    }
                }
            }
        });

        root.getChildren().addAll(next_wave, call_reinforcements, buy_normal_tower, buy_machine_gun_tower, buy_sniper_tower, upgrade, sell);

        next_wave.setVisible(false);
        call_reinforcements.setVisible(false);

        timer = new AnimationTimer()
        {
            public void handle(long currentTimeNs) {
                if (TowerDefense.startGame.isResetGame) {
                    gameStage = new GameStage();
                    gameField = new GameField();
                    TowerDefense.startGame.isResetGame = false;
                }

                theScene.setOnKeyPressed(keyEvent -> {
                    if (keyEvent.getCode() == KeyCode.ESCAPE) {
                        this.stop();
                        Text text = new Text("PAUSE");
                        text.setFill(Color.TOMATO);
                        text.setFont(Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 60));
                        text.setTextAlignment(TextAlignment.CENTER);
                        text.relocate(GameConfig.CANVAS_WIDTH / 2.0 - 70, GameConfig.CANVAS_HEIGHT / 2.0 - 70);

                        Rectangle back = new Rectangle(GameConfig.CANVAS_WIDTH, GameConfig.CANVAS_HEIGHT);
                        back.setOpacity(0.6);
                        root.getChildren().addAll(back, text);
                        theScene.setOnKeyPressed(keyEvent1 -> {
                            if (keyEvent1.getCode() == KeyCode.ESCAPE) {
                                this.start();
                                root.getChildren().removeAll(text, back);
                            }
                        });
                    }
                });

//check va cham

                for (Tower tower : gameField.getTowers())
                {
                    //tower.drawCircle();
                    if (!gameField.getEnemies().isEmpty())
                    {
                        for (int i = 0; i<gameField.getEnemies().size(); i++)
                        {
                            if (tower.checkEnemyInRange(gameField.getEnemies().get(i)))
                            {
                                if (tower.getTarget() == null)
                                {
                                    tower.setTarget(gameField.getEnemies().get(i));
                                }
                            }

                            if (gameField.getEnemies().get(i).getHealth() <= 0)
                            {
                                gameField.getEnemies().remove(i);
                            }
                        }
                        if (tower.getTarget()!= null) {
                            tower.update();
                        }
                        else {
                           tower.getBullet().Visible(false);
                        }

                    }
                }


//                if(gameField.getBullets().isEmpty()){
//                    for (Tower tower : gameField.getTowers())
//                        tower.setIs_Bullet( -1);
//                }
//                for (Tower tower : gameField.getTowers()) {
//                    for (Enemy enemy : gameField.getEnemies()) {
//                        if (tower.checkEnemyInRange(enemy) && tower.getIs_Bullet() == -1) {
//                            tower.setTarget(enemy);
//
//                            gameField.getBullets().add(new Bullet(tower.image_Bullet(), tower.getPosX(), tower.getPosY(), enemy.getPosX() - tower.getPosX() , enemy.getPosY() - tower.getPosY() , tower.getSpeed(), tower.getDamage()));
//                            tower.setIs_Bullet(gameField.getBullets().size() - 1);
//                            tower.update();
//                        }
//                    }
//                }
//
//                int k = -1;
//                for (Bullet bullet : gameField.getBullets()) {
//                    k++;
//                    int j = -1;
//                    for (Enemy enemy : gameField.getEnemies()) {
//                        j++;
//                        if (bullet.checkEnemyInRange(enemy.getPosX(), enemy.getPosY())) {
//
//                            enemy.setHealth();
//                            bullet.setDelete(true);
//
//
//                            if (enemy.getHealth() < 1) {
//                                enemy.remove_Health();
//                                enemy.delete();
//                                gameField.getEnemies().remove(j);
//
//                            }
//                        }
//
//                    }
//                    if (!gameField.getBullets().isEmpty()) break;
//                }
//
//                for (Tower tower : gameField.getTowers()) {
//                    if (gameField.getBullets().isEmpty()) break;
//
//                    if (tower.getIs_Bullet() != -1 &&
//                            tower.getIs_Bullet() < gameField.getBullets().size() &&(
//                            tower.distanceTo(gameField.getBullets().get(tower.getIs_Bullet()).getPosX(),
//                                    gameField.getBullets().get(tower.getIs_Bullet()).getPosY()) > tower.getRange() ||
//                    gameField.getBullets().get(tower.getIs_Bullet()).isDelete())) {
//
//                        gameField.getBullets().get(tower.getIs_Bullet()).setDelete(true);
//
//                        tower.setIs_Bullet(-1);
//                    }
//                }
//
//                k = -1;
//
//                if (!gameField.getBullets().isEmpty()) {
//                    for (Bullet bullet : gameField.getBullets()) {
//                        k++;
//                        if (bullet.isDelete() || bullet.getPosY() >=  GameConfig.CANVAS_HEIGHT || bullet.getPosY() <=0 || bullet.getPosX() <= 0 || bullet.getPosX() >= GameConfig.CANVAS_WIDTH) {
//
//                            bullet.delete();
//                            gameField.getBullets().remove(k);
//                            k--;
//
//
//                        } else {
//                            bullet.update();
//                            bullet.render(gc);
//                        }
//                        if (gameField.getBullets().isEmpty()) break;
//                    }
//                }

                if(!gameField.isWaveOver())
                {
                    gameField.spawnEnemies();
                }
                for (int i=0; i<gameField.getEnemies().size(); i++)
                {
                    gameField.getEnemies().get(i).update();
                    gameField.getEnemies().get(i).render(gc);

                    if (gameField.getEnemies().get(i).getPosX() >= GameConfig.GAME_WIDTH )

                    {
                        gameField.getEnemies().get(i).attack();
                        gameStage.takeDamage(gameField.getEnemies().get(i));
                        gameStage.update();
                        gameField.getEnemies().remove(i);

                    }

                    if (!gameField.getEnemies().isEmpty()){
                        if (gameField.getReinforcements().isBoomFallIntoEnemy(gameField.getEnemies().get(i))){
                            gameField.getEnemies().get(i).destroyEnemy();
                            gameField.getEnemies().remove(i);
                        }
                    }


                }

                if (gameField.isWaveOver())
                {
                    next_wave.setVisible(true);
                    next_wave.setOnMouseClicked(mouseEvent -> {
                        gameField.setWaveCount();
                        gameStage.update();
                        gameStage.updateWave(gameField.getWaveCount());
                        gameStage.getEndWaveReward(gameField.getWaveCount());
                        gameField.calculateWavePower();
                        gameField.setSpawning(true);
                        next_wave.setVisible(false);

                    });
                }

                if (gameField.getEnemies().size() >= 5 && !gameField.isCallReinforcement()){
                    call_reinforcements.setVisible(true);
                    call_reinforcements.setOnMouseClicked(mouseEvent -> {
                        gameField.setCallReinforcements(true);
                        gameField.createNewPlane();
                        call_reinforcements.setVisible(false);
                    });
                }

                if (gameField.isCallReinforcement()){
                    gameField.getReinforcements().setVisible();
                    gameField.getReinforcements().update();
                    gameField.getReinforcements().render(gc);
                }

                if (gameField.getReinforcements().isReachedEndPoint()){
                    gameField.getReinforcements().destroyReinforcements();
                    gameField.setCallReinforcements(false);
                }


                if (gameStage.isGameOver()){

                    for (Enemy enemy: gameField.getEnemies()){
                        root.getChildren().remove(enemy.getImageV());
                    }
                    for (Tower tower: gameField.getTowers()){
                        tower.delete();
                    }
                    gameField.getReinforcements().destroyReinforcements();
                    gameStage.reset();
                    gameStage.resetWave();
                    stage.close();
                    timer.stop();
//                    resetGame(gameStage, gameField);
                    EndGame endGame = new EndGame();
                    try {
                        endGame.createEndGameContent().show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }


}
