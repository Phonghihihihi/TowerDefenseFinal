package towerdefense.component;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import towerdefense.component.enemy.*;
import towerdefense.ui.Game;
import towerdefense.ui.TowerDefense;;import java.util.Map.Entry;

public class GameStage {

    private boolean waveOver;
    private boolean endWaveReward = false;
    private long money;
    private long health;
    private Text moneyText;
    private Text healthText;
    private Text wave_text;

    public boolean isEndWaveReward() {
        return endWaveReward;
    }

    public void setEndWaveReward(boolean endWaveReward) {
        this.endWaveReward = endWaveReward;
    }

    public void setWaveOver(boolean waveOver) {
        this.waveOver = waveOver;
    }

    public boolean isWaveOver()
    {
        return waveOver;
    }

    public void getEndWaveReward(int wave){
        if (isEndWaveReward()) {
            this.money += wave * 50;
            endWaveReward = false;
        }
    }

    private void renderWave(){
        wave_text.setFill(Color.LIGHTPINK);
        wave_text.setFont( Font.loadFont("file:src/Assets/Font/Acme-Regular.ttf", 40));
        wave_text.relocate(1200, 100);
        Game.root.getChildren().add(wave_text);
    }

    public void updateWave(int wave){
        Game.root.getChildren().remove(wave_text);
        wave_text = new Text("Wave: " + wave);
        renderWave();
    }

    public GameStage() {
        money = GameConfig.START_MONEY;
        health = GameConfig.START_HEALTH;
        waveOver = false;
        moneyText  = new Text( "$" + (int) this.money);
        healthText = new Text("HEALTH\n" + (int) this.health);
        wave_text = new Text("Wave: " + 0);
        render();
        renderWave();
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

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
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

    public void resetWave(){
        Game.root.getChildren().remove(wave_text);
    }

    public void takeDamage(Enemy enemy){
        this.health -= enemy.getDamage();
    }

    public void getReward(Enemy enemy){
        this.money += enemy.getReward();
    }

    public boolean isGameOver(){
        return this.health <= 0;
    }

    public long getHealth() {
        return health;
    }
}
