package towerdefense.component;

import java.util.ArrayList;
import java.util.List;

public class GameField {
    List<GameEntity> gameEntities = new ArrayList<GameEntity>(CommonFunc.MAP_TILE);

    private double width;
    private double height;
    private long tickCount;

    public GameField(GameStage gameStage){
        this.gameEntities.addAll(gameStage.getGameEntities());
        this.width = gameStage.getWidth();
        this.height = gameStage.getHeight();
        this.tickCount = 0;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public long getTickCount() {
        return tickCount;
    }
}
