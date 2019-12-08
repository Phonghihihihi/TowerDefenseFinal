package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import towerdefense.component.enemy.*;
import towerdefense.ui.Game;;import java.util.Map.Entry;

public class GameStage {

    private boolean waveOver;
    private long money;
    private long health;
    private Text moneyText;
    private Text healthText;

    public void setWaveOver(boolean waveOver) {
        this.waveOver = waveOver;
    }

    public boolean isWaveOver()
    {
        return waveOver;
    }

    public GameStage() {
        money = GameConfig.START_MONEY;
        health = GameConfig.START_HEALTH;
        waveOver = false;
        moneyText  = new Text( "$" + (int) this.money);
        healthText = new Text("HEALTH\n" + (int) this.health);
        render();
    }

    private void render(){
        moneyText.setFill(Color.LIGHTGREY);
        moneyText.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 30));
        moneyText.relocate(GameConfig.GAME_WIDTH + 20, 48);
        moneyText.setTextAlignment(TextAlignment.CENTER);


        healthText.setFill(Color.YELLOW);
        healthText.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 30));
        healthText.relocate(GameConfig.GAME_WIDTH + 90, 10);
        healthText.setTextAlignment(TextAlignment.CENTER);

        Game.root.getChildren().addAll(healthText, moneyText);
    }

    public void update(){
        reset();
        moneyText  = new Text( "$" + (int) this.money);
        healthText = new Text("HEALTH\n" + (int) this.health);
        render();
    }

    public void reset(){
        Game.root.getChildren().removeAll(healthText, moneyText);
    }

    public void takeDamage(Enemy enemy){
        if (enemy instanceof NormalEnemy){
            this.health -= 10;
        }
        else if (enemy instanceof TankerEnemy){
            this.health -= 15;
        }
        else if (enemy instanceof SmallerEnemy){
            this.health -= 5;
        }
        else if (enemy instanceof BossEnemy){
            this.health -= 25;
        }
    }

    public void getReward(Enemy enemy){
        if (enemy instanceof NormalEnemy){
            this.money += 20;
        }
        else if (enemy instanceof TankerEnemy){
            this.money += 35;
        }
        else if (enemy instanceof SmallerEnemy){
            this.money += 10;
        }
        else if (enemy instanceof BossEnemy){
            this.money += 100;
        }
    }

    public boolean isGameOver(){
        return this.health <= 0;
    }







}
