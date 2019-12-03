package towerdefense.component;

import java.util.List;

public class GameStage {

    private boolean waveOver = false;
    private long money = GameConfig.START_MONEY;

    public void setWaveOver(boolean waveOver) {
        this.waveOver = waveOver;
    }

    public boolean isWaveOver()
    {
        return waveOver;
    }





    public GameStage() {

    }





}
