package towerdefense.component;

import java.util.List;

public class GameStage {
    private final long width;
    private final long height;

    private boolean waveOver = false;

    private final List<GameEntity> gameEntities;


    public GameStage(long width, long height, List<GameEntity> gameEntities) {
        this.width = width;
        this.height = height;
        this.gameEntities = gameEntities;
    }


    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

    public long getHeight() {
        return height;
    }

    public long getWidth() {
        return width;
    }
}
