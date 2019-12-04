package towerdefense.component;

import java.util.List;

public class GameStage {

    private int waveCount = 0;
    private long money = GameConfig.START_MONEY;

    public int getWaveCount()
    {
        return this.waveCount;
    }
    public void setWaveCount(int waveCount)
    {
        this.waveCount = waveCount;
    }


    public GameStage() {

    }





}
