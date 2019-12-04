package towerdefense.component;

public final class GameConfig {
    public static final String GAME_NAME = "Tower Defense";
    public static final long TILE_SIZE = 64;
    public static final long TILE_HORIZONTAL = 18;
    public static final long TILE_VERTICAL = 12;

    public static final double PI_TO_DEGREE = 180/Math.PI;

    public static final long UI_HORIZONTAL = 200;

    public static final long GAME_WIDTH = TILE_SIZE*TILE_HORIZONTAL;
    public static final long GAME_HEIGHT = TILE_SIZE*TILE_VERTICAL;

    public static final double NS_IN_SEC = 1000000000.0;
    public static final int MAP_TILE = (int) (TILE_VERTICAL*TILE_HORIZONTAL);
    public static final long CANVAS_WIDTH = TILE_SIZE * TILE_HORIZONTAL + UI_HORIZONTAL;
    public static final long CANVAS_HEIGHT = TILE_SIZE * TILE_VERTICAL;

    public static final long START_MONEY = 50;

    public static final int SPAWN_RATE = 30;
    public static final int NORMAL_ENEMY_WAVE_NUMBER = 5;
    public static final int NORMAL_ENEMY_SPEED = 2;
    public static final int NORMAL_ENEMY_ARMOR = 1;
    public static final int NORMAL_ENEMY_HEALTH = 1;
    public static final int NORMAL_ENEMY_REWARD = 1;



    private final double SHOT_FADE_TIME =1.;

    //ENEMY
    public static final double ENEMY_SPAWN = 7;

    //UI CONTROL
    public static volatile int UI_CUR = 1;

    //Reinforcement
    public static final int PLANE_SPEED = 4;



    public static final int UI_BASE = 0;
}
